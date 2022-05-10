package org.jfree.chart.swing.editor;


import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class DefaultTitleEditorRefactoring1 implements Serializable {
	private Font titleFont;
	private final JTextField fontfield;

	public DefaultTitleEditorRefactoring1(FontDisplayField FontDisplayField) {
		this.fontfield = FontDisplayField;
	}

	public Font getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
	}

	public JTextField getFontfield() {
		return fontfield;
	}

	/**
	* Presents a font selection dialog to the user.
	*/
	public void attemptFontSelection(DefaultTitleEditor defaultTitleEditor) {
		FontChooserPanel panel = new FontChooserPanel(this.titleFont);
		int result = JOptionPane.showConfirmDialog(defaultTitleEditor, panel,
				DefaultTitleEditor.localizationResources.getString("Font_Selection"), JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			this.titleFont = panel.getSelectedFont();
			this.fontfield.setText(this.titleFont.getFontName() + " " + this.titleFont.getSize());
		}
	}
}