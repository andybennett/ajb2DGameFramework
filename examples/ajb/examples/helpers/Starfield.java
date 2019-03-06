package ajb.examples.helpers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import ajb.colours.ColourUtils;
import ajb.random.RandomInt;

public class Starfield {

	List<Star> stars = new ArrayList<Star>();
	int x, y;
	int width, height;
	int minAmountOfstars, maxAmountOfStars;

	public Starfield(int x, int y, int width, int height, int minAmountOfstars, int maxAmountOfStars) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.minAmountOfstars = minAmountOfstars;
		this.maxAmountOfStars = maxAmountOfStars;

		createStars();

	}

	private void createStars() {

		int amountOfstars = RandomInt.anyRandomIntRange(minAmountOfstars, maxAmountOfStars);

		for (int i = 0; i < amountOfstars; i++) {

			Star star = new Star();

			star.position = new Point2D.Double(RandomInt.anyRandomIntRange(x, width),
					RandomInt.anyRandomIntRange(y, height));

			stars.add(star);

		}

	}

	public void twinkle() {

		for (Star star : stars) {

			if (RandomInt.anyRandomIntRange(0, 100) > 90) {

				if (RandomInt.anyRandomIntRange(0, 1) == 1) {

					star.alpha += 10;

					if (star.alpha > 255) {

						star.alpha = 255;

					}

				} else {

					star.alpha -= 10;

					if (star.alpha < 50) {

						star.alpha = 50;

					}
				}
			}
		}
	}

	public void draw(Graphics g) {

		for (Star star : stars) {

			g.setColor(ColourUtils.makeTransparent(star.colour, star.alpha));

			g.fillOval((int) star.position.x, (int) star.position.y, star.size, star.size);

		}

	}

}

class Star {

	Point2D.Double position = null;
	int size = RandomInt.anyRandomIntRange(1, 5);
	int alpha = RandomInt.anyRandomIntRange(50, 255);
	Color colour = ColourUtils.white;

}
