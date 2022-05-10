package org.jfree.chart.swing.editor;


import org.jfree.chart.plot.PlotOrientation;
import javax.swing.JComboBox;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import java.io.Serializable;

public class DefaultPlotEditorRefactoring3 implements Serializable {
	private PlotOrientation plotOrientation;
	private JComboBox orientationCombo;

	public PlotOrientation getPlotOrientation() {
		return plotOrientation;
	}

	public void setPlotOrientation(PlotOrientation plotOrientation) {
		this.plotOrientation = plotOrientation;
	}

	public JComboBox getOrientationCombo() {
		return orientationCombo;
	}

	public void setOrientationCombo(JComboBox orientationCombo) {
		this.orientationCombo = orientationCombo;
	}

	/**
	* Allow the user to modify the plot orientation if this is an editor for a <tt>CategoryPlot</tt> or a <tt>XYPlot</tt>.
	*/
	public void attemptOrientationSelection() {
		int index = this.orientationCombo.getSelectedIndex();
		if (index == DefaultPlotEditor.ORIENTATION_VERTICAL) {
			this.plotOrientation = PlotOrientation.VERTICAL;
		} else {
			this.plotOrientation = PlotOrientation.HORIZONTAL;
		}
	}

	public void updatePlotPropertiesRefactoring3(Plot plot) {
		if (this.plotOrientation != null) {
			if (plot instanceof CategoryPlot) {
				CategoryPlot p = (CategoryPlot) plot;
				p.setOrientation(this.plotOrientation);
			} else if (plot instanceof XYPlot) {
				XYPlot p = (XYPlot) plot;
				p.setOrientation(this.plotOrientation);
			}
		}
	}
}