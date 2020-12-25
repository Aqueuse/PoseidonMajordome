package PoseidonMajordome;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LastCard extends JPanel {
    String logoPath = "GUIgenerator/mascott_without_letters.png";
    String welcomeMessage = "\n\n\n\n\n\n\n\nProject created, good job !\n";

    public JTextPane textArea = new JTextPane();
    public JPanel imgPanel = new JPanel();

    public LastCard() {
        BoxLayout confirmLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(confirmLayout);

        textArea.setFont(new Font("Sans", Font.PLAIN, 16));
        textArea.setEditable(false);
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        imgPanel.setPreferredSize(new Dimension(300,600));
        textArea.setPreferredSize(new Dimension(300,600));
        textArea.setBackground(new Color(238,238,238));

        this.add(imgPanel);
        this.add(textArea);
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        imgPanel.paint(g2);
        textArea.setText(welcomeMessage);
        textArea.repaint();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(logoPath));
        }
        catch (IOException e) {
            System.out.println(e + "in class lastCard in method paint");
        }

        g2.drawImage(img,   // version complete
                0, 100,       // dx1, dy1 - x,y destination 1st corner
                300, 441,   // dx2, dy2 - x,y destination 2nd corner
                0, 0, // sx1, sy1 - x,y source 1st corner
                300, 341, // sx2, sy2 - x,y source 2nd corner
                null);     // observer - object to get image modifications
    }
}
