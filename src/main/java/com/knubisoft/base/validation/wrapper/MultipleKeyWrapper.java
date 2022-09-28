package com.knubisoft.base.validation.wrapper;

import java.util.Objects;

public class MultipleKeyWrapper {
    private final Object key;
    public final Object additionalKey;

    public MultipleKeyWrapper(Object key, Object additionalKey) {
        this.key = key;
        this.additionalKey = additionalKey;
    }

    public Object getKey() {
        return key;
    }

    public Object getAdditionalKey() {
        return additionalKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleKeyWrapper that = (MultipleKeyWrapper) o;
        return Objects.equals(key, that.key) && Objects.equals(additionalKey, that.additionalKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, additionalKey);
    }
}
