package ajb.examples;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import ajb.examples.helpers.LookAndFeelUtils;
import ajb.examples.helpers.ShipBuilderUtils;
import ajb.examples.helpers.Starfield;
import ajb.examples.helpers.Vessel;
import ajb.framework.Base2DFramework;
import ajb.game.GameLoop;
import ajb.interfaces.Loop;

@SuppressWarnings("serial")
public class ShipBuilderExample extends Base2DFramework implements Loop {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		LookAndFeelUtils.setNimbusLookAndfeel();
		ShipBuilderExample app = new ShipBuilderExample();

	}

	Starfield starfield = null;
	GameLoop loop = new GameLoop(this);
	Vessel vessel = null;
	JFrame frame = null;
	int windowedWidth = 1024;
	int windowedHeight = 768;
	boolean displayHelp = true;

	public ShipBuilderExample() {

		super();

		setBackground(Color.decode("#242424"));

		frame = new JFrame();
		frame.setTitle("Example");
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(windowedWidth, windowedHeight);
		frame.setLocationRelativeTo(null);
		frame.add(this);

		frame.addKeyListener(this);
		frame.addComponentListener(this);

		frame.setVisible(true);

		loop.go();

	}

	@Override
	public void doLogic(double delta) {

		if (starfield != null) {

			starfield.twinkle();

		}

	}

	@Override
	public void render() {

		this.repaint();

	}

	@Override
	public void drawBeforeTransform(Graphics2D g) {

		super.drawBeforeTransform(g);

		starfield.draw(g);

	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D gr = (Graphics2D) g;

		if (vessel != null) {

			vessel.draw(gr);

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		try {
			if (e.getKeyCode() == KeyEvent.VK_HOME) {

				moveToShip();

			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {

				vessel = new Vessel(ShipBuilderUtils.generate(),
						new Point2D.Double(this.getWidth() / 2, this.getHeight() / 2));

			} else if (e.getKeyCode() == KeyEvent.VK_F11) {

				if (!frame.isUndecorated()) {

					GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
					int width = gd.getDisplayMode().getWidth();
					int height = gd.getDisplayMode().getHeight();

					frame.dispose();
					frame.setVisible(false);
					frame.setUndecorated(true);
					frame.setSize(width, height);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} else {

					frame.dispose();
					frame.setVisible(false);
					frame.setUndecorated(false);
					frame.setSize(windowedWidth, windowedHeight);
					frame.setVisible(true);

				}

			} else if (e.getKeyCode() == KeyEvent.VK_1) {

				coordTransform.setToScale(1, 1);

				moveToShip();

			} else if (e.getKeyCode() == KeyEvent.VK_2) {

				coordTransform.setToScale(0.5, 0.5);

				moveToShip();

			} else if (e.getKeyCode() == KeyEvent.VK_3) {

				coordTransform.setToScale(0.2, 0.2);

				moveToShip();

			} else if (e.getKeyCode() == KeyEvent.VK_A) {

				vessel.add();

			} else if (e.getKeyCode() == KeyEvent.VK_S) {

				vessel.subtract();

			} else if (e.getKeyCode() == KeyEvent.VK_D) {

				vessel.addSpine();

			}

		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}

	}

	private void moveToShip() {

		if (vessel != null) {

			try {

				moveToPoint(transformPoint(
						new Point2D.Double((this.getWidth() / 2) - (vessel.center.getX() * coordTransform.getScaleX()),
								(this.getHeight() / 2) - (vessel.center.getY() * coordTransform.getScaleY()))));

			} catch (NoninvertibleTransformException e1) {

				e1.printStackTrace();

			}

		}

	}

	@Override
	public void componentResized(ComponentEvent arg0) {

		starfield = new Starfield(0, 0, this.getWidth(), this.getHeight(), 100, 200);
		moveToShip();

	}

}