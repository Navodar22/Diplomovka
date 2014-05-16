package drawing;

import analyz.PtfsLPO;
import diplomovka.Command;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Feature implements MouseListener, MouseMotionListener, MouseWheelListener {

	protected MyCanvas canvas;
	protected PtfsLPO lpo;
	protected Command command;
	
	public Feature(MyCanvas canvas){
		this.canvas = canvas;
		this.lpo = canvas.getLpo();
		this.command = canvas.getCommand();
		
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
	}
	
	public void realoadLpo(){
		this.lpo = canvas.getLpo();
	}
	
	public void paintBackground(Graphics g) {
	}
	public void paintMainLayer(Graphics g) {
	}
	public void paintForegroud(Graphics g) {
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
