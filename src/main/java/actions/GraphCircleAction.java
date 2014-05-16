/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import design.MainFrame;

import java.awt.event.ActionEvent;

import javax.swing.Icon;

/**
 *
 * @author Navodar
 */
public class GraphCircleAction extends MyAction{

    public GraphCircleAction( Icon icon, MainFrame mainframe ) {
    	super(icon, mainframe);
        this.putValue(NAME, "Graph Circles");
//        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl R"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if( mainFrame.isGraphCircle() ){
    		mainFrame.setGraphCircle(false);
    	}else{
    		mainFrame.setGraphCircle(true);
    	}
    	
    	System.out.println( mainFrame.isGraphCircle() );
    	
    }
    
    
}
