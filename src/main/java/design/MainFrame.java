/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package design;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Hashtable;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import analyz.PflowPetriNet;
import analyz.PtfsLPO;
import diplomovka.Command;
import drawing.LPOtabpane;
import loader.DataHandler;
import loader.DataHandlerFileImpl;

/**
 * 
 * @author navodar
 */
public class MainFrame extends JFrame {

	private static MainFrame instance = null;
    private Command command;

	private Hashtable<File, PflowPetriNet> pnets = new Hashtable<File, PflowPetriNet>();

//	private Hashtable<File, Stack<PtfsLPO> > lpos = new Hashtable<File, Stack<PtfsLPO>>();
//	private Hashtable<File, Stack<PtfsLPO> > lpos_redo = new Hashtable<File, Stack<PtfsLPO>>();

	private DataHandler dataHandler = new DataHandlerFileImpl();
	
	private File actual_lpo_file;	
	private File actual_petri_file;
	
	private LPOtabpane tabbedPane;
	private MainFrameMenuBar mainBar;
	private boolean graphCircle = false;
	
	
	protected MainFrame() {
		// Exists only to defeat instantiation.
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	public void setMainFrame() {
		this.setLayout(new BorderLayout());
		mainBar = new MainFrameMenuBar(this);
		tabbedPane = new LPOtabpane(mainBar);
		this.add(tabbedPane);
		MainToolbar toolBar = new MainToolbar(this);
		this.add(toolBar, BorderLayout.NORTH);
		this.setSize(800, 600);		
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

    public void canvasUndo(){
        command.undo();
    }

    public void canvasRedo(){
        command.redo();
    }
	
	public PflowPetriNet getActualPetriNet( File file ){
		return pnets.get(file);		
	}
	
//	public PtfsLPO getLpoByFileWithoutRemoving(File file) {
//		return lpos.get(file).peek();
//	}
	
	public void addLpos(Hashtable<File, PtfsLPO> add_lpos) {
		// TODO Auto-generated method stub
		for(File file : add_lpos.keySet()  ){
			Stack<PtfsLPO> lpo_stack = new Stack<PtfsLPO>();
			lpo_stack.push( add_lpos.get(file)  );
//			lpos.put(file, lpo_stack);
//			lpos_redo.put(file, new Stack<PtfsLPO>());
		}
		addColumnsToMenuLpo(add_lpos);
		addClosableTabsByLpos(add_lpos);
	}
	
//	public void removeLpo(File file) {
//		lpos.remove(file);
//		lpos_redo.remove(file);
//
//		if( lpos.isEmpty() ){
//			actual_lpo_file = null;
//		}
//		// TODO Auto-generated method stub
//
//	}

	public void addPnets(Hashtable<File, PflowPetriNet> add_pnets) {		
		for(File file : add_pnets.keySet() ){
			pnets.put(file, add_pnets.get(file));		
		}		
		addColumnsToMenuPetri(add_pnets);
	}
	
	private void addColumnsToMenuPetri(Hashtable<File, PflowPetriNet> pnets2) {
		mainBar.addColumnsToMenuPetri(pnets2);		
	}

	public boolean isFileInLoadedPNets(File file){		
		for(File fileInPnets : pnets.keySet()  ){
			if( fileInPnets.getAbsolutePath().equals(file.getAbsolutePath())  ){
				return true;				
			}
		}
		return false;
	}
	
	public boolean isFileInLoadedLpos(File file){
//		for(File fileInPnets : lpos.keySet()  ){
//			if( fileInPnets.getAbsolutePath().equals(file.getAbsolutePath())  ){
//				return true;
//			}
//		}
		return false;
	}
	
	
	// getters and setters
	public File getActualLpoFile() {
		return actual_lpo_file;
	}

	public boolean isGraphCircle() {
		return graphCircle;
	}

	public void setGraphCircle(boolean graphCircle) {
		this.graphCircle = graphCircle;
	}

	public File getActualPetriFile() {
		return actual_petri_file;
	}

	public void setActualPetriFile(File actual_petri_file) {
		this.actual_petri_file = actual_petri_file;
	}

	public void setActualLpoFile(File actual_file) {
		this.actual_lpo_file = actual_file;
	}	
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(LPOtabpane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
	// private funkcie
	private void addClosableTabsByLpos(Hashtable<File, PtfsLPO> lpos) {
		for(File file : lpos.keySet() ){
			tabbedPane.addTab(this, file, lpos.get(file));
		}
	}
	
	private void addColumnsToMenuLpo(Hashtable<File, PtfsLPO> pnets2) {
		mainBar.addColumnsToMenuLpo(pnets2);		
	}

    public DataHandler getDataHandler() {
        return dataHandler;
    }

    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
