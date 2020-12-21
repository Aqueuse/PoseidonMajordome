package PoseidonMajordome;

import javax.swing.*;
import java.awt.*;

public class generator extends JFrame {
    static JPanel globalPanel = new JPanel();
    static BoxLayout global = new BoxLayout(globalPanel, BoxLayout.Y_AXIS);

    static CardLayout stepsLayout = new CardLayout();
    static JPanel cardPanel = new JPanel();

    static GridBagConstraints gbcLast = new GridBagConstraints();
    static GridBagConstraints gbcNext = new GridBagConstraints();
    static GridBagConstraints gbcCancel = new GridBagConstraints();

    static JPanel buttonsPanel = new JPanel();
    static BoxLayout buttonsLayout = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);
    static JButton last = new JButton("last");
    static JButton next = new JButton("next");
    static JButton cancel = new JButton("cancel");

    public static void windowSettings() {
        globalPanel.setLayout(global);
        cardPanel.setLayout(stepsLayout);
        cardPanel.setPreferredSize(new Dimension(600,550));

        buttonsPanel.setLayout(buttonsLayout);
        buttonsPanel.setPreferredSize(new Dimension(600,50));
        buttonsPanel.add(Box.createRigidArea(new Dimension(135,5)));
        buttonsPanel.add(last, gbcLast);
        buttonsPanel.add(Box.createRigidArea(new Dimension(135,5)));
        buttonsPanel.add(next, gbcNext);
        buttonsPanel.add(Box.createRigidArea(new Dimension(135,5)));
        buttonsPanel.add(cancel, gbcCancel);

        globalPanel.add(cardPanel);
        globalPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        globalPanel.add(buttonsPanel);
    }

    public static void main (String[] Args) throws ClassNotFoundException, UnsupportedLookAndFeelException,
                                                   InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        generator newProject = new generator();
        newProject.getContentPane().add(globalPanel);
        windowSettings();
        newProject.setResizable(false);
        newProject.pack();
        newProject.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        newProject.setLocationRelativeTo(null);
        newProject.setVisible(true);
    }
}
