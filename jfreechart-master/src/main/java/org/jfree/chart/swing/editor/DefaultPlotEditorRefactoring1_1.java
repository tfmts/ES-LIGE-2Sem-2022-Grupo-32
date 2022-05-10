package org.jfree.chart.swing.editor;


import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class DefaultPlotEditorRefactoring1_1 implements Serializable {
	private DefaultPlotEditorRefactoring1_1_1 defaultPlotEditorRefactoring1_1_1 = new DefaultPlotEditorRefactoring1_1_1();
	private PaintSample backgroundPaintSample;
	public PaintSample getBackgroundPaintSample() {
		return backgroundPaintSample;
	}

	public void setBackgroundPaintSample(PaintSample backgroundPaintSample) {
		this.backgroundPaintSample = backgroundPaintSample;
	}

	public StrokeSample getOutlineStrokeSample() {
		return defaultPlotEditorRefactoring1_1_1.getOutlineStrokeSample();
	}

	public void setOutlineStrokeSample(StrokeSample outlineStrokeSample) {
		defaultPlotEditorRefactoring1_1_1.setOutlineStrokeSample(outlineStrokeSample);
	}

	public StrokeSample[] getAvailableStrokeSamples() {
		return defaultPlotEditorRefactoring1_1_1.getAvailableStrokeSamples();
	}

	public void setAvailableStrokeSamples(StrokeSample[] availableStrokeSamples) {
		defaultPlotEditorRefactoring1_1_1.setAvailableStrokeSamples(availableStrokeSamples);
	}

	/**
	* Allow the user to change the background paint.
	*/
	public void attemptBackgroundPaintSelection(DefaultPlotEditor defaultPlotEditor) {
		Color c;
		c = JColorChooser.showDialog(defaultPlotEditor,
				DefaultPlotEditor.localizationResources.getString("Background_Color"), Color.BLUE);
		if (c != null) {
			this.backgroundPaintSample.setPaint(c);
		}
	}

	/**
	* Allow the user to change the outline stroke.
	*/
	public void attemptOutlineStrokeSelection(DefaultPlotEditor defaultPlotEditor) {
		defaultPlotEditorRefactoring1_1_1.attemptOutlineStrokeSelection(defaultPlotEditor);
	}
}