package ajb.examples.helpers;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ajb.colours.ColourUtils;

public class LookAndFeelUtils {

	public static void setNimbusLookAndfeel() {

		// set Nimbus Look And Feel
		try {
			
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
			// to nod display tabs
			UIManager.put("EditorTabDisplayerUI", "icare.ui.tweak.tab.NoTabsTabDisplayerUI");
			
			// because Nimbus LAF do not use opaque option, force it to allow painting of
			// custom tab.
			UIManager.put("TabbedPane.tabsOpaque", true);

			// set Nimbus LAF primary colors
			UIManager.put("control", ColourUtils.background);
			UIManager.put("nimbusBase", ColourUtils.background);
			UIManager.put("nimbusFocus", ColourUtils.background);
			UIManager.put("nimbusLightBackground", ColourUtils.background);
			UIManager.put("nimbusSelectionBackground", ColourUtils.background);
			UIManager.put("text", ColourUtils.green);
			UIManager.put("List[Selected].textForeground", ColourUtils.green);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
			e.printStackTrace();
			
		}
	}
}
