/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomovka;

//import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import design.MainFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

/**
 *
 * @author navodar
 */
public class Diplomovka {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");


        // TODO code application logic here
        String laf = UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(new AcrylLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Diplomovka.class.getName()).log(Level.SEVERE, null, ex);
		}

		MainFrame mainFrame = MainFrame.getInstance();
		mainFrame.setMainFrame();
    }
}
