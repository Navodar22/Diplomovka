package drawing;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class FeatureCanvasReposition extends Feature {

	private Point previousPoint = new Point(0, 0);
	private Point absolutePosition = new Point(0, 0);
	
	public FeatureCanvasReposition(MyCanvas canvas) {
		super(canvas);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		if ((e.getModifiers() & ActionEvent.CTRL_MASK) ==ActionEvent.CTRL_MASK) {
//			System.out.println("CTRL KEY PRESSED");
			canvas.setCanvasReposition(true);
//			previousPoint = new Point(canvas.getMainFrame().getMousePosition());
			previousPoint = new Point(e.getPoint());
			absolutePosition = new Point(0, 0);
			lpo.clearMouseOverEdges();
			lpo.clearMouseOverVertexes();
			lpo.clearSelectedEdges();
			lpo.clearSelectedVertexes();
		}
    	
    	canvas.repaint(); 
	}
	
	
	@Override
    public void mouseDragged(MouseEvent e) {
		if( !canvas.isCanvasReposition() ){
			return;
		}
		
		lpo.clearMouseOverEdges();
		lpo.clearMouseOverVertexes();
		lpo.clearSelectedEdges();
		lpo.clearSelectedVertexes();
		
		Point relativePosition = new Point( 
				(int) (e.getX() - previousPoint.getX() ) ,
				(int) (e.getY() - previousPoint.getY() ) );
		
		absolutePosition.x += relativePosition.x;
		absolutePosition.y += relativePosition.y;
		
//		System.out.println(absolutePoint);
		
		command.moveThisLpo(relativePosition);
		
		
		previousPoint = new Point(e.getPoint());
		canvas.repaint();
		   	   	   	
    }
	
	@Override
	public void mouseReleased(MouseEvent e){
		
		command.moveAllLpoExceptActive(absolutePosition);
		
		canvas.setCanvasReposition(false);		
		canvas.repaint();
	}

}
