package diplomovka;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;
import org.springframework.core.io.*;

public final class Icons {
    private static final Logger logger = Logger.getLogger(Icons.class.getName());

    public static final Icon UNDO = icon("/icons/Undo24.gif");
    public static final Icon UNDO_SMALL = icon("/icons/Undo16.gif");
    
    public static final Icon REDO = icon("/icons/Redo24.gif");
    public static final Icon REDO_SMALL = icon("/icons/Redo16.gif");

    public static final Icon DELETE = icon("/icons/Delete24.gif");
    public static final Icon DELETE_SMALL = icon("/icons/Delete16.gif");

    public static final Icon NEW = icon("/icons/New24.gif");
    public static final Icon NEW_SMALL = icon("/icons/New16.gif");

    public static final Icon OPEN = icon("/icons/Open24.gif");
    public static final Icon OPEN_SMALL = icon("/icons/Open16.gif");

    public static final Icon SAVE = icon("/icons/Save24.gif");
    public static final Icon SAVE_SMALL = icon("/icons/Save16.gif");

    public static final Icon SAVEAS = icon("/icons/SaveAs24.gif");
    public static final Icon SAVEAS_SMALL = icon("/icons/SaveAs16.gif");

    public static final Icon SAVEALL = icon("/icons/Save24.gif");
    public static final Icon SAVEALL_SMALL = icon("/icons/Save16.gif");

    public static final Icon SETNAME_SMALL = icon("/icons/label.gif");

    public static final Icon EDGE = icon("/icons/arc.gif");
    public static final Icon VERTEX = icon("/icons/vertex.gif");
    public static final Icon SELECT_MOORE = icon("/icons/select.gif");

    public static final Icon CLOSE_TAB_ICON = icon("/icons/closeTabButton.png");

    public static final Icon RUN_TEST_SMALL = icon("/icons/play.png");
    public static final Icon RUN_TEST = icon("/icons/play_big.png");

    public static final Icon GRAPH_CIRCLE = icon("/icons/graph_circle.png");

    public static final Image GRAPH_CIRCLE2 = getImage("/icons/graph_circle.png");
//    public static final Icon EDGE_TRANSP = icon("/java.icons/general/Delete2445twrg.gif");

    private static Icon icon(String path) {
        URL resource = Icons.class.getResource(path);
        if(resource==null) {
            logger.warning("Resource "+path+" does not exist");
            return new ImageIcon();
        }
        return new ImageIcon(resource);
    }

    public static Image getImage(final String pathAndFileName) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        return Toolkit.getDefaultToolkit().getImage(url);
    }


    
}
