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
 */

package org.jfree.chart.swing.editor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

/**
 * A panel for choosing a font from the available system fonts - still a bit of
 * a hack at the moment, but good enough for demonstration applications.
 */
public class FontChooserPanel extends JPanel {

    private FontChooserPanelRefactoring2 fontChooserPanelRefactoring2 = new FontChooserPanelRefactoring2();

	/** The font sizes that can be selected. */
    public static final String[] SIZES = {"9", "10", "11", "12", "14", "16",
            "18", "20", "22", "24", "28", "36", "48", "72"};

    /** The list of sizes. */
    private JList sizelist;

    /** The resourceBundle for the localization. */
    protected static ResourceBundle localizationResources 
            = ResourceBundle.getBundle("org.jfree.chart.ui.LocalizationBundle");

    /**
     * Standard constructor - builds a FontChooserPanel initialised with the
     * specified font.
     *
     * @param font  the initial font to display.
     */
    public FontChooserPanel(Font font) {

        final GraphicsEnvironment g
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] fonts = g.getAvailableFontFamilyNames();

        setLayout(new BorderLayout());
        final JPanel right = new JPanel(new BorderLayout());

        final JPanel fontPanel = new JPanel(new BorderLayout());
        fontPanel.setBorder(BorderFactory.createTitledBorder(
                            BorderFactory.createEtchedBorder(),
                            localizationResources.getString("Font")));
        fontChooserPanelRefactoring2.setFontlist(new JList(fonts));
        final JScrollPane fontpane = new JScrollPane(this.fontChooserPanelRefactoring2.getFontlist());
        fontpane.setBorder(BorderFactory.createEtchedBorder());
        fontPanel.add(fontpane);
        add(fontPanel);

        final JPanel sizePanel = new JPanel(new BorderLayout());
        sizePanel.setBorder(BorderFactory.createTitledBorder(
                            BorderFactory.createEtchedBorder(),
                            localizationResources.getString("Size")));
        this.sizelist = new JList(SIZES);
        final JScrollPane sizepane = new JScrollPane(this.sizelist);
        sizepane.setBorder(BorderFactory.createEtchedBorder());
        sizePanel.add(sizepane);

        final JPanel attributes = new JPanel(new GridLayout(1, 2));
        fontChooserPanelRefactoring2.getFontChooserPanelRefactoring1().setBold(new JCheckBox(localizationResources.getString("Bold")));
        fontChooserPanelRefactoring2.getFontChooserPanelRefactoring1().setItalic(new JCheckBox(localizationResources.getString("Italic")));
        attributes.add(this.fontChooserPanelRefactoring2.getFontChooserPanelRefactoring1().getBold());
        attributes.add(this.fontChooserPanelRefactoring2.getFontChooserPanelRefactoring1().getItalic());
        attributes.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                localizationResources.getString("Attributes")));

        right.add(sizePanel, BorderLayout.CENTER);
        right.add(attributes, BorderLayout.SOUTH);

        add(right, BorderLayout.EAST);

        fontChooserPanelRefactoring2.setSelectedFont(font, this.sizelist);
    }

    /**
     * Returns a Font object representing the selection in the panel.
     *
     * @return the font.
     */
    public Font getSelectedFont() {
        return fontChooserPanelRefactoring2.getSelectedFont(this);
    }

    /**
     * Returns the selected name.
     *
     * @return the name.
     */
    public String getSelectedName() {
        return fontChooserPanelRefactoring2.getSelectedName();
    }

    /**
     * Returns the selected style.
     *
     * @return the style.
     */
    public int getSelectedStyle() {
        return fontChooserPanelRefactoring2.getFontChooserPanelRefactoring1().getSelectedStyle();
    }

    /**
     * Returns the selected size.
     *
     * @return the size.
     */
    public int getSelectedSize() {
        final String selected = (String) this.sizelist.getSelectedValue();
        if (selected != null) {
            return Integer.parseInt(selected);
        }
        else {
            return 10;
        }
    }

    /**
     * Initializes the contents of the dialog from the given font
     * object.
     *
     * @param font the font from which to read the properties.
     */
    public void setSelectedFont (Font font) {
        fontChooserPanelRefactoring2.setSelectedFont(font, this.sizelist);
    }
}

