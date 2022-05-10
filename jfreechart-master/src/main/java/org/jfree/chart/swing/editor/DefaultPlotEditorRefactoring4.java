package org.jfree.chart.swing.editor;


import javax.swing.JCheckBox;
import java.io.Serializable;

public class DefaultPlotEditorRefactoring4 implements Serializable {
	private Boolean drawLines;
	private JCheckBox drawLinesCheckBox;

	public Boolean getDrawLines() {
		return drawLines;
	}

	public void setDrawLines(Boolean drawLines) {
		this.drawLines = drawLines;
	}

	public JCheckBox getDrawLinesCheckBox() {
		return drawLinesCheckBox;
	}

	public void setDrawLinesCheckBox(JCheckBox drawLinesCheckBox) {
		this.drawLinesCheckBox = drawLinesCheckBox;
	}

	/**
	* Allow the user to modify whether or not lines are drawn between data points by <tt>LineAndShapeRenderer</tt>s and <tt>StandardXYItemRenderer</tt>s.
	*/
	public void attemptDrawLinesSelection() {
		this.drawLines = this.drawLinesCheckBox.isSelected();
	}
}