package com.blah.controller.application;

import com.blah.CloudSettingsConstants;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

//@State(name = CloudSettingsConstants.CONFIGURATION_COMPONENT_NAME, storages = @Storage("other.xml"))
@State(name = CloudSettingsConstants.CONFIGURATION_COMPONENT_NAME, storages = @Storage)
public class CloudApplicationSettings implements PersistentStateComponent<CloudApplicationSettings> {
    public String HIGHLIGHT_COLOR;
    public boolean PLUGIN_ENABLED;

    private final TextAttributes _textAttributes = new TextAttributes();

    public CloudApplicationSettings() {
//        HIGHLIGHT_COLOR = DEFAULT_HIGHLIGHT_COLOR;
        PLUGIN_ENABLED = true;
//        getTextAttributes().setBackgroundColor(Helpers.parseColor(HIGHLIGHT_COLOR));
    }

    @Nullable
    @Override
    public CloudApplicationSettings getState() {
        return this;
    }

    @Override
    public void loadState(CloudApplicationSettings state) {
        XmlSerializerUtil.copyBean(state, this);

//        getTextAttributes().setBackgroundColor(Helpers.parseColor(HIGHLIGHT_COLOR));
    }

    public static CloudApplicationSettings getInstance() {
        return ServiceManager.getService(CloudApplicationSettings.class);
    }

    public TextAttributes getTextAttributes()
    {
        return _textAttributes;
    }
}
