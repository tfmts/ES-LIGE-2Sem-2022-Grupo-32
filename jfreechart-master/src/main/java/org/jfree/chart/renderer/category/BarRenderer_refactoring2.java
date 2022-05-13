package org.jfree.chart.renderer.category;


import java.io.Serializable;

public class BarRenderer_refactoring2 implements Serializable, Cloneable {
	private double upperClip;
	private double lowerClip;

	public double getUpperClip() {
		return upperClip;
	}

	public void setUpperClip(double upperClip) {
		this.upperClip = upperClip;
	}

	public double getLowerClip() {
		return lowerClip;
	}

	public void setLowerClip(double lowerClip) {
		this.lowerClip = lowerClip;
	}

	/**
	* Calculates the coordinates for the length of a single bar.
	* @param value   the value represented by the bar.
	* @return  The coordinates for each end of the bar (or  {@code  null}  if the bar is not visible for the current axis range).
	*/
	public double[] calculateBarL0L1(double value, double thisBase) {
		double lclip = lowerClip;
		double uclip = upperClip;
		double barLow = Math.min(thisBase, value);
		double barHigh = Math.max(thisBase, value);
		if (barHigh < lclip) {
			return null;
		}
		if (barLow > uclip) {
			return null;
		}
		barLow = Math.max(barLow, lclip);
		barHigh = Math.min(barHigh, uclip);
		return new double[] { barLow, barHigh };
	}

	public Object clone() throws CloneNotSupportedException {
		return (BarRenderer_refactoring2) super.clone();
	}
}