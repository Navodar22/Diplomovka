package drawing;


import analyz.PtfsEdge;
import analyz.PtfsVertex;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


public class FeatureElementsSelect extends Feature {
		
	private Point start,last;
	private boolean selectMoore = false;
	
	private Rectangle2D.Double selRect = null;
	BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
	BasicStroke.JOIN_BEVEL, 1, new float[]{5, 5}, 0);
	
	public FeatureElementsSelect(MyCanvas canvas) {
		super(canvas);
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {

		PtfsVertex pressed_vertex = canvas.kliknutyUzolOkolie(e);
		
		if ( isSelectOneVertexCondition(e, pressed_vertex) ){
			lpo.clearSelectedVertexes();
			lpo.clearSelectedEdges();
			lpo.addToSelectedVertexes(pressed_vertex);
		} else if ( isSelectOneEdgeCondition(e, pressed_vertex) ){
			lpo.clearSelectedEdges();			
			addEdgesToSelect(canvas.getMousePosition());
		} else if( isSelectMooreCondition(e, pressed_vertex) ){
			lpo.clearSelectedVertexes();
			lpo.clearSelectedEdges();			
			addEdgesToSelect(canvas.getMousePosition());			
			start = e.getPoint();
			selectMoore = true;
		}else{
			
		}
		
		canvas.repaint();
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(!selectMoore || canvas.isCanvasReposition() ){
			return;			
		}
		
		canvas.setElementsFreez(true);	
		
		lpo.clearSelectedVertexes();
		lpo.clearSelectedEdges();
		
		last = e.getPoint();

		if (selRect == null) {			
			selRect = new Rectangle2D.Double(Math.min(start.x, last.x),
					Math.min(start.x, last.y), Math.abs(start.x - last.x),
					Math.abs(start.y - last.y));
		} else {
			selRect.x = Math.min(start.x, last.x);
			selRect.y = Math.min(start.y, last.y);
			selRect.width = Math.abs(start.x - last.x);
			selRect.height = Math.abs(start.y - last.y);
		}
		
		addVertexesToSelect();
		addEdgesToSelect();
		
		canvas.repaint();
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		selRect = null;
		canvas.setElementsFreez(false);
		selectMoore = false;		
		// TODO Auto-generated method stub		
	}
	
	private boolean isSelectOneVertexCondition(MouseEvent e, PtfsVertex pressed_vertex){		
		if (	( canvas.isLeftMouseButton(e) || canvas.isRightMouseButton(e) )
				&& (
						canvas.isButtonVertexSelected() 
						|| canvas.isButtonSelectMooreSelected()
					)
				&& pressed_vertex != null
				&& !lpo.isFromSelectedVertexes(pressed_vertex) ){
			return true;
		}		
		
		return false;
	}
	
	private boolean isSelectOneEdgeCondition(MouseEvent e, PtfsVertex pressed_vertex){
		if( ( canvas.isLeftMouseButton(e)  )
			&& (
					canvas.isButtonEdgeSelected()					
				)
			&& pressed_vertex == null ){
			return true;
		}
		
		return false;
	}
	
	private boolean isSelectMooreCondition(MouseEvent e, PtfsVertex pressed_vertex){
		if( canvas.isLeftMouseButton(e) 
				&& canvas.isButtonSelectMooreSelected()
				&& pressed_vertex == null ){
			return true;
		}
		return false;
	}
	
	private void addEdgesToSelect(){
		for(PtfsEdge edge : lpo.getPtfshrany() ){
			Double x1 = edge.getSource().getCenterX();
			Double y1 = edge.getSource().getCenterY();
			Double x2 = edge.getDestination().getCenterX();
			Double y2 = edge.getDestination().getCenterY();
			
			if( selRect.intersectsLine(x1, y1, x2, y2) ){
				lpo.addToSelectedEdges(edge);								
			}
		}
	}
	
	private void addVertexesToSelect(){
		for(PtfsVertex vertex : lpo.getPtfsvrcholi() ){
			if( selRect.contains(vertex.getCenterX(),vertex.getCenterY() ) ){
				lpo.addToSelectedVertexes(vertex);								
			}
		}
	}
	
	private void addEdgesToSelect(Point point){
		for(PtfsEdge edge : lpo.getPtfshrany()){
			if( edge.isOnEdge(point) ){
				lpo.addToSelectedEdges(edge);
			}
		}
	}
	
	public void paintForegroud(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		if(selRect != null){
			Stroke default_stroke = g2D.getStroke();
			g2D.setStroke(stroke);
			g2D.draw(selRect);	
			g2D.setStroke(default_stroke);
		}
	}
		
}
