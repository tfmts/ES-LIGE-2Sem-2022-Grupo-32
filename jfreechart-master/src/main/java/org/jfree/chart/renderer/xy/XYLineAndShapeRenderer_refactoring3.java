package org.jfree.chart.renderer.xy;


import java.util.Map;
import java.io.Serializable;

public class XYLineAndShapeRenderer_refactoring3 implements Serializable, Cloneable {
	private XYLineAndShapeRenderer_refactoring4 xYLineAndShapeRenderer_refactoring4 = new XYLineAndShapeRenderer_refactoring4();
	private boolean useFillPaint;

	public Map<Integer, Boolean> getSeriesLinesVisibleMap() {
		return xYLineAndShapeRenderer_refactoring4.getSeriesLinesVisibleMap();
	}

	public void setSeriesLinesVisibleMap(Map<Integer, Boolean> seriesLinesVisibleMap) {
		xYLineAndShapeRenderer_refactoring4.setSeriesLinesVisibleMap(seriesLinesVisibleMap);
	}

	public boolean getDefaultLinesVisible() {
		return xYLineAndShapeRenderer_refactoring4.getDefaultLinesVisible();
	}

	public void setDefaultLinesVisible2(boolean defaultLinesVisible) {
		xYLineAndShapeRenderer_refactoring4.setDefaultLinesVisible2(defaultLinesVisible);
	}

	public Map<Integer, Boolean> getSeriesShapesVisibleMap() {
		return xYLineAndShapeRenderer_refactoring4.getSeriesShapesVisibleMap();
	}

	public void setSeriesShapesVisibleMap(Map<Integer, Boolean> seriesShapesVisibleMap) {
		xYLineAndShapeRenderer_refactoring4.setSeriesShapesVisibleMap(seriesShapesVisibleMap);
	}

	public boolean getDefaultShapesVisible() {
		return xYLineAndShapeRenderer_refactoring4.getDefaultShapesVisible();
	}

	public void setDefaultShapesVisible2(boolean defaultShapesVisible) {
		xYLineAndShapeRenderer_refactoring4.setDefaultShapesVisible2(defaultShapesVisible);
	}

	public Map<Integer, Boolean> getSeriesShapesFilledMap() {
		return xYLineAndShapeRenderer_refactoring4.getSeriesShapesFilledMap();
	}

	public void setSeriesShapesFilledMap(Map<Integer, Boolean> seriesShapesFilledMap) {
		xYLineAndShapeRenderer_refactoring4.setSeriesShapesFilledMap(seriesShapesFilledMap);
	}

	public boolean getDefaultShapesFilled() {
		return xYLineAndShapeRenderer_refactoring4.getDefaultShapesFilled();
	}

	public void setDefaultShapesFilled2(boolean defaultShapesFilled) {
		xYLineAndShapeRenderer_refactoring4.setDefaultShapesFilled2(defaultShapesFilled);
	}

	public boolean getDrawOutlines() {
		return xYLineAndShapeRenderer_refactoring4.getDrawOutlines();
	}

	public void setDrawOutlines2(boolean drawOutlines) {
		xYLineAndShapeRenderer_refactoring4.setDrawOutlines2(drawOutlines);
	}

	public boolean getUseFillPaint() {
		return useFillPaint;
	}

	public void setUseFillPaint2(boolean useFillPaint) {
		this.useFillPaint = useFillPaint;
	}

	/**
	* Returns the flag used to control whether or not the lines for a series are visible.
	* @param series     the series index (zero-based).
	* @return    The flag (possibly    {@code    null}   ).
	* @see #setSeriesLinesVisible(int,Boolean)
	*/
	public Boolean getSeriesLinesVisible(int series) {
		return xYLineAndShapeRenderer_refactoring4.getSeriesLinesVisible(series);
	}

