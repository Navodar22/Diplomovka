/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyz;

import drawing.Element;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author navodar
 */
public class PtfsVertex extends Element implements Comparable<Object>, Comparator, Cloneable  {

    private int id;
    private int index;
    private double center_x;
    private double center_y;
    private String name = "";
    private Boolean isinput = false;
    private Boolean isoutput = false;
    private List<PtfsText> texty;
    private Font font = new Font("Tahoma", Font.BOLD, 14);
//    private RoundRectangle2D rectangle;
    
    private double min_width = 60;
    private double height = 30;
    
    private double width;
    
    
//    public PtfsVertex() {
//    }

    public PtfsVertex(int id, Double x, Double y, String name, Double width, boolean IsInput, boolean IsOutput) {
        this.id = id;
        this.center_x = x;
        this.center_y = y;
        this.name = name;
        if( width > min_width ){
        	this.width = width;
        }else{
        	this.width = min_width;
        }
        this.isinput = IsInput;
        this.isoutput = IsOutput;
        
    }
    
    public void resize(Point position){
    	if(position == null){
    		return;
    	}
    	    	
    	// zistujeme ci sa rozsiruje prava alebo lava strana
    	if( position.x > center_x ){
    		
    		double left_x = center_x - (this.width / 2);
    		
    		double width_change = position.x - left_x;
    		
    		if( width_change < min_width  ){
				return;
			}
    		
    		RoundRectangle2D rectange = new RoundRectangle2D.Double(
    				left_x, 
    				center_y - (height / 2), 
    				width_change, height, 8, 8);

    		center_x = rectange.getCenterX();
    		this.width = rectange.getWidth();
    		
    	}else{
    		double left_x = position.x;
    		
    		double width_change = (center_x + (this.width / 2) ) - left_x;
    		
    		if( width_change < min_width  ){
				return;
			}
    		
    		RoundRectangle2D rectange = new RoundRectangle2D.Double(
    				left_x, 
    				center_y - (height / 2), 
    				width_change, height, 8, 8);

    		center_x = rectange.getCenterX();
    		this.width = rectange.getWidth();
    	}
    	
    }
    
    public boolean contains( Point point ){
    	if(point == null){
    		return false;
    	}
    	RoundRectangle2D rectangle = getRectangle();
    	if(rectangle.contains( (Point2D)  point) ){
    		return true;
    	}
    	return false;
    }

    public PtfsVertex(Point point) {
        if(point == null){
            return;
        }
    	center_x = point.x;
    	center_y = point.y;
    	this.width = min_width;		
	}

	public boolean containsPoint( Point2D point ){
    	RoundRectangle2D rectangle = getRectangle();
    	if ( rectangle.contains(point) ){
    		return true;
    	}
    	return false;
    }
    
    public Point2D getInterserctWithLine(List<Point2D> line){
    	RoundRectangle2D rectangle = getRectangle();
    	Point2D previous = line.get(0);
    	for(Point2D current : line ){
    		
    		if( !rectangle.contains(current) ){
    			return previous;
    		}
    		previous = current;
    	}
    	
    	return line.get(0);    	
    }
    
    public boolean isOnLeftOrRight(Point mousePosition) {
    	if( mousePosition == null ){
    		return false;
    	}
    	
    	RoundRectangle2D rectangle = getRectangle();
    	
    	double edge_accuracy = 10;
    	
//    	if( rectangle.contains(mousePosition) ){
    	if( rectangle.getY() < mousePosition.getY() && ( rectangle.getY() + rectangle.getHeight() > mousePosition.getY()  )    ){
    		double left_side = rectangle.getX();
    		double right_side = rectangle.getX() + rectangle.getWidth();
    		
    		if( left_side - edge_accuracy < mousePosition.getX() && mousePosition.getX() < left_side + edge_accuracy ){
    			return true;
    		} 	
    		
    		if( right_side - edge_accuracy < mousePosition.getX() && mousePosition.getX() < right_side + edge_accuracy ){
    			return true;
    		} 	
    		
    	}
    	
		// TODO Auto-generated method stub
		return false;
	}
    
    @Override
    public void draw(Graphics g) {
    	Graphics2D graphics2 = (Graphics2D) g;
    	RoundRectangle2D rectangle = getRectangle();
    	
        graphics2.setPaint(color_bcg);
        graphics2.fill(rectangle);
        graphics2.setPaint(color);
        graphics2.draw(rectangle); 
        
//        FontMetrics fm = graphics2.getFontMetrics();  
        
        if( name != null ){
        	drawLabel(graphics2, rectangle);
        }
    }
        
	private void drawLabel(Graphics2D graphics2, RoundRectangle2D rectangle) {

		graphics2.setFont(font);

		Rectangle2D r = graphics2.getFontMetrics().getStringBounds(name,
				graphics2);

		double width = r.getWidth();
		double height = r.getHeight();

		float y_position_text = (float) (rectangle.getCenterY() + (height / 2) - 2);
		float x_position_text = (float) (rectangle.getCenterX() - (width / 2));

		graphics2.drawString(name, x_position_text, y_position_text);

	}
    
    public void moveBy(Point point) {
    	center_x += point.x;
    	center_y += point.y;
		
		// TODO Auto-generated method stub
		
	}
    
    private RoundRectangle2D getRectangle(){
    	RoundRectangle2D rectangle = new RoundRectangle2D.Double(center_x - (this.width / 2), center_y - (height / 2), 
        		this.width, height, 8, 8);
    	return rectangle;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", X " + center_x + ", Y " + center_y + 
        		" , isInput: " + isinput + ", isOutput: " + isoutput + 
        		", index " + index + ", texts: " + texty + " \n";
    }

//    public int compare(Object A, Object B) {
////      int a = ((zviera)A).getVyska();
////      int b = ((zviera)B).getVyska();
////      if (a > b)
////         return -1;
////      if (a == b)
////         return 0;
//        return 1;
//    }
    
    
    @Override
    public int compareTo(Object B) {
        int a = cislo(name);
        int b = cislo(((PtfsVertex) B).getName());

        if(isoutput || ((PtfsVertex) B).isinput ){
            return 1;
        }

        if(isinput || ((PtfsVertex) B).isoutput ){
            return -1;
        }
        
        if (a > b) {
            return 1;
        }
        if (a < b) {
            return -1;
        }
        
        if(a == b){
            return 1;
        }
        return 0;
    }
    
    @Override
    public boolean equals(Object B) {
        return true;
    }
    
    @Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

    public final int cislo(String vertexname) {
    	if(vertexname == null){
    		return 0;    		
    	}
        for (int i = 0; i < 10; i++) {
            if (vertexname.indexOf("" + i + "") != -1) {
//                System.out.println(vertexname.substring(vertexname.indexOf("" + i + "")));
                return Integer.parseInt(vertexname.substring(vertexname.indexOf("" + i + "")));
            }
        }
        return 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
      
    public Double getCenterX() {
		return center_x;
	}

	public void setCenterX(int x) {
		this.center_x = x;
	}

	public Double getCenterY() {
		return center_y;
	}

	public void setCenterY(int y) {
		this.center_y = y;
	}

	public Boolean getIsinput() {
        return isinput;
    }

    public void setIsinput(Boolean isinput) {
        this.isinput = isinput;
    }

    public Boolean getIsoutput() {
        return isoutput;
    }

    public void setIsoutput(Boolean isoutput) {
        this.isoutput = isoutput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PtfsText> getTexts() {
        return texty;
    }
    

	public Double getVertexWidth() {		
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public void setTexts(List<PtfsText> texts) {
        this.texty = texts;
    }

	

	

}
