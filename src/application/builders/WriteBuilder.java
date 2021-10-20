package application.builders;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import application.PoseidonApplication;

public class WriteBuilder {
    public WriteBuilder(String[] factoryParameters) {
        String path = factoryParameters[0];
        String filename = factoryParameters[1];
        String textToWrite = factoryParameters[2];
        FileWriter textFile;

        try {
            textFile = new FileWriter(path+"/"+filename, StandardCharsets.UTF_8, true);
            textFile.append(textToWrite);
            textFile.close();
            PoseidonApplication.applicationMessages.appendSuccessToLogger("WRITE", "File writed");
        }
        catch (IOException ioException) {
            PoseidonApplication.applicationMessages.appendWarningToLogger("WRITE", ioException.getMessage());
        }
    }
}
