from multiprocessing import Process
import os
from modules import cheeryPy_launcher, port_finder, pycef_launcher


def pycef(url):
    print('starting pycef_launch_wrapper / process id:', os.getpid())
    pycef_launcher.launcher(url)


def cherry_py(port):
    print('starting cherry_py_launch_wrapper / process id:', os.getpid())
    application_path = os.path.dirname(os.path.abspath(__file__))
    cheeryPy_launcher.launcher(application_path, port)


if __name__ == '__main__':
    port = port_finder.find_socket()
    url = "http://127.0.0.1:" + str(port)
    cherrypy_process = Process(target=cherry_py, args=(port,))
    cherrypy_process.start()
    pycef_process = Process(target=pycef, args=(url,))
    pycef_process.start()
    pycef_process.join()
    cherrypy_process.terminate()
