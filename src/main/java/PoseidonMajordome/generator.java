package PoseidonMajordome;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class generator extends JFrame {
    static JPanel globalPanel = new JPanel();
    static BoxLayout global = new BoxLayout(globalPanel, BoxLayout.Y_AXIS);
    static JSeparator boxSeparator = new JSeparator(SwingConstants.HORIZONTAL);

    static CardLayout stepsLayout = new CardLayout();
    static JPanel cardPanel = new JPanel();

    static WelcomeCard WelcomePanel = new WelcomeCard();
    static BasicSettingsCard settingsPanel = new BasicSettingsCard();
    static SamplesAddingCard samplesAddingPanel = new SamplesAddingCard();
    static ConfirmCard confirmPanel = new ConfirmCard();
    static LastCard lastPanel = new LastCard();

    static JPanel buttonsPanel = new JPanel();
    static BoxLayout buttonsLayout = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);
    static JButton previous = new JButton("previous");
    static JButton next = new JButton("next");
    static JButton cancel = new JButton("cancel");
    static boolean nextConfirm = true;

    public static void windowSettings() {
        String[] settings = new String[5];

        globalPanel.setLayout(global);

        cardPanel.setLayout(stepsLayout);
        cardPanel.setPreferredSize(new Dimension(700, 550));

        cardPanel.add(WelcomePanel);
        cardPanel.add(settingsPanel);
        cardPanel.add(samplesAddingPanel);
        cardPanel.add(confirmPanel);
        cardPanel.add(lastPanel);

        previous.addActionListener(e -> {
            next.setText("next");
            nextConfirm = true;
            if (!cardPanel.getComponent(0).isVisible())
                stepsLayout.previous(cardPanel);
        });
        next.addActionListener(e -> {
            if (nextConfirm) stepsLayout.next(cardPanel);

            if (cardPanel.getComponent(2).isVisible()) {  /// basic settings page
                settings[0] = "Project name : empty, click to configure";
                settings[1] = "Project description : empty, click to configure";
                settings[2] = "Project path : invalid, click to configure";
                settings[3] = "Project type : desktop (default choice)";
                settings[4] = "No samples added";
            }

            if (cardPanel.getComponent(3).isVisible()) { /// samples page
                next.setText("finish");
                settings[4] = "No Samples added";

                if (settingsPanel.projectNameFd.getText().length() > 0)
                    settings[0] = "Project name : " + settingsPanel.projectNameFd.getText();
                if (settingsPanel.projectDescriptionPn.getText().length() > 0)
                    settings[1] = "Project description : " + settingsPanel.projectDescriptionPn.getText();
                if (Files.exists(Paths.get(settingsPanel.projectPathFd.getText())))
                    settings[2] = "Project path : " + settingsPanel.projectPathFd.getText();
                if (settingsPanel.webChoice.isSelected())
                    settings[3] = "Project type : Web project";

                // on ajoute les labels des checkBox validée dans la liste samples
                // et on change la valeur de settings[4] pour accompagner la liste
                List<String> samples = new ArrayList<>();
                for (int c = 0; c < samplesAddingPanel.checkboxes.size(); c++) {
                    if (samplesAddingPanel.checkboxes.get(c).getState()) {
                        settings[4] = "Samples added :";
                        samples.add(samplesAddingPanel.checkboxes.get(c).getLabel());
                    }
                }

                List<String> confirmLst = new ArrayList<>();

                confirmLst.addAll(Arrays.asList(settings));
                confirmLst.addAll(samples);

                confirmPanel.confirmArea.removeAll();
                final JList confirmJlist = new JList(confirmLst.toArray(new String[0]));
                confirmJlist.addListSelectionListener(
                        e1 -> {
                            next.setText("next");
                            stepsLayout.first(cardPanel);
                            stepsLayout.next(cardPanel);
                            nextConfirm = true;
                        });

                confirmPanel.confirmArea.add(confirmJlist);
                confirmPanel.confirmArea.revalidate();

                if (settingsPanel.projectNameFd.getText().length() == 0) {
                    nextConfirm=false;
                    confirmJlist.setForeground(Color.red);
                    confirmJlist.revalidate();
                }
                if (!Files.exists(Paths.get(settingsPanel.projectPathFd.getText()))) {
                    nextConfirm = false;
                }
            }
            if (cardPanel.getComponent(4).isVisible()) { /// confirm page
                Project.createProject(settingsPanel.projectNameFd.getText(),
                            settingsPanel.projectDescriptionPn.getText(),
                            settingsPanel.projectPathFd.getText(),
                            settingsPanel.webChoice.isSelected());
                buttonsPanel.setVisible(false);
                boxSeparator.setVisible(false);
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
        globalPanel.add(boxSeparator);
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
