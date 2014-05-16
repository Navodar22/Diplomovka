package loader;


import analyz.PflowPetriNet;
import analyz.PtfsLPO;
import design.MainFrame;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Navodar on 4/17/14.
 */
public interface DataHandler {

    public void loadData(MainFrame mainFrame);

    public Hashtable<File, PtfsLPO> getLpos();

    public Hashtable<File, PflowPetriNet> getPetriNets();

    public void saveAllLpos(List<File> files);

    public void saveAsLpo(File file);

    public void saveLpo(File file);

}
