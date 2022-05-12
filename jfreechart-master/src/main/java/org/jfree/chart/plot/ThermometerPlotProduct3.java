package org.jfree.chart.plot;


import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class ThermometerPlotProduct3 implements Serializable, Cloneable {
	private int bulbRadius = ThermometerPlot.DEFAULT_BULB_RADIUS;
	private int gap = ThermometerPlot.DEFAULT_GAP;

	public int getBulbRadius() {
		return bulbRadius;
	}

	public void setBulbRadius(int bulbRadius) {
		this.bulbRadius = bulbRadius;
	}

	public int getGap() {
		return gap;
	}

	/**
	* Returns the bulb diameter, which is always twice the value returned by  {@link #getBulbRadius()} .
	* @return  The bulb diameter.
	*/
	public int getBulbDiameter() {
		return bulbRadius * 2;
	}

	public Rectangle2D dataArea(Rectangle2D area, int thisColumnRadius) {
		Rectangle2D interior = (Rectangle2D) area.clone();
		int midX = (int) (interior.getX() + (interior.getWidth() / 2));
		int stemTop = (int) (interior.getMinY() + bulbRadius);
		int stemBottom = (int) (interior.getMaxY() - getBulbDiameter());
		Rectangle2D dataArea = new Rectangle2D.Double(midX - thisColumnRadius, stemTop, thisColumnRadius,
				stemBottom - stemTop);
		return dataArea;
	}

	/**
	* Sets the gap (in Java2D units) between the two outlines that represent the thermometer, and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param gap   the new gap.
	* @see #getGap()
	*/
	public void setGap(int gap, ThermometerPlot thermometerPlot) {
		this.gap = gap;
		thermometerPlot.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (ThermometerPlotProduct3) super.clone();
	}
}