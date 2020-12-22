package PoseidonMajordome;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class OtherSettingsCard extends JPanel {
    String welcomeMessage = "\n\n\n\n\n\nChoose you project's settings\n" +
            "if you want a web Project\n\n" +
            "With TomCat Embedded\n\n"+
            "Choose Web Project";

    public JTextPane textArea = new JTextPane();
    public JPanel settingsPanel = new JPanel();

    public OtherSettingsCard() {
        BoxLayout welcomeLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(welcomeLayout);

        textArea.setText(welcomeMessage);
        textArea.setFont(new Font("Sans", Font.PLAIN, 16));
        textArea.setEditable(false);
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        textArea.setBackground(new Color(238,238,238));
        textArea.setPreferredSize(new Dimension(300,550));

        settingsPanel.setPreferredSize(new Dimension(300,550));
        settingsPanel.setBackground(new Color(238,238,238));

        this.add(textArea);
        this.add(settingsPanel);
    }
}
