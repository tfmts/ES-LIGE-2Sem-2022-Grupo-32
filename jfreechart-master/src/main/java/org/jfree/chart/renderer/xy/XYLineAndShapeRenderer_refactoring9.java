package org.jfree.chart.renderer.xy;


import java.io.Serializable;

public class XYLineAndShapeRenderer_refactoring9 implements Serializable, Cloneable {
	private XYLineAndShapeRenderer_refactoring8 xYLineAndShapeRenderer_refactoring8 = new XYLineAndShapeRenderer_refactoring8();
	private boolean defaultShapesVisible;

	public XYLineAndShapeRenderer_refactoring8 getXYLineAndShapeRenderer_refactoring8() {
		return xYLineAndShapeRenderer_refactoring8;
	}

	public boolean getDefaultShapesVisible() {
		return defaultShapesVisible;
	}

	public void setDefaultShapesVisible2(boolean defaultShapesVisible) {
		this.defaultShapesVisible = defaultShapesVisible;
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is visible. <p> The default implementation passes control to the      {@code       getSeriesShapesVisible()}       method. You can override this method if you require different behaviour.
	* @param series        the series index (zero-based).
	* @param item        the item index (zero-based).
	* @return       A boolean.
	*/
	public boolean getItemShapeVisible(int series, int item) {
		Boolean flag = xYLineAndShapeRenderer_refactoring8.getSeriesShapesVisible(series);
		if (flag != null) {
			return flag;
		}
		return this.defaultShapesVisible;
	}

	/**
	* Sets the default 'shapes visible' flag and sends a      {@link RendererChangeEvent}       to all registered listeners.
	* @param flag        the flag.
	* @see #getDefaultShapesVisible()
	*/
	public void setDefaultShapesVisible(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		this.defaultShapesVisible = flag;
		xYLineAndShapeRenderer.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYLineAndShapeRenderer_refactoring9) super.clone();
	}
}