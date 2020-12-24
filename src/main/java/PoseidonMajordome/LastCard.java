package PoseidonMajordome;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class LastCard extends JPanel {
    String welcomeMessage = "\n\n\n\nProject created, good job !\n";

    public JTextPane textArea = new JTextPane();

    public LastCard() {
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

        this.add(textArea);
    }
}
