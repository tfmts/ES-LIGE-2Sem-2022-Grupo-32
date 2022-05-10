package org.jfree.chart.swing.editor;


import javax.swing.JCheckBox;
import java.awt.Font;
import java.io.Serializable;

public class FontChooserPanelRefactoring1 implements Serializable {
	private JCheckBox bold;
	private JCheckBox italic;

	public JCheckBox getBold() {
		return bold;
	}

	public void setBold(JCheckBox bold) {
		this.bold = bold;
	}

	public JCheckBox getItalic() {
		return italic;
	}

	public void setItalic(JCheckBox italic) {
		this.italic = italic;
	}

	/**
	* Returns the selected style.
	* @return  the style.
	*/
	public int getSelectedStyle() {
		if (this.bold.isSelected() && this.italic.isSelected()) {
			return Font.BOLD + Font.ITALIC;
		}
		if (this.bold.isSelected()) {
			return Font.BOLD;
		}
		if (this.italic.isSelected()) {
			return Font.ITALIC;
		} else {
			return Font.PLAIN;
		}
	}
}