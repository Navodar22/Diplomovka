package loader;

import actions.ExampleFileFilterAction;
import actions.LoadDataAction;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Created by Navodar on 4/19/14.
 */
public class FileChooserLoad extends JFileChooser {

    private JFileChooser chooser;

    public FileChooserLoad() {
        Preferences preferences = Preferences.userNodeForPackage(LoadDataAction.class);
        String dir = preferences.get("adresar", ".");
        File currentDirectory = new File(dir);
        chooser = new JFileChooser(currentDirectory);
        chooser.setMultiSelectionEnabled(true);
        ExampleFileFilterAction filter = new ExampleFileFilterAction();
        filter.addExtension("ptfs");
        filter.addExtension("pflow");
        filter.setDescription("Petri Net's and LPO's XML's");
        chooser.setFileFilter(filter);

    }

    public List<File> getFiles() {
        List<File> files = null;
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File[] selected_files = chooser.getSelectedFiles();
            files = new ArrayList<File>();
            for (File file : selected_files) {
                files.add(file);
            }
            return files;
        }
        return null;
    }

}
