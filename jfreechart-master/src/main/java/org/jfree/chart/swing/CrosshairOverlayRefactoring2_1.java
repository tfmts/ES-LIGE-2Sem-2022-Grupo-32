package org.jfree.chart.swing;


import java.util.List;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.internal.Args;
import java.util.ArrayList;
import java.io.Serializable;
import org.jfree.chart.api.PublicCloneable;

public class CrosshairOverlayRefactoring2_1 implements Serializable, PublicCloneable {
	private List<Crosshair> xCrosshairs;

	public List<Crosshair> getXCrosshairs() {
		return xCrosshairs;
	}

	public void setXCrosshairs(List<Crosshair> xCrosshairs) {
		this.xCrosshairs = xCrosshairs;
	}

	/**
	* Removes a domain axis crosshair and sends an   {@link OverlayChangeEvent}  to all registered listeners.
	* @param crosshair    the crosshair (  {@code   null}   not permitted).
	* @see #addDomainCrosshair(org.jfree.chart.plot.Crosshair)
	*/
	public void removeDomainCrosshair(Crosshair crosshair, CrosshairOverlay crosshairOverlay) {
		Args.nullNotPermitted(crosshair, "crosshair");
		if (this.xCrosshairs.remove(crosshair)) {
			crosshair.removePropertyChangeListener(crosshairOverlay);
			crosshairOverlay.fireOverlayChanged();
		}
	}

	/**
	* Clears all the domain crosshairs from the overlay and sends an  {@link OverlayChangeEvent}   to all registered listeners (unless there were no crosshairs to begin with).
	*/
	public void clearDomainCrosshairs(CrosshairOverlay crosshairOverlay) {
		if (this.xCrosshairs.isEmpty()) {
			return;
		}
		for (Crosshair c : getDomainCrosshairs()) {
			this.xCrosshairs.remove(c);
			c.removePropertyChangeListener(crosshairOverlay);
		}
		crosshairOverlay.fireOverlayChanged();
	}

	/**
	* Returns a new list containing the domain crosshairs for this overlay.
	* @return   A list of crosshairs.
	*/
	public List<Crosshair> getDomainCrosshairs() {
		return new ArrayList<>(this.xCrosshairs);
	}

	/**
	* Adds a crosshair against the domain axis (x-axis) and sends an  {@link OverlayChangeEvent}   to all registered listeners.
	* @param crosshair    the crosshair (  {@code   null}   not permitted).
	* @see #removeDomainCrosshair(org.jfree.chart.plot.Crosshair)
	* @see #addRangeCrosshair(org.jfree.chart.plot.Crosshair)
	*/
	public void addDomainCrosshair(Crosshair crosshair, CrosshairOverlay crosshairOverlay) {
		Args.nullNotPermitted(crosshair, "crosshair");
		this.xCrosshairs.add(crosshair);
		crosshair.addPropertyChangeListener(crosshairOverlay);
		crosshairOverlay.fireOverlayChanged();
	}

	/**
	* Adds a crosshair against the range axis and sends an  {@link OverlayChangeEvent}   to all registered listeners.
	* @param crosshair    the crosshair (  {@code   null}   not permitted).
	*/
	public void addRangeCrosshair(Crosshair crosshair, CrosshairOverlay crosshairOverlay,
			List<Crosshair> thisYCrosshairs) {
		Args.nullNotPermitted(crosshair, "crosshair");
		thisYCrosshairs.add(crosshair);
		crosshair.addPropertyChangeListener(crosshairOverlay);
		crosshairOverlay.fireOverlayChanged();
	}

	public Object clone() throws CloneNotSupportedException {
		return (CrosshairOverlayRefactoring2_1) super.clone();
	}
}