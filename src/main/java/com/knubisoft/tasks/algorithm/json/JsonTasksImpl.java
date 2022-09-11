package com.knubisoft.tasks.algorithm.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knubisoft.tasks.algorithm.ModelRoot;
import com.knubisoft.tasks.algorithm.ModelRoot.Item;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class JsonTasksImpl implements JsonTasks {

    @Override
    @SneakyThrows
    public ModelRoot parseJson(String json) {
        if (json.isEmpty()) {
            throw new NullPointerException();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        return mapper.readValue(json, ModelRoot.class);
    }

    @SneakyThrows
    @Override
    public List<Integer> getAllIds(String json) {
        if (json.isEmpty()) {
            throw new NullPointerException();
        }

        List<Integer> idList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        mapper.findAndRegisterModules();

        ModelRoot modelRoot = mapper.readValue(json, ModelRoot.class);

        int counter = 0;
        for (Item integer : modelRoot.getItems()) {
            idList.add(counter, integer.id);
            counter++;
        }

        return idList;
    }

    @Override
    @SneakyThrows
    public List<String> getNameWithIdMoreThan1(String json) {
        if (json.isEmpty()) {
            throw new NullPointerException();
        }

        List<String> stringList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        ModelRoot modelRoot = mapper.readValue(json, ModelRoot.class);

        for (Item integer : modelRoot.getItems()) {
            if (integer.id > 1) {
                stringList.add(integer.name);
            }
        }

        return stringList;
    }

    @Override
    public List<Map<String, String>> getAllItems(String json) {
        if (json.isEmpty()) {
            throw new NullPointerException();
        }

        Map<String, String> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        ModelRoot modelRoot = null;

        try {
            modelRoot = mapper.readValue(json, ModelRoot.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<Item> items = modelRoot.getItems();

        //return JsonPath.parse(json).read("$.items[*]");
        return items.stream().map(item -> getMap(item, map)).collect(Collectors.toList());
    }

    private Map<String, String> getMap(Item item, Map<String, String> map) {
        map.put(item.getName(), item.toString());

        return map;
    }

}
