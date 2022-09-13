package com.knubisoft.tasks.algorithm.reflection;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

public class ConstructorUtilsImpl implements ConstructorUtils {
    @SneakyThrows
    @Override
    public <T> T invokeConstructor(Class<T> cls, Object... args) {
        if (cls == null || args == null) {
            throw new NullPointerException();
        }

        Class<?>[] parameters = new Class[args.length];

        int count = 0;
        for (Object object : args) {
            if (object.getClass() == Integer.class) {
                parameters[count] = int.class;
                count++;
            }

            if (object.getClass() == Double.class) {
                parameters[count] = double.class;
                count++;
            }

            if (object.getClass() == String.class) {
                parameters[count] = String.class;
                count++;
            }

        }

        Constructor<T> constructor = cls.getConstructor(parameters);

        return (T) constructor.newInstance(args);
    }

    @Override
    @SneakyThrows
    public <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... parameterTypes) {
        if (cls == null || parameterTypes == null) {
            throw new NullPointerException();
        }

        return cls.getConstructor(parameterTypes);
    }
}
