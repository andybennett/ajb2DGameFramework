package ajb.examples;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import ajb.examples.helpers.Starfield;
import ajb.framework.Base2DFramework;
import ajb.game.GameLoop;
import ajb.interfaces.Loop;

@SuppressWarnings("serial")
public class StarfieldExample extends Base2DFramework implements Loop {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		StarfieldExample app = new StarfieldExample();

	}

	Starfield starfield = null;
	GameLoop loop = new GameLoop(this);

	public StarfieldExample() {

		super();

		starfield = new Starfield(-5000, -5000, 5000, 5000, 5000, 10000);

		setBackground(Color.decode("#242424"));

		JFrame frame = new JFrame();
		frame.setTitle("Example");
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);
		frame.add(this);

		frame.addKeyListener(this);

		frame.setVisible(true);

		loop.go();

	}

	@Override
	public void doLogic(double delta) {

		starfield.twinkle();

	}

	@Override
	public void render() {

		this.repaint();

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		// draw here
		starfield.draw(g);

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_HOME) {

			try {

				moveToPoint(transformPoint(new Point2D.Double(0 + this.getWidth() / 2, 0 + this.getHeight() / 2)));

			} catch (NoninvertibleTransformException e1) {

				e1.printStackTrace();

			}
		}

	}

}