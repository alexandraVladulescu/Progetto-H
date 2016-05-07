package gui;

import javax.swing.JFrame;

/**
 * Questo è il frame principale del programma. Rappresenta l'applicazione. E' un singleton.
 * @author Markenos
 */
public class MainFrame extends JFrame {
    private static MainFrame mainFrame;
    
    private MainFrame(){
        
    }
    
    public static MainFrame getInstance(){
        if (mainFrame == null){
            mainFrame = new MainFrame();
        }
        return mainFrame;
    }
}
