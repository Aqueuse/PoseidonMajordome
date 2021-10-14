package application.builders;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import application.PoseidonApplication;

public class ReadBuilder {
    public ReadBuilder(String[] factoryParameters) {
        String path = factoryParameters[0];
        BufferedReader textFile;

        if (path.getBytes(StandardCharsets.UTF_8).length == 0) {
            PoseidonApplication.paneMessages.getSelectionModel().select(PoseidonApplication.applicationMessagesTab);
            PoseidonApplication.applicationMessagesTextArea.setText("path not configured");
        }

        if (!Files.exists(Paths.get(path))) {
            PoseidonApplication.paneMessages.getSelectionModel().select(PoseidonApplication.applicationMessagesTab);
            PoseidonApplication.applicationMessagesTextArea.setText("path not found");
        }

        else {
            try {
                textFile = Files.newBufferedReader(Paths.get(path), StandardCharsets.ISO_8859_1);
                PoseidonApplication.dataFlow = textFile.lines().collect(Collectors.joining());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
