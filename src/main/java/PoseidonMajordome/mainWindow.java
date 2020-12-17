package PoseidonMajordome;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class mainWindow extends JPanel {
    public static JFrame f = new JFrame();
    public void swingApp() {
        f.getContentPane().add(new mainWindow());
        f.setSize(1200, 800);
        f.setLocationRelativeTo( null );
        f.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        f.setVisible(true);

        /// create a menu for :
        ///     [File] : new project | open project / Save Project / Save Project As | export | quit
        ///     [edit] : undo / reddo | cut / copy / paste
        ///     [add] :  mongoDB attachement / output folder / Rscript / external dependencies
        ///     [project] servlet template / HTML output template / project template / test locally
        
        JMenuBar menuBar = new JMenuBar();

        ///     [File] : new project / open project / Save Project / Save Project As / export / quit
        JMenu menuFile = new JMenu( "File" );
            menuFile.setMnemonic( 'F' );

            JMenuItem menuNewFile = new JMenuItem( "New Project" );
            menuNewFile.setMnemonic( 'N' );
            menuNewFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK) );

            menuNewFile.addActionListener( this::menuNewListener );
            menuFile.add(menuNewFile);

            menuFile.addSeparator();

            JMenuItem menuOpenFile = new JMenuItem( "Open Project ..." );
            menuOpenFile.setMnemonic( 'O' );
            menuOpenFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK) );
            menuFile.add(menuOpenFile);

            JMenuItem menuSaveFile = new JMenuItem( "Save Project ..." );
            menuSaveFile.setMnemonic( 'S' );
            menuSaveFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK) );
            menuFile.add(menuSaveFile);

            JMenuItem menuSaveFileAs = new JMenuItem( "Save Project As ..." );
            menuSaveFileAs.setMnemonic( 'A' );
            menuFile.add(menuSaveFileAs);

            menuFile.addSeparator();

            JMenuItem menuExit = new JMenuItem( "Exit" );
            menuExit.setMnemonic( 'x' );
            menuExit.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK) );
            menuFile.add(menuExit);

            menuBar.add(menuFile);
           
        ///     [add] :  mongoDB attachement / output folder / Rscript / external dependencies
        JMenu menuAdd = new JMenu("Add");
        menuAdd.setMnemonic('A');

             /// [add] :  Rscript / Servlet / mongoDB attachement / output folder / external dependencies
            JMenuItem menuRscript = new JMenuItem( "new Rscript interaction template" );
            menuAdd.add(menuRscript);

            JMenuItem menuServlet = new JMenuItem( "new servlet template" );
            menuAdd.add(menuServlet);

            JMenuItem menuMongo = new JMenuItem( "MongoDB attachement template" );
            menuAdd.add(menuMongo);

            menuAdd.addSeparator();

            JMenuItem menuDistantAPI = new JMenuItem( "connection to a distant API" );
            menuAdd.add(menuDistantAPI);

            JMenuItem menuFolderIn = new JMenuItem( "Input Folder" );
            menuAdd.add(menuFolderIn);

            JMenuItem menuFolderOut = new JMenuItem( "Output Folder" );
            menuAdd.add(menuFolderOut);

            JMenuItem menuDependencies = new JMenuItem( "External dependencies" );
            menuAdd.add(menuDependencies);

        menuBar.add(menuAdd);

        ///     [project] : servlet template / HTML output template / project template / test locally
        JMenu menuProject = new JMenu( "Project" );
        menuProject.setMnemonic( 'P' );

            JMenuItem menuTest = new JMenuItem( "test locally" );
            menuProject.add(menuTest);

            JMenuItem menuBundle = new JMenuItem( "create application bundle" );
            menuProject.add(menuBundle);

        menuBar.add( menuProject );
        
        ///     [help] 
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic( 'H' );
                    JMenuItem menuSettings = new JMenuItem( "Settings" );
                    menuHelp.add(menuSettings);

        menuBar.add( menuHelp );
        
        f.add(menuBar);
        f.setJMenuBar(menuBar);
    }

    public void menuNewListener( ActionEvent event ) {
        JOptionPane.showMessageDialog( f, "créer un nouveau projet" );
    }
    
    public static void main(String[] Args) {
        mainWindow newPoseidon = new mainWindow();
        newPoseidon.swingApp();
    }
}