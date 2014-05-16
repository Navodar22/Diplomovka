/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package design;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author navodar
 */
public class MenuAlgorithmSelect extends JMenu{

	ButtonGroup group = new ButtonGroup();
    JMenuItem menuitem_empty;

    public MenuAlgorithmSelect(String name) {
    	super(name);    	
    }
    
    public void addColumn(JCheckBoxMenuItem menuItem){
    	this.remove(menuitem_empty);    	
    	group.add(menuItem);    	
    	this.add(menuItem);   
    	menuItem.setSelected(true);
    }

    public void setEmptyMenu(){
    	menuitem_empty = new JMenuItem("No Algorithm");
    	this.add(menuitem_empty);
    }
    
    public void removeColumn(JCheckBoxMenuItem menuItem){
    	this.remove(menuItem);
    	
    	if( this.getMenuComponentCount() < 1 ){
    		setEmptyMenu();
    	}    	
    }
    
    
}
