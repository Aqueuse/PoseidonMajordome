package PoseidonMajordome;

import javax.swing.*;

public class FileChooser extends JFrame {
    public JFileChooser chooseFolder=new JFileChooser();

    public FileChooser() {
        chooseFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooseFolder.setAcceptAllFileFilterUsed(false);

        this.add(chooseFolder);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
    }
}