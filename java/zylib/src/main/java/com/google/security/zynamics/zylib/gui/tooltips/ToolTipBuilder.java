// Copyright 2011 Google Inc. All Rights Reserved.

package com.google.security.zynamics.zylib.gui.tooltips;

/**
 * This class can be used to build HTML tooltips which can be shown on random Swing objects.
 */
public class ToolTipBuilder {
  private final StringBuilder toolTip = new StringBuilder();

  public ToolTipBuilder() {
    toolTip.append("<html><table border=\"1\" cellpadding=\"1\" cellspacing=\"0f\">");
  }

  public ToolTipBuilder(final int borderWidth) {
    toolTip.append("<html><table border=\"" + borderWidth
        + "\" cellpadding=\"1\" cellspacing=\"0f\">");
  }

  /**
   * Adds a cell to the current row in the tooltip table.
   * 
   * Note: Please make sure to escape the string before adding it. Otherwise the HTML code might get
   * screwed up.
   * 
   * @param cellContent
   */
  public void addCell(final String cellContent) {
    toolTip.append("<td>");
    toolTip.append(cellContent);
    toolTip.append("</td>");
  }

  public void beginNewTable(final int borderWidth) {
    toolTip.append("</table><table border=\"" + borderWidth
        + "\" cellpadding=\"1\" cellspacing=\"0f\">");
  }

  public void beginRow() {
    toolTip.append("<tr>");
  }

  public void endRow() {
    toolTip.append("</tr>");
  }

  public String finish() {
    toolTip.append("</table></html>");

    return toolTip.toString();
  }
}