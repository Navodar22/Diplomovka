/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import design.MainFrame;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

/**
 *
 * @author navodar
 */
public class MenuPetriAction extends AbstractAction {

	private File file;
	private MainFrame mainFrame;
	
	public MenuPetriAction(File file, MainFrame mainframe){
		this.putValue(NAME, file.getName() );
		this.file = file;
		this.mainFrame = mainframe;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		System.out.println(file);
		
		mainFrame.setActualPetriFile(file);
		// TODO Auto-generated method stub
		
	}
    
}
