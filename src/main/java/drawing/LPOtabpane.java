/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import analyz.PtfsLPO;
import design.MainFrame;
import diplomovka.Icons;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import design.MainFrameMenuBar;
import loader.FileOperations;

/**
 * @author Navodar
 */
public class LPOtabpane extends JTabbedPane {

    private MainFrameMenuBar mainBar;
    private MainFrame mainFrame;
    private MyCanvas canvas = null;
    JPanel pnlTab = null;

    public LPOtabpane(MainFrameMenuBar mainBar) {
        super();
        this.mainBar = mainBar;
    }


    public MyCanvas getCanvas() {
        return canvas;
    }


    public void addTab(MainFrame mainFrame, final File file, PtfsLPO lpo) {
        this.mainFrame = mainFrame;

        canvas = new MyCanvas(file, mainFrame, lpo);
        mainFrame.setCommand(canvas.getCommand());

        this.addTab(null, canvas);
        int pos = this.indexOfComponent(canvas);

        // Create a FlowLayout that will space things 5px apart
        FlowLayout f = new FlowLayout(FlowLayout.CENTER, 5, 0);
        pnlTab = new JPanel(f);
        pnlTab.setOpaque(false);
        pnlTab.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        this.setTabComponentAt(pos, pnlTab);
        this.setSelectedComponent(canvas);

        mainFrame.setActualLpoFile(file);
        addChangeListener(this, canvas, file);
        setLPOTabPaneTitle(file);
        addCloseButton(file);
    }

    private void addCloseButtonListener(JButton btnClose, final MyCanvas canvas, final File file) {

        // Add the listener that removes the tab
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // The component parameter must be declared "final" so that it can be
                // referenced in the anonymous listener class like this.
                removeTab(canvas, file);
            }
        };
        btnClose.addActionListener(listener);
    }

    private void addChangeListener(final LPOtabpane pane, final MyCanvas canvas, final File file) {

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();

                int index = sourceTabbedPane.getSelectedIndex();
                int pos = pane.indexOfComponent(canvas);

                if (pos == index) {
                    mainFrame.setActualLpoFile(file);
                    mainFrame.setCommand(canvas.getCommand());
                }
            }
        };
        this.addChangeListener(changeListener);
    }

    private void setLPOTabPaneTitle(File file) {
        JLabel lblTitle = null;

        if (FileOperations.isTempFile(file)) {
            lblTitle = new JLabel(FileOperations.getName(file));
        } else {
            lblTitle = new JLabel(file.getName());
        }

        pnlTab.add(lblTitle);
    }

    private void addCloseButton(File file) {
        JButton btnClose = new JButton();
        btnClose.setOpaque(false);

        // Configure icon and rollover icon for button
        btnClose.setRolloverIcon(Icons.CLOSE_TAB_ICON);
        btnClose.setRolloverEnabled(true);
        btnClose.setIcon(Icons.CLOSE_TAB_ICON);

        // Set border null so the button doesn't make the tab too big
        btnClose.setBorder(null);

        // Make sure the button can't get focus, otherwise it looks funny
        btnClose.setFocusable(false);

        pnlTab.add(btnClose);
        addCloseButtonListener(btnClose, canvas, file);
    }

    private void removeTab(JComponent panel, File file) {
        this.remove(panel);
        mainBar.removeMenuLpoColumn(file);
//        mainFrame.removeLpo(file);
    }


}
