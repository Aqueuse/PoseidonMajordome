import subprocess
import sys


def route(langage, script):
    if langage == "python":
        # save the python script as script.py
        script_file = open("script.py", "w+")
        script_file.write(script)
        script_file.close()
        result = subprocess.run([sys.executable, "script.py", "raise ValueError('oops')"], capture_output=True, text=True)
        if result.stderr:
            return result.stderr
        else:
            return result.stdout
