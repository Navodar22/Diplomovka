/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;

/**
 *
 * @author navodar
 */
public class MenuLpoAction extends AbstractAction {

	private File file;
	
	public MenuLpoAction(File file){
		this.putValue(NAME, file.getName() );
		this.file = file;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(file);
		// TODO Auto-generated method stub
		
	}
    
}
