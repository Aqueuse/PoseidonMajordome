package globalwindow;

import javax.swing.*;

public class ViewContainerPane extends JInternalFrame {
    public ViewContainerPane()  {
        ( (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI() ).setNorthPane(null);
        this.setBorder(BorderFactory.createEmptyBorder());

        this.setResizable(false);
        this.setSize(800,1000);
        this.setLocation(200,0);
        this.setVisible(true);
    }
}
