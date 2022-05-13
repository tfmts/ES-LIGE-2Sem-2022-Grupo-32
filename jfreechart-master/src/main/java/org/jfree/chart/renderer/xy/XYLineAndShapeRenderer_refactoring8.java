package org.jfree.chart.renderer.xy;


import java.util.Map;
import java.io.Serializable;

public class XYLineAndShapeRenderer_refactoring8 implements Serializable, Cloneable {
	private Map<Integer, Boolean> seriesShapesVisibleMap;

	public Map<Integer, Boolean> getSeriesShapesVisibleMap() {
		return seriesShapesVisibleMap;
	}

	public void setSeriesShapesVisibleMap(Map<Integer, Boolean> seriesShapesVisibleMap) {
		this.seriesShapesVisibleMap = seriesShapesVisibleMap;
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are visible.
	* @param series        the series index (zero-based).
	* @return       A boolean.
	* @see #setSeriesShapesVisible(int,Boolean)
	*/
	public Boolean getSeriesShapesVisible(int series) {
		return this.seriesShapesVisibleMap.get(series);
	}

	/**
	* Sets the 'shapes visible' flag for a series and sends a      {@link RendererChangeEvent}       to all registered listeners.
	* @param series        the series index (zero-based).
	* @param flag        the flag.
	* @see #getSeriesShapesVisible(int)
	*/
	public void setSeriesShapesVisible(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		this.seriesShapesVisibleMap.put(series, flag);
		xYLineAndShapeRenderer.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYLineAndShapeRenderer_refactoring8) super.clone();
	}
}