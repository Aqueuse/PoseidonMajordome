package PoseidonMajordome;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.File;

public class BasicSettingsCard extends JPanel {
    String basicSettingsMessage = "\n\n\n\n\n\nChoose you project's settings\n\n"
            +" They will be saved in GUIgenerator/ProjectSettings.ini\n in the PoseidonMajordome "+
            "installation folder\n\n You can change your mind until the confirmation step if you need";

    String desktopStr = "  Desktop Project\n (Swing Template\n     embedded)";
    String webStr = "         Web Project\n (Tomcat embedded)";

    public JTextPane textArea = new JTextPane();
    public JPanel settingsPanel = new JPanel();

    public JLabel projectNameLb = new JLabel("Project Name : ");
    public JTextField projectNameFd = new JTextField();

    public JLabel projectDescriptionLb = new JLabel("Description :   ");
    public JTextPane projectDescriptionPn = new JTextPane();

    public JLabel projectPathLb = new JLabel("Location : ");
    public JTextField projectPathFd = new JTextField();
    public JButton openNavigator = new JButton("...");

    public JTextPane desktopPn = new JTextPane();
    public JTextPane webPn = new JTextPane();
    public ButtonGroup projectType = new ButtonGroup();
    public JRadioButton desktopChoice = new JRadioButton();
    public JRadioButton webChoice = new JRadioButton();

    public BasicSettingsCard() {
        BoxLayout BasicSettingsLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(BasicSettingsLayout);

        textArea.setText(basicSettingsMessage);
        textArea.setFont(new Font("Sans", Font.PLAIN, 16));
        textArea.setEditable(false);
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        textArea.setBackground(new Color(238,238,238));
        textArea.setPreferredSize(new Dimension(300,550));
        textArea.setText(basicSettingsMessage);

        settingsPanel.setPreferredSize(new Dimension(300,550));
        settingsPanel.setBackground(new Color(238,238,238));
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(new EmptyBorder(new Insets(5,5,5,5)));

        settingsPanel.add(Box.createRigidArea(new Dimension(80,60)));

        projectNameFd.setPreferredSize(new Dimension(220,30));
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        namePanel.add(projectNameLb);
        namePanel.add(projectNameFd);
        settingsPanel.add(namePanel);

        settingsPanel.add(Box.createRigidArea(new Dimension(80,20)));

        projectDescriptionPn.setPreferredSize(new Dimension(220,200));
        projectDescriptionPn.setBorder(BorderFactory.createEtchedBorder(1, new Color(122,138,153), Color.WHITE));
        projectDescriptionPn.setText("will be saved in readme.md");
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new FlowLayout());
        descriptionPanel.add(projectDescriptionLb);
        descriptionPanel.add(projectDescriptionPn);
        settingsPanel.add(descriptionPanel);

        settingsPanel.add(Box.createRigidArea(new Dimension(80,20)));

        projectPathFd.setPreferredSize(new Dimension(200,30));
        JPanel pathPanel = new JPanel();
        openNavigator.addActionListener(e -> {
            FileChooser projectFileChooser = new FileChooser();

            if (projectFileChooser.chooseFolder.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fileSelected = projectFileChooser.chooseFolder.getSelectedFile();

                String filepath = fileSelected.getPath();
                System.out.println(filepath);
                projectPathFd.setText(filepath);
            }
        });
        pathPanel.setLayout(new FlowLayout());
        pathPanel.add(projectPathLb);
        pathPanel.add(projectPathFd);
        pathPanel.add(openNavigator);
        settingsPanel.add(pathPanel);

        settingsPanel.add(Box.createRigidArea(new Dimension(80,60)));

        projectType.add(desktopChoice);
        projectType.add(webChoice);

        desktopPn.setEditable(false);
        desktopPn.setText(desktopStr);
        desktopPn.setBackground(new Color(238,238,238));

        webPn.setEditable(false);
        webPn.setText(webStr);
        webPn.setBackground(new Color(238,238,238));

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));

        JPanel desktopPanel = new JPanel();
        desktopPanel.setLayout(new BoxLayout(desktopPanel, BoxLayout.Y_AXIS));
        desktopPanel.setBorder(new EmptyBorder(new Insets(0,20,0,30)));
        desktopPanel.add(desktopChoice);
        desktopPanel.add(desktopPn);

        JPanel webPanel = new JPanel();
        webPanel.setLayout(new BoxLayout(webPanel, BoxLayout.Y_AXIS));
        webPanel.setBorder(new EmptyBorder(new Insets(0,30,0,20)));
        webPanel.add(webChoice);
        webPanel.add(webPn);

        typePanel.add(desktopPanel);
        typePanel.add(new JSeparator(SwingConstants.VERTICAL));
        typePanel.add(webPanel);

        settingsPanel.add(typePanel);

        this.add(textArea);
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(settingsPanel);
    }
}
