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
public class MenuPetri extends JMenu{

    ButtonGroup group = new ButtonGroup();
    JMenuItem menuitem_empty;

    public MenuPetri(String name) {
    	super(name);
    }

    public void setEmptyMenu(){
    	menuitem_empty = new JMenuItem("No Petri Nets loaded");
    	this.add(menuitem_empty);
    }
    
    public void addColumn(JCheckBoxMenuItem menuItem){
    	this.remove(menuitem_empty);    	
    	group.add(menuItem);
    	this.add(menuItem);
    }
    
    
    
}
