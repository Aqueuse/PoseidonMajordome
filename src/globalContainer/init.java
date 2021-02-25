package globalContainer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;

public class init {
    public static void main(String[] args) throws IOException {
        // lets open a chromium browser and show it
        final CefSettings mySettings = new CefSettings();
        final CefApp myApp = CefApp.getInstance(mySettings);

        JFrame window = new JFrame();

        CefClient client = myApp.createClient();

        Path clientPath = Paths.get("static/helloWorld/index.html");
        final CefBrowser browser = client.createBrowser(clientPath.toAbsolutePath().toString(), true, false);
        final Component browserUI = browser.getUIComponent();
        window.add(browserUI);

        window.setSize(1200, 800);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }
}