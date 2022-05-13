package org.jfree.chart.renderer.xy;


import java.util.Map;
import java.io.Serializable;

public class XYLineAndShapeRenderer_refactoring2 implements Serializable, Cloneable {
	private XYLineAndShapeRenderer_refactoring3 xYLineAndShapeRenderer_refactoring3 = new XYLineAndShapeRenderer_refactoring3();
	private boolean useOutlinePaint;

	public Map<Integer, Boolean> getSeriesLinesVisibleMap() {
		return xYLineAndShapeRenderer_refactoring3.getSeriesLinesVisibleMap();
	}

	public void setSeriesLinesVisibleMap(Map<Integer, Boolean> seriesLinesVisibleMap) {
		xYLineAndShapeRenderer_refactoring3.setSeriesLinesVisibleMap(seriesLinesVisibleMap);
	}

	public boolean getDefaultLinesVisible() {
		return xYLineAndShapeRenderer_refactoring3.getDefaultLinesVisible();
	}

	public void setDefaultLinesVisible2(boolean defaultLinesVisible) {
		xYLineAndShapeRenderer_refactoring3.setDefaultLinesVisible2(defaultLinesVisible);
	}

	public Map<Integer, Boolean> getSeriesShapesVisibleMap() {
		return xYLineAndShapeRenderer_refactoring3.getSeriesShapesVisibleMap();
	}

	public void setSeriesShapesVisibleMap(Map<Integer, Boolean> seriesShapesVisibleMap) {
		xYLineAndShapeRenderer_refactoring3.setSeriesShapesVisibleMap(seriesShapesVisibleMap);
	}

	public boolean getDefaultShapesVisible() {
		return xYLineAndShapeRenderer_refactoring3.getDefaultShapesVisible();
	}

	public void setDefaultShapesVisible2(boolean defaultShapesVisible) {
		xYLineAndShapeRenderer_refactoring3.setDefaultShapesVisible2(defaultShapesVisible);
	}

	public Map<Integer, Boolean> getSeriesShapesFilledMap() {
		return xYLineAndShapeRenderer_refactoring3.getSeriesShapesFilledMap();
	}

	public void setSeriesShapesFilledMap(Map<Integer, Boolean> seriesShapesFilledMap) {
		xYLineAndShapeRenderer_refactoring3.setSeriesShapesFilledMap(seriesShapesFilledMap);
	}

	public boolean getDefaultShapesFilled() {
		return xYLineAndShapeRenderer_refactoring3.getDefaultShapesFilled();
	}

	public void setDefaultShapesFilled2(boolean defaultShapesFilled) {
		xYLineAndShapeRenderer_refactoring3.setDefaultShapesFilled2(defaultShapesFilled);
	}

	public boolean getDrawOutlines() {
		return xYLineAndShapeRenderer_refactoring3.getDrawOutlines();
	}

	public void setDrawOutlines2(boolean drawOutlines) {
		xYLineAndShapeRenderer_refactoring3.setDrawOutlines2(drawOutlines);
	}

	public boolean getUseFillPaint() {
		return xYLineAndShapeRenderer_refactoring3.getUseFillPaint();
	}

	public void setUseFillPaint2(boolean useFillPaint) {
		xYLineAndShapeRenderer_refactoring3.setUseFillPaint2(useFillPaint);
	}

	public boolean getUseOutlinePaint() {
		return useOutlinePaint;
	}

	public void setUseOutlinePaint2(boolean useOutlinePaint) {
		this.useOutlinePaint = useOutlinePaint;
	}

