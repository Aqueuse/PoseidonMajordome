package PoseidonMajordome;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class SamplesAddingCard extends JPanel {
    String otherSettingsMessage = "\n\n\n\n\n\nChoose what samples you need\n" +
            "too go faster.\n\n" +
            "They will be past in your\n"+
            "default package project\n"+
            "from Samples/";

    public JTextPane textArea = new JTextPane();
    public JPanel OthersettingsPanel = new JPanel();
    public JList<Object> samplesChoose = new JList<>();

    public SamplesAddingCard() {
        BoxLayout MessageLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(MessageLayout);

        textArea.setText(otherSettingsMessage);
        textArea.setFont(new Font("Sans", Font.PLAIN, 16));
        textArea.setEditable(false);
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        textArea.setBackground(new Color(238,238,238));
        textArea.setPreferredSize(new Dimension(300,550));

        OthersettingsPanel.setPreferredSize(new Dimension(300,550));
        OthersettingsPanel.setBackground(new Color(238,238,238));

        // populate JList samplesChoose with the Samples/*.java samples files
        Set<String> SamplesList = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("Samples/"))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    SamplesList.add(path.getFileName().toString());
                }
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

        samplesChoose.setListData(SamplesList.toArray());

        OthersettingsPanel.add(samplesChoose);
        this.add(textArea);
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(OthersettingsPanel);
    }
}