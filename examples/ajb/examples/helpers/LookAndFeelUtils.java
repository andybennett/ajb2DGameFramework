package ajb.examples.helpers;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
			UIManager.put("control", Colours.background);
			UIManager.put("nimbusBase", Colours.background);
			UIManager.put("nimbusFocus", Colours.background);
			UIManager.put("nimbusLightBackground", Colours.background);
			UIManager.put("nimbusSelectionBackground", Colours.background);
			UIManager.put("text", Colours.green);
			UIManager.put("List[Selected].textForeground", Colours.green);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
			e.printStackTrace();
			
		}
	}
}
