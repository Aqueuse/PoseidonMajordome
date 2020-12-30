package projectmanagement;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class OpenProject {
    // open the file Explorer
    // detect if it is a project (the folder or its subfolders
    // must contain at least one .java file)

    // if yes, load the folder in the tree view
    static String openingPath;

    public OpenProject() {
        // 2 tricks to have a perfect 2D rendering of the Swing window
        System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame windowOpenProject = new JFrame();
        JFileChooser chooseProject = new JFileChooser();

        chooseProject.setDialogTitle("Open a project ...");
        chooseProject.setPreferredSize(new Dimension(600,600));
        chooseProject.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        windowOpenProject.setLocationRelativeTo(null);
        windowOpenProject.setVisible(true);
        windowOpenProject.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        int returnVal = chooseProject.showOpenDialog(windowOpenProject);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            openingPath = chooseProject.getSelectedFile().getPath();
            TreeViewer.exploreProject(openingPath);
        }

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            windowOpenProject.dispose();
        }
    }
}
