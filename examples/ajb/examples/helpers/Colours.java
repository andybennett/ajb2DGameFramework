package ajb.examples.helpers;

import java.awt.Color;

public class Colours {

	public static Color background = Color.decode("#242424");
	public static Color blue = Color.decode("#8ac6f2");
	public static Color red = Color.decode("#f08080");
	public static Color green = Color.decode("#cae682");
	public static Color white = Color.decode("#f3f6ee");
	public static Color gray = Color.decode("#656565");

	public static Color makeTransparent(Color source, int alpha) {

		return new Color(source.getRed(), source.getGreen(), source.getBlue(), alpha);

	}

	public static Color getColor(double percent) {

		double power = percent / 100;

		double H = power * 0.4; // Hue (note 0.4 = Green, see huge chart below)
		double S = 0.3; // Saturation
		double B = 0.3; // Brightness

		return Color.getHSBColor((float) H, (float) S, (float) B);

	}
}
