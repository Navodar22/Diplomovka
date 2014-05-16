package actions;

import design.MainFrame;
import loader.DataHandler;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public abstract class MyAction extends AbstractAction{

    protected MainFrame mainFrame;

	public MyAction( Icon icon, MainFrame mainframe ){
		this.mainFrame = mainframe;
		this.putValue(SMALL_ICON, icon );
	}

    public MyAction( Icon icon, MainFrame mainframe, DataHandler dataHandler ){
        this.mainFrame = mainframe;
        this.putValue(SMALL_ICON, icon );
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
