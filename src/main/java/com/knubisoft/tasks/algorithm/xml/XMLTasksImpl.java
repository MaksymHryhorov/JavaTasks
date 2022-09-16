package com.knubisoft.tasks.algorithm.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.knubisoft.tasks.algorithm.ModelRoot;
import com.knubisoft.tasks.algorithm.ModelRoot.Item;
import lombok.SneakyThrows;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class XMLTasksImpl implements XMLTasks {

    @Override
    @SneakyThrows
    public ModelRoot parseXML(String xml) {
        if (xml == null || xml.trim().isEmpty()) {
            throw new NullPointerException();
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();

        return xmlMapper.readValue(xml, ModelRoot.class);
    }

    @SneakyThrows
    @Override
    public List<Integer> getAllIds(String xml) {
        if (xml == null || xml.trim().isEmpty()) {
            throw new NullPointerException();
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();

        List<Integer> integerList = new ArrayList<>();
        ModelRoot modelRoot = xmlMapper.readValue(xml, ModelRoot.class);

        int counter = 0;
        for (Item integer : modelRoot.getItems()) {
            integerList.add(counter, integer.id);
            counter++;
        }

        return integerList;
    }

    @Override
    @SneakyThrows
    public List<String> getNameWithIdMoreThan1(String xml) {
        if (xml == null || xml.trim().isEmpty()) {
            throw new NullPointerException();
        }

        List<String> stringList = new ArrayList<>();
        XmlMapper xmlMapper = new XmlMapper();
        ModelRoot modelRoot = xmlMapper.readValue(xml, ModelRoot.class);

        for (Item integer : modelRoot.getItems()) {
            if (integer.id > 1) {
                stringList.add(integer.name);
            }
        }

        return stringList;
    }

    @SneakyThrows
    private Object xpath(String xml, String expression, QName q) {
        return null;
    }

    private <T> List<T> apply(NodeList nodeList, Function<Node, T> tFunction) {

        return null;
    }
}
