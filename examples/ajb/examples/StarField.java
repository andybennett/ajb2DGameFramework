package ajb.examples;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ajb.framework.Base2DFramework;
import ajb.game.GameLoop;
import ajb.interfaces.Loop;
import ajb.random.RandomInt;

public class StarField extends Base2DFramework implements Loop {

    @SuppressWarnings("unused")
    public static void main(String[] args) {               
        StarField app = new StarField();
    }
    
    GameLoop loop = new GameLoop(this);
    List<Star> stars = new ArrayList<Star>();

    public StarField() {
        super();
        
    	JFrame frame = new JFrame();
        frame.setTitle("Example");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        
        createStars();
        
        frame.setVisible(true);
        
        loop.go();
    }

    @Override
    public void doLogic(double delta) {
        for (Star star : stars) {
        	if (RandomInt.anyRandomIntRange(0, 100) > 99) {
	        	if (RandomInt.anyRandomIntRange(0, 1) == 1) {
	        		star.colour = star.colour.brighter();
	        	} else {
	        		if (star.colour.getBlue() > 100) {
	        			star.colour = star.colour.darker();
	        		}
	        	}
        	}
        }
    }

    @Override
    public void render() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // draw here
        for (Star star : stars) {
        	g.setColor(star.colour);
        	g.fillOval((int)star.position.x, (int)star.position.y, star.size, star.size);
        }
        
        g.setColor(Color.BLACK);
        g.drawRect(-5000, -5000, 10000, 10000);
    }
    
    private void createStars() {
    	
    	int amountOfstars = RandomInt.anyRandomIntRange(5000, 10000);
    	
    	for(int i = 0; i < amountOfstars; i++) {
    		Star star = new Star();
    		star.position = new Point2D.Double(RandomInt.anyRandomIntRange(-5000, 5000), RandomInt.anyRandomIntRange(-5000, 5000));
    		stars.add(star);
    	}
    	
    }

}

class Star {
	
	Color blue = Color.decode("#8ac6f2");
	Color red = Color.decode("#f08080");
	Color green = Color.decode("#cae682");
	Color white = Color.decode("#f3f6ee");
	
	Point2D.Double position = null;	
	int size = RandomInt.anyRandomIntRange(1, 3);
	Color colour = null;
	
	public Star() {
		int random = RandomInt.anyRandomIntRange(4, 4);
		
		switch(random) {
			case 1: colour = blue; break;
			case 2: colour = red; break;
			case 3: colour = green; break;
			case 4: colour = white; break;
		}
	}
}
