/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

/**
 *
 * @author navodar
 */
public class QuitAction extends AbstractAction {

        /**
         * konstruktor
         */
	public QuitAction() {
		this.putValue(NAME, "Quit");
		this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Q"));
                this.putValue(SHORT_DESCRIPTION, "Quit");
	}
        /**
         * metoda na zachytenie akcie a ukoncenie programu
         * @param e
         */
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
