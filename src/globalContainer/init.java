package globalContainer;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import org.graalvm.polyglot.*;

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
        final CefBrowser browser = client.createBrowser("file:///E:/backup_data_files/JavaProjects/PoseidonMajordome/NodejsApp/static/index.html", true, false);
        final Component browserUI = browser.getUIComponent();
        window.add(browserUI);

        window.setSize(1200, 800);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }
}