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
    open_window.setGeometry((screen_width/2)-300, (screen_height/2)-300, 300, 300)

    select_folder_label = QtWidgets.QLabel("choose a folder to open ...")
    select_folder_button = QtWidgets.QPushButton('explore', open_window)
    select_folder_button.clicked.connect(open_file_explorer)

    path_validation_label = QtWidgets.QLabel("project folder : ")
    global path_validation_lineEdit
    path_validation_lineEdit = QtWidgets.QLineEdit()

    cancel_button = QtWidgets.QPushButton('annuler', open_window)
    cancel_button.clicked.connect(lambda: qt_application.exit())

    close_button = QtWidgets.QPushButton('ok', open_window)
    close_button.clicked.connect(lambda: qt_application.exit())

    vertical_layout = QtWidgets.QVBoxLayout()
    vertical_layout.addStretch(1)
    select_folder_layout = QtWidgets.QHBoxLayout()

    select_folder_layout.addWidget(select_folder_label)
    select_folder_layout.addWidget(select_folder_button)
    vertical_layout.addLayout(select_folder_layout)

    vertical_layout.addStretch(1)

    vertical_layout.addWidget(path_validation_label)
    vertical_layout.addWidget(path_validation_lineEdit)

    vertical_layout.addStretch(1)

    bottom_buttons_layout = QtWidgets.QHBoxLayout()
    bottom_buttons_layout.addStretch(1)
    bottom_buttons_layout.addWidget(cancel_button)
    bottom_buttons_layout.addWidget(close_button)
    vertical_layout.addLayout(bottom_buttons_layout)

    open_window.setLayout(vertical_layout)

    print("initialisation ok")
    open_window.show()
    open_window.raise_()
    qt_application.exec()
    print("application exec ok")
    return path_validation_lineEdit.text()


def open_file_explorer():
    global file_name
    file_name = QtWidgets.QFileDialog.getExistingDirectory(None, 'Open project folder ...')
    path_validation_lineEdit.setText(str(file_name))
