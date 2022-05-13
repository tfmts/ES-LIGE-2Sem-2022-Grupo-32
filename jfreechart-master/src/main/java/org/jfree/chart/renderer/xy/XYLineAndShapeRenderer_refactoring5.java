package org.jfree.chart.renderer.xy;


import java.util.Map;
import java.io.Serializable;

public class XYLineAndShapeRenderer_refactoring5 implements Serializable, Cloneable {
	private XYLineAndShapeRenderer_refactoring10 xYLineAndShapeRenderer_refactoring10 = new XYLineAndShapeRenderer_refactoring10();
	private XYLineAndShapeRenderer_refactoring9 xYLineAndShapeRenderer_refactoring9 = new XYLineAndShapeRenderer_refactoring9();
	private XYLineAndShapeRenderer_refactoring6 xYLineAndShapeRenderer_refactoring6 = new XYLineAndShapeRenderer_refactoring6();
	private boolean defaultLinesVisible;
	public Map<Integer, Boolean> getSeriesLinesVisibleMap() {
		return xYLineAndShapeRenderer_refactoring10.getSeriesLinesVisibleMap();
	}

	public void setSeriesLinesVisibleMap(Map<Integer, Boolean> seriesLinesVisibleMap) {
		xYLineAndShapeRenderer_refactoring10.setSeriesLinesVisibleMap(seriesLinesVisibleMap);
	}

	public boolean getDefaultLinesVisible() {
		return defaultLinesVisible;
	}

	public void setDefaultLinesVisible2(boolean defaultLinesVisible) {
		this.defaultLinesVisible = defaultLinesVisible;
	}

	public Map<Integer, Boolean> getSeriesShapesVisibleMap() {
		return xYLineAndShapeRenderer_refactoring9.getXYLineAndShapeRenderer_refactoring8().getSeriesShapesVisibleMap();
	}

	public void setSeriesShapesVisibleMap(Map<Integer, Boolean> seriesShapesVisibleMap) {
		xYLineAndShapeRenderer_refactoring9.getXYLineAndShapeRenderer_refactoring8().setSeriesShapesVisibleMap(seriesShapesVisibleMap);
	}

	public boolean getDefaultShapesVisible() {
		return xYLineAndShapeRenderer_refactoring9.getDefaultShapesVisible();
	}

	public void setDefaultShapesVisible2(boolean defaultShapesVisible) {
		xYLineAndShapeRenderer_refactoring9.setDefaultShapesVisible2(defaultShapesVisible);
	}

	public Map<Integer, Boolean> getSeriesShapesFilledMap() {
		return xYLineAndShapeRenderer_refactoring6.getSeriesShapesFilledMap();
	}

	public void setSeriesShapesFilledMap(Map<Integer, Boolean> seriesShapesFilledMap) {
		xYLineAndShapeRenderer_refactoring6.setSeriesShapesFilledMap(seriesShapesFilledMap);
	}

	public boolean getDefaultShapesFilled() {
		return xYLineAndShapeRenderer_refactoring6.getDefaultShapesFilled();
	}

	public void setDefaultShapesFilled2(boolean defaultShapesFilled) {
		xYLineAndShapeRenderer_refactoring6.setDefaultShapesFilled2(defaultShapesFilled);
	}

	/**
	* Returns the flag used to control whether or not the lines for a series are visible.
	* @param series       the series index (zero-based).
	* @return      The flag (possibly      {@code      null}     ).
	* @see #setSeriesLinesVisible(int,Boolean)
	*/
	public Boolean getSeriesLinesVisible(int series) {
		return xYLineAndShapeRenderer_refactoring10.getSeriesLinesVisible(series);
	}

	/**
	* Sets the 'lines visible' flag for a series and sends a     {@link RendererChangeEvent}      to all registered listeners.
	* @param series       the series index (zero-based).
	* @param flag       the flag (     {@code      null}      permitted).
	* @see #getSeriesLinesVisible(int)
	*/
	public void setSeriesLinesVisible(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring10.setSeriesLinesVisible(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is visible.
	* @param series       the series index (zero-based).
	* @param item       the item index (zero-based).
	* @return      A boolean.
	*/
	public boolean getItemLineVisible(int series, int item) {
		Boolean flag = xYLineAndShapeRenderer_refactoring10.getSeriesLinesVisible(series);
		if (flag != null) {
			return flag;
		}
		return this.defaultLinesVisible;
	}

	/**
	* Sets the default 'lines visible' flag and sends a     {@link RendererChangeEvent}      to all registered listeners.
	* @param flag       the flag.
	* @see #getDefaultLinesVisible()
	*/
	public void setDefaultLinesVisible(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		this.defaultLinesVisible = flag;
		xYLineAndShapeRenderer.fireChangeEvent();
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are visible.
	* @param series       the series index (zero-based).
	* @return      A boolean.
	* @see #setSeriesShapesVisible(int,Boolean)
	*/
	public Boolean getSeriesShapesVisible(int series) {
		return xYLineAndShapeRenderer_refactoring9.getXYLineAndShapeRenderer_refactoring8().getSeriesShapesVisible(series);
	}

	/**
	* Sets the 'shapes visible' flag for a series and sends a     {@link RendererChangeEvent}      to all registered listeners.
	* @param series       the series index (zero-based).
	* @param flag       the flag.
	* @see #getSeriesShapesVisible(int)
	*/
	public void setSeriesShapesVisible(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring9.getXYLineAndShapeRenderer_refactoring8().setSeriesShapesVisible(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is visible. <p> The default implementation passes control to the     {@code      getSeriesShapesVisible()}      method. You can override this method if you require different behaviour.
	* @param series       the series index (zero-based).
	* @param item       the item index (zero-based).
	* @return      A boolean.
	*/
	public boolean getItemShapeVisible(int series, int item) {
		return xYLineAndShapeRenderer_refactoring9.getItemShapeVisible(series, item);
	}

	/**
	* Sets the default 'shapes visible' flag and sends a     {@link RendererChangeEvent}      to all registered listeners.
	* @param flag       the flag.
	* @see #getDefaultShapesVisible()
	*/
	public void setDefaultShapesVisible(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring9.setDefaultShapesVisible(flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are filled.
	* @param series       the series index (zero-based).
	* @return      A boolean.
	* @see #setSeriesShapesFilled(int,Boolean)
	*/
	public Boolean getSeriesShapesFilled(int series) {
		return xYLineAndShapeRenderer_refactoring6.getSeriesShapesFilled(series);
	}

	/**
	* Sets the 'shapes filled' flag for a series and sends a     {@link RendererChangeEvent}      to all registered listeners.
	* @param series       the series index (zero-based).
	* @param flag       the flag.
	* @see #getSeriesShapesFilled(int)
	*/
	public void setSeriesShapesFilled(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring6.setSeriesShapesFilled(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is filled. <p> The default implementation passes control to the     {@code      getSeriesShapesFilled}      method. You can override this method if you require different behaviour.
	* @param series       the series index (zero-based).
	* @param item       the item index (zero-based).
	* @return      A boolean.
	*/
	public boolean getItemShapeFilled(int series, int item) {
		return xYLineAndShapeRenderer_refactoring6.getItemShapeFilled(series, item);
	}

	/**
	* Sets the default 'shapes filled' flag and sends a     {@link RendererChangeEvent}      to all registered listeners.
	* @param flag       the flag.
	* @see #getDefaultShapesFilled()
	*/
	public void setDefaultShapesFilled(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring6.setDefaultShapesFilled(flag, xYLineAndShapeRenderer);
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYLineAndShapeRenderer_refactoring5) super.clone();
	}
}