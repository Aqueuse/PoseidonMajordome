package globalwindow;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;

public class GradlePanel extends JInternalFrame implements ComponentListener {
    public GradlePanel() {
        JPanel gradlePanel = new JPanel();
        this.add(gradlePanel);

        // desactivate the possibility of moving the frame to create an IDE typical structure
        for (MouseListener listener : ( (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI() )
                .getNorthPane().getMouseListeners()) {
            ( (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI() ).getNorthPane().removeMouseListener(listener);
        }

        this.addComponentListener(this);

        this.setTitle("Gradle tasks");
        this.setResizable(true);
        this.setSize(200,600);
        this.setLocation(0,200);
        this.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Window.navigator.setBounds(
                0,0,
                Window.gradle.getWidth(),Window.gradle.getY()
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
