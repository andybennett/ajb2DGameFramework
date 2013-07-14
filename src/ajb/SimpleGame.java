package ajb;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.WindowConstants;

public class SimpleGame extends Component2D implements WindowListener {

    public SimpleGame(String title, int width, int height) {
        
        super();
        
        try {
            MyFrame frame = new MyFrame(title);
            MyPanel myPanel = new MyPanel(title);

            frame.setSize(width, height);
            frame.setMinimumSize(new Dimension(width, height));

            frame.add(myPanel);
            myPanel.setLayout(new java.awt.BorderLayout());
            myPanel.add(this, BorderLayout.CENTER);
            
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.addWindowListener(this);
            
            this.requestFocusInWindow(); 
            
            frame.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }
    
    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void paint(Graphics g1) {
        super.paint(g1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
    }
}
