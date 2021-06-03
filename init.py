import os

from modules.processManagement import manager

application_path = os.path.dirname(os.path.abspath(__file__))

if __name__ == '__main__':
    manager.starter()
