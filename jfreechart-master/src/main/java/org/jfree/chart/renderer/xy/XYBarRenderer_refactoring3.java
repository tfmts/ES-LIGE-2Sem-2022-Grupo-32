package org.jfree.chart.renderer.xy;


import java.io.Serializable;

public class XYBarRenderer_refactoring3 implements Serializable, Cloneable {
	private double base;
	private double barAlignmentFactor;

	public double getBase() {
		return base;
	}

	public void setBase2(double base) {
		this.base = base;
	}

	public double getBarAlignmentFactor() {
		return barAlignmentFactor;
	}

	public void setBarAlignmentFactor2(double barAlignmentFactor) {
		this.barAlignmentFactor = barAlignmentFactor;
	}

	/**
	* Sets the base value for the bars and sends a   {@link RendererChangeEvent}  to all registered listeners.  The base value is not used if the dataset's y-interval is being used to determine the bar length.
	* @param base    the new base value.
	* @see #getBase()
	* @see #getUseYInterval()
	*/
	public void setBase(double base, XYBarRenderer xYBarRenderer) {
		this.base = base;
		xYBarRenderer.fireChangeEvent();
	}

	/**
	* Sets the bar alignment factor and sends a   {@link RendererChangeEvent}  to all registered listeners.  If the alignment factor is outside the range 0.0 to 1.0, no alignment will be performed by the renderer.
	* @param factor    the factor.
	*/
	public void setBarAlignmentFactor(double factor, XYBarRenderer xYBarRenderer) {
		this.barAlignmentFactor = factor;
		xYBarRenderer.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYBarRenderer_refactoring3) super.clone();
	}
}