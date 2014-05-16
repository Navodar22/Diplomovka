package design;

import diplomovka.Enums;
import diplomovka.Icons;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import actions.DeleteAction;
import actions.DinicAction;
import actions.LoadDataAction;
import actions.MenuLpoAction;
import actions.MenuPetriAction;
import actions.NewLPOAction;
import actions.PreFlowPushAction;
import actions.QuitAction;
import actions.RedoAction;
import actions.SaveAsLPOAction;
import actions.SaveLPOAction;
import actions.TestRunAction;
import actions.UndoAction;
import analyz.PflowPetriNet;
import analyz.PtfsLPO;
import diplomovka.AlgorithmMenuStatus;
import enums.MaxFlowAlgorithmChoices;

@SuppressWarnings("serial")
public class MainFrameMenuBar extends JMenuBar {
	
	private MainFrame mainFrame;
	private MenuPetri petriMenu;
	private MenuAlgorithmSelect lpoMenu;
	private MenuAlgorithmSelect algMenu;
	
	private HashMap<File, JCheckBoxMenuItem> menuLpoColumns;
	

	public MainFrameMenuBar(MainFrame mainframe) {
		this.mainFrame = mainframe;
		menuLpoColumns = new HashMap<File, JCheckBoxMenuItem>();
		
		JMenuBar menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new NewLPOAction(Icons.NEW_SMALL, mainFrame));
		fileMenu.addSeparator();
		fileMenu.add(new LoadDataAction(Icons.OPEN_SMALL, mainFrame));
		fileMenu.addSeparator();
		fileMenu.add(new SaveLPOAction(Icons.SAVE_SMALL, mainFrame));
		fileMenu.add(new SaveAsLPOAction(Icons.SAVEAS_SMALL, mainFrame));
//		fileMenu.add(new SaveAllLPOAction(Icons.SAVEALL_SMALL, mainFrame));
		fileMenu.addSeparator();
		fileMenu.add(new QuitAction());

		menuBar.add(fileMenu);

		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);

		editMenu.add(new UndoAction(Icons.UNDO_SMALL, mainFrame));
		editMenu.add(new RedoAction(Icons.REDO_SMALL, mainFrame));
		editMenu.add(new DeleteAction( Icons.DELETE_SMALL, mainFrame ));

		petriMenu = new MenuPetri("PetriNet's");
		petriMenu.setEmptyMenu();
		menuBar.add(petriMenu);

		lpoMenu = new MenuAlgorithmSelect("LPO's");
		lpoMenu.setEmptyMenu();
//		menuBar.add(lpoMenu);

		
		algMenu = new MenuAlgorithmSelect("Algorithm");
		algMenu.setEmptyMenu();
		algMenu.addColumn( new JCheckBoxMenuItem( new PreFlowPushAction(null, mainframe)) );
		algMenu.addColumn( new JCheckBoxMenuItem( new DinicAction(null, mainframe)) );		
		AlgorithmMenuStatus.status = MaxFlowAlgorithmChoices.DINIC;
		
		menuBar.add(algMenu);
		
		
		JMenu runMenu = new JMenu("Run");
		runMenu.add( new TestRunAction( Icons.RUN_TEST_SMALL, mainFrame ) );		
		menuBar.add(runMenu);
		
		

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

	}
	
	public void addColumnsToMenuPetri(Hashtable<File, PflowPetriNet> pnets2) {
		for(File file : pnets2.keySet() ){
			JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem( new MenuPetriAction(file, mainFrame) );
			petriMenu.addColumn(menuItem);			
		}		
	}
	
	public void addColumnsToMenuLpo(Hashtable<File, PtfsLPO > pnets2) {
		for(File file : pnets2.keySet() ){
			JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem( new MenuLpoAction(file) );
			menuLpoColumns.put(file, menuItem);
			lpoMenu.addColumn(menuItem);			
		}		
	}

	public void removeMenuLpoColumn(File file) {		
		lpoMenu.removeColumn( menuLpoColumns.get(file)  );		
	}

}
