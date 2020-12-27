package globalwindow;

import projectgenerator.*;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

// we instanciate :
// the menu
// the tree navigator
// the gradle pane
// the view global pane with his two contained panes (overviewPane and UMLPane)

public class Window extends JFrame  implements ComponentListener {
    // let's create the general Panel : a JdesktopPane to contain them all
    public static JDesktopPane globalWindowPanel = new JDesktopPane();

    public static Window currentWindow;

    public static WindowMenu menu = new WindowMenu();
    public static TreeNavigator navigator = new TreeNavigator();
    public static GradlePanel gradle = new GradlePanel();
    public static ViewContainerPane viewContainer = new ViewContainerPane();

    public static int initialWindowHeight = 800;
    public static int initialWindowWidth = 1200;

    public Window() {
        globalWindowPanel.add(navigator);
        globalWindowPanel.add(gradle);
        globalWindowPanel.add(viewContainer);

        this.add(globalWindowPanel);
        this.setJMenuBar(menu);

        this.addComponentListener(this);

        this.setSize(initialWindowWidth, initialWindowHeight);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] Args) {
        // 2 tricks to have a perfect 2D rendering of the Swing window
        System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
        setDefaultLookAndFeelDecorated(true);

        currentWindow = new Window();
        currentWindow.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        viewContainer.setBounds(gradle.getWidth(), 0, globalWindowPanel.getWidth()-200, globalWindowPanel.getHeight());
        gradle.setBounds(0, navigator.getHeight(), gradle.getWidth(), globalWindowPanel.getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
