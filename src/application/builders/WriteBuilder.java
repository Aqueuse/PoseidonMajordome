package application.builders;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import application.PoseidonApplication;

public class WriteBuilder {
    public WriteBuilder(String[] factoryParameters) {
        String path = factoryParameters[0];
        String filename = factoryParameters[1];
        FileWriter textFile;
        try {
            textFile = new FileWriter(path+"/"+filename, StandardCharsets.UTF_8, false);
            textFile.append(PoseidonApplication.dataFlow);
            textFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