	/**
	* Sets the 'lines visible' flag for a series and sends a   {@link RendererChangeEvent}    to all registered listeners.
	* @param series     the series index (zero-based).
	* @param flag     the flag (   {@code    null}    permitted).
	* @see #getSeriesLinesVisible(int)
	*/
	public void setSeriesLinesVisible(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring4.setSeriesLinesVisible(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is visible.
	* @param series     the series index (zero-based).
	* @param item     the item index (zero-based).
	* @return    A boolean.
	*/
	public boolean getItemLineVisible(int series, int item) {
		return xYLineAndShapeRenderer_refactoring4.getItemLineVisible(series, item);
	}

	/**
	* Sets the default 'lines visible' flag and sends a   {@link RendererChangeEvent}    to all registered listeners.
	* @param flag     the flag.
	* @see #getDefaultLinesVisible()
	*/
	public void setDefaultLinesVisible(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring4.setDefaultLinesVisible(flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are visible.
	* @param series     the series index (zero-based).
	* @return    A boolean.
	* @see #setSeriesShapesVisible(int,Boolean)
	*/
	public Boolean getSeriesShapesVisible(int series) {
		return xYLineAndShapeRenderer_refactoring4.getSeriesShapesVisible(series);
	}

	/**
	* Sets the 'shapes visible' flag for a series and sends a   {@link RendererChangeEvent}    to all registered listeners.
	* @param series     the series index (zero-based).
	* @param flag     the flag.
	* @see #getSeriesShapesVisible(int)
	*/
	public void setSeriesShapesVisible(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring4.setSeriesShapesVisible(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is visible. <p> The default implementation passes control to the   {@code    getSeriesShapesVisible()}    method. You can override this method if you require different behaviour.
	* @param series     the series index (zero-based).
	* @param item     the item index (zero-based).
	* @return    A boolean.
	*/
	public boolean getItemShapeVisible(int series, int item) {
		return xYLineAndShapeRenderer_refactoring4.getItemShapeVisible(series, item);
	}

	/**
	* Sets the default 'shapes visible' flag and sends a   {@link RendererChangeEvent}    to all registered listeners.
	* @param flag     the flag.
	* @see #getDefaultShapesVisible()
	*/
	public void setDefaultShapesVisible(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring4.setDefaultShapesVisible(flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are filled.
	* @param series     the series index (zero-based).
	* @return    A boolean.
	* @see #setSeriesShapesFilled(int,Boolean)
	*/
	public Boolean getSeriesShapesFilled(int series) {
		return xYLineAndShapeRenderer_refactoring4.getSeriesShapesFilled(series);
	}

	/**
	* Sets the 'shapes filled' flag for a series and sends a   {@link RendererChangeEvent}    to all registered listeners.
	* @param series     the series index (zero-based).
	* @param flag     the flag.
	* @see #getSeriesShapesFilled(int)
	*/
	public void setSeriesShapesFilled(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring4.setSeriesShapesFilled(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is filled. <p> The default implementation passes control to the   {@code    getSeriesShapesFilled}    method. You can override this method if you require different behaviour.
	* @param series     the series index (zero-based).
	* @param item     the item index (zero-based).
	* @return    A boolean.
	*/
	public boolean getItemShapeFilled(int series, int item) {
		return xYLineAndShapeRenderer_refactoring4.getItemShapeFilled(series, item);
	}

	/**
	* Sets the default 'shapes filled' flag and sends a   {@link RendererChangeEvent}    to all registered listeners.
	* @param flag     the flag.
	* @see #getDefaultShapesFilled()
	*/
	public void setDefaultShapesFilled(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring4.setDefaultShapesFilled(flag, xYLineAndShapeRenderer);
	}

	/**
	* Sets the flag that controls whether outlines are drawn for shapes, and sends a    {@link RendererChangeEvent}    to all registered listeners. <P> In some cases, shapes look better if they do NOT have an outline, but this flag allows you to set your own preference.
	* @param flag     the flag.
	* @see #getDrawOutlines()
	*/
	public void setDrawOutlines(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring4.setDrawOutlines(flag, xYLineAndShapeRenderer);
	}

	/**
	* Sets the flag that controls whether the fill paint is used to fill shapes, and sends a    {@link RendererChangeEvent}    to all registered listeners.
	* @param flag     the flag.
	* @see #getUseFillPaint()
	*/
	public void setUseFillPaint(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		this.useFillPaint = flag;
		xYLineAndShapeRenderer.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYLineAndShapeRenderer_refactoring3) super.clone();
	}
}