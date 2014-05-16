package actions;


import design.MainFrame;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public class SaveAllLPOAction extends MyAction {


    public SaveAllLPOAction( Icon icon, MainFrame mainframe ) {
    	super(icon, mainframe);
        this.putValue(NAME, "Save All");
        this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift S"));
        this.putValue(SHORT_DESCRIPTION, "Save All");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Save All");
            
    }
}
