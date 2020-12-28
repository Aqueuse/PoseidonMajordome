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
        this.setSize(200,400);
        this.setLocation(-7,200);
        this.setVisible(true);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Window.navigator.setSize(Window.gradle.getWidth(), Window.viewContainer.getHeight()-Window.gradle.getHeight());

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
