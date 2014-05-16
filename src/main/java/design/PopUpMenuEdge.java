package design;

import actions.DeleteAction;
import diplomovka.Icons;

import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

public class PopUpMenuEdge {
	JPopupMenu Pmenu;
	


	public PopUpMenuEdge(MouseEvent e, MainFrame mainFrame) {		
		Pmenu = new JPopupMenu();		
		
		Pmenu.add(new DeleteAction( Icons.DELETE_SMALL, mainFrame));

		Pmenu.show(e.getComponent(), e.getX(), e.getY());
		
	}
}