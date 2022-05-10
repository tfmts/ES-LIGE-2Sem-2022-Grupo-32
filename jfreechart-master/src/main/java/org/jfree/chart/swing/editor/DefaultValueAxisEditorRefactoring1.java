package org.jfree.chart.swing.editor;


import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.io.Serializable;

public class DefaultValueAxisEditorRefactoring1 implements Serializable {
	private boolean autoTickUnitSelection;
	private JCheckBox autoTickUnitSelectionCheckBox;

	public boolean getAutoTickUnitSelection() {
		return autoTickUnitSelection;
	}

	public void setAutoTickUnitSelection(boolean autoTickUnitSelection) {
		this.autoTickUnitSelection = autoTickUnitSelection;
	}

	public JCheckBox getAutoTickUnitSelectionCheckBox() {
		return autoTickUnitSelectionCheckBox;
	}

	public void setAutoTickUnitSelectionCheckBox(JCheckBox autoTickUnitSelectionCheckBox) {
		this.autoTickUnitSelectionCheckBox = autoTickUnitSelectionCheckBox;
	}

	/**
	* Creates a panel for the tick units.
	* @return  A panel. 
	*/
	public JPanel createTickUnitPanel(DefaultValueAxisEditor defaultValueAxisEditor) {
		JPanel tickUnitPanel = new JPanel(new LCBLayout(3));
		tickUnitPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		tickUnitPanel.add(new JPanel());
		this.autoTickUnitSelectionCheckBox = new JCheckBox(
				DefaultValueAxisEditor.localizationResources.getString("Auto-TickUnit_Selection"),
				this.autoTickUnitSelection);
		this.autoTickUnitSelectionCheckBox.setActionCommand("AutoTickOnOff");
		this.autoTickUnitSelectionCheckBox.addActionListener(defaultValueAxisEditor);
		tickUnitPanel.add(this.autoTickUnitSelectionCheckBox);
		tickUnitPanel.add(new JPanel());
		return tickUnitPanel;
	}

	/**
	* Sets the auto-tick unit selection field to the value in the check box.
	*/
	public void toggleAutoTick() {
		this.autoTickUnitSelection = this.autoTickUnitSelectionCheckBox.isSelected();
	}
}