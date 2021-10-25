package application.builders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import application.PoseidonApplication;

public class RBuilder {
    public RBuilder(String[] requestParameters) {
        String rFilePath = requestParameters[0];
        String rPath = Objects.requireNonNull(PoseidonApplication.class.getResource("ressources/R/bin/Rscript.exe")).getPath();
        Process shell;
        try {
            shell = Runtime.getRuntime().exec(rPath+" "+rFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                PoseidonApplication.applicationMessages.appendToLogger(line);
            }
        }
        catch (IOException ioException) {
            PoseidonApplication.applicationMessages.appendWarningToLogger("R", ioException.getMessage());
        }
    }
}
