package globalwindow;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;

public class TreeNavigator extends JInternalFrame implements ComponentListener {
    public TreeNavigator() {
        JPanel treePanel = new JPanel();
        this.add(treePanel);

        // desactivate the possibility of moving the frame to create an IDE typical structure
        for (MouseListener listener : ( (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI() )
                .getNorthPane().getMouseListeners()) {
            ( (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI() ).getNorthPane().removeMouseListener(listener);
        }

        this.addComponentListener(this);

        this.setTitle("File explorer");
        this.setResizable(true);
        this.setSize(200,400);
        this.setLocation(-7,0);
        this.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Window.navigator.setLocation(-7,0);
        Window.gradle.setLocation(-7,Window.navigator.getHeight());
        Window.gradle.setSize(Window.navigator.getWidth(), Window.viewContainer.getHeight()-Window.navigator.getHeight());

        Window.viewContainer.setBounds(
                Window.gradle.getWidth()-7,0,
                (Window.globalWindowPanel.getWidth()-Window.gradle.getWidth())+7,Window.globalWindowPanel.getHeight()
        );
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
