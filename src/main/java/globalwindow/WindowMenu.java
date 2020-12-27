package globalwindow;

import javax.swing.*;

public class WindowMenu extends JMenuBar {
    public JMenu projectMenu = new JMenu("Project");
    public JMenu testMenu = new JMenu("Test");
    public JMenu settingsMenu = new JMenu("Settings");
    public JMenu helpMenu = new JMenu("help");

    public WindowMenu() {
        this.add(projectMenu);
        this.add(testMenu);
        this.add(settingsMenu);
        this.add(helpMenu);
    }
}