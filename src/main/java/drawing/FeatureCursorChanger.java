package drawing;


import analyz.PtfsVertex;
import diplomovka.Icons;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;


public class FeatureCursorChanger extends Feature {
	
	private PtfsVertex cursor_vertex = null;

	public FeatureCursorChanger(MyCanvas canvas) {
		super(canvas);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void mouseMoved(MouseEvent e) {
		cursor_vertex = null;
		if( isOnVertexEdges() && !canvas.isButtonEdgeSelected() ){
			canvas.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
		} else if( ( isOnVertex() && !canvas.isButtonEdgeSelected() ) ){
			canvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)); 
		}else if ( canvas.isButtonEdgeSelected() ){
			URL resource = Icons.class.getResource("/main/resources/icons/arc_big.gif");
			
			Toolkit tk = Toolkit.getDefaultToolkit();
	    	Image cursorImage = tk.getImage(resource);   
//	    	Image cursorImage = tk.getImage("/java.icons/play_big.png");
	    	
	    	Cursor cursor = tk.createCustomCursor(cursorImage, new Point(0,0), "Custom Cursor");    	
	    	canvas.setCursor(cursor);			
		}else if ( canvas.isButtonVertexSelected() ){
			BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

			// Create a new blank cursor.
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			    cursorImg, new Point(0, 0), "blank cursor");
			canvas.setCursor(blankCursor);	
			
			cursor_vertex = new PtfsVertex(canvas.getMousePosition());
			
		}else{
			canvas.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
		}		
    	canvas.repaint();    	
    }
	
	
	@Override
	public void mouseReleased(MouseEvent e){
		PtfsVertex vertex = canvas.kliknutyUzol(e);
		if( vertex != null &&  !canvas.isButtonEdgeSelected() ){
			cursor_vertex = null;
			canvas.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));			
		}
		canvas.repaint();  
	}
	
	public void paintForegroud(Graphics g) {
		if( cursor_vertex != null ){
			cursor_vertex.draw(g);
		}
	}
	
	private boolean isOnVertexEdges(){
		for( PtfsVertex vertex : lpo.getPtfsvrcholi() ){
    		if( vertex.isOnLeftOrRight( canvas.getMousePosition() )  ){ 
    			return true;
    		}
    	}
		
		return false;
	}
	
	private boolean isOnVertex(){
		for( PtfsVertex vertex : lpo.getPtfsvrcholi() ){    		
    		if( vertex.contains(canvas.getMousePosition() )  ){    			
    			return true;
    		}		
    	}
		return false;
	}
	

}
