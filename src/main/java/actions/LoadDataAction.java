/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Icon;
import javax.swing.KeyStroke;
import javax.xml.parsers.ParserConfigurationException;

import design.MainFrame;
import loader.DataHandler;


/**
 * 
 * @author navodar
 */
public class LoadDataAction extends MyAction {

	public LoadDataAction(Icon icon, MainFrame mainframe) {
		super( icon, mainframe );
		this.putValue(NAME, "Load Data");
		this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl L"));
		this.putValue(SHORT_DESCRIPTION, "Load Data");
		// this.setToolTipText("Click this button to disable the middle button.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        mainFrame.getDataHandler().loadData(mainFrame);
	}

}
