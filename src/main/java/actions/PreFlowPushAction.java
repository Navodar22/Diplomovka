package actions;

import java.awt.event.ActionEvent;

import javax.swing.Icon;

import design.MainFrame;
import diplomovka.AlgorithmMenuStatus;
import diplomovka.Command;
import enums.MaxFlowAlgorithmChoices;

/**
 *
 * @author Navodar
 */
public class PreFlowPushAction extends MyAction{

    public PreFlowPushAction(Icon icon, MainFrame mainFrame) {
    	super( icon, mainFrame );
    	this.putValue(NAME, "Preflow Push alg.");
        this.putValue(SHORT_DESCRIPTION, "PreflowPush");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	AlgorithmMenuStatus.status = MaxFlowAlgorithmChoices.PREFLOWPUSH;
    }
    
}
