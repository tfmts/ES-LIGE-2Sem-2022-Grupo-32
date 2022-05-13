package org.jfree.chart.swing.editor;


import org.jfree.chart.api.RectangleInsets;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class DefaultPlotEditorRefactoring1 implements Serializable {
	private DefaultPlotEditorRefactoring1_1 defaultPlotEditorRefactoring1_1 = new DefaultPlotEditorRefactoring1_1();
	private PaintSample outlinePaintSample;
	private RectangleInsets plotInsets;

	public PaintSample getBackgroundPaintSample() {
		return defaultPlotEditorRefactoring1_1.getBackgroundPaintSample();
	}

	public void setBackgroundPaintSample(PaintSample backgroundPaintSample) {
		defaultPlotEditorRefactoring1_1.setBackgroundPaintSample(backgroundPaintSample);
	}

	public StrokeSample getOutlineStrokeSample() {
		return defaultPlotEditorRefactoring1_1.getOutlineStrokeSample();
	}

	public void setOutlineStrokeSample(StrokeSample outlineStrokeSample) {
		defaultPlotEditorRefactoring1_1.setOutlineStrokeSample(outlineStrokeSample);
	}

	public PaintSample getOutlinePaintSample() {
		return outlinePaintSample;
	}

	public void setOutlinePaintSample(PaintSample outlinePaintSample) {
		this.outlinePaintSample = outlinePaintSample;
	}

	public StrokeSample[] getAvailableStrokeSamples() {
		return defaultPlotEditorRefactoring1_1.getAvailableStrokeSamples();
	}

	public void setAvailableStrokeSamples(StrokeSample[] availableStrokeSamples) {
		defaultPlotEditorRefactoring1_1.setAvailableStrokeSamples(availableStrokeSamples);
	}

	public void setPlotInsets(RectangleInsets plotInsets) {
		this.plotInsets = plotInsets;
	}

	/**
	* Allow the user to change the background paint.
	*/
	public void attemptBackgroundPaintSelection(DefaultPlotEditor defaultPlotEditor) {
		defaultPlotEditorRefactoring1_1.attemptBackgroundPaintSelection(defaultPlotEditor);
	}

	/**
	* Allow the user to change the outline stroke.
	*/
	public void attemptOutlineStrokeSelection(DefaultPlotEditor defaultPlotEditor) {
		defaultPlotEditorRefactoring1_1.attemptOutlineStrokeSelection(defaultPlotEditor);
	}

	/**
	* Allow the user to change the outline paint.  We use JColorChooser, so the user can only choose colors (a subset of all possible paints).
	*/
	public void attemptOutlinePaintSelection(DefaultPlotEditor defaultPlotEditor) {
		Color c;
		c = JColorChooser.showDialog(defaultPlotEditor,
				DefaultPlotEditor.localizationResources.getString("Outline_Color"), Color.BLUE);
		if (c != null) {
			this.outlinePaintSample.setPaint(c);
		}
	}

	/**
	* Returns the current plot insets.
	* @return  The current plot insets.
	*/
	public RectangleInsets getPlotInsets() {
		if (this.plotInsets == null) {
			this.plotInsets = new RectangleInsets(0.0, 0.0, 0.0, 0.0);
		}
		return this.plotInsets;
	}
}