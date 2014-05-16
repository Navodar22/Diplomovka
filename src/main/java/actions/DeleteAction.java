/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.KeyStroke;

import design.MainFrame;
import diplomovka.Command;

/**
 *
 * @author Navodar
 */
public class DeleteAction extends MyAction{

	private Command command;
	
    public DeleteAction(Icon icon, MainFrame mainFrame) {
    	super( icon, mainFrame );
    	this.putValue(NAME, "Delete");
    	this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("DELETE"));
        this.putValue(SHORT_DESCRIPTION, "Delete");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	command.delete();
    }
    
}
