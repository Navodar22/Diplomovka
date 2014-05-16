/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyz;

/**
 *
 * @author navodar
 */

public class PflowArc {

    public PflowArc() {
    }

    private int sourceId;
    private int destinationId;
    private int multiplicity;

    public PflowArc(int sourceId, int destinationId, int multiplicity) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.multiplicity = multiplicity;        
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }
    
    

    @Override
    public String toString() {
        return "Source ID: " + sourceId + ", Destination Id: " + destinationId + ", Multiplicity: " + multiplicity;
    }
}
