Framework for creating 2D games - panel, mouse listeners, keyboard listeners and game loop wrapped into an extendable class.

# Example Usage

```java
public class Game2D extends Base2DFramework implements Loop {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		LookAndFeelUtils.setNimbusLookAndfeel();
		Game2D app = new Game2D();

	}

	GameLoop loop = new GameLoop(this);

	public Game2D() {

		super();

		JFrame frame = new JFrame();
		frame.setTitle("Example");
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);
		frame.add(this);

		frame.setVisible(true);

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

	}

}
```
