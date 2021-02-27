package globalContainer;

import java.io.IOException;

public class NodeJS {

    public static void startNodeServer () throws IOException, InterruptedException {
        /// start the nodeJS server
        ProcessBuilder pBuilder = new ProcessBuilder("./nodejs/nodeBinary/node.exe", "../nodejs/app.js");
        Process process = pBuilder.start();

        process.waitFor();
    }

    /// give the code to the good method
    /// give back the results to plot if so
}
