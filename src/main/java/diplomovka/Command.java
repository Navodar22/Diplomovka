package diplomovka;

import analyz.PtfsEdge;
import analyz.PtfsLPO;
import design.MainFrame;

import java.awt.Point;
import java.util.Stack;

import analyz.PtfsVertex;
import drawing.MyCanvas;

public class Command {
	
	private MainFrame mainFrame;
    private MyCanvas canvas;

    private Stack<PtfsLPO> lpos = new Stack<PtfsLPO>();
    private Stack<PtfsLPO> lpos_redo = new Stack<PtfsLPO>();

	
	public Command(MainFrame mainFrame, MyCanvas canvas){
        this.canvas = canvas;
		this.mainFrame = mainFrame;		
	}
	
	public void moveThisLpo(Point relativePosition){
		Stack<PtfsLPO> mainFrameLpoStack = lpos;
		PtfsLPO lpo = mainFrameLpoStack.peek();
		lpo.move(relativePosition);	
	}
	
	public void moveAllLpoExceptActive(Point absolutePosition){
		Stack<PtfsLPO> mainFrameLpoStack = lpos;
		PtfsLPO lpo_active = mainFrameLpoStack.peek();
		for( PtfsLPO lpo : mainFrameLpoStack ){
			if( lpo != lpo_active ){
				lpo.move(absolutePosition);
			}		
		}
		
		Stack<PtfsLPO> mainFrameRedoLpoStack = lpos_redo;
		for( PtfsLPO lpo_redo : mainFrameRedoLpoStack ){
			lpo_redo.move(absolutePosition);
		}		
	}


	public void addVertex(PtfsLPO lpo, PtfsVertex vertex ){
		PtfsLPO lpo_copy = lpo.createDeepCopy();
        lpo_copy.addVertex(vertex);
        lpos.push(lpo);
        lpos_redo.clear();
        updateCanvas(lpo_copy);
	}
	
	public void addEdge(PtfsLPO lpo, PtfsEdge edge ){
		if( mainFrame.isGraphCircle() || !lpo.pathExist(edge.getDestination(), edge.getSource())){
			PtfsLPO lpo_copy = lpo.createDeepCopy();
            lpo_copy.addEdge(edge);
            lpos.push(lpo);
            lpos_redo.clear();
            updateCanvas(lpo_copy);
		}
	}
	
	/*
	 * najprv sa vklada kopia(ta predstavuje povodne LPO)
	 * a potom sa z povodneho zmaze oznaceny obsah a ten sa nasledne 
	 * vlozi. Dovod je ten ze createDeepCopy nekopiruje vybrane prvky - vertexes_select
	 */
	public void delete(){
		Stack<PtfsLPO> mainFrameLpoStack = lpos;
		PtfsLPO lpo = mainFrameLpoStack.pop();
		PtfsLPO copy_lpo = lpo.createDeepCopy();
		lpo.deleteElements();
		mainFrameLpoStack.push(copy_lpo);
		mainFrameLpoStack.push(lpo);
		lpo.clearMouseOverEdges();
		lpo.clearSelectedEdges();
		lpo.clearMouseOverVertexes();
		lpo.clearSelectedVertexes();
        lpos_redo.clear();
        canvas.repaint();
	}
	
	public void renameVertex(String name) {
		Stack<PtfsLPO> mainFrameLpoStack = lpos;
		PtfsLPO lpo = mainFrameLpoStack.pop();
		PtfsLPO copy_lpo = lpo.createDeepCopy();
		lpo.renameVertexes(name);
		mainFrameLpoStack.push(copy_lpo);
		mainFrameLpoStack.push(lpo);
		lpo.clearMouseOverEdges();
		lpo.clearSelectedEdges();
		lpo.clearMouseOverVertexes();
		lpo.clearSelectedVertexes();
        lpos_redo.clear();
		mainFrame.repaint();
	}
	
	public void startsMoving() {
		Stack<PtfsLPO> mainFrameLpoStack = lpos;
		PtfsLPO lpo = mainFrameLpoStack.pop();
		PtfsLPO copy_lpo = lpo.createDeepCopy();
		mainFrameLpoStack.push(copy_lpo);
		mainFrameLpoStack.push(lpo);
        lpos_redo.clear();
		mainFrame.repaint();		
	}
	
	public void startsResizing() {
		Stack<PtfsLPO> mainFrameLpoStack = lpos;
		PtfsLPO lpo = mainFrameLpoStack.pop();
		PtfsLPO copy_lpo = lpo.createDeepCopy();
		mainFrameLpoStack.push(copy_lpo);
		mainFrameLpoStack.push(lpo);
        lpos_redo.clear();
		mainFrame.repaint();		
	}
	
	public void undo(){
		if( lpos.size() >  0 ){
			PtfsLPO lpo = lpos.pop();
            lpos_redo.push(lpo);
            updateCanvas(lpo);
		}
	}
	
	public void redo(){
		if( lpos_redo.size() > 0 ){
			PtfsLPO lpo = lpos_redo.pop();
            lpos.push(lpo);
            updateCanvas(lpo);
		}
	}

    private void updateCanvas(PtfsLPO lpo_copy){
        canvas.setLpo(lpo_copy);
        canvas.repaint();
    }

}
