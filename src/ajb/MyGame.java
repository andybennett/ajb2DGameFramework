package ajb;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;

public class MyGame extends SimpleGame implements Loop {

    boolean paused = false;
    
    Point2D rectanglePosition = new Point2D.Double(0, 0);
    GameLoop gameLoop = new GameLoop(this);    
    
    public MyGame(String title, int width, int height) {
        super(title, width, height);
        gameLoop.go();
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        // Do any clear up here...
        gameLoop.setGameRunning(false);
        System.exit(0);
    }

    @Override
    public void doLogic(double delta) {
        // Game logic in here
        if (!paused) {
            rectanglePosition.setLocation(rectanglePosition.getX() + (0.1 * delta), 0);
        }
    }

    @Override
    public void render() {
        // Simply calls repaint
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g1) {
        super.paint(g1);

        Graphics2D g = (Graphics2D) g1;
                
        // White rectangle displayed in top left
        g.setColor(Color.decode("#D197D9"));    
        g.setStroke(new BasicStroke(4));
        g.drawRect((int)rectanglePosition.getX(), (int)rectanglePosition.getY(), 50, 50);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        this.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        this.paused = !paused;
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        this.repaint();
    }
}
