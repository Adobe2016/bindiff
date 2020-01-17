// Copyright 2011-2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.security.zynamics.bindiff.gui.tabpanels;

import com.google.common.base.Preconditions;
import com.google.security.zynamics.bindiff.gui.window.MainWindow;
import com.google.security.zynamics.bindiff.project.Workspace;
import com.google.security.zynamics.bindiff.resources.Constants;
import com.google.security.zynamics.bindiff.utils.ImageUtils;
import com.google.security.zynamics.zylib.general.Pair;
import com.google.security.zynamics.zylib.gui.CDialogAboutEx;
import com.google.security.zynamics.zylib.gui.GuiHelper;
import com.google.security.zynamics.zylib.gui.license.UpdateCheckHelper;
import java.awt.Desktop;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/** Controller class with functions applicable to all "tab panels". */
public class TabPanelFunctions {
  private static final ImageIcon BINDIFF_ABOUT_IMAGE =
      ImageUtils.getImageIcon(Constants.ABOUT_BINDIFF_IMAGE_PATH);

  private final Workspace workspace;

  private final MainWindow window;

  public TabPanelFunctions(final MainWindow window, final Workspace workspace) {
    this.window = Preconditions.checkNotNull(window);
    this.workspace = Preconditions.checkNotNull(workspace);
  }

  public MainWindow getMainWindow() {
    return window;
  }

  public void checkForUpdates() {
    UpdateCheckHelper.checkForUpdatesWithUi(
        window, Constants.PRODUCT_NAME, Constants.PRODUCT_VERSION);
  }

  public void exitBinDiff() {
    window.getController().exitBinDiff();
  }

  public Workspace getWorkspace() {
    return workspace;
  }

  public void reportABug() {
    try {
      Desktop.getDesktop().browse(new URL(Constants.BUG_REPORT_URL).toURI());
    } catch (final MalformedURLException e) {
      // Should never happen
      assert false : "Malformed URL";
    } catch (final URISyntaxException e) {
      // Should never happen
      assert false : "URL could not be converted to URI";
    } catch (final IOException e) {
      JOptionPane.showMessageDialog(
          window,
          "Couldn't open URL \"" + Constants.BUG_REPORT_URL + "\"!",
          Constants.DEFAULT_WINDOW_TITLE,
          JOptionPane.ERROR_MESSAGE);
    }
  }

  public void showAboutDialog() {
    final List<Pair<String, URL>> urls = new ArrayList<>();
    try {
      urls.add(new Pair<>("zynamics Website", new URL(Constants.ZYNAMICS_HOME_URL)));
      urls.add(
          new Pair<>("BinDiff Product Site", new URL(Constants.ZYNAMICS_BINDIFF_PRODUCT_SITE_URL)));
      urls.add(new Pair<>("Report Bugs", new URL(Constants.ZYNAMICS_SUPPORT_MAIL_URL)));
    } catch (final MalformedURLException e) {
      assert false : "Malformed URL in About dialog.";
    }

    final String message = Constants.PRODUCT_NAME_VERSION + "\n" + Constants.COPYRIGHT_TEXT;
    final String description =
        "\nParts of this software were created by third parties and may have "
            + "different licensing requirements.\n"
            + "Please see the manual for a complete list.\n";

    final Image appImage = BINDIFF_ABOUT_IMAGE.getImage();
    final CDialogAboutEx dlg =
        new CDialogAboutEx(
            window, new ImageIcon(appImage), Constants.PRODUCT_NAME, message, description, urls);

    dlg.setIconImage(ImageUtils.getImageIcon(Constants.APP_ICON_PATH_16X16).getImage());

    dlg.setSize(dlg.getWidth() - 8, dlg.getHeight());

    GuiHelper.centerChildToParent(window, dlg, true);
    dlg.setVisible(true);
  }

  public void showHelp() {
    try {
      Desktop.getDesktop().browse(new URL(Constants.MANUAL_URL).toURI());
    } catch (final MalformedURLException e) {
      // Should never happen
      assert false : "Malformed URL";
    } catch (final URISyntaxException e) {
      // Should never happen
      assert false : "URL could not be converted to URI";
    } catch (final IOException e) {
      JOptionPane.showMessageDialog(
          window,
          "Couldn't open URL \"" + Constants.MANUAL_URL + "\"!",
          Constants.DEFAULT_WINDOW_TITLE,
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
