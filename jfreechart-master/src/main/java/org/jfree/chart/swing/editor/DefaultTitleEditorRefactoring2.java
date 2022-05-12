package org.jfree.chart.swing.editor;


import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.io.Serializable;

public class DefaultTitleEditorRefactoring2 implements Serializable {
	private boolean showTitle;
	private final JCheckBox showTitleCheckBox;
	private final JTextField titleField;
	private final JButton selectFontButton;
	private final JButton selectPaintButton;

	public DefaultTitleEditorRefactoring2(JTextField JTextField, JCheckBox JCheckBox, JButton JButton) {
		this.titleField = JTextField;
		this.showTitleCheckBox = JCheckBox;
		this.selectFontButton = JButton;
		this.selectPaintButton = JButton;
	}

	public boolean getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}

	public JCheckBox getShowTitleCheckBox() {
		return showTitleCheckBox;
	}

	public JTextField getTitleField() {
		return titleField;
	}

	public JButton getSelectFontButton() {
		return selectFontButton;
	}

	public JButton getSelectPaintButton() {
		return selectPaintButton;
	}

	/**
	* Allow the user the opportunity to change whether the title is displayed on the chart or not.
	*/
	public void attemptModifyShowTitle() {
		this.showTitle = this.showTitleCheckBox.isSelected();
		this.enableOrDisableControls();
	}

	/**
	* Returns the title text entered in the panel.
	* @return  The title text entered in the panel.
	*/
	public String getTitleText() {
		return this.titleField.getText();
	}

	/**
	* If we are supposed to show the title, the controls are enabled. If we are not supposed to show the title, the controls are disabled.
	*/
	public void enableOrDisableControls() {
		boolean enabled = (this.showTitle == true);
		this.titleField.setEnabled(enabled);
		this.selectFontButton.setEnabled(enabled);
		this.selectPaintButton.setEnabled(enabled);
	}
}