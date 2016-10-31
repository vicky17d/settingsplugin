package com.blah.view;

import javax.swing.*;
import java.awt.*;

public class ConfigurationPanel extends JPanel
{
    private JCheckBox _pluginEnabled;
    private JTextField _settingsFilePath;

    public ConfigurationPanel()
    {
        buildGUI();
    }

    private void buildGUI()
    {
        _pluginEnabled = new JCheckBox("Enable Cloud Settings Plugin");
        _pluginEnabled.setToolTipText("Enable/disable the PsiViewer tool window");

        setLayout(new BorderLayout());

        JPanel topPane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
        topPane.setBorder(BorderFactory.createEtchedBorder());
        topPane.add(_pluginEnabled);
        topPane.add(_settingsFilePath);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setPreferredSize(new Dimension(400, 600));


        add(topPane, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }


    public String getSettingsFilePath() {
        return _settingsFilePath.getText();
    }

    public void setSettingsFilePath(String str) {
        _settingsFilePath.setText(str);
    }

}
