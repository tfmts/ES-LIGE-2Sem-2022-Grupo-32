package org.jfree.chart.title;


import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.chart.event.TitleChangeListener;
import java.io.Serializable;
import javax.swing.event.EventListenerList;

public class TitleRefactoring1 implements Serializable, Cloneable {
	private boolean notify;

	public boolean getNotify() {
		return notify;
	}

	public void setNotify2(boolean notify) {
		this.notify = notify;
	}

	/**
	* Sets the flag that indicates whether or not the notification mechanism is enabled.  There are certain situations (such as cloning) where you want to turn notification off temporarily.
	* @param flag   the new value of the flag.
	*/
	public void setNotify(boolean flag, Title title, EventListenerList thisListenerList) {
		this.notify = flag;
		if (flag) {
			notifyListeners(new TitleChangeEvent(title), thisListenerList);
		}
	}

	/**
	* Notifies all registered listeners that the chart title has changed in some way.
	* @param event   an object that contains information about the change to the title.
	*/
	public void notifyListeners(TitleChangeEvent event, EventListenerList thisListenerList) {
		if (this.notify) {
			Object[] listeners = thisListenerList.getListenerList();
			for (int i = listeners.length - 2; i >= 0; i -= 2) {
				if (listeners[i] == TitleChangeListener.class) {
					((TitleChangeListener) listeners[i + 1]).titleChanged(event);
				}
			}
		}
	}

	public Object clone() throws CloneNotSupportedException {
		return (TitleRefactoring1) super.clone();
	}
}