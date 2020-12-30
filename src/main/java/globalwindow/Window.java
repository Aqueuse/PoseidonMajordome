package globalwindow;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

// we instanciate :
// the menu
// the tree navigator
// the gradle pane
// the view global pane with his two contained panes (overviewPane and UMLPane)

public class Window {
    public static JFrame windowGlobal;

    // let's create the general Panel : a JdesktopPane to contain them all
    public static JDesktopPane globalWindowPanel = new JDesktopPane();

    public static WindowMenu menu = new WindowMenu();
    public static TreeNavigator navigator = new TreeNavigator();
    public static GradlePanel gradle = new GradlePanel();
    public static ViewContainerPane viewContainer = new ViewContainerPane();

    public static int initialWindowHeight = 800;
    public static int initialWindowWidth = 1200;

    public static void main(String[] Args) {
        // 2 tricks to have a perfect 2D rendering of the Swing window
        System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
        JFrame.setDefaultLookAndFeelDecorated(true);

        globalWindowPanel.add(navigator);
        globalWindowPanel.add(gradle);
        globalWindowPanel.add(viewContainer);

        windowGlobal = new JFrame();
        windowGlobal.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                viewContainer.setBounds(gradle.getWidth() - 7, 0, (globalWindowPanel.getWidth() - Window.gradle.getWidth()) + 7, globalWindowPanel.getHeight());
                Window.gradle.setSize(Window.navigator.getWidth(), Window.viewContainer.getHeight() - Window.navigator.getHeight());
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
        });

        windowGlobal.add(globalWindowPanel);
        windowGlobal.setJMenuBar(menu);

        windowGlobal.setTitle("Poseidon Majordome - DataScience Accelerator");
        windowGlobal.setSize(initialWindowWidth, initialWindowHeight);
        windowGlobal.setLocationRelativeTo(null);

        windowGlobal.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        windowGlobal.setVisible(true);
    }
}
