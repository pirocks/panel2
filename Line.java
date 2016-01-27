import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Line {
		int x1, x2, y1, y2;
		int colorCode;
		
		public Line(int x1, int y1, int x2, int y2, int colorCode) {
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
			this.colorCode = colorCode;
		}
}