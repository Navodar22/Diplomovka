package loader;

import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import design.MainFrame;

import actions.ExampleFileFilterAction;
import actions.LoadDataAction;

public class FileChooserSave {
	
	JFileChooser chooser;
	MainFrame mainFrame;
	
	public FileChooserSave(MainFrame mainframe){
		this.mainFrame = mainframe;
		Preferences preferences = Preferences
				.userNodeForPackage(LoadDataAction.class);

		String dir = preferences.get("adresar", ".");
		File currentDirectory = new File(dir);
		
		chooser = new JFileChooser(currentDirectory);
		chooser.setMultiSelectionEnabled(false);
		ExampleFileFilterAction filter = new ExampleFileFilterAction();
		filter.addExtension("ptfs");
		filter.setDescription("LPO's XML's");
		chooser.setFileFilter(filter);
			
	}

	
	public File getFileFromChooser() {

		int rVal = chooser.showSaveDialog(chooser);

		if (rVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();

			if(!file.getAbsolutePath().toLowerCase().endsWith(".ptfs"))
			{
				file = new File(file.getAbsolutePath() + ".ptfs");
			}
			
			if (file.exists()) {
				int reply = JOptionPane.showConfirmDialog(null,
						"The file exists, overwrite?", "Existing file",
						JOptionPane.YES_NO_OPTION);
				switch (reply) {
				case JOptionPane.YES_OPTION:
					break;
				case JOptionPane.NO_OPTION:
					getFileFromChooser();
					break;
				case JOptionPane.CLOSED_OPTION:
					return null;
				case JOptionPane.CANCEL_OPTION:
					return null;
				}
			}
			
			
			if( mainFrame.isFileInLoadedLpos(file) ){
				JOptionPane.showMessageDialog(null, "This file is already open", "Error", JOptionPane.ERROR_MESSAGE);
				getFileFromChooser();
			}
			return file;
		}

		return null;
	}
}
