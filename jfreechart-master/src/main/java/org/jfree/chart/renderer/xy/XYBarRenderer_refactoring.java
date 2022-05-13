package org.jfree.chart.renderer.xy;


import org.jfree.chart.internal.Args;
import java.io.Serializable;

public class XYBarRenderer_refactoring implements Serializable, Cloneable {
	private XYBarRenderer_refactoring3 xYBarRenderer_refactoring3 = new XYBarRenderer_refactoring3();
	private XYBarRenderer_refactoring2 xYBarRenderer_refactoring2 = new XYBarRenderer_refactoring2();
	private double margin;
	private boolean shadowsVisible;
	public double getBase() {
		return xYBarRenderer_refactoring3.getBase();
	}

	public void setBase2(double base) {
		xYBarRenderer_refactoring3.setBase2(base);
	}

	public boolean getUseYInterval() {
		return xYBarRenderer_refactoring2.getUseYInterval();
	}

	public void setUseYInterval2(boolean useYInterval) {
		xYBarRenderer_refactoring2.setUseYInterval2(useYInterval);
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin2(double margin) {
		this.margin = margin;
	}

	public XYBarPainter getBarPainter() {
		return xYBarRenderer_refactoring2.getBarPainter();
	}

	public void setBarPainter2(XYBarPainter barPainter) {
		xYBarRenderer_refactoring2.setBarPainter2(barPainter);
	}

	public boolean getShadowsVisible() {
		return shadowsVisible;
	}

	public void setShadowsVisible(boolean shadowsVisible) {
		this.shadowsVisible = shadowsVisible;
	}

	public double getBarAlignmentFactor() {
		return xYBarRenderer_refactoring3.getBarAlignmentFactor();
	}

	public void setBarAlignmentFactor2(double barAlignmentFactor) {
		xYBarRenderer_refactoring3.setBarAlignmentFactor2(barAlignmentFactor);
	}

	/**
	* Sets the base value for the bars and sends a  {@link RendererChangeEvent} to all registered listeners.  The base value is not used if the dataset's y-interval is being used to determine the bar length.
	* @param base   the new base value.
	* @see #getBase()
	* @see #getUseYInterval()
	*/
	public void setBase(double base, XYBarRenderer xYBarRenderer) {
		xYBarRenderer_refactoring3.setBase(base, xYBarRenderer);
	}

	/**
	* Sets the flag that determines whether the y-interval from the dataset is used to calculate the length of each bar, and sends a {@link RendererChangeEvent}  to all registered listeners.
	* @param use   the flag.
	* @see #getUseYInterval()
	*/
	public void setUseYInterval(boolean use, XYBarRenderer xYBarRenderer) {
		xYBarRenderer_refactoring2.setUseYInterval(use, xYBarRenderer);
	}

	/**
	* Sets the percentage amount by which the bars are trimmed and sends a {@link RendererChangeEvent}  to all registered listeners.
	* @param margin   the new margin.
	* @see #getMargin()
	*/
	public void setMargin(double margin, XYBarRenderer xYBarRenderer) {
		this.margin = margin;
		xYBarRenderer.fireChangeEvent();
	}

	/**
	* Sets the bar painter and sends a  {@link RendererChangeEvent}  to all registered listeners.
	* @param painter   the painter ( {@code  null}  not permitted).
	*/
	public void setBarPainter(XYBarPainter painter, XYBarRenderer xYBarRenderer) {
		xYBarRenderer_refactoring2.setBarPainter(painter, xYBarRenderer);
	}

	/**
	* Sets the flag that controls whether or not the renderer draws shadows for the bars, and sends a {@link RendererChangeEvent}  to all registered listeners.
	* @param visible   the new flag value.
	*/
	public void setShadowVisible(boolean visible, XYBarRenderer xYBarRenderer) {
		this.shadowsVisible = visible;
		xYBarRenderer.fireChangeEvent();
	}

	/**
	* Sets the bar alignment factor and sends a  {@link RendererChangeEvent} to all registered listeners.  If the alignment factor is outside the range 0.0 to 1.0, no alignment will be performed by the renderer.
	* @param factor   the factor.
	*/
	public void setBarAlignmentFactor(double factor, XYBarRenderer xYBarRenderer) {
		xYBarRenderer_refactoring3.setBarAlignmentFactor(factor, xYBarRenderer);
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYBarRenderer_refactoring) super.clone();
	}
}