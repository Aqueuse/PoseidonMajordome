import sys
from PyQt5 import QtWidgets, QtCore


def create_dialog():
    qt_application = QtWidgets.QApplication(sys.argv)
    create_window = QtWidgets.QWidget()
    create_window.setWindowFlag(QtCore.Qt.WindowStaysOnTopHint)

    screen_width = qt_application.primaryScreen().size().width()
    screen_height = qt_application.primaryScreen().size().height()

    qt_application.setApplicationName("Create a project")
    create_window.setGeometry((screen_width/2)-300, (screen_height/2)-300, 600, 600)

    select_folder_button = QtWidgets.QPushButton('choose ...', create_window)
    select_folder_button.clicked.connect(open_file_explorer)
    # path_validation = QTextEdit(self)

    close_button = QtWidgets.QPushButton('ok', create_window)
    close_button.clicked.connect(lambda: qt_application.exit())

    vertical_layout = QtWidgets.QVBoxLayout()
    vertical_layout.addWidget(select_folder_button)
    vertical_layout.addWidget(close_button)

    create_window.setLayout(vertical_layout)

    create_window.show()
    create_window.raise_()
    qt_application.exec_()


def open_file_explorer():
    file_name = QtWidgets.QFileDialog.getOpenFileName(None, 'OpenFile')
    print(file_name)
    return file_name
