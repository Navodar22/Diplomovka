package drawing;

import analyz.PtfsVertex;
import design.PopUpMenuVertex;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;


public class FeatureVertex extends Feature {

	private PtfsVertex vertex_resize;
	
	private boolean startResize;
	
	public FeatureVertex(MyCanvas canvas) {
		super(canvas);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void mousePressed(MouseEvent e) {  
		if( canvas.isCanvasReposition() ){
			return;
		}
		
    	PtfsVertex pressed_vertex = canvas.kliknutyUzolOkolie(e);

    	if( canvas.isLeftMouseButton(e) && canvas.isButtonVertexSelected() 
    			&& pressed_vertex == null ){
		// pridavanie vertexu. Ak lave tlacidlo a tlacidlo z menu je vybrane
    		PtfsVertex vertex = new PtfsVertex(e.getPoint());
    		command.addVertex(canvas.getLpo() ,vertex);
		
    	} else if( canvas.isRightMouseButton(e) && pressed_vertex != null &&
    			!canvas.isButtonEdgeSelected()){
		// resize vertexu. Ak vertex neni null
			new PopUpMenuVertex(e, canvas.getMainFrame() );
			
		} else if ( ( canvas.isRightMouseButton(e) || canvas.isLeftMouseButton(e) ) && pressed_vertex == null ) {
			
		/*
		 *  Ak bolo kliknute prave tlacidlo a nekliklo sa na vertex. 
		 *  Pridane aby dalsia podmienka nehadzala NullPointerException
		 *  
		 */
			
		} else if( pressed_vertex.isOnLeftOrRight( canvas.getMousePosition()) &&
    			!canvas.isButtonEdgeSelected() ){
		// ak sa kliklo na vertex na jeho kraje.
			System.out.println("zmena velkosti"); 
			vertex_resize = pressed_vertex;
			canvas.setVertexResize(true);
		} else {
//			System.out.println("No action to vertex");
		}
    	
    	canvas.repaint();
    	
    }
	
	@Override
	public void mouseDragged(MouseEvent e){
		if( !canvas.isVertexResize() ){
			return;			
		}
		
		if( !startResize ){
			startResize = true;
			command.startsResizing();
		}
		
		Point current_point = canvas.getMousePosition();
		vertex_resize.resize(current_point);
		
		canvas.repaint();
	}
	
	
	@Override
    public void mouseMoved(MouseEvent e) {
    	if( !canvas.isButtonVertexSelected() && !canvas.isButtonSelectMooreSelected() ){
    		return;  		
    	}
    	lpo.clearMouseOverVertexes();
    	canvas.repaint();
    	for( PtfsVertex vertex : lpo.getPtfsvrcholi() ){
    		if( vertex.contains(canvas.getMousePosition() )  ){
    			lpo.addToMouseOverVertexes(vertex);
    			return;
    		}		
    	}   	   	
    	
    	canvas.repaint();
    	
    }
	
	@Override
	public void mouseReleased(MouseEvent e){
		canvas.setVertexResize(false);
		vertex_resize = null;
		startResize = false;
	}
	
	
	public void paintMainLayer(Graphics g) {
		
		for( PtfsVertex vertex : lpo.getPtfsvrcholi() ){
    		vertex.color = Color.BLACK;      
    	}
    	    	
    	for( PtfsVertex vertex : lpo.getPtfsvrcholi() ){
    		vertex.draw( g );        
    	}
	}
	
	public void paintForegroud(Graphics g) {
		
		
		for(PtfsVertex vertex_mouseover : lpo.getMouseoverVertexes() ){
    		vertex_mouseover.color = Color.BLUE; 
    		vertex_mouseover.draw(g);  
    	}
		
		for(PtfsVertex vertex_select : lpo.getSelectVertexes() ){
    		vertex_select.color = Color.GREEN;
    		vertex_select.draw(g);    
		}		
	}

}
