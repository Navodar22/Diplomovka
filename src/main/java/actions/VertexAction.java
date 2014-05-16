/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import design.MainFrame;
import design.MainToolbar;
import enums.MainToolbarButtonChoices;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.KeyStroke;

/**
 *
 * @author Navodar
 */
public class VertexAction extends MyAction{

//	private MainToolbar maintoolbar;
	
    public VertexAction(Icon icon, MainFrame mainFrame) {
    	super(icon, mainFrame);        
    	this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl V"));
        this.putValue(SHORT_DESCRIPTION, "Vertex");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	MainToolbar.buttons = MainToolbarButtonChoices.VERTEX;
    }
    
}
