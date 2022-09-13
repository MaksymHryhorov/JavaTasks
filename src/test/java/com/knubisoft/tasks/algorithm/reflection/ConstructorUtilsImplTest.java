package com.knubisoft.tasks.algorithm.reflection;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstructorUtilsImplTest {
    private final ConstructorUtils constructor = new ConstructorUtilsImpl();

    @Test
    @SneakyThrows
    void invokeConstructor() {
        Class<?> cls = Class.forName("com.knubisoft.tasks.algorithm.ModelRoot$Item");
        Object[] type0 = {"wrong"};
        Object[] type = {10};
        Object[] type2 = {11, "newType"};
        Object[] type3 = {12, "newType", "newName"};
        Object[] type4 = {13, "newType", "newName", 14.1};


        assertThrows(NullPointerException.class, () -> constructor.invokeConstructor(cls, null));
        assertThrows(NullPointerException.class, () -> constructor.invokeConstructor(null, ""));
        assertThrows(NoSuchMethodException.class, () -> constructor.invokeConstructor(cls, type0));

        assertEquals("ModelRoot.Item(id=10, type=null, name=null, ppu=0.0, batters=null, topping=null)",
                constructor.invokeConstructor(cls, type).toString());
        assertEquals("ModelRoot.Item(id=11, type=newType, name=null, ppu=0.0, batters=null, topping=null)",
                constructor.invokeConstructor(cls, type2).toString());
        assertEquals("ModelRoot.Item(id=12, type=newType, name=newName, ppu=0.0, batters=null, topping=null)",
                constructor.invokeConstructor(cls, type3).toString());
        assertEquals("ModelRoot.Item(id=13, type=newType, name=newName, ppu=14.1, batters=null, topping=null)",
                constructor.invokeConstructor(cls, type4).toString());

    }

    @Test
    @SneakyThrows
    void getMatchingAccessibleConstructor() {
        Class<?> cls = Class.forName("com.knubisoft.tasks.algorithm.ModelRoot$Item");
        Class<?>[] type = {int.class};
        Class<?>[] type2 = {int.class, String.class};


        assertThrows(NullPointerException.class, () -> constructor.getMatchingAccessibleConstructor(cls, null));
        assertThrows(NullPointerException.class, () -> constructor.getMatchingAccessibleConstructor(null, null));

        assertEquals(cls.getConstructor(type),
                constructor.getMatchingAccessibleConstructor(cls, type));
        assertEquals(cls.getConstructor(type2),
                constructor.getMatchingAccessibleConstructor(cls, type2));


    }
}
