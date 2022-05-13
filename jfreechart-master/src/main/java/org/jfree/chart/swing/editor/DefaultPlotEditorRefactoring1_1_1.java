package org.jfree.chart.swing.editor;


import javax.swing.JOptionPane;
import java.io.Serializable;

public class DefaultPlotEditorRefactoring1_1_1 implements Serializable {
	private StrokeSample outlineStrokeSample;
	private StrokeSample[] availableStrokeSamples;

	public StrokeSample getOutlineStrokeSample() {
		return outlineStrokeSample;
	}

	public void setOutlineStrokeSample(StrokeSample outlineStrokeSample) {
		this.outlineStrokeSample = outlineStrokeSample;
	}

	public StrokeSample[] getAvailableStrokeSamples() {
		return availableStrokeSamples;
	}

	public void setAvailableStrokeSamples(StrokeSample[] availableStrokeSamples) {
		this.availableStrokeSamples = availableStrokeSamples;
	}

	/**
	* Allow the user to change the outline stroke.
	*/
	public void attemptOutlineStrokeSelection(DefaultPlotEditor defaultPlotEditor) {
		StrokeChooserPanel panel = new StrokeChooserPanel(this.outlineStrokeSample, this.availableStrokeSamples);
		int result = JOptionPane.showConfirmDialog(defaultPlotEditor, panel,
				DefaultPlotEditor.localizationResources.getString("Stroke_Selection"), JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			this.outlineStrokeSample.setStroke(panel.getSelectedStroke());
		}
	}
}