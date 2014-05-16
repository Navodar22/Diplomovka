/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyz;
/**
 *
 * @author navodar
 */
public class PflowPlace {
    
    private int id;
    private int tokens;
    private String label;

    public PflowPlace() {
    }

    public PflowPlace(Integer id, Integer x, Integer y, String label, Integer tokens, Boolean isStatic) {
        this.id = id;
        this.label = label;
        this.tokens = tokens;        
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

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", label: " + label + ", tokens: " + tokens + "\n";
    }
    
//    @Override
//    public int compareTo(Object B) {
//        int a = cislo(label);
//        int b = cislo(((PflowPlace) B).getLabel());
//
//
//        if (a > b) {
//            return 1;
//        }
//        if (a < b) {
//            return -1;
//        }
//
//        return 0;
//    }

    private final int cislo(String placename) {
        for (int i = 0; i < 10; i++) {
            if (placename.indexOf("" + i + "") != -1) {
//                System.out.println(vertexname.substring(vertexname.indexOf("" + i + "")));
                return Integer.parseInt(placename.substring(placename.indexOf("" + i + "")));
            }
        }
        return 0;
    }
}
