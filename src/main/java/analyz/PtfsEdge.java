/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package analyz;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import diplomovka.LineIterator;
import drawing.Element;

/**
 *
 * @author navodar
 */
public class PtfsEdge extends Element implements Cloneable{
    
    private PtfsVertex source;
    private PtfsVertex destination;
    
    private int flow;
    private List<PtfsText> texts;
    
    private final int ARR_WIDTH = 10;
    private final int ARR_SIZE = 15;

    
    public void draw(Graphics g1){
    	Graphics2D g = (Graphics2D) g1.create();
    	double x1 = source.getCenterX();
    	double y1 = source.getCenterY();    	
    	double x2 = destination.getCenterX();    	
    	double y2 = destination.getCenterY();
    	
    	
    	Line2D line = new Line2D.Double(x2,y2,x1,y1);
    	
    	
		List<Point2D> ary = new ArrayList<Point2D>();
		Point2D current;
		for(Iterator<Point2D> iter = new LineIterator(line); iter.hasNext();) {
			current =iter.next();
			ary.add(current);
		}
    	
		Point2D intersect_point = destination.getInterserctWithLine(ary);
    			
        double dx = intersect_point.getX() - x1, dy = intersect_point.getY() - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        drawArrow(g, len);
    }
    
    public void draw(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        drawArrow(g, len);
    }
    
    public void drawByEndPoint(Graphics g1, Point endPoint){
    	
    	Graphics2D g = (Graphics2D) g1.create();
    	double x1 = source.getCenterX();
    	double y1 = source.getCenterY();    
    	int x2 = endPoint.x;
    	int y2 = endPoint.y;    	

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
    	
    	drawArrow(g, len);  	
    	
    }
    
    
	private void drawArrow(Graphics2D g, int len) {
		g.setPaint(color);
		g.drawLine(0, 0, len, 0);
		g.fillPolygon(new int[] { len, len - ARR_SIZE, len - ARR_SIZE + 5,
				len - ARR_SIZE, len }, new int[] { 0, -ARR_WIDTH, 0, ARR_WIDTH,
				0 }, 5);

	}
    
    public boolean isOnEdge( Point point ){
    	double x1 = source.getCenterX();
    	double y1 = source.getCenterY();
    	double x2 = destination.getCenterX();
    	double y2 = destination.getCenterY();
    	double px = point.getX();
    	double py = point.getY();
    	
    	if( Line2D.ptSegDistSq(x1, y1, x2, y2, px, py ) < 30  ){
    		return true;
    	}
    	
    	return false;    	
    }
    
    @Override
    public String toString() {
        return "Source: " + source.getId() + ", " + source.getName() + 
        		", Destination: " + destination.getId() + ", " + destination.getName() + 
        		", flow " + flow + ", texts: " + texts + " \n";
    }    
    
    @Override
    protected PtfsEdge clone() throws CloneNotSupportedException {
        PtfsEdge clone_a = (PtfsEdge) super.clone();

        clone_a.destination = destination;
        clone_a.source = source;
        clone_a.flow = flow;

        return clone_a;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        PtfsEdge hrana = (PtfsEdge) obj;
        if ((hrana.destination == destination) && (hrana.source == source)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.source.getId();
        hash = 41 * hash + this.destination.getId();
        hash = 41 * hash + this.flow;
        hash = 41 * hash + (this.texts != null ? this.texts.hashCode() : 0);
        return hash;
    }
    
    public PtfsEdge(){}
    
    public PtfsEdge(PtfsVertex zdroj, PtfsVertex destinacia, int flow, List<PtfsText> texty){
    	this.source = zdroj;
    	this.destination = destinacia;
        this.flow = flow;
        this.texts = texty;
        this.flow = flow;
    }
    
//    public PtfsEdge(int zdroj, int destinacia, int flow, List<PtfsText> texty){
//    	this.source = new PtfsVertex();
//    	this.destination = new PtfsVertex();
//        this.source.setId(zdroj);
//        this.destination.setId(destinacia);
//        this.flow = flow;
//        this.texts = texty;
//        this.flow = flow;
//    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
    
    
    public PtfsEdge(int zdroj, int destinacia){
        this.destination.setId(destinacia);
        this.source.setId(zdroj);
    }

    public int getDestinationId() {
        return destination.getId();
    }

    public void setDestinationId(int destination) {
        this.destination.setId(destination) ;
    }

    public int getSourceId() {
        return source.getId();
    }

    public void setSourceId(int source) {
        this.source.setId(source);
    }

    public List<PtfsText> getTexts() {
        return texts;
    }

    public void setTexts(List<PtfsText> texts) {
        this.texts = texts;
    }

	public PtfsVertex getSource() {
		return source;
	}

	public void setSource(PtfsVertex source) {
		this.source = source;
	}

	public PtfsVertex getDestination() {
		return destination;
	}

	public void setDestination(PtfsVertex destination) {
		this.destination = destination;
	}

	
    
    

    

}
