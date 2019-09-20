package com.shareyuan.core.ratelimit;

import com.shareyuan.support.AbstractStatefulConfigurable;
import org.springframework.core.style.ToStringCreator;
import org.springframework.validation.Validator;

import java.util.Map;

/**
 * @Author : kent
 * @Description : 限流抽象类
 * @Date : 11:37 2019/9/17
 */
public abstract class AbstractRateLimiter<C> extends AbstractStatefulConfigurable<C> implements RateLimiter<C>/*, ApplicationListener<FilterArgsEvent>*/ {
    private String configurationPropertyName;
    private Validator validator;

    protected AbstractRateLimiter(Class<C> configClass, String configurationPropertyName, Validator validator) {
        super(configClass);
        this.configurationPropertyName = configurationPropertyName;
        this.validator = validator;
    }

    protected String getConfigurationPropertyName() {
        return configurationPropertyName;
    }

    protected Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

  /*  @Override
    public void onApplicationEvent(FilterArgsEvent event) {
        Map<String, Object> args = event.getArgs();

        if (args.isEmpty() || !hasRelevantKey(args)) {
            return;
        }

        String routeId = event.getRouteId();
        C routeConfig = newConfig();
        ConfigurationUtils.bind(routeConfig, args,
                configurationPropertyName, configurationPropertyName, validator);
        getConfig().put(routeId, routeConfig);
    }*/

    private boolean hasRelevantKey(Map<String, Object> args) {
        return args.keySet().stream()
                .anyMatch(key -> key.startsWith(configurationPropertyName + "."));
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("configurationPropertyName", configurationPropertyName)
                .append("config", getConfig())
                .append("configClass", getConfigClass())
                .toString();
    }

}
