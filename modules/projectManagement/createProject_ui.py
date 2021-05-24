import sys
from multiprocessing import Process
from PyQt5 import QtWidgets


def create_dialog():
    application = QtWidgets.QApplication(sys.argv)
    new_window = QtWidgets.QWidget()

    application.setApplicationName("Create a project")
    new_window.setGeometry(200, 200, 600, 600)

    select_folder_button = QtWidgets.QPushButton('choose ...', new_window)
    select_folder_button.clicked.connect(open_file_explorer)
    # path_validation = QTextEdit(self)

    close_button = QtWidgets.QPushButton('ok', new_window)
    close_button.clicked.connect(lambda: application.exit())

    vertical_layout = QtWidgets.QVBoxLayout()
    vertical_layout.addWidget(select_folder_button)
    vertical_layout.addWidget(close_button)

    new_window.setLayout(vertical_layout)

    new_window.show()
    new_window.raise_()
    sys.exit(application.exec_())


def create():
    pyqt_process = Process(target=create_dialog)
    pyqt_process.start()


def open_file_explorer():
    file_name = QtWidgets.QFileDialog.getOpenFileName(None, 'OpenFile')
    print(file_name)
    return file_name
