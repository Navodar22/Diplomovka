/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyz;

import java.util.List;

/**
 *
 * @author navodar
 */

public class PflowPetriNet {

    private List<PflowPlace> places;
    private List<PflowTransition> transitions;
    private List<PflowArc> arcs;

    public PflowPetriNet() {

    }

    public PflowPetriNet(List<PflowPlace> places, List<PflowTransition> transitions, List<PflowArc> arcs) {
        this.places = places;
        this.transitions = transitions;
        this.arcs = arcs;
    }

    public Integer getOutputTransitionWeight(PflowPlace place, String tranName){
    	PflowTransition transition = getTransitionByName(tranName);
    	
    	if(transition == null){
    		return 0;
    	}
    	
    	for (PflowArc arc : arcs) {
            if ((arc.getSourceId() == place.getId()) && (arc.getDestinationId() == transition.getId())) {
                return arc.getMultiplicity();
            }
        }
        return 0;
    }
    
    public Integer getInputTransitionWeight(PflowPlace place, String tranName){
    	PflowTransition transition = getTransitionByName(tranName);
    	
    	if(transition == null){
    		return 0;
    	}
    	
    	for (PflowArc arc : arcs) {
            if ((arc.getSourceId() == transition.getId() ) && (arc.getDestinationId() == place.getId() )) {
                return arc.getMultiplicity();
            }
        }
        return 0;
    }
    
    private PflowTransition getTransitionByName(String name){
    	for (PflowTransition transition : transitions){
    		if( name.equalsIgnoreCase(transition.getLabel()) ){
    			return transition;
    		}
    	}
    	return null;
    }
    
    public List<PflowArc> getArcs() {
        return arcs;
    }

    public void setArcs(List<PflowArc> arcs) {
        this.arcs = arcs;
    }

    public List<PflowPlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<PflowPlace> places) {
        this.places = places;
    }

    public List<PflowTransition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<PflowTransition> transitions) {
        this.transitions = transitions;
    }
    
}
