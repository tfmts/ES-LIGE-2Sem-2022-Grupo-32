package org.jfree.chart.swing.editor;


import javax.swing.JList;
import java.awt.Font;
import javax.swing.ListModel;
import java.io.Serializable;

public class FontChooserPanelRefactoring2 implements Serializable {
	private FontChooserPanelRefactoring1 fontChooserPanelRefactoring1 = new FontChooserPanelRefactoring1();
	private JList fontlist;

	public FontChooserPanelRefactoring1 getFontChooserPanelRefactoring1() {
		return fontChooserPanelRefactoring1;
	}

	public JList getFontlist() {
		return fontlist;
	}

	public void setFontlist(JList fontlist) {
		this.fontlist = fontlist;
	}

	/**
	* Returns a Font object representing the selection in the panel.
	* @return  the font.
	*/
	public Font getSelectedFont(FontChooserPanel fontChooserPanel) {
		return new Font(getSelectedName(), fontChooserPanelRefactoring1.getSelectedStyle(),
				fontChooserPanel.getSelectedSize());
	}

	/**
	* Initializes the contents of the dialog from the given font object.
	* @param font  the font from which to read the properties.
	*/
	public void setSelectedFont(Font font, JList thisSizelist) {
		if (font == null) {
			throw new NullPointerException();
		}
		this.fontChooserPanelRefactoring1.getBold().setSelected(font.isBold());
		this.fontChooserPanelRefactoring1.getItalic().setSelected(font.isItalic());
		final String fontName = font.getName();
		ListModel model = this.fontlist.getModel();
		this.fontlist.clearSelection();
		for (int i = 0; i < model.getSize(); i++) {
			if (fontName.equals(model.getElementAt(i))) {
				this.fontlist.setSelectedIndex(i);
				break;
			}
		}
		final String fontSize = String.valueOf(font.getSize());
		model = thisSizelist.getModel();
		thisSizelist.clearSelection();
		for (int i = 0; i < model.getSize(); i++) {
			if (fontSize.equals(model.getElementAt(i))) {
				thisSizelist.setSelectedIndex(i);
				break;
			}
		}
	}

	/**
	* Returns the selected name.
	* @return  the name.
	*/
	public String getSelectedName() {
		return (String) this.fontlist.getSelectedValue();
	}
}