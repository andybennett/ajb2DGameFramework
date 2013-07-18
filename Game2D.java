package base2DFramework;

import java.awt.Color;
import java.awt.Graphics;

public class Game2D extends Base2DFramework implements Loop {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Game2D app = new Game2D();
    }
    
    GameLoop loop = new GameLoop(this);

    public Game2D() {
        super();
        loop.go();
    }

    @Override
    public void doLogic(double delta) {
        // logic here
    }

    @Override
    public void render() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // draw here
        g.setColor(Color.WHITE);
        g.drawRect((this.getWidth() / 2) - 50, (this.getHeight() / 2) - 50, 100, 100);
        
        g.drawString(String.valueOf(loop.getFps()), 10, 10);
    }

}
