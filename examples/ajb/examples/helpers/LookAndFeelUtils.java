package ajb.examples.helpers;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

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
			UIManager.put("control", Color.decode("#1E1E1E"));
			UIManager.put("nimbusBase", Color.decode("#1E1E1E"));
			UIManager.put("nimbusFocus", Color.decode("#1E1E1E"));
			UIManager.put("nimbusLightBackground", Color.decode("#1E1E1E"));
			UIManager.put("nimbusSelectionBackground", Color.decode("#1E1E1E"));
			UIManager.put("text", Color.decode("#FFFFFF"));
			UIManager.put("List[Selected].textForeground", Color.decode("#FFFFFF"));

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

}
