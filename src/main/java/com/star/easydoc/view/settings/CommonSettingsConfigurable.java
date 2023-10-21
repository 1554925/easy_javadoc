package com.star.easydoc.view.settings;

import java.util.Objects;
import java.util.TreeMap;
import javax.swing.*;

import com.google.common.collect.Maps;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.star.easydoc.common.Consts;
import com.star.easydoc.config.EasyDocConfig;
import com.star.easydoc.config.EasyDocConfigComponent;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nls.Capitalization;
import org.jetbrains.annotations.Nullable;

/**
 * @author wangchao
 * @date 2019/08/25
 */
public class CommonSettingsConfigurable implements Configurable {

    private EasyDocConfig config = ServiceManager.getService(EasyDocConfigComponent.class).getState();
    private CommonSettingsView view = new CommonSettingsView();

    @Nls(capitalization = Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "StepEasyDoc";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return view.getComponent();
    }

    @Override
    public boolean isModified() {
        if (!Objects.equals(config.getTranslator(), view.getTranslatorBox().getSelectedItem())) {
            return true;
        }
        if (!Objects.equals(config.getAppId(), view.getAppIdTextField().getText())) {
            return true;
        }
        if (!Objects.equals(config.getAppSecret(), view.getAppSecretTextField().getText())) {
            return true;
        }
        if (!Objects.equals(config.getAppKey(), view.getAppKeyTextField().getText())) {
            return true;
        }
        if (!Objects.equals(config.getProxyUrl(), view.getProxyUrlText().getText())) {
            return true;
        }
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {
        config.setTranslator(String.valueOf(view.getTranslatorBox().getSelectedItem()));
        config.setAppId(view.getAppIdTextField().getText());
        config.setAppSecret(view.getAppSecretTextField().getText());
        config.setAppKey(view.getAppKeyTextField().getText());
        config.setProxyUrl(view.getProxyUrlText().getText());

        if (config.getWordMap() == null) {
            config.setWordMap(new TreeMap<>());
        }
        if (config.getProjectWordMap() == null) {
            config.setProjectWordMap(Maps.newTreeMap());
        }

        if (config.getTranslator() == null || !Consts.ENABLE_ZH_NAME_SET.contains(config.getTranslator())) {
            throw new ConfigurationException("请选择正确的翻译方式");
        }
        if (StringUtils.isBlank(config.getProxyUrl())) {
            throw new ConfigurationException("代理地址不能为空");
        }

        switch (config.getTranslator()) {
            case Consts.BAIDU_TRANSLATOR:
            case Consts.ALIYUN_TRANSLATOR:
            case Consts.YOUDAO_AI_TRANSLATOR:
            case Consts.JINSHAN_TRANSLATOR:
                if (StringUtils.isBlank(config.getAppKey())) {
                    throw new ConfigurationException("AppId不能为空");
                }
                if (StringUtils.isBlank(config.getAppSecret())) {
                    throw new ConfigurationException("AppSecret不能为空");
                }
                break;
            case Consts.MICROSOFT_TRANSLATOR:
            case Consts.MICROSOFT_FREE_TRANSLATOR:
            case Consts.TENCENT_TRANSLATOR:
            case Consts.GOOGLE_TRANSLATOR:
                if (StringUtils.isBlank(config.getAppKey())) {
                    throw new ConfigurationException("AppKey不能为空");
                }
                break;
        }

    }

    @Override
    public void reset() {
        view.refresh();
    }
}
