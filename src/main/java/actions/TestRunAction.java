/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import design.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;




/**
 *
 * @author Navodar
 */
public class TestRunAction extends MyAction{

    public TestRunAction( Icon icon, MainFrame mainframe ) {
    	super(icon, mainframe);
        this.putValue(NAME, "Run test");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl R"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//    	System.out.println("teeest");
    	
//    	CompactTokenFlowTest compactTFtest = new CompactTokenFlowTest();
//
//    	File actual_lpo_file = mainFrame.getActualLpoFile();
//    	File actual_petri_file = mainFrame.getActualPetriFile();
//
//    	if( actual_lpo_file == null || actual_petri_file == null ){
//    		JOptionPane.showMessageDialog(mainFrame, "LPO or Petri Net is not selected", "Select Error", JOptionPane.ERROR_MESSAGE);
//    		return;
//    	}
//
//    	PflowPetriNet petriNet = mainFrame.getActualPetriNet(actual_petri_file);
//    	PtfsLPO lpo = mainFrame.getLpoByFileWithoutRemoving(actual_lpo_file);
//        PflowPlace place = null;
//    	long startTime = System.currentTimeMillis();
//        for(int i = 0; i < 50000; i++ ){
//    	    place = compactTFtest.test(lpo, petriNet, AlgorithmMenuStatus.status);
//        }
//    	long elapsed  = (System.currentTimeMillis() - startTime);
//
//    	System.out.println(elapsed);
//
//		if ( place  == null ){
//			JOptionPane.showMessageDialog(mainFrame, "LPO is executable on tested Petri's net", "Test Ok", JOptionPane.INFORMATION_MESSAGE);
//		}else{
//			JOptionPane.showMessageDialog(mainFrame, "LPO is NOT executable on tested Petri's net for place " + place.getLabel() +
//					" with id " + place.getId() , "Test Failed", JOptionPane.INFORMATION_MESSAGE);
//		}
    }
    
    
}
