package ajb.framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Base2DFramework extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private Color background = Color.decode("#1E1E1E");

    protected boolean allowDrag = true;
    protected boolean init = true;
    protected Point dragStartScreen;
    protected Point dragEndScreen;
    protected AffineTransform coordTransform = new AffineTransform();

    public Base2DFramework() {

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    @Override
    public void paint(Graphics g1) {

        Graphics2D g = (Graphics2D) g1;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g.setColor(background);
        Dimension d = getSize();
        g.fillRect(0, 0, (int) d.getWidth(), (int) d.getHeight());

        drawBeforeTransform(g);

        if (init) {

            init = false;
            coordTransform = g.getTransform();

        } else {

            g.setTransform(coordTransform);

        }

    }

    public Color getBackground() {

        return background;

    }

    public void setBackground(Color background) {

        this.background = background;

    }

    public void drawBeforeTransform(Graphics2D g) {

    }

    public void moveToPoint(Point2D.Double p) {

        coordTransform.translate(p.getX(), p.getY());
        this.repaint();

    }

    protected void moveCamera(MouseEvent e) {

        try {

            if (allowDrag) {

                dragEndScreen = e.getPoint();
                Point2D.Double dragStart = transformPoint(dragStartScreen);
                Point2D.Double dragEnd = transformPoint(dragEndScreen);
                double dx = dragEnd.getX() - dragStart.getX();
                double dy = dragEnd.getY() - dragStart.getY();
                coordTransform.translate(dx, dy);
                dragStartScreen = dragEndScreen;
                dragEndScreen = null;
                this.repaint();

            }

        } catch (NoninvertibleTransformException ex) {

            ex.printStackTrace();

        }

    }

    protected Point2D.Double transformPoint(Point p1) throws NoninvertibleTransformException {

        AffineTransform inverse = coordTransform.createInverse();
        Point2D.Double p2 = new Point2D.Double();
        inverse.transform(p1, p2);
        return p2;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        dragStartScreen = e.getPoint();
        dragEndScreen = null;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // moveCamera(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        moveCamera(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
