package ajb;

public class App {

    @SuppressWarnings("unused")
    public App() {
        try {
            MyGame game = new MyGame("Demo", 800, 600);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        App app = new App();
    }
}
