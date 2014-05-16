/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import design.MainFrame;
import diplomovka.Enums;

import java.awt.event.ActionEvent;

import javax.swing.Icon;

import diplomovka.AlgorithmMenuStatus;
import diplomovka.Command;
import enums.MaxFlowAlgorithmChoices;

/**
 *
 * @author Navodar
 */
public class DinicAction extends MyAction{

	private Command command;
	
    public DinicAction(Icon icon, MainFrame mainFrame) {
    	super( icon, mainFrame );
    	this.putValue(NAME, "Dinic's alg.");
        this.putValue(SHORT_DESCRIPTION, "Dinic");
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	AlgorithmMenuStatus.status = MaxFlowAlgorithmChoices.DINIC;
    }
    
}
