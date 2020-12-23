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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SamplesAddingCard extends JPanel {
    String otherSettingsMessage = "\n\n\n\n\n\nChoose what samples you need\n" +
            "too go faster.\n\n" +
            "They will be past in your\n"+
            "default package project\n"+
            "from Samples/";

    public Object[] listArray;

    public JTextPane textArea = new JTextPane();
    public JPanel othersettingsPanel = new JPanel();
    public JPanel BorderListPanel = new JPanel();
    public JPanel BoxListPanel = new JPanel();

    public List<Checkbox> checkboxes = new ArrayList<>();

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

        othersettingsPanel.setPreferredSize(new Dimension(350,550));
        othersettingsPanel.setBackground(new Color(238,238,238));

        BorderListPanel.setLayout(new BorderLayout());
        BoxListPanel.setLayout(new BoxLayout(BoxListPanel, BoxLayout.Y_AXIS));

        BorderListPanel.setPreferredSize(new Dimension(340,550));

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

        listArray = SamplesList.toArray();

        for (int i = 0; i < listArray.length; i++) {
            Checkbox checkbox = new Checkbox(listArray[i].toString());
            checkboxes.add(checkbox);
            BoxListPanel.add(checkbox);
        }

        BorderListPanel.add(BoxListPanel, BorderLayout.WEST);
        othersettingsPanel.add(BorderListPanel);

        this.add(textArea);
        this.add(new JSeparator(SwingConstants.VERTICAL));
        this.add(othersettingsPanel);
    }
}