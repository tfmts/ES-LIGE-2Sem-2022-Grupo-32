package org.jfree.chart.renderer.xy;


import java.util.Map;
import java.io.Serializable;

public class XYLineAndShapeRenderer_refactoring6 implements Serializable, Cloneable {
	private XYLineAndShapeRenderer_refactoring7 xYLineAndShapeRenderer_refactoring7 = new XYLineAndShapeRenderer_refactoring7();
	private boolean defaultShapesFilled;

	public Map<Integer, Boolean> getSeriesShapesFilledMap() {
		return xYLineAndShapeRenderer_refactoring7.getSeriesShapesFilledMap();
	}

	public void setSeriesShapesFilledMap(Map<Integer, Boolean> seriesShapesFilledMap) {
		xYLineAndShapeRenderer_refactoring7.setSeriesShapesFilledMap(seriesShapesFilledMap);
	}

	public boolean getDefaultShapesFilled() {
		return defaultShapesFilled;
	}

	public void setDefaultShapesFilled2(boolean defaultShapesFilled) {
		this.defaultShapesFilled = defaultShapesFilled;
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are filled.
	* @param series        the series index (zero-based).
	* @return       A boolean.
	* @see #setSeriesShapesFilled(int,Boolean)
	*/
	public Boolean getSeriesShapesFilled(int series) {
		return xYLineAndShapeRenderer_refactoring7.getSeriesShapesFilled(series);
	}

	/**
	* Sets the 'shapes filled' flag for a series and sends a      {@link RendererChangeEvent}       to all registered listeners.
	* @param series        the series index (zero-based).
	* @param flag        the flag.
	* @see #getSeriesShapesFilled(int)
	*/
	public void setSeriesShapesFilled(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring7.setSeriesShapesFilled(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is filled. <p> The default implementation passes control to the      {@code       getSeriesShapesFilled}       method. You can override this method if you require different behaviour.
	* @param series        the series index (zero-based).
	* @param item        the item index (zero-based).
	* @return       A boolean.
	*/
	public boolean getItemShapeFilled(int series, int item) {
		Boolean flag = xYLineAndShapeRenderer_refactoring7.getSeriesShapesFilled(series);
		if (flag != null) {
			return flag;
		}
		return this.defaultShapesFilled;
	}

	/**
	* Sets the default 'shapes filled' flag and sends a      {@link RendererChangeEvent}       to all registered listeners.
	* @param flag        the flag.
	* @see #getDefaultShapesFilled()
	*/
	public void setDefaultShapesFilled(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		this.defaultShapesFilled = flag;
		xYLineAndShapeRenderer.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYLineAndShapeRenderer_refactoring6) super.clone();
	}
}