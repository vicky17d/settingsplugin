package com.blah.controller.application;

import com.blah.CloudSettingsConstants;
import com.blah.view.ConfigurationPanel;
import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by VickyD on 10/30/16.
 */
public class CloudConfiguration extends BaseConfigurable implements CloudSettingsConstants {
  private ConfigurationPanel _panel;

//  private final CloudApplicationSettings settings;

  @Nls
  @Override
  public String getDisplayName() {
    return PLUGIN_NAME;
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return null;
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return null;
  }

  @Override
  public void apply() throws ConfigurationException {

  }

  @Override
  public void reset() {

  }

  @Override
  public void disposeUIResources() {

  }
}
