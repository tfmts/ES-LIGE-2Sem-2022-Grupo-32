package org.jfree.chart.swing;


import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.Crosshair;
import java.awt.geom.Line2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.Font;
import org.jfree.chart.api.RectangleAnchor;
import java.awt.geom.Point2D;
import org.jfree.chart.text.TextAnchor;
import java.awt.Shape;
import org.jfree.chart.text.TextUtils;
import java.awt.Rectangle;
import java.io.Serializable;
import org.jfree.chart.api.PublicCloneable;

public class CrosshairOverlayRefactoring1 implements Serializable, PublicCloneable {
	/**
	* Draws a crosshair horizontally across the plot.
	* @param g2   the graphics target.
	* @param dataArea   the data area.
	* @param y   the y-value in Java2D space.
	* @param crosshair   the crosshair.
	*/
	public void drawHorizontalCrosshair(Graphics2D g2, Rectangle2D dataArea, double y, Crosshair crosshair) {
		if (y >= dataArea.getMinY() && y <= dataArea.getMaxY()) {
			Line2D line = new Line2D.Double(dataArea.getMinX(), y, dataArea.getMaxX(), y);
			Paint savedPaint = g2.getPaint();
			Stroke savedStroke = g2.getStroke();
			g2.setPaint(crosshair.getPaint());
			g2.setStroke(crosshair.getStroke());
			g2.draw(line);
			if (crosshair.isLabelVisible()) {
				String label = crosshair.getLabelGenerator().generateLabel(crosshair);
				if (label != null && !label.isEmpty()) {
					Font savedFont = g2.getFont();
					g2.setFont(crosshair.getLabelFont());
					RectangleAnchor anchor = crosshair.getLabelAnchor();
					Point2D pt = calculateLabelPoint(line, anchor, crosshair.getLabelXOffset(),
							crosshair.getLabelYOffset());
					float xx = (float) pt.getX();
					float yy = (float) pt.getY();
					TextAnchor alignPt = textAlignPtForLabelAnchorH(anchor);
					Shape hotspot = TextUtils.calculateRotatedStringBounds(label, g2, xx, yy, alignPt, 0.0,
							TextAnchor.CENTER);
					if (!dataArea.contains(hotspot.getBounds2D())) {
						anchor = flipAnchorV(anchor);
						pt = calculateLabelPoint(line, anchor, crosshair.getLabelXOffset(),
								crosshair.getLabelYOffset());
						xx = (float) pt.getX();
						yy = (float) pt.getY();
						alignPt = textAlignPtForLabelAnchorH(anchor);
						hotspot = TextUtils.calculateRotatedStringBounds(label, g2, xx, yy, alignPt, 0.0,
								TextAnchor.CENTER);
					}
					g2.setPaint(crosshair.getLabelBackgroundPaint());
					g2.fill(hotspot);
					if (crosshair.isLabelOutlineVisible()) {
						g2.setPaint(crosshair.getLabelOutlinePaint());
						g2.setStroke(crosshair.getLabelOutlineStroke());
						g2.draw(hotspot);
					}
					g2.setPaint(crosshair.getLabelPaint());
					TextUtils.drawAlignedString(label, g2, xx, yy, alignPt);
					g2.setFont(savedFont);
				}
			}
			g2.setPaint(savedPaint);
			g2.setStroke(savedStroke);
		}
	}

