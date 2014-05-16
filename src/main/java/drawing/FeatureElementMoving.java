package drawing;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class FeatureElementMoving extends Feature {
		
	// premenna zistujuca ci sa s vybranymi prvkami pohlo. 
	private boolean startMoving = false;
	
	public FeatureElementMoving(MyCanvas canvas)  {
		super(canvas);
	}
	
	// Posledny kliknuty prvok (suradnice ako objekt triedy Point):
	private Point previousPoint = new Point(0, 0);

	public void mousePressed(MouseEvent e) {
		if( !canvas.isLeftMouseButton(e) ){
			return;			
		}
		
		if( !lpo.getSelectVertexes().isEmpty() ){	
//			System.out.println("bbbb");
			canvas.setElementsMoving(true);				
		}	
		// Zapamataj si suradnicu posledneho stlacenia tlacitka mysi
		previousPoint = new Point(e.getPoint());
	}

	

	public void mouseDragged(MouseEvent e) {		
		if( canvas.isElementsFreez() 
				|| !canvas.isElementsMoving() 
				|| canvas.isVertexResize()
				|| canvas.isCanvasReposition() ){
			return;			
		}
		
		
		if( !startMoving ){
			startMoving = true;
			command.startsMoving();
		}
		
		// Vypocitaj relativnu poziciu o kolko sa kurzor mysi pohol
		//   od posledneho kliknutia alebo tahania mysi
		Point relativePosition = new Point( 
				(int) (e.getX() - previousPoint.getX() ) ,
				(int) (e.getY() - previousPoint.getY() ) );
		
		lpo.moveElements(relativePosition);
		
		previousPoint = new Point(e.getPoint());
		canvas.repaint();
	}
	
	public void mouseReleased(MouseEvent e) {
		canvas.repaint();
		canvas.setElementsMoving(false);	
		startMoving = false;
	}
}
