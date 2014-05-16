package actions;

import design.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class SaveLPOAction extends MyAction {


    public SaveLPOAction( Icon icon, MainFrame mainframe ) {
    	super(icon, mainframe);
        this.putValue(NAME, "Save");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        this.putValue(SHORT_DESCRIPTION, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//		Stack<PtfsLPO> mainFrameLpoStack = mainFrame.getActualStack();
//		PtfsLPO lpo = mainFrameLpoStack.peek();
//
//
//		if( lpo == null){
//			return;
//		}
//
//		File actual_file = mainFrame.getActualLpoFile();
//
//		File lpo_file = null;
//		if( FileOperations.isTempFile(actual_file) ){
//			FileChooserSave chooser = new FileChooserSave(mainFrame);
//			lpo_file = chooser.getFileFromChooser();
//		}else{
//			lpo_file = actual_file;
//		}
//
//		if(lpo_file == null){
//			return;
//		}
//
//		if( lpo_file.getAbsolutePath().equals(actual_file.getAbsolutePath()) ){
//        	LPOSaver saver = new LPOSaver(mainFrame);
//        	saver.saveLpo(lpo, lpo_file);
//        }else if ( !lpo_file.getAbsolutePath().equals(actual_file.getAbsolutePath()) ){
//        	LPOSaver saver = new LPOSaver(mainFrame);
//        	saver.saveAsLpo(lpo, lpo_file, actual_file);
//        }else{
//
//        }
		
    }
}
