package com.blah.util;

import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;


public final class Helpers
{
  private static final Logger LOG = Logger.getInstance("com.blah.util.Helpers");
  private static final Icon DEFAULT_ICON = getDefaultIcon();

  /**
   * Gets an icon either via the class loader, or from a url (maybe a file). <p> To keep the peace, it will always
   * return <i>some</i> sort of icon even if it has to build one on-the-fly.
   *
   * @param path
   * @return An Icon almost guaranteed to be usable.
   */
  public static Icon getIcon(String path)
  {
    URL url = Helpers.class.getResource(path);      // pull icon from jar first
    if (url == null)
    {
      try
      {
        url = new URL(path);    // now try from a url
      }
      catch (MalformedURLException e)
      {
        LOG.debug("Could not find icon " + path);
        return DEFAULT_ICON;
      }
    }

    Icon icon = new ImageIcon(url);
    if (icon.getIconWidth() < 0 || icon.getIconHeight() < 0)
    {
      LOG.debug("Bad icon data " + path);
      return DEFAULT_ICON;
    }

    return icon;
  }

  private static Icon getDefaultIcon()
  {
    BufferedImage bi = new BufferedImage(18, 18, BufferedImage.TYPE_INT_ARGB_PRE);
    Graphics2D g2 = bi.createGraphics();
    g2.setBackground(Color.red);
    g2.clearRect(0, 0, bi.getWidth(), bi.getHeight());
    g2.setColor(Color.white);
    g2.setStroke(new BasicStroke(2));
    GeneralPath x = new GeneralPath();
    x.moveTo(0, 0);
    x.lineTo(bi.getWidth() - 1, bi.getHeight() - 1);
    x.moveTo(0, bi.getHeight() - 1);
    x.lineTo(bi.getWidth() - 1, 0);
    g2.draw(x);
    return new ImageIcon(bi);
  }

}
