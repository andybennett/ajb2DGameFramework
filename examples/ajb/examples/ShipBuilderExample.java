package ajb.examples;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

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
	List<Vessel> vessels = new ArrayList<Vessel>();
	Vessel selectedVessel = null;
	JFrame frame = null;
	int windowedWidth = 1024;
	int windowedHeight = 768;
	boolean displayHelp = true;
	Point2D.Double clickPoint = null;

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

		for (Vessel vessel : vessels) {

			vessel.draw(gr);

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		try {

			if (e.getKeyCode() == KeyEvent.VK_HOME) {

				moveToShip();

			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {

				Vessel vessel = selectedVessel;

				if (vessel != null) {

					selectedVessel.random();

				}

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

				Vessel vessel = selectedVessel;

				if (vessel != null) {

					vessel.add();

				}

			} else if (e.getKeyCode() == KeyEvent.VK_S) {

				Vessel vessel = selectedVessel;

				if (vessel != null) {

					vessel.subtract();

				}

			} else if (e.getKeyCode() == KeyEvent.VK_D) {

				Vessel vessel = selectedVessel;

				if (vessel != null) {

					vessel.addSpine();

				}

			} else if (e.getKeyCode() == KeyEvent.VK_F) {

				Vessel vessel = selectedVessel;

				if (vessel != null) {

					vessel.flip();

				}

			}

		} catch (

		Exception ex) {

			ex.printStackTrace();

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		try {

			clickPoint = transformPoint(new Point2D.Double(e.getX(), e.getY()));

		} catch (NoninvertibleTransformException e1) {

			e1.printStackTrace();

		}

		boolean vesselSelected = false;

		for (Vessel vessel : vessels) {

			if (vessel.containsPoint(clickPoint)) {

				vessel.selected = true;
				vesselSelected = true;
				selectedVessel = vessel;

			} else {

				vessel.selected = false;

			}

		}
		
		if (selectedVessel != null) {
			
			vessels.remove(selectedVessel);
			vessels.add(selectedVessel);			
			
		}

		if (e.getButton() == MouseEvent.BUTTON3) {

			JPopupMenu myPopupMenu = new JPopupMenu();

			if (!vesselSelected) {

				JMenuItem newVessel = new JMenuItem(new AbstractAction("New") {
					public void actionPerformed(ActionEvent ae) {
						
						Vessel vessel = new Vessel(ShipBuilderUtils.generate(), clickPoint);
						vessel.selected = true;
						selectedVessel = vessel;
						vessels.add(vessel);
						
					}
				});

				myPopupMenu.add(newVessel);

			} else {

				JMenu nameSubMenu = new JMenu("Name");
				JMenuItem assignName = new JMenuItem("Assign");
				JMenuItem randomName = new JMenuItem("Random");
				nameSubMenu.add(assignName);
				nameSubMenu.add(randomName);
				
				myPopupMenu.add(nameSubMenu);

				JSeparator separator = new JSeparator();
				myPopupMenu.add(separator);
				
				JMenu hullAllocationSubMenu = new JMenu("Hull Allocation");
				JMenu shields = new JMenu("Shields");
				JMenuItem ten = new JMenuItem("10%");
				JMenuItem twenty = new JMenuItem("20%");
				JMenuItem thirty = new JMenuItem("30%");
				shields.add(ten);
				shields.add(twenty);
				shields.add(thirty);
				JMenu armour = new JMenu("Armour");
				JMenu engines = new JMenu("Engines");
				hullAllocationSubMenu.add(shields);
				hullAllocationSubMenu.add(armour);
				hullAllocationSubMenu.add(engines);
				
				myPopupMenu.add(hullAllocationSubMenu);

				JSeparator separator1 = new JSeparator();
				myPopupMenu.add(separator1);				

				JMenuItem deleteVessel = new JMenuItem(new AbstractAction("Delete") {
					public void actionPerformed(ActionEvent ae) {
						
						vessels.remove(selectedVessel);
						selectedVessel = null;
						
					}
				});

				myPopupMenu.add(deleteVessel);

			}

			myPopupMenu.show(this, e.getX(), e.getY());

		}

	}

	private void moveToShip() {

		Vessel vessel = selectedVessel;

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