/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import java.io.File;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.filechooser.*;

/**
48   * A convenience implementation of FileFilter that filters out
49   * all files except for those type extensions that it knows about.
50   *
51   * Extensions are of the type ".foo", which is typically found on
52   * Windows and Unix boxes, but not on Macinthosh. Case is ignored.
53   *
54   * Example - create a new filter that filerts out all files
55   * but gif and jpg image files:
56   *
57   *     JFileChooser chooser = new JFileChooser();
58   *     ExampleFileFilter filter = new ExampleFileFilter(
59   *                   new String{"gif", "jpg"}, "JPEG & GIF Images")
60   *     chooser.addChoosableFileFilter(filter);
61   *     chooser.showOpenDialog(this);
62   *
63   * @version 1.6 01/23/03
64   * @author Jeff Dinkins
65   */
public class ExampleFileFilterAction extends FileFilter {

    private static String TYPE_UNKNOWN = "Type Unknown";
    private static String HIDDEN_FILE = "Hidden File";
    private Hashtable filters = null;
    private String description = null;
    private String fullDescription = null;
    private boolean useExtensionsInDescription = true;

    /**
    77       * Creates a file filter. If no filters are added, then all
    78       * files are accepted.
    79       *
    80       * @see #addExtension
    81       */
    public ExampleFileFilterAction() {
        this.filters = new Hashtable();
    }

    /**
    87       * Creates a file filter that accepts files with the given extension.
    88       * Example: new ExampleFileFilter("jpg");
    89       *
    90       * @see #addExtension
    91       */
    public ExampleFileFilterAction(String extension) {
        this(extension, null);
    }

    /**
    97       * Creates a file filter that accepts the given file type.
    98       * Example: new ExampleFileFilter("jpg", "JPEG Image Images");
    99       *
    100      * Note that the "." before the extension is not needed. If
    101      * provided, it will be ignored.
    102      *
    103      * @see #addExtension
    104      */
    public ExampleFileFilterAction(String extension, String description) {
        this();
        if (extension != null) {
            addExtension(extension);
        }
        if (description != null) {
            setDescription(description);
        }
    }

    /**
    112      * Creates a file filter from the given string array.
    113      * Example: new ExampleFileFilter(String {"gif", "jpg"});
    114      *
    115      * Note that the "." before the extension is not needed adn
    116      * will be ignored.
    117      *
    118      * @see #addExtension
    119      */
    public ExampleFileFilterAction(String[] filters) {
        this(filters, null);
    }

    /**
    125      * Creates a file filter from the given string array and description.
    126      * Example: new ExampleFileFilter(String {"gif", "jpg"}, "Gif and JPG Images");
    127      *
    128      * Note that the "." before the extension is not needed and will be ignored.
    129      *
    130      * @see #addExtension
    131      */
    public ExampleFileFilterAction(String[] filters, String description) {
        this();
        for (int i = 0; i < filters.length; i++) {
            // add filters one by one
            addExtension(filters[i]);
        }
        if (description != null) {
            setDescription(description);
        }
    }

    /**
    142      * Return true if this file should be shown in the directory pane,
    143      * false if it shouldn't.
    144      *
    145      * Files that begin with "." are ignored.
    146      *
    147      * @see #getExtension
    148      * @see FileFilter#accepts
    149      */
    public boolean accept(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                return true;
            }
            String extension = getExtension(f);
            if (extension != null && filters.get(getExtension(f)) != null) {
                return true;
            }
        }
        return false;
    }

    /**
    164      * Return the extension portion of the file's name .
    165      *
    166      * @see #getExtension
    167      * @see FileFilter#accept
    168      */
    public String getExtension(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
        }
        return null;
    }

    /**
    181      * Adds a filetype "dot" extension to filter against.
    182      *
    183      * For example: the following code will create a filter that filters
    184      * out all files except those that end in ".jpg" and ".tif":
    185      *
    186      *   ExampleFileFilter filter = new ExampleFileFilter();
    187      *   filter.addExtension("jpg");
    188      *   filter.addExtension("tif");
    189      *
    190      * Note that the "." before the extension is not needed and will be ignored.
    191      */
    public void addExtension(String extension) {
        if (filters == null) {
            filters = new Hashtable(5);
        }
        filters.put(extension.toLowerCase(), this);
        fullDescription = null;
    }

    /**
    202      * Returns the human readable description of this filter. For
    203      * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
    204      *
    205      * @see setDescription
    206      * @see setExtensionListInDescription
    207      * @see isExtensionListInDescription
    208      * @see FileFilter#getDescription
    209      */
    public String getDescription() {
        if (fullDescription == null) {
            if (description == null || isExtensionListInDescription()) {
                fullDescription = description == null ? "(" : description + " (";
                // build the description from the extension list
                Enumeration extensions = filters.keys();
                if (extensions != null) {
                    fullDescription += "." + (String) extensions.nextElement();
                    while (extensions.hasMoreElements()) {
                        fullDescription += ", ." + (String) extensions.nextElement();
                    }
                }
                fullDescription += ")";
            } else {
                fullDescription = description;
            }
        }
        return fullDescription;
    }

    /**
    231      * Sets the human readable description of this filter. For
    232      * example: filter.setDescription("Gif and JPG Images");
    233      *
    234      * @see setDescription
    235      * @see setExtensionListInDescription
    236      * @see isExtensionListInDescription
    237      */
    public void setDescription(String description) {
        this.description = description;
        fullDescription = null;
    }

    /**
    244      * Determines whether the extension list (.jpg, .gif, etc) should
    245      * show up in the human readable description.
    246      *
    247      * Only relevent if a description was provided in the constructor
    248      * or using setDescription();
    249      *
    250      * @see getDescription
    251      * @see setDescription
    252      * @see isExtensionListInDescription
    253      */
    public void setExtensionListInDescription(boolean b) {
        useExtensionsInDescription = b;
        fullDescription = null;
    }

    /**
    260      * Returns whether the extension list (.jpg, .gif, etc) should
    261      * show up in the human readable description.
    262      *
    263      * Only relevent if a description was provided in the constructor
    264      * or using setDescription();
    265      *
    266      * @see getDescription
    267      * @see setDescription
    268      * @see setExtensionListInDescription
    269      */
    public boolean isExtensionListInDescription() {
        return useExtensionsInDescription;
    }
}
