import sys
from PyQt5 import QtWidgets, QtCore

file_name = ''


def open_dialog():
    qt_application = QtWidgets.QApplication(sys.argv)

    open_window = QtWidgets.QWidget()
    open_window.setWindowFlag(QtCore.Qt.WindowStaysOnTopHint)
    open_window.setFocus()

    screen_width = qt_application.primaryScreen().size().width()
    screen_height = qt_application.primaryScreen().size().height()

    qt_application.setApplicationName("Open a project")
    open_window.setGeometry((screen_width/2)-300, (screen_height/2)-300, 600, 600)

    select_folder_button = QtWidgets.QPushButton('choose ...', open_window)
    select_folder_button.clicked.connect(open_file_explorer)

    global path_validation
    path_validation = QtWidgets.QLineEdit()

    close_button = QtWidgets.QPushButton('ok', open_window)
    close_button.clicked.connect(lambda: qt_application.exit())

    vertical_layout = QtWidgets.QVBoxLayout()
    vertical_layout.addWidget(select_folder_button)
    vertical_layout.addWidget(path_validation)
    vertical_layout.addWidget(close_button)

    open_window.setLayout(vertical_layout)

    print("initialisation ok")
    open_window.show()
    open_window.raise_()
    qt_application.exec()
    print("application exec ok")
    return path_validation.text()


def open_file_explorer():
    global file_name
    file_name = QtWidgets.QFileDialog.getExistingDirectory(None, 'Open project folder ...')
    path_validation.setText(str(file_name))
