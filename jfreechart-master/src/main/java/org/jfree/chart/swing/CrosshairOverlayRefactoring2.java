package org.jfree.chart.swing;


import java.util.List;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.internal.Args;
import java.util.ArrayList;
import java.io.Serializable;
import org.jfree.chart.api.PublicCloneable;

public class CrosshairOverlayRefactoring2 implements Serializable, PublicCloneable {
	private CrosshairOverlayRefactoring2_1 crosshairOverlayRefactoring2_1 = new CrosshairOverlayRefactoring2_1();
	private List<Crosshair> yCrosshairs;

	public List<Crosshair> getXCrosshairs() {
		return crosshairOverlayRefactoring2_1.getXCrosshairs();
	}

	public void setXCrosshairs(List<Crosshair> xCrosshairs) {
		crosshairOverlayRefactoring2_1.setXCrosshairs(xCrosshairs);
	}

	public List<Crosshair> getYCrosshairs() {
		return yCrosshairs;
	}

	public void setYCrosshairs(List<Crosshair> yCrosshairs) {
		this.yCrosshairs = yCrosshairs;
	}

	/**
	* Removes a domain axis crosshair and sends an  {@link OverlayChangeEvent} to all registered listeners.
	* @param crosshair   the crosshair ( {@code  null}  not permitted).
	* @see #addDomainCrosshair(org.jfree.chart.plot.Crosshair)
	*/
	public void removeDomainCrosshair(Crosshair crosshair, CrosshairOverlay crosshairOverlay) {
		crosshairOverlayRefactoring2_1.removeDomainCrosshair(crosshair, crosshairOverlay);
	}

	/**
	* Clears all the domain crosshairs from the overlay and sends an {@link OverlayChangeEvent}  to all registered listeners (unless there were no crosshairs to begin with).
	*/
	public void clearDomainCrosshairs(CrosshairOverlay crosshairOverlay) {
		crosshairOverlayRefactoring2_1.clearDomainCrosshairs(crosshairOverlay);
	}

	/**
	* Returns a new list containing the domain crosshairs for this overlay.
	* @return  A list of crosshairs.
	*/
	public List<Crosshair> getDomainCrosshairs() {
		return crosshairOverlayRefactoring2_1.getDomainCrosshairs();
	}

	/**
	* Adds a crosshair against the domain axis (x-axis) and sends an {@link OverlayChangeEvent}  to all registered listeners.
	* @param crosshair   the crosshair ( {@code  null}  not permitted).
	* @see #removeDomainCrosshair(org.jfree.chart.plot.Crosshair)
	* @see #addRangeCrosshair(org.jfree.chart.plot.Crosshair)
	*/
	public void addDomainCrosshair(Crosshair crosshair, CrosshairOverlay crosshairOverlay) {
		crosshairOverlayRefactoring2_1.addDomainCrosshair(crosshair, crosshairOverlay);
	}

	/**
	* Adds a crosshair against the range axis and sends an {@link OverlayChangeEvent}  to all registered listeners.
	* @param crosshair   the crosshair ( {@code  null}  not permitted).
	*/
	public void addRangeCrosshair(Crosshair crosshair, CrosshairOverlay crosshairOverlay) {
		crosshairOverlayRefactoring2_1.addRangeCrosshair(crosshair, crosshairOverlay, this.yCrosshairs);
	}

	/**
	* Removes a range axis crosshair and sends an  {@link OverlayChangeEvent} to all registered listeners.
	* @param crosshair   the crosshair ( {@code  null}  not permitted).
	* @see #addRangeCrosshair(org.jfree.chart.plot.Crosshair)
	*/
	public void removeRangeCrosshair(Crosshair crosshair, CrosshairOverlay crosshairOverlay) {
		Args.nullNotPermitted(crosshair, "crosshair");
		if (this.yCrosshairs.remove(crosshair)) {
			crosshair.removePropertyChangeListener(crosshairOverlay);
			crosshairOverlay.fireOverlayChanged();
		}
	}

	/**
	* Clears all the range crosshairs from the overlay and sends an {@link OverlayChangeEvent}  to all registered listeners (unless there were no crosshairs to begin with).
	*/
	public void clearRangeCrosshairs(CrosshairOverlay crosshairOverlay) {
		if (this.yCrosshairs.isEmpty()) {
			return;
		}
		for (Crosshair c : getRangeCrosshairs()) {
			this.yCrosshairs.remove(c);
			c.removePropertyChangeListener(crosshairOverlay);
		}
		crosshairOverlay.fireOverlayChanged();
	}

	/**
	* Returns a new list containing the range crosshairs for this overlay.
	* @return  A list of crosshairs.
	*/
	public List<Crosshair> getRangeCrosshairs() {
		return new ArrayList<>(this.yCrosshairs);
	}

	public Object clone() throws CloneNotSupportedException {
		return (CrosshairOverlayRefactoring2) super.clone();
	}
}