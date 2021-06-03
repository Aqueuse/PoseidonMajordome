import json
import os
import mimetypes
import shutil
import urllib.parse
import urllib.request


def make_tree(path):
    tree = dict(name=os.path.basename(path), children=[])
    try:
        lst = os.listdir(path)
    except OSError:
        pass  # ignore errors
    else:
        for name in lst:
            filename = os.path.join(path, name)
            if os.path.isdir(filename):
                tree['children'].append(make_tree(filename))
            else:
                tree['children'].append(dict(name=name, path=path))
    return tree


def check_file_return_log(path):
    cleaned_path = urllib.parse.unquote(path).replace('\\', '/')
    url = urllib.request.pathname2url(cleaned_path)

    if os.path.exists(cleaned_path):
        if os.path.getsize(cleaned_path) / 1024 <= 70000:
            if is_image(url):
                shutil.copy2(cleaned_path, 'static/temp/plot.png', follow_symlinks=True)
                return json.dumps({"log": "OK image"})
            if mimetypes.guess_type(url)[0][:5] == 'text/':
                shutil.copy2(cleaned_path, 'static/temp/plot', follow_symlinks=True)
                return json.dumps({"log": "OK text"})
            else:
                return json.dumps({"log": "filetype not supported"})
        if not os.path.getsize(cleaned_path) / 1024 <= 70000:
            return json.dumps({"log": cleaned_path + " is too big"})

    if not os.path.exists(cleaned_path):
        return json.dumps({"log": cleaned_path + " don't exist"})


# check if image is really an image and less or equal to 700mo
def is_image(url):
    images_type_supported = ['image/png', 'image/jpeg', 'image/gif', 'image/webp', 'image/svg+xml']

    for i in images_type_supported:
        if mimetypes.guess_type(url)[0] == i:
            return True
    return False
