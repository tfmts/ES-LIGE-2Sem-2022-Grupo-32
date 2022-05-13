package org.jfree.chart.renderer.xy;


import java.util.Map;
import java.io.Serializable;

public class XYLineAndShapeRenderer_refactoring7 implements Serializable, Cloneable {
	private Map<Integer, Boolean> seriesShapesFilledMap;

	public Map<Integer, Boolean> getSeriesShapesFilledMap() {
		return seriesShapesFilledMap;
	}

	public void setSeriesShapesFilledMap(Map<Integer, Boolean> seriesShapesFilledMap) {
		this.seriesShapesFilledMap = seriesShapesFilledMap;
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are filled.
	* @param series         the series index (zero-based).
	* @return        A boolean.
	* @see #setSeriesShapesFilled(int,Boolean)
	*/
	public Boolean getSeriesShapesFilled(int series) {
		return this.seriesShapesFilledMap.get(series);
	}

	/**
	* Sets the 'shapes filled' flag for a series and sends a       {@link RendererChangeEvent}        to all registered listeners.
	* @param series         the series index (zero-based).
	* @param flag         the flag.
	* @see #getSeriesShapesFilled(int)
	*/
	public void setSeriesShapesFilled(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		this.seriesShapesFilledMap.put(series, flag);
		xYLineAndShapeRenderer.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYLineAndShapeRenderer_refactoring7) super.clone();
	}
}