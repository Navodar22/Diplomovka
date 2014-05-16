package loader;


import analyz.PflowPetriNet;
import analyz.PtfsLPO;
import design.MainFrame;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Navodar on 4/19/14.
 */
public class DataHandlerFileImpl implements DataHandler {

    @Override
    public void loadData(MainFrame mainFrame) {
        FileChooserLoad fileChooser = new FileChooserLoad();
        Loadpflow loadPflow = new Loadpflow();
        Loadptfs loadptfs = new Loadptfs();

        List<File> loaded_files = fileChooser.getFiles();

		try {

			Hashtable<File, PtfsLPO > lpos = loadptfs.getLposFromFiles(mainFrame, loaded_files);
			Hashtable<File, PflowPetriNet> pnets = loadPflow.getPflowPnetsFromFiles(mainFrame, loaded_files);

            mainFrame.addLpos( lpos );
            mainFrame.addPnets( pnets );

		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
