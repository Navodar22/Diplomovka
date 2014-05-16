/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyz;


/**
 *
 * @author navodar
 */
public class PflowTransition {

    public PflowTransition() {
    }
    
    private int id;
    private String label;

    public PflowTransition(int id, int x, int y, String label) {
        this.id = id;
        this.label = label;        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", label: " + label;
    }
}
