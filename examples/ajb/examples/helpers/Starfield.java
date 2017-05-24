package ajb.examples.helpers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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

                    star.colour = star.colour.brighter();

                } else {

                    if (star.colour.getBlue() > 100) {

                        star.colour = star.colour.darker();

                    }
                }
            }
        }
    }

    public void draw(Graphics g) {

        for (Star star : stars) {

            g.setColor(star.colour);

            g.fillOval((int) star.position.x, (int) star.position.y, star.size, star.size);

        }

        g.setColor(Color.decode("#242424").darker());
        g.drawRect(x, y, width * 2, height * 2);

    }

}

class Star {

    Point2D.Double position = null;
    int size = RandomInt.anyRandomIntRange(1, 5);
    Color colour = Colours.white;

}
