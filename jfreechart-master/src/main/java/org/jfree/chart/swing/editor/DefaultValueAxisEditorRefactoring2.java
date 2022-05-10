package org.jfree.chart.swing.editor;


import java.awt.BasicStroke;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class DefaultValueAxisEditorRefactoring2 implements Serializable {
	private final StrokeSample gridStrokeSample;
	private final StrokeSample[] availableStrokeSamples;

	public DefaultValueAxisEditorRefactoring2(StrokeSample StrokeSample) {
		this.gridStrokeSample = StrokeSample;
		this.availableStrokeSamples = new StrokeSample[3];
	}

	public StrokeSample[] getAvailableStrokeSamples() {
		return availableStrokeSamples;
	}

	/**
	* Handle a grid stroke selection.
	*/
	public void attemptGridStrokeSelection(DefaultValueAxisEditor defaultValueAxisEditor) {
		StrokeChooserPanel panel = new StrokeChooserPanel(this.gridStrokeSample, this.availableStrokeSamples);
		int result = JOptionPane.showConfirmDialog(defaultValueAxisEditor, panel,
				DefaultValueAxisEditor.localizationResources.getString("Stroke_Selection"),
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			this.gridStrokeSample.setStroke(panel.getSelectedStroke());
		}
	}
}