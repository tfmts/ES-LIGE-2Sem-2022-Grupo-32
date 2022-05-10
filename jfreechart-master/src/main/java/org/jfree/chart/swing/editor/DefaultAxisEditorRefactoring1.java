package org.jfree.chart.swing.editor;


import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class DefaultAxisEditorRefactoring1 implements Serializable {
	private Font labelFont;
	private final JTextField labelFontField;

	public DefaultAxisEditorRefactoring1(FontDisplayField FontDisplayField) {
		this.labelFontField = FontDisplayField;
	}

	public Font getLabelFont() {
		return labelFont;
	}

	public void setLabelFont(Font labelFont) {
		this.labelFont = labelFont;
	}

	public JTextField getLabelFontField() {
		return labelFontField;
	}

	/**
	* Presents a font selection dialog to the user.
	*/
	public void attemptLabelFontSelection(DefaultAxisEditor defaultAxisEditor) {
		FontChooserPanel panel = new FontChooserPanel(this.labelFont);
		int result = JOptionPane.showConfirmDialog(defaultAxisEditor, panel,
				DefaultAxisEditor.localizationResources.getString("Font_Selection"), JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			this.labelFont = panel.getSelectedFont();
			this.labelFontField.setText(this.labelFont.getFontName() + " " + this.labelFont.getSize());
		}
	}
}