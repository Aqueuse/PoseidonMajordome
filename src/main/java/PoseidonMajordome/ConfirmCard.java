package PoseidonMajordome;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ConfirmCard extends JPanel {
    String welcomeMessage =
            "\n\n\n\n\nValidate your parameters by clicking finish\n" +
            "This will create the project\n"+
            "and all his files";

    public JTextPane textArea = new JTextPane();
    public JPanel confirmPanel = new JPanel();
    public JTextArea settingsText = new JTextArea();

    public ConfirmCard() {
        BoxLayout confirmLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(confirmLayout);

        textArea.setText(welcomeMessage);
        textArea.setFont(new Font("Sans", Font.PLAIN, 16));
        textArea.setEditable(false);
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        textArea.setBackground(new Color(238,238,238));
        textArea.setPreferredSize(new Dimension(300,550));

        confirmPanel.setPreferredSize(new Dimension(300,550));
        confirmPanel.setBackground(new Color(238,238,238));

        confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.Y_AXIS));
        confirmPanel.add(settingsText);

        this.add(textArea);
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(confirmPanel);
    }
}
