package projectmanagement;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class SaveProject extends JFileChooser {
    // open the file explorer
    // save all the files of the project folder
    // in the choosen directory
    public SaveProject() {
        // 2 tricks to have a perfect 2D rendering of the Swing window
        System.setProperty("sun.java2d.noddraw",Boolean.TRUE.toString());
        JDialog.setDefaultLookAndFeelDecorated(true);

        JFrame windowSaveProject = new JFrame();
        JFileChooser saveProject = new JFileChooser();

        String savingPath;

        saveProject.setDialogTitle("Save your project at ...");
        saveProject.setPreferredSize(new Dimension(600,600));

        windowSaveProject.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        windowSaveProject.setLocationRelativeTo(null);
        windowSaveProject.setVisible(true);

        // Yeah yeah, show open dialog can be write with very compact code but I find
        // it very confusing to apprehend and debug so I choose to write it
        // very verbosely. Hit me if you want.
        int returnVal = saveProject.showOpenDialog(windowSaveProject);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            savingPath = saveProject.getSelectedFile().getPath();
            System.out.println(savingPath);
        }

        if (returnVal == JFileChooser.CANCEL_OPTION) {
            windowSaveProject.dispose();
        }
    }
}
