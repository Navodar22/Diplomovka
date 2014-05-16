package drawing;


import analyz.PtfsEdge;
import analyz.PtfsVertex;
import design.PopUpMenuEdge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;


public class FeatureEdge extends Feature{
	
	private PtfsVertex startElement;
	private PtfsEdge helpEdge;
	
	private Point endPoint;
	
	private boolean edgeDrawing;
	
	public FeatureEdge(MyCanvas canvas) {
		super(canvas);		
	}
	
	/*
	 * 
	 * @see Feature#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {		
		if ( canvas.isLeftMouseButton(e) && canvas.isButtonEdgeSelected() ) {
			lpo.clearSelectedVertexes();
			lpo.clearSelectedEdges();
			startElement = canvas.kliknutyUzol(e);
			if (startElement != null) {
				edgeDrawing = true;
				helpEdge = new PtfsEdge();
				helpEdge.setSource(startElement);
				endPoint = e.getPoint();
				canvas.repaint();
			}
		} else if( canvas.isRightMouseButton(e) && 
				(canvas.isButtonEdgeSelected() || canvas.isButtonSelectMooreSelected() )
				&& !lpo.getMouseoverEdges().isEmpty()){
			lpo.getSelectEdges().addAll(lpo.getMouseoverEdges());
			new PopUpMenuEdge(e, canvas.getMainFrame() );
		}
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (!edgeDrawing) {
			return;
		}
		
		PtfsVertex endElement = canvas.kliknutyUzol(e);
		if( endElement != null && endElement != startElement ){
			helpEdge.setDestination(endElement);
		}else{
			helpEdge.setDestination(null);
			endPoint = e.getPoint();				
		}
		canvas.repaint();

		// TODO Auto-generated method stub		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if( edgeDrawing && helpEdge.getDestination()!= null && helpEdge.getSource() != null ){
			command.addEdge(canvas.getLpo() ,helpEdge);
//			lpo.addEdge(helpEdge);
			
		}
		edgeDrawing = false;
		helpEdge = null;
		canvas.repaint();
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e){
		if( !canvas.isButtonEdgeSelected() && !canvas.isButtonSelectMooreSelected() ){
			return;
		}
		
		lpo.clearMouseOverEdges();
		canvas.repaint();
		for( PtfsEdge edge : lpo.getPtfshrany() ){
			if( edge.isOnEdge(e.getPoint()) ){
				lpo.addToMouseOverEdges(edge);								
			}					
		}		
	}
	
	@Override
	public void paintBackground(Graphics g) {
		for( PtfsEdge edge : lpo.getPtfshrany() ){
    		edge.color = Color.BLACK;      
    	}
    	
    	for(PtfsEdge edge : lpo.getSelectEdges() ){
    		edge.color = Color.GREEN;
		}
    	
    	for(PtfsEdge edge : lpo.getMouseoverEdges() ){
    		edge.color = Color.BLUE;    		
    	}
    	
    	for( PtfsEdge edge : lpo.getPtfshrany() ){
    		edge.draw(g);        
    	}
		
		if (edgeDrawing) {
			if(helpEdge.getDestination() == null){
				helpEdge.drawByEndPoint(g, endPoint);
			}else{
				helpEdge.draw(g);
			}
		}
	}
	
	
	

}
