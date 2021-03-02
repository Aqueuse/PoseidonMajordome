package globalContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;

public class init {
    static CefSettings mySettings = new CefSettings();
    static CefApp myApp = CefApp.getInstance(mySettings);
    static CefClient client = myApp.createClient();

    static JFrame windowApp = new JFrame();

    public static void main(String[] args) throws IOException, InterruptedException {
        CefBrowser browser = client.createBrowser("http://localhost:3003/static", true, false);
        Component applicationScreen = browser.getUIComponent();
        windowApp.add(applicationScreen);

        windowApp.setSize(1200, 800);
        windowApp.setLocationRelativeTo(null);
        windowApp.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        windowApp.setVisible(true);

        windowApp.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i = JOptionPane.showConfirmDialog(null,
                        "are you sure ?", "are you sure ?", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    System.exit(0);
                }
            }
        });
    }
}