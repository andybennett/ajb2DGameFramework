package base2DFramework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

public class Base2DFramework extends JFrame implements MouseListener, MouseMotionListener {

    public Color background = Color.decode("#1E1E1E");

    private boolean init = true;
    private Point dragStartScreen;
    private Point dragEndScreen;
    private AffineTransform coordTransform = new AffineTransform();

    public Base2DFramework() {
        setTitle("JHSS");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);

        addMouseListener(this);
        addMouseMotionListener(this);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g.setColor(background);
        Dimension d = getSize();
        g.fillRect(0, 0, (int) d.getWidth(), (int) d.getHeight());

        if (init) {
            init = false;
            coordTransform = g.getTransform();
        }
        else {
            g.setTransform(coordTransform);
        }
    }

    private void moveCamera(MouseEvent e) {
        try {
            dragEndScreen = e.getPoint();
            Point2D.Float dragStart = transformPoint(dragStartScreen);
            Point2D.Float dragEnd = transformPoint(dragEndScreen);
            double dx = dragEnd.getX() - dragStart.getX();
            double dy = dragEnd.getY() - dragStart.getY();
            coordTransform.translate(dx, dy);
            dragStartScreen = dragEndScreen;
            dragEndScreen = null;

            this.repaint();
        }
        catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
        }
    }

    private Point2D.Float transformPoint(Point p1) throws NoninvertibleTransformException {
        AffineTransform inverse = coordTransform.createInverse();
        Point2D.Float p2 = new Point2D.Float();
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
}
