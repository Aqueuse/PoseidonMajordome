import os
from multiprocessing import Process
from modules.cherryPy import cherry
from modules import port_finder, pycef_launcher, projectManagement
from modules.projectManagement import createProject_ui
from modules.projectManagement import openProject_ui
from init import application_path


def pycef(url):
    print('starting pycef_launch_wrapper / process id:', os.getpid())
    pycef_launcher.launcher(url)


def cherry_py(port):
    print('starting cherry_py_launch_wrapper / process id:', os.getpid())
    cherry.launcher(application_path, port)


def pyqt_create_project():
    createProject_ui.create_dialog()


def pyqt_open_project():
    openProject_ui.create_dialog()


def monitor(name):
    port = port_finder.find_socket()
    url = "http://127.0.0.1:" + str(port)

    if name == 'cherrypy':
        process = Process(name=name, target=cherry_py, args=(port,))
        process.start()

    if name == 'pycef':
        process = Process(name=name, target=pycef, args=(url,))
        process.start()
        process.join()

    if name == "pyqt_open":
        process = Process(name=name, target=pyqt_open_project())
        print(process.name)
        process.start()
#        process.join()

    if name == "pyqt_create":
        process = Process(name=name, target=pyqt_create_project())
        print(process.name)
        process.start()
#        process.join()


def starter():
    monitor(name='cherrypy')
    monitor(name='pycef')
