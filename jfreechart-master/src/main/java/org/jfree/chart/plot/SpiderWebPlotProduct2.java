package org.jfree.chart.plot;


import java.awt.Paint;
import java.awt.Stroke;
import org.jfree.chart.api.Rotation;
import java.awt.Shape;
import java.util.Map;
import java.awt.Font;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.internal.Args;
import java.io.Serializable;

public class SpiderWebPlotProduct2 implements Serializable, Cloneable {
	private SpiderWebPlotProduct spiderWebPlotProduct = new SpiderWebPlotProduct();
	private double headPercent;
	private double interiorGap;
	private transient Paint axisLinePaint;
	private transient Stroke axisLineStroke;
	private double maxValue;
	private double startAngle;
	private Rotation direction;
	private transient Shape legendItemShape;
	private transient Map<Integer, Paint> seriesPaints;
	private transient Paint defaultSeriesPaint;
	private transient Map<Integer, Paint> seriesOutlinePaints;
	private transient Paint defaultSeriesOutlinePaint;
	private transient Map<Integer, Stroke> seriesOutlineStrokes;
	private transient Stroke defaultSeriesOutlineStroke;
	private Font labelFont;
	private transient Paint labelPaint;
	private CategoryItemLabelGenerator labelGenerator;
	private boolean webFilled = true;

	public SpiderWebPlotProduct getSpiderWebPlotProduct() {
		return spiderWebPlotProduct;
	}

	public void setSpiderWebPlotProduct(SpiderWebPlotProduct spiderWebPlotProduct) {
		this.spiderWebPlotProduct = spiderWebPlotProduct;
	}

	public double getHeadPercent() {
		return headPercent;
	}

	public void setHeadPercent2(double headPercent) {
		this.headPercent = headPercent;
	}

	public double getInteriorGap() {
		return interiorGap;
	}

	public void setInteriorGap2(double interiorGap) {
		this.interiorGap = interiorGap;
	}

	public Paint getAxisLinePaint() {
		return axisLinePaint;
	}

	public void setAxisLinePaint2(Paint axisLinePaint) {
		this.axisLinePaint = axisLinePaint;
	}

	public Stroke getAxisLineStroke() {
		return axisLineStroke;
	}

