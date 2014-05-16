package drawing;

import analyz.PtfsLPO;
import design.MainFrame;
import diplomovka.Command;
import diplomovka.Enums;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import analyz.PtfsVertex;
import design.MainToolbar;
import enums.MainToolbarButtonChoices;

/**
 *
 * @author Radovan Martis 55956 24.03.2013
 */
@SuppressWarnings("serial")
public class MyCanvas extends JPanel{

    private MainFrame mainFrame;
    private PtfsLPO lpo;
    private File file;
    private Command command;
    private Color foreGroundColor = Color.BLACK;
    private Color backGroundColor = Color.WHITE;
    private List<Feature> features = new ArrayList<Feature>();
    
    // nastavenie toho aby sa prvky nemohli premiestnovat
    private boolean elementsFreez = false;
    private boolean elementsMoving = false;
    private boolean vertexResize = false;
    private boolean canvasReposition = false;
//    MainToolbarButtonChoices buttons;
    public MyCanvas(){}
    
    

	public MyCanvas(File file, MainFrame mainFrame, PtfsLPO lpo){
        this.mainFrame = mainFrame;        
        
        this.file = file;
        this.lpo = lpo;

        command = new Command(mainFrame, this);
        
        features.add(new FeatureCanvasReposition(this));
        features.add(new FeatureEdge(this));	
        features.add(new FeatureElementsSelect(this));
		features.add(new FeatureElementMoving(this));
		features.add(new FeatureVertex(this));
		features.add(new FeatureCursorChanger(this));
		
    }

    @Override
    public void paint(Graphics g) {
            this.setBackground(backGroundColor);   
            
            super.paint(g);
            
//            this.lpo = mainFrame.getLpoByFileWithoutRemoving(file);
            
            for (Feature feature : features) {
    			feature.realoadLpo();
    		}
            
            for (Feature feature : features) {
    			feature.paintBackground(g);
    		}

    		for (Feature feature : features) {
    			feature.paintMainLayer(g);
    		}
    		
    		for (Feature feature : features) {
    			feature.paintForegroud(g);
    		}
    }
    
    public PtfsVertex kliknutyUzol(MouseEvent e) {
		// Iteracia cez vsetky prvky pola
		for (PtfsVertex prvok : lpo.getPtfsvrcholi() ) {
				if ( prvok.contains( e.getPoint() ) ) {
					return prvok;
				}
		}
		return null;
	}
    
    public PtfsVertex kliknutyUzolOkolie(MouseEvent e){
    	for (PtfsVertex prvok : lpo.getPtfsvrcholi() ) {
			if ( prvok.contains( e.getPoint() ) ) {
				return prvok;
			}
		}
    	
    	for( PtfsVertex vertex : lpo.getPtfsvrcholi() ){
    		if( vertex.isOnLeftOrRight( this.getMousePosition() )  ){    			
    			return vertex;
    		}	
    	}
    	
		return null;    	
    }

    public boolean isLeftMouseButton(MouseEvent e){
    	if( e.getButton() == MouseEvent.BUTTON1 ){
    		return true;    		
    	}
    	return false;    	
    }
    
    public boolean isRightMouseButton(MouseEvent e){
    	if( e.getButton() == MouseEvent.BUTTON3 ){
    		return true;    		
    	}
    	return false;    	
    }
    
    public boolean isButtonSelectMooreSelected(){
    	if( MainToolbar.buttons == MainToolbarButtonChoices.SELECT_MORE ){
    		return true;    		
    	}
    	return false;
    }
    
    public boolean isButtonVertexSelected(){
    	if( MainToolbar.buttons == MainToolbarButtonChoices.VERTEX ){
    		return true;    		
    	}
    	return false;
    }

    public boolean isButtonEdgeSelected(){
    	if( MainToolbar.buttons == MainToolbarButtonChoices.EDGE ){
    		return true;    		
    	}
    	return false;
    }
    
    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public Color getForeGroundColor() {
        return foreGroundColor;
    }

    public void setForeGroundColor(Color foreGroundColor) {
        this.foreGroundColor = foreGroundColor;
    }

    public PtfsLPO getLpo() {
		return lpo;
	}

	public void setLpo(PtfsLPO lpo) {
		this.lpo = lpo;
	}

	public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
	public boolean isElementsFreez() {
		return elementsFreez;
	}

	public void setElementsFreez(boolean elementsFreez) {
		this.elementsFreez = elementsFreez;
	}
	
	public boolean isElementsMoving() {
		return elementsMoving;
	}

	public void setElementsMoving(boolean elementsMoving) {
		this.elementsMoving = elementsMoving;
	}

	public boolean isVertexResize() {
		return vertexResize;
	}

	public void setVertexResize(boolean vertexResize) {
		this.vertexResize = vertexResize;
	}
	
	public boolean isCanvasReposition() {
		return canvasReposition;
	}

	public void setCanvasReposition(boolean canvasReposition) {
		this.canvasReposition = canvasReposition;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
}