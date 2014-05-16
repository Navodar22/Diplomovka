package actions;

import design.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class SaveAsLPOAction extends MyAction {

	public SaveAsLPOAction(Icon icon, MainFrame mainframe) {
		super(icon, mainframe);
		this.putValue(NAME, "Save As");
		this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt S"));
		this.putValue(SHORT_DESCRIPTION, "Save As");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("Save As");
//
//		Stack<PtfsLPO> mainFrameLpoStack = mainFrame.getActualStack();
//
//		PtfsLPO lpo = mainFrameLpoStack.peek();
//
//		if (lpo == null) {
//			return;
//		}
//
//		FileChooserSave chooser = new FileChooserSave(mainFrame);
//		File lpo_file = chooser.getFileFromChooser();
//		File actual_file = mainFrame.getActualLpoFile();
//
//		System.out.println(lpo_file);
//
//		if (lpo_file != null) {
//
//			LPOSaver saver = new LPOSaver(mainFrame);
//			saver.saveAsLpo(lpo, lpo_file, actual_file);
//		}

	}
}
