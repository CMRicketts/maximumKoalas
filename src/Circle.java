import java.awt.Color;
import java.awt.event.*;
import acm.program.*;
import acm.graphics.*;


/* Write a program that does the following:
 *
 *   1. When the program is started up, a black oval is added
 *      to the display whose width and height are the width
 *      and height of the window.
 *   2. Whenever the mouse is pressed on a circle, that circle
 *      should be removed and four new circles should be added
 *      in its place, each of which is one-quarter the size
 *      of the original circle.
 *
 * Once you get this working, try making it so that when the
 * mouse is *moved* over a circle, that circle is replaced.
 * It leads to crazy results!
 *
 * As a hint, use getElementAt to find the object at the
 * current mouse position. Once you've done that, you can
 * use the obj.getX(), obj.getY(), obj.getWidth(), and
 * obj.getHeight() methods to determine the position and
 * size of the object that you hit.
 */
public class Circle extends GraphicsProgram {
    /* These constants give the window a nice initial size.
     * Feel free to change them!
     */
    public static GImage pic = new GImage("birb.jpg");
    public static int APPLICATION_WIDTH = (int) pic.getWidth();
    public static int APPLICATION_HEIGHT = (int) pic.getHeight();
    public int[][] pixelArray = pic.getPixelArray();


    public void init(){
        setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
    }

    @Override public void mouseMoved(MouseEvent e){
        GObject foo = getElementAt(e.getX(),e.getY());
        if (foo != null && foo.getWidth()>1 && foo != pic){
            splice(foo);
            remove(foo);
        }
    }

    public void splice(GObject circ){
        double x = circ.getX();
        double y = circ.getY();
        double diameter = circ.getWidth()/2;
        GOval circ1 = new GOval(x, y, diameter, diameter);
        GOval circ2 = new GOval(x + diameter, y, diameter, diameter);
        GOval circ3 = new GOval(x, y + diameter,diameter, diameter);
        GOval circ4 = new GOval(x + diameter, y+ diameter, diameter, diameter);
        Color first = getColor(circ1);
        Color second = getColor(circ2);
        Color third = getColor(circ3);
        Color fourth = getColor(circ4);
        circ1.setFillColor(first);
        circ2.setFillColor(second);
        circ3.setFillColor(third);
        circ4.setFillColor(fourth);
        circ1.setColor(first);
        circ2.setColor(second);
        circ3.setColor(third);
        circ4.setColor(fourth);
        circ1.setFilled(true);
        circ2.setFilled(true);
        circ3.setFilled(true);
        circ4.setFilled(true);
        add(circ1);
        add(circ2);
        add(circ3);
        add(circ4);
    }
    public Color getColor(GObject g){
        int red = 0;
        int green = 0;
        int blue = 0;
        int pixels = 0;

        for(int i = (int) g.getY(); i < g.getY()+ g.getWidth(); i++){
            for(int j = (int) g.getX(); j < g.getX() + g.getHeight();j++){
                red += pic.getRed(pixelArray[i][j]);
                green += pic.getGreen(pixelArray[i][j]);
                blue += pic.getBlue(pixelArray[i][j]);
                pixels++;
            }
        }
        blue /= pixels;
        green /= pixels;
        red /= pixels;
        Color average = new Color(red, green, blue);
        return(average);

    }

    public void run(){
        addMouseListeners();
        GOval circ = new GOval(0,0,APPLICATION_WIDTH,APPLICATION_HEIGHT);
        //Color foo = getColor(circ);
        circ.setColor(Color.black);
        circ.setFillColor(Color.black);
        circ.setFilled(true);
        add(circ);
    }




}
