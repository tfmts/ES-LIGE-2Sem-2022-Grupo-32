package org.jfree.chart.title;


import org.jfree.chart.api.HorizontalAlignment;
import org.jfree.chart.internal.Args;
import org.jfree.chart.event.TitleChangeEvent;
import java.io.Serializable;

public class TextTitleRefactoring1 implements Serializable, Cloneable {
	private HorizontalAlignment textAlignment;
	private boolean expandToFitSpace = false;
	private int maximumLinesToDisplay = Integer.MAX_VALUE;

	public HorizontalAlignment getTextAlignment() {
		return textAlignment;
	}

	public void setTextAlignment2(HorizontalAlignment textAlignment) {
		this.textAlignment = textAlignment;
	}

	public boolean getExpandToFitSpace() {
		return expandToFitSpace;
	}

	public int getMaximumLinesToDisplay() {
		return maximumLinesToDisplay;
	}

	/**
	* Sets the text alignment and sends a  {@link TitleChangeEvent}  to all registered listeners.
	* @param alignment   the alignment ( {@code  null}  not permitted).
	*/
	public void setTextAlignment(HorizontalAlignment alignment, TextTitle textTitle) {
		Args.nullNotPermitted(alignment, "alignment");
		this.textAlignment = alignment;
		textTitle.notifyListeners(new TitleChangeEvent(textTitle));
	}

	/**
	* Sets the maximum number of lines to display and sends a {@link TitleChangeEvent}  to all registered listeners.
	* @param max   the maximum.
	* @see #getMaximumLinesToDisplay()
	*/
	public void setMaximumLinesToDisplay(int max, TextTitle textTitle) {
		this.maximumLinesToDisplay = max;
		textTitle.notifyListeners(new TitleChangeEvent(textTitle));
	}

	/**
	* Sets the flag that controls whether the title expands to fit the available space, and sends a  {@link TitleChangeEvent}  to all registered listeners.
	* @param expand   the flag.
	*/
	public void setExpandToFitSpace(boolean expand, TextTitle textTitle) {
		this.expandToFitSpace = expand;
		textTitle.notifyListeners(new TitleChangeEvent(textTitle));
	}

	public Object clone() throws CloneNotSupportedException {
		return (TextTitleRefactoring1) super.clone();
	}
}