package globalContainer;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;

public class init {
    public static void main(String[] args) throws IOException, InterruptedException {

        // start the nodeJS server and wait for a good response
        NodeJS.startNodeServer();

        /// while it's loading, so a start image with poseidon logo

        // open a chromium browser (CEF) and show the starting page
        final CefSettings mySettings = new CefSettings();
        final CefApp myApp = CefApp.getInstance(mySettings);

        JFrame window = new JFrame();

        CefClient client = myApp.createClient();

        final CefBrowser browser = client.createBrowser("http://localhost:3003/static", true, false);
        final Component browserUI = browser.getUIComponent();
        window.add(browserUI);

        window.setSize(1200, 800);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setVisible(true);


    }
}