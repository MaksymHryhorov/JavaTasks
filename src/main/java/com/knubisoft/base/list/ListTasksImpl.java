package com.knubisoft.base.list;

import java.util.*;

public class ListTasksImpl implements ListTasks {
    @Override
    public List<String> addElements(String... elements) {
        List<String> stringList = new ArrayList<>();

        stringList.addAll(Arrays.asList(elements));

        // for (String list: elements){
        //      stringList.add(list); }

        return stringList;
    }

    @Override
    public List<String> getElementsByIndexes(List<String> elements, int[] indexes) {

        if (indexes == null || elements.stream().toArray().length > 3) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i <= indexes.length - 1; i++) {
            if (indexes[i] > 3 || indexes[i] < 0) {
                throw new IllegalArgumentException();
            }
        }

        List<String> list = new ArrayList<>();

        list.addAll(elements);

        for (int index : indexes) {
            String str = elements.get(index);
            list.add(str);
        }

        return list;
    }

    @Override
    public ArrayList<String> addElementsByIndexes(ArrayList<String> elements, int[] indexes) {
        if (elements.stream().toArray().length > 3 || indexes == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i <= indexes.length - 1; i++) {
            if (indexes[i] > 3 || indexes[i] < 0) {
                throw new IllegalArgumentException();
            }
        }

        ArrayList<String> list = new ArrayList<>(elements);

        for (int i : indexes) {
            String element = elements.get(i);
            list.add(i, element);
        }

        return list;
    }

    @Override
    public LinkedList<String> setElementsByIndexes(LinkedList<String> elements, int[] indexes) {
        LinkedList<String> linkedList = new LinkedList<>(elements);

        for (int i : indexes) {
            String element = elements.get(i);

            linkedList.set(i, element);
        }

        return linkedList;
    }

    @Override
    public int getListSize(List<String> list) {
        if (list == null) {
            return 0;
        }

        return list.size();
    }

    @Override
    public List<Long> merge(List<Integer> first, List<Long> second, List<String> third) {
        if (third.contains(null)) {
            throw new NullPointerException();
        }

        List<Long> list = new ArrayList<>();

        for (Integer integer : first) {
            list.add(integer.longValue());
        }

        list.addAll(second);

        for (String s : third) {
            list.add(Long.valueOf(s));
        }

        return list;
    }

    @Override
    public int findMaxValue(List<Integer> first, List<Integer> second, List<Integer> third) {
        List list = new ArrayList<Integer>();
        list.addAll(first);
        list.addAll(second);
        list.addAll(third);

        Collections.sort(list);

        int max = (int) list.get(list.size() - 1);
        return max;
    }

    @Override
    public int findMinValue(List<Integer> first, List<Integer> second, List<Integer> third) {
        List list = new ArrayList<Integer>();
        list.addAll(first);
        list.addAll(second);
        list.addAll(third);

        Collections.sort(list);

        int min = (int) list.get(0);

        return min;

    }

    @Override
    public int multiplyMax2Elements(List<Integer> first, List<Integer> second, List<Integer> third) {
        List list = new ArrayList<Integer>();
        list.addAll(first);
        list.addAll(second);
        list.addAll(third);

        Collections.sort(list);

        int result = 1;
        for (int i = 0; i < 2; i++) {
            int max = (int) list.get(list.size() - 1);
            result = result * max;
        }

        return result;
    }

    @Override
    public List<String> removeNulls(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.remove(null);
        }

        return list;
    }

    @Override
    public List<Integer> flatMapWithoutNulls(List<List<Integer>> list) {
        if (list == null) {
            throw new NoSuchElementException();
        }

        List<Integer> integerList = new ArrayList<>();

        for (List element : list) {
            integerList.addAll(element.stream()
                    .filter(Objects::nonNull)
                    .toList());
        }

        return integerList;
    }

    @Override
    public List<Integer> cloneIds(List<Integer> originalIds) {
        if (originalIds == null) {
            throw new NoSuchElementException();
        }

        List<Integer> integerList = new ArrayList<>();

        for (int i = 0; i < originalIds.size(); i++) {
            Integer integer = originalIds.get(i);

            if (integer != null) {
                integerList.add(integer);
            }

        }

        return integerList;
    }

    @Override
    public List<String> shuffle(List<String> originalStrings) {
        List<String> list = new ArrayList<>(originalStrings);

        Collections.sort(list);
        System.out.println(list);

        return list;
    }

    @Override
    public String getLastElement(LinkedList<String> list) {
        String str = "";

        try {
            if (list.size() > 0) {
                str = list.get(list.size() - 1);
            }
        } catch (NullPointerException e) {
            throw new NoSuchElementException();
        }

        return str;
    }

    @Override
    public List<String> compareElements(LinkedList<String> originalCollection, LinkedList<String> additionalCollection) {
        if (originalCollection == null || additionalCollection == null) {
            throw new IllegalArgumentException();
        }

        List<String> list = new ArrayList<>();

        for (String e : originalCollection) {
            if (additionalCollection.contains(e)) {
                list.add(e);
            }
        }


        return list;
    }
}
