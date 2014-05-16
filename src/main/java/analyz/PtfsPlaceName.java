/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package analyz;

/**
 *
 * @author navodar
 */
public class PtfsPlaceName implements Comparable {

    private int id;
    private String name;

    public PtfsPlaceName(){}

    public PtfsPlaceName(int idecko, String name){
        this.id = idecko;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    

    public void setId(int idecko) {
        this.id = idecko;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }

    @Override
    public int compareTo(Object B) {
        int a = cislo(name);
        int b = cislo(((PtfsPlaceName) B).getName());

//        System.out.println(a);
//        System.out.println(b);

        if (a > b) {
            return 1;
        }
        if (a < b) {
            return -1;
        }

        return 0;
    }

    public final int cislo(String placename) {
        for (int i = 0; i < 10; i++) {
            if (placename.indexOf("" + i + "") != -1) {
//                System.out.println(vertexname.substring(vertexname.indexOf("" + i + "")));
                return Integer.parseInt(placename.substring(placename.indexOf("" + i + "")));
            }
        }
        return 0;
    }

    

}
