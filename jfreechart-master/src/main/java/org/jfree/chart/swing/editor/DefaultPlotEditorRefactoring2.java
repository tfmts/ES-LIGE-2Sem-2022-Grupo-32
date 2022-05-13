package org.jfree.chart.swing.editor;


import org.jfree.chart.plot.Plot;
import org.jfree.chart.axis.Axis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import javax.swing.JTabbedPane;
import javax.swing.BorderFactory;
import org.jfree.chart.plot.PolarPlot;
import java.io.Serializable;

public class DefaultPlotEditorRefactoring2 implements Serializable {
	private DefaultAxisEditor domainAxisPropertyPanel;
	private DefaultAxisEditor rangeAxisPropertyPanel;

	public DefaultAxisEditor getDomainAxisPropertyPanel() {
		return domainAxisPropertyPanel;
	}

	public DefaultAxisEditor getRangeAxisPropertyPanel() {
		return rangeAxisPropertyPanel;
	}

	public void updatePlotPropertiesRefactoring1(Plot plot) {
		if (this.domainAxisPropertyPanel != null) {
			Axis domainAxis = null;
			if (plot instanceof CategoryPlot) {
				CategoryPlot p = (CategoryPlot) plot;
				domainAxis = p.getDomainAxis();
			} else if (plot instanceof XYPlot) {
				XYPlot p = (XYPlot) plot;
				domainAxis = p.getDomainAxis();
			}
			if (domainAxis != null) {
				this.domainAxisPropertyPanel.setAxisProperties(domainAxis);
			}
		}
	}

	/**
	* Creates a tabbed pane for the plot.
	* @param plot   the plot.
	* @return  A tabbed pane. 
	*/
	public JTabbedPane createPlotTabs(Plot plot) {
		JTabbedPane tabs = new JTabbedPane();
		tabs.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		Axis domainAxis = null;
		if (plot instanceof CategoryPlot) {
			domainAxis = ((CategoryPlot) plot).getDomainAxis();
		} else if (plot instanceof XYPlot) {
			domainAxis = ((XYPlot) plot).getDomainAxis();
		}
		this.domainAxisPropertyPanel = DefaultAxisEditor.getInstance(domainAxis);
		if (this.domainAxisPropertyPanel != null) {
			this.domainAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			tabs.add(DefaultPlotEditor.localizationResources.getString("Domain_Axis"), this.domainAxisPropertyPanel);
		}
		Axis rangeAxis = null;
		if (plot instanceof CategoryPlot) {
			rangeAxis = ((CategoryPlot) plot).getRangeAxis();
		} else if (plot instanceof XYPlot) {
			rangeAxis = ((XYPlot) plot).getRangeAxis();
		} else if (plot instanceof PolarPlot) {
			rangeAxis = ((PolarPlot) plot).getAxis();
		}
		this.rangeAxisPropertyPanel = DefaultAxisEditor.getInstance(rangeAxis);
		if (this.rangeAxisPropertyPanel != null) {
			this.rangeAxisPropertyPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			tabs.add(DefaultPlotEditor.localizationResources.getString("Range_Axis"), this.rangeAxisPropertyPanel);
		}
		return tabs;
	}

	public void updatePlotPropertiesRefactoring2(Plot plot) {
		if (this.rangeAxisPropertyPanel != null) {
			Axis rangeAxis = null;
			if (plot instanceof CategoryPlot) {
				CategoryPlot p = (CategoryPlot) plot;
				rangeAxis = p.getRangeAxis();
			} else if (plot instanceof XYPlot) {
				XYPlot p = (XYPlot) plot;
				rangeAxis = p.getRangeAxis();
			} else if (plot instanceof PolarPlot) {
				PolarPlot p = (PolarPlot) plot;
				rangeAxis = p.getAxis();
			}
			if (rangeAxis != null) {
				this.rangeAxisPropertyPanel.setAxisProperties(rangeAxis);
			}
		}
	}
}