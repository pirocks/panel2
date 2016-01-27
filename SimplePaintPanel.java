import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class SimplePaintPanel extends JPanel{


    /**
     * Some constants to represent the color selected by the user.
     */
    private final static int BLACK = 0,
            RED = 1,     
            GREEN = 2,   
            BLUE = 3, 
            CYAN = 4,   
            MAGENTA = 5,
            YELLOW = 6;

    public int currentColor = BLACK;  // The currently selected drawing color,
                                       //   coded as one of the above constants.

    /* The following variables are used when the user is sketching a
         curve while dragging a mouse. */

      // This is set to true while the user is drawing.

    
    public ArrayList<Line> lines = new ArrayList<Line>();
    

    /**
     * Constructor for SimplePaintPanel class sets the background color to be
     * white and sets it to listen for mouse events on itself.
     */
    SimplePaintPanel() {
        this.setBackground(Color.WHITE);
        SimplePaintListener listener = new SimplePaintListener();
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fill with background color (white).

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.

        int colorSpacing = (height - 56) / 7;

        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width-1, height-1);  //***one rectangle for each pixel 
        g.drawRect(1, 1, width-3, height-3);
        g.drawRect(2, 2, width-5, height-5);

        /* Draw a 56-pixel wide gray rectangle along the right edge of the panel.
             The color palette and Clear button will be drawn on top of this.
             (This covers some of the same area as the border I just drew. */

        g.fillRect(width - 56, 0, 56, height);

        /* Draw the "Clear button" as a 50-by-50 white rectangle in the lower right
             corner of the panel, allowing for a 3-pixel border. */

        g.setColor(Color.WHITE);
        g.fillRect(width-53,  height-53, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(width-53, height-53, 49, 49);
        g.drawString("CLEAR", width-48, height-23); 

        /* Draw the seven color rectangles. */

        g.setColor(Color.BLACK);
        g.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.RED);
        g.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.GREEN);
        g.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.BLUE);
        g.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.CYAN);
        g.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.MAGENTA);
        g.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.YELLOW);
        g.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);

        /* Draw a 2-pixel white border around the color rectangle
             of the current drawing color. */

        g.setColor(Color.WHITE);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
 
        for(int i = 0; i < lines.size(); i++) {
            switch (lines.get(i).colorCode) {
            case BLACK:
                g.setColor(Color.BLACK);
                break;
            case RED:
                g.setColor(Color.RED);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                break;
            case BLUE:
                g.setColor(Color.BLUE);
                break;
            case CYAN:
                g.setColor(Color.CYAN);
                break;
            case MAGENTA:
                g.setColor(Color.MAGENTA);
                break;
            case YELLOW:
                g.setColor(Color.YELLOW);
                break;
            }
        		g.drawLine(lines.get(i).x1, lines.get(i).y1, lines.get(i).x2, lines.get(i).y2);
        }
    } // end paintComponent()


    public void changeColor(int y) {
        int width = getWidth();           // Width of panel.
        int height = getHeight();         // Height of panel.
        int colorSpacing = (height - 56) / 7;  // Space for one color rectangle.
        int newColor = y / colorSpacing;       // Which color number was clicked?

        if (newColor < 0 || newColor > 6)      // Make sure the color number is valid.
            return;

        currentColor = newColor;
    } 


    
} // end class SimplePaintPanel
