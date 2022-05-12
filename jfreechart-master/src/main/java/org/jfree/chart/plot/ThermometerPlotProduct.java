package org.jfree.chart.plot;


import org.jfree.chart.api.RectangleInsets;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import org.jfree.chart.internal.Args;
import java.io.Serializable;

public class ThermometerPlotProduct implements Serializable, Cloneable {
	private RectangleInsets padding;
	private int units = ThermometerPlot.UNITS_CELCIUS;
	private int valueLocation = ThermometerPlot.BULB;
	private int axisLocation = ThermometerPlot.LEFT;
	private Font valueFont = new Font("SansSerif", Font.BOLD, 16);
	private NumberFormat valueFormat = new DecimalFormat();

	public RectangleInsets getPadding() {
		return padding;
	}

	public void setPadding2(RectangleInsets padding) {
		this.padding = padding;
	}

	public int getUnits() {
		return units;
	}

	public int getValueLocation() {
		return valueLocation;
	}

	public int getAxisLocation() {
		return axisLocation;
	}

	public Font getValueFont() {
		return valueFont;
	}

	public NumberFormat getValueFormat() {
		return valueFormat;
	}

	public void setValueFormat2(NumberFormat valueFormat) {
		this.valueFormat = valueFormat;
	}

	/**
	* Sets the padding for the thermometer and sends a  {@link PlotChangeEvent} to all registered listeners.
	* @param padding   the padding ( {@code  null}  not permitted).
	* @see #getPadding()
	*/
	public void setPadding(RectangleInsets padding, ThermometerPlot thermometerPlot) {
		Args.nullNotPermitted(padding, "padding");
		this.padding = padding;
		thermometerPlot.fireChangeEvent();
	}

	/**
	* Sets the units to be displayed in the thermometer. Use one of the following constants: <ul> <li>UNITS_NONE : no units displayed.</li> <li>UNITS_FAHRENHEIT : units displayed in Fahrenheit.</li> <li>UNITS_CELCIUS : units displayed in Celcius.</li> <li>UNITS_KELVIN : units displayed in Kelvin.</li> </ul>
	* @param u   the new unit type.
	* @see #getUnits()
	*/
	public void setUnits(int u, ThermometerPlot thermometerPlot) {
		if ((u >= 0) && (u < ThermometerPlot.UNITS.length)) {
			if (this.units != u) {
				this.units = u;
				thermometerPlot.fireChangeEvent();
			}
		}
	}

	/**
	* Sets the location at which the current value is displayed and sends a {@link PlotChangeEvent}  to all registered listeners. <P> The location can be one of the constants:  {@code  NONE} ,  {@code  RIGHT} , {@code  LEFT}  and  {@code  BULB} .
	* @param location   the location.
	*/
	public void setValueLocation(int location, ThermometerPlot thermometerPlot) {
		if ((location >= 0) && (location < 4)) {
			this.valueLocation = location;
			thermometerPlot.fireChangeEvent();
		} else {
			throw new IllegalArgumentException("Location not recognised.");
		}
	}

	/**
	* Sets the location at which the axis is displayed relative to the thermometer, and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param location   the location (one of  {@link #NONE} ,  {@link #LEFT}  and {@link #RIGHT} ).
	* @see #getAxisLocation()
	*/
	public void setAxisLocation(int location, ThermometerPlot thermometerPlot) {
		if ((location >= 0) && (location < 3)) {
			this.axisLocation = location;
			thermometerPlot.fireChangeEvent();
		} else {
			throw new IllegalArgumentException("Location not recognised.");
		}
	}

	/**
	* Sets the font used to display the current value.
	* @param f   the new font ( {@code  null}  not permitted).
	* @see #getValueFont()
	*/
	public void setValueFont(Font f, ThermometerPlot thermometerPlot) {
		Args.nullNotPermitted(f, "f");
		if (!this.valueFont.equals(f)) {
			this.valueFont = f;
			thermometerPlot.fireChangeEvent();
		}
	}

	/**
	* Sets the formatter for the value label and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param formatter   the new formatter ( {@code  null}  not permitted).
	*/
	public void setValueFormat(NumberFormat formatter, ThermometerPlot thermometerPlot) {
		Args.nullNotPermitted(formatter, "formatter");
		this.valueFormat = formatter;
		thermometerPlot.fireChangeEvent();
	}

	public Object clone() throws CloneNotSupportedException {
		return (ThermometerPlotProduct) super.clone();
	}
}