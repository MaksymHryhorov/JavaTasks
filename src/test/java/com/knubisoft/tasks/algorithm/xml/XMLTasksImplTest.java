package com.knubisoft.tasks.algorithm.xml;

import com.knubisoft.tasks.algorithm.ModelRoot;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class XMLTasksImplTest {

    private final XMLTasks object = new XMLTasksImpl();
    private final String content;

    public XMLTasksImplTest() throws IOException {
        content = IOUtils.toString(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream("xml.xml")), StandardCharsets.UTF_8);
    }

    @Test
    void parseXML() {
        ModelRoot result = object.parseXML(content);
        InputStream wrongPath = ModelRoot.class.getClassLoader().getResourceAsStream("js.s");

        assertThrows(IndexOutOfBoundsException.class, () -> result.items.get(result.getItems().size() + 1));
        assertThrows(NullPointerException.class, () -> object.parseXML(wrongPath.toString()));

        assertEquals(3, result.items.size());
        assertEquals(result.getItems().get(0), result.getItems().get(0));
        assertEquals("Cake3:3", result.getItems().get(2).name + ":" + result.getItems().get(2).id);

    }

    @Test
    void getAllIds() {
        List<Integer> result = object.getAllIds(content);
        InputStream wrongPath = ModelRoot.class.getClassLoader().getResourceAsStream("xml.s");

        assertThrows(NullPointerException.class, () -> object.getAllIds(wrongPath.toString()));
        assertThrows(NullPointerException.class, () -> object.getAllIds(null));
        assertThrows(IndexOutOfBoundsException.class, () -> object.getAllIds(content).get(result.size() + 10));

        assertEquals(Arrays.asList(1, 2, 3), result);
        assertEquals(result.get(0), object.getAllIds(content).get(0));
        assertEquals(result.get(1), object.getAllIds(content).get(1));

    }

    @Test
    void getNameWithIdMoreThan1() {
        List<String> result = object.getNameWithIdMoreThan1(content);
        InputStream wrongPath = ModelRoot.class.getClassLoader().getResourceAsStream("xml.s");

        assertThrows(NullPointerException.class, () -> object.getNameWithIdMoreThan1(wrongPath.toString()));
        assertThrows(NullPointerException.class, () -> object.getNameWithIdMoreThan1(null));
        assertThrows(NullPointerException.class, () -> object.getNameWithIdMoreThan1(""));
        assertThrows(NullPointerException.class, () -> object.getNameWithIdMoreThan1("       "));
        assertThrows(IndexOutOfBoundsException.class, () -> object.getNameWithIdMoreThan1(content).get(result.size() + 10));

        assertEquals("Cake2", result.get(0));
        assertEquals("Cake3", result.get(1));
        assertEquals(Arrays.asList("Cake2", "Cake3"), result);
    }
}