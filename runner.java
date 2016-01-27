import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class runner
{
	public static  SimplePaintPanel content;
    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Paint");
        content = new SimplePaintPanel();
        window.setContentPane(content);
        window.setSize(700,380);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }
}