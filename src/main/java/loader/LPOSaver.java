package loader;

import design.MainFrame;

import java.io.File;
import java.util.Hashtable;

import analyz.PtfsLPO;
import diplomovka.ExportPtfs;

public class LPOSaver {
	
	MainFrame mainFrame;
	
	public LPOSaver(MainFrame mainframe){
		this.mainFrame = mainframe;
	}
	
	
	public void saveAsLpo(PtfsLPO lpo, File lpo_file, File lpo_old_file){
		System.out.println("save AS LPO");
		
		ExportPtfs exporter = new ExportPtfs();
    	boolean succes = exporter.exportPtfsToXml(lpo, lpo_file); 
    	
    	if(succes){
    		Hashtable<File, PtfsLPO> lpos = new Hashtable<File, PtfsLPO>();
    		lpos.put(lpo_file, lpo);    		
    		mainFrame.getTabbedPane().remove(mainFrame.getTabbedPane().getSelectedComponent());    		
    		mainFrame.addLpos(lpos);    		
//    		mainFrame.removeLpo(lpo_old_file);
    	}   	
    	
	}
	
	public void saveLpo(PtfsLPO lpo, File lpo_file){
		System.out.println("save LPO");		
		ExportPtfs exporter = new ExportPtfs();
    	exporter.exportPtfsToXml(lpo, lpo_file); 
    	
	}
	
}
