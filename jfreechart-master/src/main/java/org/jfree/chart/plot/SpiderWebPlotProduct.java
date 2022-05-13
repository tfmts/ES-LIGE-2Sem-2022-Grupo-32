package org.jfree.chart.plot;


import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.api.TableOrder;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.entity.CategoryItemEntity;
import java.awt.geom.Point2D;
import java.awt.Shape;
import java.awt.Rectangle;
import org.jfree.chart.internal.Args;
import java.io.Serializable;

public class SpiderWebPlotProduct implements Serializable, Cloneable {
	private CategoryDataset dataset;
	private TableOrder dataExtractOrder;
	private CategoryToolTipGenerator toolTipGenerator;
	private CategoryURLGenerator urlGenerator;

	public CategoryDataset getDataset() {
		return dataset;
	}

	public void setDataset(CategoryDataset dataset) {
		this.dataset = dataset;
	}

	public TableOrder getDataExtractOrder() {
		return dataExtractOrder;
	}

	public void setDataExtractOrder2(TableOrder dataExtractOrder) {
		this.dataExtractOrder = dataExtractOrder;
	}

	public CategoryToolTipGenerator getToolTipGenerator() {
		return toolTipGenerator;
	}

	public CategoryURLGenerator getUrlGenerator() {
		return urlGenerator;
	}

	public CategoryItemEntity entity(int series, double headH, double headW, int cat, Point2D point) {
		int row, col;
		if (this.dataExtractOrder == TableOrder.BY_ROW) {
			row = series;
			col = cat;
		} else {
			row = cat;
			col = series;
		}
		String tip = tip(row, col);
		String url = url(row, col);
		Shape area = new Rectangle((int) (point.getX() - headW), (int) (point.getY() - headH), (int) (headW * 2),
				(int) (headH * 2));
		CategoryItemEntity entity = new CategoryItemEntity(area, tip, url, this.dataset, this.dataset.getRowKey(row),
				this.dataset.getColumnKey(col));
		return entity;
	}

	/**
	* Sets the tool tip generator for the plot and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param generator   the generator ( {@code  null}  permitted).
	* @see #getToolTipGenerator()
	*/
	public void setToolTipGenerator(CategoryToolTipGenerator generator, SpiderWebPlot spiderWebPlot) {
		this.toolTipGenerator = generator;
		spiderWebPlot.fireChangeEvent();
	}

	public String tip(int row, int col) {
		String tip = null;
		if (this.toolTipGenerator != null) {
			tip = this.toolTipGenerator.generateToolTip(this.dataset, row, col);
		}
		return tip;
	}

	/**
	* Sets the URL generator for the plot and sends a {@link PlotChangeEvent}  to all registered listeners.
	* @param generator   the generator ( {@code  null}  permitted).
	* @see #getURLGenerator()
	*/
	public void setURLGenerator(CategoryURLGenerator generator, SpiderWebPlot spiderWebPlot) {
		this.urlGenerator = generator;
		spiderWebPlot.fireChangeEvent();
	}

	public String url(int row, int col) {
		String url = null;
		if (this.urlGenerator != null) {
			url = this.urlGenerator.generateURL(this.dataset, row, col);
		}
		return url;
	}

	/**
	* Sets the data extract order (by row or by column) and sends a {@link PlotChangeEvent} to all registered listeners.
	* @param order  the order ( {@code  null}  not permitted).
	* @throws IllegalArgumentException  if  {@code  order}  is {@code  null} .
	* @see #getDataExtractOrder()
	*/
	public void setDataExtractOrder(TableOrder order, SpiderWebPlot spiderWebPlot) {
		Args.nullNotPermitted(order, "order");
		this.dataExtractOrder = order;
		spiderWebPlot.fireChangeEvent();
	}

	/**
	* Returns the value to be plotted at the intersection of the series and the category.  This allows us to plot {@code  BY_ROW}  or  {@code  BY_COLUMN}  which basically is just reversing the definition of the categories and data series being plotted.
	* @param series  the series to be plotted.
	* @param cat  the category within the series to be plotted.
	* @return  The value to be plotted (possibly  {@code  null} ).
	* @see #getDataExtractOrder()
	*/
	public Number getPlotValue(int series, int cat) {
		Number value = null;
		if (this.dataExtractOrder == TableOrder.BY_ROW) {
			value = this.dataset.getValue(series, cat);
		} else if (this.dataExtractOrder == TableOrder.BY_COLUMN) {
			value = this.dataset.getValue(cat, series);
		}
		return value;
	}

	public Object clone() throws CloneNotSupportedException {
		return (SpiderWebPlotProduct) super.clone();
	}
}