package PoseidonMajordome;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class generator extends JFrame {
    static JPanel globalPanel = new JPanel();
    static BoxLayout global = new BoxLayout(globalPanel, BoxLayout.Y_AXIS);

    static CardLayout stepsLayout = new CardLayout();
    static JPanel cardPanel = new JPanel();

    static WelcomeCard WelcomePanel = new WelcomeCard();
    static BasicSettingsCard settingsPanel = new BasicSettingsCard();
    static SamplesAddingCard samplesAddingPanel = new SamplesAddingCard();

    static JPanel buttonsPanel = new JPanel();
    static BoxLayout buttonsLayout = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);
    static JButton previous = new JButton("previous");
    static JButton next = new JButton("next");
    static JButton cancel = new JButton("cancel");

    static int nextHint = 0;
    static int typeChoose = 0;
    static int nameChoose = 0;
    static int descrChoose = 0;
    static int pathChoose = 0;

    public static void windowSettings() {
        globalPanel.setLayout(global);

        cardPanel.setLayout(stepsLayout);
        cardPanel.setPreferredSize(new Dimension(700,550));

        cardPanel.add(WelcomePanel);
        cardPanel.add(settingsPanel);
        cardPanel.add(samplesAddingPanel);

        previous.addActionListener(e -> {
            if (!cardPanel.getComponent(0).isVisible())
                stepsLayout.previous(cardPanel);

                // on remet les compteurs a zero : the user can choose again is type of project
                settingsPanel.typePanel.setBorder(BorderFactory.createEmptyBorder());
                nextHint = 0;
                typeChoose = 0;
                nameChoose = 0;
                descrChoose = 0;
                pathChoose = 0;
        });
        next.addActionListener(e -> {
            if (cardPanel.getComponent(0).isVisible()) {
                stepsLayout.next(cardPanel);
            }
            if (cardPanel.getComponent(1).isVisible()) {
                nextHint = nextHint + 1;

                if (settingsPanel.projectNameFd.getText().length() > 0) nameChoose = 1;
                if (settingsPanel.projectDescriptionPn.getText().length() > 0) descrChoose = 1;
                if (settingsPanel.webChoice.isSelected() || settingsPanel.desktopChoice.isSelected()) typeChoose = 1;

                try {
                    if (Files.exists(Paths.get(settingsPanel.projectPathFd.getText()))) pathChoose = 1;
                }
                catch (InvalidPathException IPE) {
                    System.out.println("folder not found");
                }

                System.out.println(
                        "nextHint = "+nextHint+
                                "typeChoose = "+typeChoose+
                                "nameChoose = "+nameChoose+
                                "descrChoose = "+descrChoose+
                                "pathChoose = "+pathChoose
                );

                if (nameChoose + descrChoose + pathChoose + typeChoose < 4 && nextHint>=1) {
                    if (nameChoose==0) settingsPanel.namePanel.setBorder(BorderFactory.createEtchedBorder());
                    if (descrChoose==0) settingsPanel.projectDescriptionPn.setBorder(BorderFactory.createEtchedBorder());
                    if (pathChoose==0) settingsPanel.pathPanel.setBorder(BorderFactory.createEtchedBorder());
                    if (typeChoose==0) settingsPanel.typePanel.setBorder(BorderFactory.createEtchedBorder());
                }

                if (nameChoose + descrChoose + pathChoose + typeChoose == 4) {
                    try {
                        Project.initSettingsSave(settingsPanel.projectNameFd.getText(),
                                settingsPanel.projectDescriptionPn.getText(),
                                settingsPanel.projectPathFd.getText(),
                                settingsPanel.webChoice.isSelected()
                        );
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    settingsPanel.namePanel.setBorder(BorderFactory.createEmptyBorder());
                    settingsPanel.descriptionPanel.setBorder(BorderFactory.createEmptyBorder());
                    settingsPanel.pathPanel.setBorder(BorderFactory.createEmptyBorder());
                    settingsPanel.typePanel.setBorder(BorderFactory.createEmptyBorder());
                    stepsLayout.next(cardPanel);
                }
            }
        });
        cancel.addActionListener(e -> {
            System.exit(0);
        });

        buttonsPanel.setLayout(buttonsLayout);
        buttonsPanel.setPreferredSize(new Dimension(700,50));
        buttonsPanel.add(Box.createRigidArea(new Dimension(135,5)));
        buttonsPanel.add(previous);
        buttonsPanel.add(Box.createRigidArea(new Dimension(135,5)));
        buttonsPanel.add(next);
        buttonsPanel.add(Box.createRigidArea(new Dimension(135,5)));
        buttonsPanel.add(cancel);

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
