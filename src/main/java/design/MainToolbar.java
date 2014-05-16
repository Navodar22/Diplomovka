/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package design;


import actions.*;
import diplomovka.Enums;
import diplomovka.Icons;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import enums.MainToolbarButtonChoices;


/**
 *
 * @author Navodar
 */
@SuppressWarnings("serial")
public class MainToolbar extends JToolBar{   
	
	
	public static MainToolbarButtonChoices buttons;
	
	
    public MainToolbar(MainFrame mainframe){
    	super();
    	    	
        this.setFloatable(false);
        this.setBorder(null);
        
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        
        this.add(new NewLPOAction( Icons.NEW, mainframe ));
        this.add(new LoadDataAction(Icons.OPEN, mainframe ));
        this.add(new SaveLPOAction( Icons.SAVE, mainframe ));
        this.add(new SaveAsLPOAction( Icons.SAVEAS, mainframe ));
        
//        this.add(new SaveAllLPOAction( Icons.SAVEALL, mainframe ));

        
        this.addSeparator(new Dimension(20,20));

        this.add(new UndoAction( Icons.UNDO, mainframe ));
        this.add(new RedoAction( Icons.REDO, mainframe ));
        this.add(new DeleteAction( Icons.DELETE, mainframe ));
        this.add(new RenameVertexAction(Icons.SETNAME_SMALL,mainframe));

        JToggleButton manual = new JToggleButton(new GraphCircleAction(Icons.GRAPH_CIRCLE, mainframe));
        manual.setHideActionText(true);
        this.add(manual);
        
        this.addSeparator(new Dimension(20,20));
        
        ButtonGroup toolbarButtons = new ButtonGroup();
        
        JToggleButton selectMoore = new JToggleButton( new ElementsSelectAction(Icons.SELECT_MOORE, mainframe));
        toolbarButtons.add(selectMoore);
        this.add(selectMoore);
        
        
        //nastavenie defaultneho tlacidla a jeho hodnoty.
        selectMoore.setSelected(true);
        buttons = MainToolbarButtonChoices.SELECT_MORE;
        
        JToggleButton vertex = new JToggleButton( new VertexAction(Icons.VERTEX, mainframe));
        toolbarButtons.add(vertex);
        this.add(vertex);        
        
        JToggleButton edge = new JToggleButton( new EdgeAction(Icons.EDGE, mainframe) );
        toolbarButtons.add(edge);
        this.add(edge);
        
        this.addSeparator(new Dimension(20,20));
        
        this.add(new TestRunAction(Icons.RUN_TEST, mainframe));
        
        
                
    }
   
    
}