	public void setAxisLineStroke2(Stroke axisLineStroke) {
		this.axisLineStroke = axisLineStroke;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue2(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public void setStartAngle2(double startAngle) {
		this.startAngle = startAngle;
	}

	public Rotation getDirection() {
		return direction;
	}

	public void setDirection2(Rotation direction) {
		this.direction = direction;
	}

	public Shape getLegendItemShape() {
		return legendItemShape;
	}

	public void setLegendItemShape2(Shape legendItemShape) {
		this.legendItemShape = legendItemShape;
	}

	public Map<Integer, Paint> getSeriesPaints() {
		return seriesPaints;
	}

	public void setSeriesPaints(Map<Integer, Paint> seriesPaints) {
		this.seriesPaints = seriesPaints;
	}

	public Paint getDefaultSeriesPaint() {
		return defaultSeriesPaint;
	}

	public void setDefaultSeriesPaint2(Paint defaultSeriesPaint) {
		this.defaultSeriesPaint = defaultSeriesPaint;
	}

	public Map<Integer, Paint> getSeriesOutlinePaints() {
		return seriesOutlinePaints;
	}

	public void setSeriesOutlinePaints(Map<Integer, Paint> seriesOutlinePaints) {
		this.seriesOutlinePaints = seriesOutlinePaints;
	}

	public Paint getDefaultSeriesOutlinePaint() {
		return defaultSeriesOutlinePaint;
	}

	public void setDefaultSeriesOutlinePaint2(Paint defaultSeriesOutlinePaint) {
		this.defaultSeriesOutlinePaint = defaultSeriesOutlinePaint;
	}

	public Map<Integer, Stroke> getSeriesOutlineStrokes() {
		return seriesOutlineStrokes;
	}

	public void setSeriesOutlineStrokes(Map<Integer, Stroke> seriesOutlineStrokes) {
		this.seriesOutlineStrokes = seriesOutlineStrokes;
	}

	public Stroke getDefaultSeriesOutlineStroke() {
		return defaultSeriesOutlineStroke;
	}

	public void setDefaultSeriesOutlineStroke2(Stroke defaultSeriesOutlineStroke) {
		this.defaultSeriesOutlineStroke = defaultSeriesOutlineStroke;
	}

	public Font getLabelFont() {
		return labelFont;
	}

	public void setLabelFont2(Font labelFont) {
		this.labelFont = labelFont;
	}

	public Paint getLabelPaint() {
		return labelPaint;
	}

	public void setLabelPaint2(Paint labelPaint) {
		this.labelPaint = labelPaint;
	}

	public CategoryItemLabelGenerator getLabelGenerator() {
		return labelGenerator;
	}

	public void setLabelGenerator2(CategoryItemLabelGenerator labelGenerator) {
		this.labelGenerator = labelGenerator;
	}

	public boolean getWebFilled() {
		return webFilled;
	}

	/**
	* Sets the starting angle and sends a  {@link PlotChangeEvent}  to all registered listeners. <P> The initial default value is 90 degrees, which corresponds to 12 o'clock. A value of zero corresponds to 3 o'clock... this is the encoding used by Java's Arc2D class.
	* @param angle   the angle (in degrees).
	* @see #getStartAngle()
	*/
	public void setStartAngle(double angle, SpiderWebPlot spiderWebPlot) {
		this.startAngle = angle;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the direction in which the radar axes are drawn and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param direction   the direction ( {@code  null}  not permitted).
	* @see #getDirection()
	*/
	public void setDirection(Rotation direction, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(direction, "direction");
		this.direction = direction;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the paint used to draw the axis lines and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param paint   the paint ( {@code  null}  not permitted).
	* @see #getAxisLinePaint()
	*/
	public void setAxisLinePaint(Paint paint, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(paint, "paint");
		this.axisLinePaint = paint;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the stroke used to draw the axis lines and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param stroke   the stroke ( {@code  null}  not permitted).
	* @see #getAxisLineStroke()
	*/
	public void setAxisLineStroke(Stroke stroke, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(stroke, "stroke");
		this.axisLineStroke = stroke;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the default series paint.
	* @param paint   the paint ( {@code  null}  not permitted).
	* @see #getDefaultSeriesPaint()
	*/
	public void setDefaultSeriesPaint(Paint paint, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(paint, "paint");
		this.defaultSeriesPaint = paint;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Returns the paint for the specified series.
	* @param series   the series index (zero-based).
	* @return  The paint (never  {@code  null} ).
	*/
	public Paint getSeriesOutlinePaint(int series) {
		Paint result = this.seriesOutlinePaints.get(series);
		if (result == null) {
			result = this.defaultSeriesOutlinePaint;
		}
		return result;
	}

	/**
	* Sets the base series paint and sends a change event to all registered listeners.
	* @param paint   the paint ( {@code  null}  not permitted).
	*/
	public void setDefaultSeriesOutlinePaint(Paint paint, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(paint, "paint");
		this.defaultSeriesOutlinePaint = paint;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Returns the stroke for the specified series.
	* @param series   the series index (zero-based).
	* @return  The stroke (never  {@code  null} ).
	*/
	public Stroke getSeriesOutlineStroke(int series) {
		Stroke result = this.seriesOutlineStrokes.get(series);
		if (result == null) {
			result = this.defaultSeriesOutlineStroke;
		}
		return result;
	}

	/**
	* Sets the default series stroke and sends a change event to all  registered listeners.
	* @param stroke   the stroke ( {@code  null}  not permitted).
	*/
	public void setDefaultSeriesOutlineStroke(Stroke stroke, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(stroke, "stroke");
		this.defaultSeriesOutlineStroke = stroke;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the series label paint and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param paint   the paint ( {@code  null}  not permitted).
	* @see #getLabelPaint()
	*/
	public void setLabelPaint(Paint paint, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(paint, "paint");
		this.labelPaint = paint;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the head percent and sends a  {@link PlotChangeEvent}  to all registered listeners.  Note that 0.10 is 10 percent.
	* @param percent   the percent (must be greater than zero).
	* @see #getHeadPercent()
	*/
	public void setHeadPercent(double percent, SpiderWebPlot spiderWebPlot) {
		Args.requireNonNegative(percent, "percent");
		this.headPercent = percent;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the interior gap and sends a  {@link PlotChangeEvent}  to all registered listeners. This controls the space between the edges of the plot and the plot area itself (the region where the axis labels appear).
	* @param percent   the gap (as a percentage of the available drawing space).
	* @see #getInteriorGap()
	*/
	public void setInteriorGap(double percent, SpiderWebPlot spiderWebPlot) {
		if ((percent < 0.0) || (percent > SpiderWebPlot.MAX_INTERIOR_GAP)) {
			throw new IllegalArgumentException("Percentage outside valid range.");
		}
		if (this.interiorGap != percent) {
			this.interiorGap = percent;
			spiderWebPlot.fireChangeEvent();
		}
	}

	/**
	* Sets the series label font and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param font   the font ( {@code  null}  not permitted).
	* @see #getLabelFont()
	*/
	public void setLabelFont(Font font, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(font, "font");
		this.labelFont = font;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the label generator and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param generator   the generator ( {@code  null}  not permitted).
	* @see #getLabelGenerator()
	*/
	public void setLabelGenerator(CategoryItemLabelGenerator generator) {
		Args.nullNotPermitted(generator, "generator");
		this.labelGenerator = generator;
	}

	/**
	* Sets the webFilled flag and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param flag   the flag.
	* @see #isWebFilled()
	*/
	public void setWebFilled(boolean flag, SpiderWebPlot spiderWebPlot) {
		this.webFilled = flag;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the maximum value any category axis can take and sends a  {@link PlotChangeEvent}  to all registered listeners.
	* @param value   the maximum value.
	* @see #getMaxValue()
	*/
	public void setMaxValue(double value, SpiderWebPlot spiderWebPlot) {
		this.maxValue = value;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the paint used to fill a series of the radar and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param series   the series index (zero-based).
	* @param paint   the paint ( {@code  null}  permitted).
	* @see #getSeriesPaint(int)
	*/
	public void setSeriesPaint(int series, Paint paint, SpiderWebPlot spiderWebPlot) {
		this.seriesPaints.put(series, paint);
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the paint used to fill a series of the radar and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param series   the series index (zero-based).
	* @param paint   the paint ( {@code  null}  permitted).
	*/
	public void setSeriesOutlinePaint(int series, Paint paint, SpiderWebPlot spiderWebPlot) {
		this.seriesOutlinePaints.put(series, paint);
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the stroke used to fill a series of the radar and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param series   the series index (zero-based).
	* @param stroke   the stroke ( {@code  null}  permitted).
	*/
	public void setSeriesOutlineStroke(int series, Stroke stroke, SpiderWebPlot spiderWebPlot) {
		this.seriesOutlineStrokes.put(series, stroke);
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Sets the shape used for legend items and sends a  {@link PlotChangeEvent} to all registered listeners.
	* @param shape   the shape ( {@code  null}  not permitted).
	* @see #getLegendItemShape()
	*/
	public void setLegendItemShape(Shape shape, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(shape, "shape");
		this.legendItemShape = shape;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Returns the paint for the specified series.
	* @param series   the series index (zero-based).
	* @return  The paint (never  {@code  null} ).
	* @see #setSeriesPaint(int,Paint)
	*/
	public Paint getSeriesPaint(int series, SpiderWebPlot spiderWebPlot) {
		Paint result = this.seriesPaints.get(series);
		if (result == null) {
			DrawingSupplier supplier = spiderWebPlot.getDrawingSupplier();
			if (supplier != null) {
				Paint p = supplier.getNextPaint();
				this.seriesPaints.put(series, p);
				result = p;
			} else {
				result = this.defaultSeriesPaint;
			}
		}
		return result;
	}

	/**
	* loop through each of the series to get the maximum value on each category axis
	* @param seriesCount   the number of series
	* @param catCount   the number of categories
	*/
	public void calculateMaxValue(int seriesCount, int catCount) {
		double v;
		Number nV;
		for (int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++) {
			for (int catIndex = 0; catIndex < catCount; catIndex++) {
				nV = spiderWebPlotProduct.getPlotValue(seriesIndex, catIndex);
				if (nV != null) {
					v = nV.doubleValue();
					if (v > this.maxValue) {
						this.maxValue = v;
					}
				}
			}
		}
	}

	public Object clone() throws CloneNotSupportedException {
		return (SpiderWebPlotProduct2) super.clone();
	}
}