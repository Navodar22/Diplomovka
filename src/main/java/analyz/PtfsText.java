/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package analyz;

/**
 *
 * @author navodar
 */
public class PtfsText {
    private int Count;
    private int ID;

    public PtfsText(){}

    public PtfsText(int idecko, int kount){
        this.Count = kount;
        this.ID = idecko;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ID: " + ID + ", Count: " + Count ;
    }

    

}
