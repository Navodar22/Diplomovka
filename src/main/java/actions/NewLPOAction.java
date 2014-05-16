package actions;

import analyz.PtfsEdge;
import analyz.PtfsLPO;
import analyz.PtfsVertex;
import design.MainFrame;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeSet;

import javax.swing.Icon;
import javax.swing.KeyStroke;

public class NewLPOAction extends MyAction {

    public NewLPOAction( Icon icon, MainFrame mainframe ) {
    	super(icon, mainframe);
        this.putValue(NAME, "New");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
        this.putValue(SHORT_DESCRIPTION, "New LPO");
    }

    private int index = 1;
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("new");
        
    	String tDir = System.getProperty("java.io.tmpdir");
    	File tempFile = new File(tDir + "Untitled" + index + ".tmp_ptfs");
    	
    	index++;
    	
		PtfsLPO lpo = new PtfsLPO(new TreeSet<PtfsVertex>(), new HashSet<PtfsEdge>());
        Hashtable<File, PtfsLPO> lpos = new Hashtable<File, PtfsLPO>();
        lpos.put(tempFile, lpo);
//        System.out.println(tempFile);
        mainFrame.addLpos(lpos);	
    }
}
