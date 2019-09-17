package com.shareyuan.support;

public interface Configurable<C> {
    Class<C> getConfigClass();
    C newConfig();
}
