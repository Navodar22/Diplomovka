package actions;

import design.MainFrame;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.KeyStroke;

import diplomovka.Command;
/**
 * trieda na akciu Undo
 * @author navodar
 */
public class UndoAction extends MyAction {

    /**
     * konstruktor s argumentom canvasu
     * nastavujeme klav. skratku, ikonu a nazov
     *
     */
    public UndoAction( Icon icon, MainFrame mainframe ) {
    	super( icon, mainframe );
        this.putValue(NAME, "Undo");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Z"));
        this.putValue(SHORT_DESCRIPTION, "undo");
    }

    /**
     * metoda na zachytenie akcie
     * ak bola zachytena tak vyberieme z commandu posledny prvok resp prvky
     * a vlozime ich do druheho commandu
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	mainFrame.canvasUndo();
    }
        
}
