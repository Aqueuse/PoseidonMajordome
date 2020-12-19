package PoseidonMajordome;

import java.io.IOException;

public class generator {
    public static void main(String[] Args) throws IOException {
        // TODO : in the script, inform when project already exist, if yes, then ask to overwrite

        Project.createProject(
                "test",
                "this is a test",
                "simpleAPI",
                "C:/Users/Megaport/Desktop/",
                true
        );
    }

    public void scriptShell() {

    }
}