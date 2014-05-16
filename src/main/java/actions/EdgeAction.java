/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import design.MainFrame;
import diplomovka.Enums;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.KeyStroke;

import design.MainToolbar;
import enums.MainToolbarButtonChoices;


/**
 *
 * @author Navodar
 */
public class EdgeAction extends MyAction{

//	private MainToolbar maintoolbar;
	
    public EdgeAction(Icon icon, MainFrame mainFrame) {
    	super( icon, mainFrame );
    	this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
        this.putValue(SHORT_DESCRIPTION, "Edge");
        //this.setToolTipText("Click this button to disable the middle button.");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	MainToolbar.buttons = MainToolbarButtonChoices.EDGE;
    }
    
}
