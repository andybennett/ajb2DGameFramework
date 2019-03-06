package ajb.examples;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import ajb.examples.helpers.LookAndFeelUtils;

public class SwingExample {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		LookAndFeelUtils.setNimbusLookAndfeel();
		SwingExample app = new SwingExample();

	}

	public SwingExample() {

		super();

		JFrame frame = new JFrame();
		frame.setTitle("Example");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);

		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Panel1", new JPanel());

		frame.add(tabs);

		frame.setVisible(true);

	}

}
