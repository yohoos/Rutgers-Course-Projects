import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import java.awt.Color;

public class Draw extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Iterable<Line2D> lines;
	Point2D[] points;
	
    public Draw(Iterable<Line2D> list, Point2D[] points) {                   // set up graphics window
    	
        setBackground(Color.WHITE);
        setSize(1000, 1000);
        this.points = points;
        this.lines = list;
    }

    public void paintComponent(Graphics g) { // draw graphics in the panel
    
    	Graphics2D g2 = (Graphics2D) g;


        // Drawing code goes here
    	for (int i=0; i<this.points.length; i++) {
    		g2.fillRect((int) (this.points[i].getX()-3), (int) (this.points[i].getY()-3), 6, 6);
    		g2.drawString(""+i, (int) (this.points[i].getX()-9), (int) (this.points[i].getY()-6));
    	}
        
        for (Line2D x: this.lines) {
        	g2.draw(x);
        }
    }
}
