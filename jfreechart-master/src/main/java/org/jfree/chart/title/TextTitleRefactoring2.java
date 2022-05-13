package org.jfree.chart.title;


import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.chart.entity.ChartEntity;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.block.EntityBlockParams;
import org.jfree.chart.entity.TitleEntity;
import java.io.Serializable;

public class TextTitleRefactoring2 implements Serializable, Cloneable {
	private String toolTipText;
	private String urlText;

	public String getToolTipText() {
		return toolTipText;
	}

	public void setToolTipText2(String toolTipText) {
		this.toolTipText = toolTipText;
	}

	public String getUrlText() {
		return urlText;
	}

	public void setUrlText(String urlText) {
		this.urlText = urlText;
	}

	/**
	* Sets the tool tip text to the specified text and sends a {@link TitleChangeEvent}  to all registered listeners.
	* @param text   the text ( {@code  null}  permitted).
	*/
	public void setToolTipText(String text, TextTitle textTitle) {
		this.toolTipText = text;
		textTitle.notifyListeners(new TitleChangeEvent(textTitle));
	}

	/**
	* Sets the URL text to the specified text and sends a {@link TitleChangeEvent}  to all registered listeners.
	* @param text   the text ( {@code  null}  permitted).
	*/
	public void setURLText(String text, TextTitle textTitle) {
		this.urlText = text;
		textTitle.notifyListeners(new TitleChangeEvent(textTitle));
	}

	public ChartEntity drawRefactoring1(Rectangle2D area, Object params, ChartEntity entity, TextTitle textTitle) {
		if (params instanceof EntityBlockParams) {
			EntityBlockParams p = (EntityBlockParams) params;
			if (p.getGenerateEntities()) {
				entity = new TitleEntity(area, textTitle, this.toolTipText, this.urlText);
			}
		}
		return entity;
	}

	public Object clone() throws CloneNotSupportedException {
		return (TextTitleRefactoring2) super.clone();
	}
}