	/**
	* Draws a crosshair vertically on the plot.
	* @param g2   the graphics target.
	* @param dataArea   the data area.
	* @param x   the x-value in Java2D space.
	* @param crosshair   the crosshair.
	*/
	public void drawVerticalCrosshair(Graphics2D g2, Rectangle2D dataArea, double x, Crosshair crosshair) {
		if (x >= dataArea.getMinX() && x <= dataArea.getMaxX()) {
			Line2D line = new Line2D.Double(x, dataArea.getMinY(), x, dataArea.getMaxY());
			Paint savedPaint = g2.getPaint();
			Stroke savedStroke = g2.getStroke();
			g2.setPaint(crosshair.getPaint());
			g2.setStroke(crosshair.getStroke());
			g2.draw(line);
			if (crosshair.isLabelVisible()) {
				String label = crosshair.getLabelGenerator().generateLabel(crosshair);
				if (label != null && !label.isEmpty()) {
					Font savedFont = g2.getFont();
					g2.setFont(crosshair.getLabelFont());
					RectangleAnchor anchor = crosshair.getLabelAnchor();
					Point2D pt = calculateLabelPoint(line, anchor, crosshair.getLabelXOffset(),
							crosshair.getLabelYOffset());
					float xx = (float) pt.getX();
					float yy = (float) pt.getY();
					TextAnchor alignPt = textAlignPtForLabelAnchorV(anchor);
					Shape hotspot = TextUtils.calculateRotatedStringBounds(label, g2, xx, yy, alignPt, 0.0,
							TextAnchor.CENTER);
					if (!dataArea.contains(hotspot.getBounds2D())) {
						anchor = flipAnchorH(anchor);
						pt = calculateLabelPoint(line, anchor, crosshair.getLabelXOffset(),
								crosshair.getLabelYOffset());
						xx = (float) pt.getX();
						yy = (float) pt.getY();
						alignPt = textAlignPtForLabelAnchorV(anchor);
						hotspot = TextUtils.calculateRotatedStringBounds(label, g2, xx, yy, alignPt, 0.0,
								TextAnchor.CENTER);
					}
					g2.setPaint(crosshair.getLabelBackgroundPaint());
					g2.fill(hotspot);
					if (crosshair.isLabelOutlineVisible()) {
						g2.setPaint(crosshair.getLabelOutlinePaint());
						g2.setStroke(crosshair.getLabelOutlineStroke());
						g2.draw(hotspot);
					}
					g2.setPaint(crosshair.getLabelPaint());
					TextUtils.drawAlignedString(label, g2, xx, yy, alignPt);
					g2.setFont(savedFont);
				}
			}
			g2.setPaint(savedPaint);
			g2.setStroke(savedStroke);
		}
	}

	/**
	* Calculates the anchor point for a label.
	* @param line   the line for the crosshair.
	* @param anchor   the anchor point.
	* @param deltaX   the x-offset.
	* @param deltaY   the y-offset.
	* @return  The anchor point.
	*/
	public Point2D calculateLabelPoint(Line2D line, RectangleAnchor anchor, double deltaX, double deltaY) {
		double x, y;
		boolean left = (anchor == RectangleAnchor.BOTTOM_LEFT || anchor == RectangleAnchor.LEFT
				|| anchor == RectangleAnchor.TOP_LEFT);
		boolean right = (anchor == RectangleAnchor.BOTTOM_RIGHT || anchor == RectangleAnchor.RIGHT
				|| anchor == RectangleAnchor.TOP_RIGHT);
		boolean top = (anchor == RectangleAnchor.TOP_LEFT || anchor == RectangleAnchor.TOP
				|| anchor == RectangleAnchor.TOP_RIGHT);
		boolean bottom = (anchor == RectangleAnchor.BOTTOM_LEFT || anchor == RectangleAnchor.BOTTOM
				|| anchor == RectangleAnchor.BOTTOM_RIGHT);
		Rectangle rect = line.getBounds();
		if (line.getX1() == line.getX2()) {
			x = line.getX1();
			y = (line.getY1() + line.getY2()) / 2.0;
			x = calculateLabelPointRefactoring1(deltaX, x, left, right);
			y = calculateLabelPointRefactoring3(line, deltaY, y, top, bottom);
		} else {
			x = (line.getX1() + line.getX2()) / 2.0;
			y = line.getY1();
			x = calculateLabelPointRefactoring2(line, deltaX, x, left, right);
			y = calculateLabelPointRefactoring1(deltaY, y, top, bottom);
		}
		return new Point2D.Double(x, y);
	}

	public double calculateLabelPointRefactoring3(Line2D line, double deltaY, double y, boolean top, boolean bottom) {
		if (top) {
			y = Math.min(line.getY1(), line.getY2()) + deltaY;
		}
		if (bottom) {
			y = Math.max(line.getY1(), line.getY2()) - deltaY;
		}
		return y;
	}

	public double calculateLabelPointRefactoring2(Line2D line, double deltaX, double x, boolean left, boolean right) {
		if (left) {
			x = Math.min(line.getX1(), line.getX2()) + deltaX;
		}
		if (right) {
			x = Math.max(line.getX1(), line.getX2()) - deltaX;
		}
		return x;
	}

	public double calculateLabelPointRefactoring1(double deltaX, double x, boolean left, boolean right) {
		if (left) {
			x = x - deltaX;
		}
		if (right) {
			x = x + deltaX;
		}
		return x;
	}

