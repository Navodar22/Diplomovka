package loader;

import analyz.PflowPetriNet;
import analyz.PtfsLPO;
import design.MainFrame;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Navodar on 4/19/14.
 */
public class DataHandlerRestImpl implements DataHandler {

    @Override
    public void loadData(MainFrame mainFrame) {

    }

    @Override
    public Hashtable<File, PtfsLPO> getLpos() {
        return null;
    }

    @Override
    public Hashtable<File, PflowPetriNet> getPetriNets() {
        return null;
    }

    @Override
    public void saveAllLpos(List<File> files) {

    }

    @Override
    public void saveAsLpo(File file) {

    }

    @Override
    public void saveLpo(File file) {

    }
}