	/**
	* Returns the flag used to control whether or not the lines for a series are visible.
	* @param series    the series index (zero-based).
	* @return   The flag (possibly   {@code   null}  ).
	* @see #setSeriesLinesVisible(int,Boolean)
	*/
	public Boolean getSeriesLinesVisible(int series) {
		return xYLineAndShapeRenderer_refactoring3.getSeriesLinesVisible(series);
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are visible.
	* @param series    the series index (zero-based).
	* @return   A boolean.
	* @see #setSeriesShapesVisible(int,Boolean)
	*/
	public Boolean getSeriesShapesVisible(int series) {
		return xYLineAndShapeRenderer_refactoring3.getSeriesShapesVisible(series);
	}

	/**
	* Returns the flag used to control whether or not the shapes for a series are filled.
	* @param series    the series index (zero-based).
	* @return   A boolean.
	* @see #setSeriesShapesFilled(int,Boolean)
	*/
	public Boolean getSeriesShapesFilled(int series) {
		return xYLineAndShapeRenderer_refactoring3.getSeriesShapesFilled(series);
	}

	/**
	* Sets the 'lines visible' flag for a series and sends a  {@link RendererChangeEvent}   to all registered listeners.
	* @param series    the series index (zero-based).
	* @param flag    the flag (  {@code   null}   permitted).
	* @see #getSeriesLinesVisible(int)
	*/
	public void setSeriesLinesVisible(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring3.setSeriesLinesVisible(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Sets the 'shapes visible' flag for a series and sends a  {@link RendererChangeEvent}   to all registered listeners.
	* @param series    the series index (zero-based).
	* @param flag    the flag.
	* @see #getSeriesShapesVisible(int)
	*/
	public void setSeriesShapesVisible(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring3.setSeriesShapesVisible(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Sets the 'shapes filled' flag for a series and sends a  {@link RendererChangeEvent}   to all registered listeners.
	* @param series    the series index (zero-based).
	* @param flag    the flag.
	* @see #getSeriesShapesFilled(int)
	*/
	public void setSeriesShapesFilled(int series, Boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring3.setSeriesShapesFilled(series, flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is visible.
	* @param series    the series index (zero-based).
	* @param item    the item index (zero-based).
	* @return   A boolean.
	*/
	public boolean getItemLineVisible(int series, int item) {
		return xYLineAndShapeRenderer_refactoring3.getItemLineVisible(series, item);
	}

	/**
	* Sets the default 'lines visible' flag and sends a  {@link RendererChangeEvent}   to all registered listeners.
	* @param flag    the flag.
	* @see #getDefaultLinesVisible()
	*/
	public void setDefaultLinesVisible(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring3.setDefaultLinesVisible(flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is visible. <p> The default implementation passes control to the  {@code   getSeriesShapesVisible()}   method. You can override this method if you require different behaviour.
	* @param series    the series index (zero-based).
	* @param item    the item index (zero-based).
	* @return   A boolean.
	*/
	public boolean getItemShapeVisible(int series, int item) {
		return xYLineAndShapeRenderer_refactoring3.getItemShapeVisible(series, item);
	}

	/**
	* Sets the default 'shapes visible' flag and sends a  {@link RendererChangeEvent}   to all registered listeners.
	* @param flag    the flag.
	* @see #getDefaultShapesVisible()
	*/
	public void setDefaultShapesVisible(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring3.setDefaultShapesVisible(flag, xYLineAndShapeRenderer);
	}

	/**
	* Returns the flag used to control whether or not the shape for an item is filled. <p> The default implementation passes control to the  {@code   getSeriesShapesFilled}   method. You can override this method if you require different behaviour.
	* @param series    the series index (zero-based).
	* @param item    the item index (zero-based).
	* @return   A boolean.
	*/
	public boolean getItemShapeFilled(int series, int item) {
		return xYLineAndShapeRenderer_refactoring3.getItemShapeFilled(series, item);
	}

	/**
	* Sets the default 'shapes filled' flag and sends a  {@link RendererChangeEvent}   to all registered listeners.
	* @param flag    the flag.
	* @see #getDefaultShapesFilled()
	*/
	public void setDefaultShapesFilled(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring3.setDefaultShapesFilled(flag, xYLineAndShapeRenderer);
	}

	/**
	* Sets the flag that controls whether the outline paint is used to draw shape outlines, and sends a   {@link RendererChangeEvent}   to all registered listeners. <p> Refer to   {@code   XYLineAndShapeRendererDemo2.java}   to see the effect of this flag.
	* @param flag    the flag.
	* @see #getUseOutlinePaint()
	*/
	public void setUseOutlinePaint(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		this.useOutlinePaint = flag;
		xYLineAndShapeRenderer.fireChangeEvent();
	}

	/**
	* Sets the flag that controls whether the fill paint is used to fill shapes, and sends a   {@link RendererChangeEvent}   to all registered listeners.
	* @param flag    the flag.
	* @see #getUseFillPaint()
	*/
	public void setUseFillPaint(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring3.setUseFillPaint(flag, xYLineAndShapeRenderer);
	}

	/**
	* Sets the flag that controls whether outlines are drawn for shapes, and sends a   {@link RendererChangeEvent}   to all registered listeners. <P> In some cases, shapes look better if they do NOT have an outline, but this flag allows you to set your own preference.
	* @param flag    the flag.
	* @see #getDrawOutlines()
	*/
	public void setDrawOutlines(boolean flag, XYLineAndShapeRenderer xYLineAndShapeRenderer) {
		xYLineAndShapeRenderer_refactoring3.setDrawOutlines(flag, xYLineAndShapeRenderer);
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYLineAndShapeRenderer_refactoring2) super.clone();
	}
}