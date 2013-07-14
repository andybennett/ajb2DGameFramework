package ajb;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
    public MyFrame(String title) {
        super();
        setTitle(title);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
