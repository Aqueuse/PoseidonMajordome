package PoseidonMajordome;

import javax.swing.*;
import java.awt.*;

public class generator extends JFrame {
    static GridLayout global = new GridLayout(2,1);
    static JPanel globalPanel = new JPanel();

    static CardLayout steps = new CardLayout();
    static JPanel cardPanel = new JPanel();

    static GridBagLayout buttonsLayout = new GridBagLayout();
    static GridBagConstraints gbcLast = new GridBagConstraints();
    static GridBagConstraints gbcNext = new GridBagConstraints();
    static GridBagConstraints gbcCancel = new GridBagConstraints();

    static JPanel buttonsPanel = new JPanel();
    static JButton last = new JButton("last");
    static JButton next = new JButton("next");
    static JButton cancel = new JButton("cancel");

    public static void windowSettings() {
        gbcLast.weighty = 2;
        gbcLast.anchor = GridBagConstraints.LAST_LINE_START;

        gbcNext.weighty = 2;
        gbcNext.anchor = GridBagConstraints.PAGE_END;

        gbcCancel.weighty = 2;
        gbcCancel.anchor = GridBagConstraints.LAST_LINE_END;

        globalPanel.setLayout(global);
        cardPanel.setLayout(steps);

        buttonsPanel.setLayout(buttonsLayout);
        buttonsPanel.add(last, gbcLast);
        buttonsPanel.add(next, gbcNext);
        buttonsPanel.add(cancel, gbcCancel);

        globalPanel.add(cardPanel);
        globalPanel.add(buttonsPanel);
    }

    public static void main (String[] Args) {
        generator newProject = new generator();
        newProject.add(globalPanel);
        windowSettings();
        newProject.setSize(600,600);
        newProject.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        newProject.setLocationRelativeTo(null);
        newProject.setVisible(true);
    }
}
