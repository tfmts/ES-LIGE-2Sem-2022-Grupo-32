package org.jfree.chart.renderer.xy;


import org.jfree.chart.internal.Args;
import java.io.Serializable;

public class XYBarRenderer_refactoring2 implements Serializable, Cloneable {
	private boolean useYInterval;
	private XYBarPainter barPainter;

	public boolean getUseYInterval() {
		return useYInterval;
	}

	public void setUseYInterval2(boolean useYInterval) {
		this.useYInterval = useYInterval;
	}

	public XYBarPainter getBarPainter() {
		return barPainter;
	}

	public void setBarPainter2(XYBarPainter barPainter) {
		this.barPainter = barPainter;
	}

	/**
	* Sets the flag that determines whether the y-interval from the dataset is used to calculate the length of each bar, and sends a  {@link RendererChangeEvent}   to all registered listeners.
	* @param use    the flag.
	* @see #getUseYInterval()
	*/
	public void setUseYInterval(boolean use, XYBarRenderer xYBarRenderer) {
		if (this.useYInterval != use) {
			this.useYInterval = use;
			xYBarRenderer.fireChangeEvent();
		}
	}

	/**
	* Sets the bar painter and sends a   {@link RendererChangeEvent}   to all registered listeners.
	* @param painter    the painter (  {@code   null}   not permitted).
	*/
	public void setBarPainter(XYBarPainter painter, XYBarRenderer xYBarRenderer) {
		Args.nullNotPermitted(painter, "painter");
		this.barPainter = painter;
		xYBarRenderer.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (XYBarRenderer_refactoring2) super.clone();
	}
}