	/**
	* Returns the text anchor that is used to align a label to its anchor  point.
	* @param anchor   the anchor.
	* @return  The text alignment point.
	*/
	public TextAnchor textAlignPtForLabelAnchorV(RectangleAnchor anchor) {
		TextAnchor result = TextAnchor.CENTER;
		if (anchor.equals(RectangleAnchor.TOP_LEFT)) {
			result = TextAnchor.TOP_RIGHT;
		} else if (anchor.equals(RectangleAnchor.TOP)) {
			result = TextAnchor.TOP_CENTER;
		} else if (anchor.equals(RectangleAnchor.TOP_RIGHT)) {
			result = TextAnchor.TOP_LEFT;
		} else if (anchor.equals(RectangleAnchor.LEFT)) {
			result = TextAnchor.HALF_ASCENT_RIGHT;
		} else if (anchor.equals(RectangleAnchor.RIGHT)) {
			result = TextAnchor.HALF_ASCENT_LEFT;
		} else if (anchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
			result = TextAnchor.BOTTOM_RIGHT;
		} else if (anchor.equals(RectangleAnchor.BOTTOM)) {
			result = TextAnchor.BOTTOM_CENTER;
		} else if (anchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
			result = TextAnchor.BOTTOM_LEFT;
		}
		return result;
	}

	/**
	* Returns the text anchor that is used to align a label to its anchor point.
	* @param anchor   the anchor.
	* @return  The text alignment point.
	*/
	public TextAnchor textAlignPtForLabelAnchorH(RectangleAnchor anchor) {
		TextAnchor result = TextAnchor.CENTER;
		if (anchor.equals(RectangleAnchor.TOP_LEFT)) {
			result = TextAnchor.BOTTOM_LEFT;
		} else if (anchor.equals(RectangleAnchor.TOP)) {
			result = TextAnchor.BOTTOM_CENTER;
		} else if (anchor.equals(RectangleAnchor.TOP_RIGHT)) {
			result = TextAnchor.BOTTOM_RIGHT;
		} else if (anchor.equals(RectangleAnchor.LEFT)) {
			result = TextAnchor.HALF_ASCENT_LEFT;
		} else if (anchor.equals(RectangleAnchor.RIGHT)) {
			result = TextAnchor.HALF_ASCENT_RIGHT;
		} else if (anchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
			result = TextAnchor.TOP_LEFT;
		} else if (anchor.equals(RectangleAnchor.BOTTOM)) {
			result = TextAnchor.TOP_CENTER;
		} else if (anchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
			result = TextAnchor.TOP_RIGHT;
		}
		return result;
	}

	public RectangleAnchor flipAnchorH(RectangleAnchor anchor) {
		RectangleAnchor result = anchor;
		if (anchor.equals(RectangleAnchor.TOP_LEFT)) {
			result = RectangleAnchor.TOP_RIGHT;
		} else if (anchor.equals(RectangleAnchor.TOP_RIGHT)) {
			result = RectangleAnchor.TOP_LEFT;
		} else if (anchor.equals(RectangleAnchor.LEFT)) {
			result = RectangleAnchor.RIGHT;
		} else if (anchor.equals(RectangleAnchor.RIGHT)) {
			result = RectangleAnchor.LEFT;
		} else if (anchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
			result = RectangleAnchor.BOTTOM_RIGHT;
		} else if (anchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
			result = RectangleAnchor.BOTTOM_LEFT;
		}
		return result;
	}

	public RectangleAnchor flipAnchorV(RectangleAnchor anchor) {
		RectangleAnchor result = anchor;
		if (anchor.equals(RectangleAnchor.TOP_LEFT)) {
			result = RectangleAnchor.BOTTOM_LEFT;
		} else if (anchor.equals(RectangleAnchor.TOP_RIGHT)) {
			result = RectangleAnchor.BOTTOM_RIGHT;
		} else if (anchor.equals(RectangleAnchor.TOP)) {
			result = RectangleAnchor.BOTTOM;
		} else if (anchor.equals(RectangleAnchor.BOTTOM)) {
			result = RectangleAnchor.TOP;
		} else if (anchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
			result = RectangleAnchor.TOP_LEFT;
		} else if (anchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
			result = RectangleAnchor.TOP_RIGHT;
		}
		return result;
	}

	public Object clone() throws CloneNotSupportedException {
		return (CrosshairOverlayRefactoring1) super.clone();
	}
}