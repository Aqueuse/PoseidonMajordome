import threading

from modules.cherryPy import cherry
from modules import port_finder, pycef_launcher
from modules.projectManagement import createProject_ui
from modules.projectManagement import openProject_ui
from init import application_path


def pycef(url):
    pycef_launcher.launcher(url)


def cherry_py(port):
    cherry.launcher(application_path, port)


def pyqt_create_project():
    createProject_ui.create_dialog()


def pyqt_open_project():
    openProject_ui.open_dialog()


def monitor(name):
    if name == 'pycef':
        url = "http://127.0.0.1:" + str(port_finder.find_socket())
        thread = threading.Thread(target=pycef, args=(url,))
        thread.start()

    if name == 'cherrypy':
        thread = threading.Thread(target=cherry_py, args=(port_finder.find_socket(),), daemon=True)
        thread.start()

    if name == "pyqt_open":
        thread = threading.Thread(target=pyqt_open_project(), daemon=True)
        thread.start()
        thread.join()

    if name == "pyqt_create":
        thread = threading.Thread(target=pyqt_create_project(), daemon=True)
        thread.start()


def starter():
    monitor(name="cherrypy")
    monitor(name='pycef')
