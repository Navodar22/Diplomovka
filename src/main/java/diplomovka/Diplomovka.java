/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomovka;

//import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

import design.MainFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


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

		MainFrame mainFrame = MainFrame.getInstance();
		mainFrame.setMainFrame();
    }
}
