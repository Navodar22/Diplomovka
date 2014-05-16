/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;


import design.MainFrame;
import diplomovka.Command;

import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 *
 * @author Navodar
 */
public class RenameVertexAction extends MyAction{

	private Command command;
	
    public RenameVertexAction(Icon icon, MainFrame mainFrame) {
    	super(icon, mainFrame);
    	this.putValue(NAME, "Set label");
        this.putValue(SHORT_DESCRIPTION, "Set label");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
//    	System.out.println("rename");
    	
//    	Set<PtfsVertex> vertexes = mainFrame.getActualStack().peek().getSelectVertexes();
//    	String default_name = null;
//
//    	for( PtfsVertex vertex : vertexes  ){
//    		 default_name = vertex.getName();
//    	}
//
//    	String name = JOptionPane.showInputDialog(mainFrame,"Set label", default_name);
//    	if( name != null && !name.equals(default_name)){
//    		command.renameVertex(name);
//    	}
    }
    
}
