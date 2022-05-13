package org.jfree.chart.swing.editor;


import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class DefaultAxisEditorRefactoring2 implements Serializable {
	private Font tickLabelFont;
	private final JTextField tickLabelFontField;

	public DefaultAxisEditorRefactoring2(FontDisplayField FontDisplayField) {
		this.tickLabelFontField = FontDisplayField;
	}

	public Font getTickLabelFont() {
		return tickLabelFont;
	}

	public void setTickLabelFont(Font tickLabelFont) {
		this.tickLabelFont = tickLabelFont;
	}

	public JTextField getTickLabelFontField() {
		return tickLabelFontField;
	}

	/**
	* Presents a tick label font selection dialog to the user.
	*/
	public void attemptTickLabelFontSelection(DefaultAxisEditor defaultAxisEditor) {
		FontChooserPanel panel = new FontChooserPanel(this.tickLabelFont);
		int result = JOptionPane.showConfirmDialog(defaultAxisEditor, panel,
				DefaultAxisEditor.localizationResources.getString("Font_Selection"), JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			this.tickLabelFont = panel.getSelectedFont();
			this.tickLabelFontField.setText(this.tickLabelFont.getFontName() + " " + this.tickLabelFont.getSize());
		}
	}
}