package org.jfree.chart.renderer.xy;


import java.util.Map;
import java.io.Serializable;

public class XYLineAndShapeRenderer_refactoring10 implements Serializable, Cloneable {
	private Map<Integer, Boolean> seriesLinesVisibleMap;

	public Map<Integer, Boolean> getSeriesLinesVisibleMap() {
		return seriesLinesVisibleMap;
	}

	public void setSeriesLinesVisibleMap(Map<Integer, Boolean> seriesLinesVisibleMap) {
		this.seriesLinesVisibleMap = seriesLinesVisibleMap;
	}

	/**
	* Returns the flag used to control whether or not the lines for a series are visible.
	* @param series        the series index (zero-based).
	* @return       The flag (possibly       {@code       null}      ).
	* @see #setSeriesLinesVisible(int,Boolean)
	*/
	public Boolean getSeriesLinesVisible(int series) {
		return this.seriesLinesVisibleMap.get(series);
	}

	/**
	* Sets the 'lines visible' flag for a series and sends a      {@link RendererChangeEvent}       to all registered listeners.
	* @param series        the series index (zero-based).
	* @param flag        the flag (      {@code       null}       permitted).
	* @see #getSeriesLinesVisible(int)
	*/
	public void setSeriesLinesVisible(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		this.seriesLinesVisibleMap.put(series, flag);
		xYLineAndShapeRenderer.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYLineAndShapeRenderer_refactoring10) super.clone();
	}
}