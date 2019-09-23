package com.shareyuan.support;

import org.springframework.core.style.ToStringCreator;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStatefulConfigurable<C> extends AbstractConfigurable<C> implements StatefulConfigurable<C> {

    private Map<String, C> config = new HashMap<>();

    protected AbstractStatefulConfigurable(Class<C> configClass) {
        super(configClass);
    }

    public Map<String, C> getConfig() {
        return this.config;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("config", config)
                .append("configClass", getConfigClass())
                .toString();
    }
}
