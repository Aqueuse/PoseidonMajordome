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
        this.setSize(200,200);
        this.setLocation(0,0);
        this.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Window.gradle.setBounds(
                0,Window.navigator.getHeight(),
                Window.navigator.getWidth(),Window.gradle.getHeight()
        );
        Window.viewContainer.setBounds(
                Window.gradle.getWidth(),0,
                Window.globalWindowPanel.getWidth()-Window.gradle.getWidth(),Window.globalWindowPanel.getHeight()
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
