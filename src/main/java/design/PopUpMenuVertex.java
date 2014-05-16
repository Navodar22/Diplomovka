package design;

import actions.DeleteAction;
import actions.RenameVertexAction;
import diplomovka.Icons;

import javax.swing.*;

import java.awt.event.*;

public class PopUpMenuVertex {
	JPopupMenu Pmenu;
	


	public PopUpMenuVertex(MouseEvent e, MainFrame mainFrame) {		
		Pmenu = new JPopupMenu();		
		
		Pmenu.add(new RenameVertexAction( Icons.SETNAME_SMALL, mainFrame));
		Pmenu.add(new DeleteAction( Icons.DELETE_SMALL, mainFrame));

		Pmenu.show(e.getComponent(), e.getX(), e.getY());
		
	}
}