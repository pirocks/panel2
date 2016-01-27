import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class SimplePaintListener  implements MouseListener, MouseMotionListener {
	private int prevX, prevY;     // The previous location of the mouse.
	private boolean dragging;

	/**
     * This is called when the user presses the mouse anywhere in the panel.  
     * There are three possible responses, depending on where the user clicked:  
     * Change the current color, clear the drawing, or start drawing a curve.  
     * (Or do nothing if user clicks on the border.)
     */
    public void mousePressed(MouseEvent evt) {
        int x = evt.getX();   // x-coordinate where the user clicked.
        int y = evt.getY();   // y-coordinate where the user clicked.

        int width = runner.content.getWidth();    // Width of the panel.
        int height = runner.content.getHeight();  // Height of the panel.

        if (dragging == true)  // Ignore mouse presses that occur
            return;            //    when user is already drawing a curve.
                               //    (This can happen if the user presses
                               //    two mouse buttons at the same time.)
									//***like left button is down+dragging but you click the right button
        if (x > width - 53) {
            if (y > height - 53) {
            		runner.content.lines = new ArrayList<Line>(); //*** lines now points to a new,  empty ArrayList
                runner.content.repaint();       //  Clicked on "CLEAR button".
            }
            else {
                runner.content.changeColor(y);  // Clicked on the color palette.
                runner.content.repaint(); //***added this to update the highlighted square of color
            }
        }
        else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
                // The user has clicked on the white drawing area.
                // Start drawing a curve from the point (x,y).
            prevX = x;
            prevY = y;
            dragging = true;
        }

    } // end mousePressed()


    /**
     * Called whenever the user releases the mouse button. If the user was drawing 
     * a curve, the curve is done, so we should set drawing to false and get rid of
     * the graphics context that we created to use during the drawing.
     */
    public void mouseReleased(MouseEvent evt) {
        if (dragging == false)
            return;  // Nothing to do because the user isn't drawing.
        dragging = false;
    }


    /**
     * Called whenever the user moves the mouse while a mouse button is held down.  
     * If the user is drawing, draw a line segment from the previous mouse location 
     * to the current mouse location, and set up prevX and prevY for the next call.  
     * Note that in case the user drags outside of the drawing area, the values of
     * x and y are "clamped" to lie within this area.  This avoids drawing on the color 
     * palette or clear button.
     */
    public void mouseDragged(MouseEvent evt) {
    //	System.out.println("mouseDragged!");
        if (dragging == false)
            return;  // Nothing to do because the user isn't drawing.

        int x = evt.getX();   // x-coordinate of mouse.
        int y = evt.getY();   // y-coordinate of mouse.

        if (x < 3)                          // Adjust the value of x,
            x = 3;                           //   to make sure it's in
        if (x > runner.content.getWidth() - 57)       //   the drawing area.
            x = runner.content.getWidth() - 57;

        if (y < 3)                          // Adjust the value of y,
            y = 3;                           //   to make sure it's in
        if (y > runner.content.getHeight() - 4)       //   the drawing area.
            y = runner.content.getHeight() - 4;

        runner.content.lines.add(new Line(prevX, prevY, x, y, runner.content.currentColor));  // **** simply add the line to the ArrayList, will be drawn later
        runner.content.repaint(); 	// ***Have System call paintComponent(), otherwise lines won't show up until you clicked the 
        				//the next color
        prevX = x;  // Get ready for the next line segment in the curve.
        prevY = y;

    } // end mouseDragged()


    public void mouseEntered(MouseEvent evt) { }   // Some empty routines.
    public void mouseExited(MouseEvent evt) { }    //    (Required by the MouseListener
    public void mouseClicked(MouseEvent evt) { }   //    and MouseMotionListener
    public void mouseMoved(MouseEvent evt) { }     //    interfaces).



}