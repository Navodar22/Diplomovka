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
public class ElementsSelectAction extends MyAction{

//	private MainToolbar maintoolbar;
	
    public ElementsSelectAction(Icon icon, MainFrame mainFrame) {
    	super( icon, mainFrame );
        
    	this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl A"));
        this.putValue(SHORT_DESCRIPTION, "Select");
//        this.maintoolbar = maintoolbar;
        
        //this.setToolTipText("Click this button to disable the middle button.");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	MainToolbar.buttons = MainToolbarButtonChoices.SELECT_MORE;
    }
    
}
