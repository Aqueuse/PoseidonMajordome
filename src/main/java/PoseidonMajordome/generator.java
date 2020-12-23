package PoseidonMajordome;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class generator extends JFrame {
    static JPanel globalPanel = new JPanel();
    static BoxLayout global = new BoxLayout(globalPanel, BoxLayout.Y_AXIS);

    static CardLayout stepsLayout = new CardLayout();
    static JPanel cardPanel = new JPanel();

    static WelcomeCard WelcomePanel = new WelcomeCard();
    static BasicSettingsCard settingsPanel = new BasicSettingsCard();
    static SamplesAddingCard samplesAddingPanel = new SamplesAddingCard();
    static ConfirmCard confirmPanel = new ConfirmCard();

    static JPanel buttonsPanel = new JPanel();
    static BoxLayout buttonsLayout = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);
    static JButton previous = new JButton("previous");
    static JButton next = new JButton("next");
    static JButton cancel = new JButton("cancel");

    public static void windowSettings() {
        String[] settings = new String[4];

        globalPanel.setLayout(global);

        cardPanel.setLayout(stepsLayout);
        cardPanel.setPreferredSize(new Dimension(700, 550));

        cardPanel.add(WelcomePanel);
        cardPanel.add(settingsPanel);
        cardPanel.add(samplesAddingPanel);
        cardPanel.add(confirmPanel);

        WelcomePanel.setName("welcome");
        settingsPanel.setName("settings");
        samplesAddingPanel.setName("samples");
        confirmPanel.setName("confirm");

        previous.addActionListener(e -> {
            if (!cardPanel.getComponent(0).isVisible())
                stepsLayout.previous(cardPanel);
            next.setText("next");
        });

        next.addActionListener(e -> {
            stepsLayout.next(cardPanel);

           if (cardPanel.getComponent(2).isVisible()) {  /// Samples page
               settings[0] = settingsPanel.projectNameFd.getText();
               settings[1] = settingsPanel.projectDescriptionPn.getText();
               settings[2] = settingsPanel.projectPathFd.getText();
               settings[3] = String.valueOf(settingsPanel.webChoice.isSelected());

               next.setText("finish");
           }

           if (cardPanel.getComponent(3).isVisible()) { /// confirm page
               // on ajoute les labels des checkBox validée dans la liste samples
               List<String> samples = new ArrayList<>();

               for (int c = 0; c < samplesAddingPanel.checkboxes.size(); c++) {
                   if (samplesAddingPanel.checkboxes.get(c).getState()) {
                       samples.add(samplesAddingPanel.checkboxes.get(c).getLabel());
                   }
               }

               String confirmStr = "";
               for (int s = 0; s < settings.length; s++)
                   confirmStr = confirmStr.concat(settings[s] + "\n");
               for (int sa = 0; sa < samples.size(); sa++)
                   confirmStr = confirmStr.concat(samples.get(sa) + "\n");

               System.out.println(confirmStr);
               confirmPanel.settingsText.setText(confirmStr);
               confirmPanel.settingsText.revalidate();
           }
        });
        cancel.addActionListener(e -> {
            System.exit(0);
        });

        buttonsPanel.setLayout(buttonsLayout);
        buttonsPanel.setPreferredSize(new Dimension(700, 50));
        buttonsPanel.add(Box.createRigidArea(new Dimension(135, 5)));
        buttonsPanel.add(previous);
        buttonsPanel.add(Box.createRigidArea(new Dimension(135, 5)));
        buttonsPanel.add(next);
        buttonsPanel.add(Box.createRigidArea(new Dimension(135, 5)));
        buttonsPanel.add(cancel);

        globalPanel.add(cardPanel);
        globalPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
        globalPanel.add(buttonsPanel);
    }

    public static void main(String[] Args) throws ClassNotFoundException, UnsupportedLookAndFeelException,
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
