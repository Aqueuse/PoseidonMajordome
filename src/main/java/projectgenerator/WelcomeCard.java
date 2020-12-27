package projectgenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WelcomeCard extends JPanel {
    String logoPath = "GUIgenerator/mascott_without_letters.png";
    String welcomeMessage = "\n\n\n\n\n\nWelcome to the Poseidon Majordome\n" +
            "Project Generator\n\n" +
            "Let's create a new amazing project\n\n"+
            "Click next when you a ready";

    public JPanel imgPanel = new JPanel();
    public JTextPane textArea = new JTextPane();

    public WelcomeCard () {
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
        textArea.setPreferredSize(new Dimension(280,550));

        imgPanel.setPreferredSize(new Dimension(320,550));
        imgPanel.setBackground(new Color(238,238,238));

        this.add(imgPanel);
        this.add(textArea);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        imgPanel.paint(g2);
        textArea.repaint();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(logoPath));
        }
        catch (IOException e) {
            System.out.println(e + "in class WelcomeCard in method paint");
        }

        g2.drawImage(img,   // version complete
                20, 50,       // dx1, dy1 - x,y destination 1st corner
                320, 391,   // dx2, dy2 - x,y destination 2nd corner
                0, 0, // sx1, sy1 - x,y source 1st corner
                300, 341, // sx2, sy2 - x,y source 2nd corner
                null);     // observer - object to get image modifications
    }
}
