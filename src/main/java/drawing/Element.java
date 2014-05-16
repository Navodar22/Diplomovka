/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 *
 * @author navodar
 */
public class Element extends JPanel{
	protected Color color = Color.BLACK;;
	protected Color color_bcg = Color.WHITE;
    
    public Color getFarba() {
        return color;
    }

    public void setFarba(Color farba) {
        this.color = farba;
    }
   

    public void draw(Graphics g) {

	}


}
