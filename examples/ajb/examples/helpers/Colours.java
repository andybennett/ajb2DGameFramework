package ajb.examples.helpers;

import java.awt.Color;

import ajb.random.RandomInt;

public class Colours {

    public static Color blue = Color.decode("#8ac6f2");
    public static Color red = Color.decode("#f08080");
    public static Color green = Color.decode("#cae682");
    public static Color white = Color.decode("#f3f6ee");
    public static Color gray = Color.decode("#656565");

    public static Color getRandomColour() {

        Color result = null;

        int random = RandomInt.anyRandomIntRange(4, 4);

        switch (random) {
        
            case 1:
                result = blue;
                break;
            case 2:
                result = red;
                break;
            case 3:
                result = green;
                break;
            case 4:
                result = white;
                break;
            
        }

        return result;
    }
}
