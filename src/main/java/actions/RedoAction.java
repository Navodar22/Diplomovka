package actions;

import design.MainFrame;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

import diplomovka.Command;

public class RedoAction extends MyAction {

    public RedoAction( Icon icon, MainFrame mainframe ) {
    	super(icon, mainframe);
        this.putValue(NAME, "Redo");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Y"));
        this.putValue(SHORT_DESCRIPTION, "redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.canvasRedo();
    }
}
