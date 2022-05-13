/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2022, by David Gilbert and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -----------------------
 * DefaultTitleEditor.java
 * -----------------------
 * (C) Copyright 2005-2022, by David Gilbert.
 *
 * Original Author:  David Gilbert;
 * Contributor(s):   Arnaud Lelievre;
 *                   Daniel Gredler;
 *
 */

package org.jfree.chart.swing.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;

/**
 * A panel for editing the properties of a chart title.
 */
class DefaultTitleEditor extends JPanel implements ActionListener {

    private DefaultTitleEditorRefactoring2 defaultTitleEditorRefactoring2;

	private DefaultTitleEditorRefactoring1 defaultTitleEditorRefactoring1;

	/** The paint (color) used to draw the title. */
    private final PaintSample titlePaint;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources
            = ResourceBundle.getBundle("org.jfree.chart.editor.LocalizationBundle");

    /**
     * Standard constructor: builds a panel for displaying/editing the
     * properties of the specified title.
     *
     * @param title  the title, which should be changed.
     */
    public DefaultTitleEditor(Title title) {

        TextTitle t = (title != null ? (TextTitle) title
                : new TextTitle(localizationResources.getString("Title")));
        defaultTitleEditorRefactoring2.setShowTitle((title != null));
        defaultTitleEditorRefactoring1.setTitleFont(t.getFont());
        this.titlePaint = new PaintSample(t.getPaint());

        setLayout(new BorderLayout());

        JPanel general = new JPanel(new BorderLayout());
        general.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                localizationResources.getString("General")
            )
        );

        JPanel interior = new JPanel(new LCBLayout(4));
        interior.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        interior.add(new JLabel(localizationResources.getString("Show_Title")));
        this.defaultTitleEditorRefactoring2.getShowTitleCheckBox().setSelected(this.defaultTitleEditorRefactoring2.getShowTitle());
        this.defaultTitleEditorRefactoring2.getShowTitleCheckBox().setActionCommand("ShowTitle");
        this.defaultTitleEditorRefactoring2.getShowTitleCheckBox().addActionListener(this);
        interior.add(new JPanel());
        interior.add(this.defaultTitleEditorRefactoring2.getShowTitleCheckBox());

        JLabel titleLabel = new JLabel(localizationResources.getString("Text"));
        interior.add(titleLabel);
        interior.add(this.defaultTitleEditorRefactoring2.getTitleField());
        interior.add(new JPanel());

        JLabel fontLabel = new JLabel(localizationResources.getString("Font"));
		this.defaultTitleEditorRefactoring1 = new DefaultTitleEditorRefactoring1(new FontDisplayField(this.getTitleFont()));
        this.defaultTitleEditorRefactoring2.getSelectFontButton().setActionCommand("SelectFont");
        this.defaultTitleEditorRefactoring2.getSelectFontButton().addActionListener(this);
        interior.add(fontLabel);
        interior.add(this.defaultTitleEditorRefactoring1.getFontfield());
        interior.add(this.defaultTitleEditorRefactoring2.getSelectFontButton());

        JLabel colorLabel = new JLabel(
            localizationResources.getString("Color")
        );
		this.defaultTitleEditorRefactoring2 = new DefaultTitleEditorRefactoring2(new JTextField(t.getText()),
				new JCheckBox(), new JButton(localizationResources.getString("Select...")));
        this.defaultTitleEditorRefactoring2.getSelectPaintButton().setActionCommand("SelectPaint");
        this.defaultTitleEditorRefactoring2.getSelectPaintButton().addActionListener(this);
        interior.add(colorLabel);
        interior.add(this.titlePaint);
        interior.add(this.defaultTitleEditorRefactoring2.getSelectPaintButton());

        defaultTitleEditorRefactoring2.enableOrDisableControls();

        general.add(interior);
        add(general, BorderLayout.NORTH);
    }

    /**
     * Returns the title text entered in the panel.
     *
     * @return The title text entered in the panel.
     */
    public String getTitleText() {
        return defaultTitleEditorRefactoring2.getTitleText();
    }

    /**
     * Returns the font selected in the panel.
     *
     * @return The font selected in the panel.
     */
    public Font getTitleFont() {
        return this.defaultTitleEditorRefactoring1.getTitleFont();
    }

    /**
     * Returns the paint selected in the panel.
     *
     * @return The paint selected in the panel.
     */
    public Paint getTitlePaint() {
        return this.titlePaint.getPaint();
    }

    /**
     * Handles button clicks by passing control to an appropriate handler
     * method.
     *
     * @param event  the event
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        String command = event.getActionCommand();

        if (command.equals("SelectFont")) {
            defaultTitleEditorRefactoring1.attemptFontSelection(this);
        }
        else if (command.equals("SelectPaint")) {
            attemptPaintSelection();
        }
        else if (command.equals("ShowTitle")) {
            defaultTitleEditorRefactoring2.attemptModifyShowTitle();
        }
    }

    /**
     * Presents a font selection dialog to the user.
     */
    public void attemptFontSelection() {

        defaultTitleEditorRefactoring1.attemptFontSelection(this);
    }

    /**
     * Allow the user the opportunity to select a Paint object.  For now, we
     * just use the standard color chooser - all colors are Paint objects, but
     * not all Paint objects are colors (later we can implement a more general
     * Paint chooser).
     */
    public void attemptPaintSelection() {
        Paint p = this.titlePaint.getPaint();
        Color defaultColor = (p instanceof Color ? (Color) p : Color.BLUE);
        Color c = JColorChooser.showDialog(
            this, localizationResources.getString("Title_Color"), defaultColor
        );
        if (c != null) {
            this.titlePaint.setPaint(c);
        }
    }

    /**
     * Sets the properties of the specified title to match the properties
     * defined on this panel.
     *
     * @param chart  the chart whose title is to be modified.
     */
    public void setTitleProperties(JFreeChart chart) {
        if (this.defaultTitleEditorRefactoring2.getShowTitle()) {
            TextTitle title = chart.getTitle();
            if (title == null) {
                title = new TextTitle();
                chart.setTitle(title);
            }
            title.setText(defaultTitleEditorRefactoring2.getTitleText());
            title.setFont(getTitleFont());
            title.setPaint(getTitlePaint());
        }
        else {
            chart.setTitle((TextTitle) null);
        }
    }

}
