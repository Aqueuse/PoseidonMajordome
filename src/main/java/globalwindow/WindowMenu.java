package globalwindow;

import javax.swing.*;

public class WindowMenu extends JMenuBar {
    public JMenu projectMenu = new JMenu("Project");
        public JMenuItem newProject = new JMenuItem("New project...");
        public JMenuItem openProject = new JMenuItem("Open project...");
        public JMenuItem saveProject = new JMenuItem("Save project...");
        public JMenuItem closeProject = new JMenuItem("Close project");
        public JMenuItem quit = new JMenuItem("Quit");

    public JMenu viewMenu = new JMenu("View");
        public JMenuItem viewHideFileExplorer = new JMenuItem("view File Explorer");
        public JMenuItem viewHideGradleTasks = new JMenuItem("view Gradle Tasks");

    public JMenu runMenu = new JMenu("Run");
        public JMenuItem test = new JMenuItem("Test");
        public JMenuItem build = new JMenuItem("Build");
        public JMenuItem export = new JMenuItem("Export");

    public JMenu settingsMenu = new JMenu("Settings");
        public JMenuItem applicationSettings = new JMenuItem("Application settings");
        public JMenuItem projectSettings = new JMenuItem("Project settings");

    public JMenu helpMenu = new JMenu("Help");
        public JMenuItem openManual = new JMenuItem("Open manual");

    public WindowMenu() {
            newProject.addActionListener(e -> new projectgenerator.generator());

            projectMenu.add(newProject);
            projectMenu.add(openProject);
            projectMenu.add(saveProject);
            projectMenu.add(closeProject);
            projectMenu.add(new JSeparator());
            projectMenu.add(quit);
        this.add(projectMenu);

            viewMenu.add(viewHideFileExplorer);
            viewMenu.add(viewHideGradleTasks);
        this.add(viewMenu);

            runMenu.add(test);
            runMenu.add(build);
            runMenu.add(export);
        this.add(runMenu);

            settingsMenu.add(projectSettings);
            settingsMenu.add(applicationSettings);
        this.add(settingsMenu);

            helpMenu.add(openManual);
        this.add(helpMenu);
    }
}