package com.knubisoft.tasks.algorithm.collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.*;

public class UtilsImpl implements Utils {
    @Override
    public <K, V> Map<V, K> invertMap(Map<K, V> map) {
        if (map.isEmpty()) {
            throw new NullPointerException();
        }

        Map<V, K> invertMap = new HashMap<>();
        //Map<V, K> invertMap = map.entrySet().stream()
        // .collect(Collectors.toMap(Map.Entry::getValue , Map.Entry::getKey));

        for (Map.Entry<K, V> entry : map.entrySet()) {
            invertMap.put(entry.getValue(), entry.getKey());
        }

        //return MapUtils.invertMap(map);
        return invertMap;
    }

    @Override
    public <E> List<E> union(List<? extends E> list1, List<? extends E> list2) {
        if (list1.isEmpty() || list2.isEmpty()) {
            throw new NullPointerException();
        }

        List<E> mergedList = new ArrayList<>();
        //List<E> mergedList = Stream.concat(list1.stream(), list2.stream()).toList();

        mergedList.addAll(list1);
        mergedList.addAll(list2);

        //return CollectionUtils.union(list1, list2).stream().toList();
        return mergedList;
    }

    @Override
    public boolean isEqualList(Collection<?> list1, Collection<?> list2) {
        if (list1.isEmpty() || list2.isEmpty()) {
            throw new NullPointerException();
        }

        return CollectionUtils.containsAll(list1, list2);
        //return list1.equals(list2) && list1.size() == list2.size();
    }

    @Override
    public <K, V> Map<K, V> synchronizedMap(Map<K, V> map) {
        if (map.isEmpty()) {
            throw new NullPointerException();
        }

        Map<K, V> synchronizedMap = MapUtils.synchronizedMap(map);
        Set<K> s = synchronizedMap.keySet();

        Map<K, V> result = new HashMap<>();

        synchronized (synchronizedMap) {
            for (K k : s) {
                result.put(k, synchronizedMap.get(k));
            }
        }

        return result;
    }

    @Override
    public <O> Collection<O> disjunction(Iterable<? extends O> a, Iterable<? extends O> b) {
        if (!a.iterator().hasNext() || !a.iterator().hasNext()) {
            throw new NullPointerException();
        }

        return CollectionUtils.disjunction(a, b);
    }

    @Override
    public <O> Collection<O> subtract(Iterable<? extends O> a, Iterable<? extends O> b) {
        if (!a.iterator().hasNext() || !a.iterator().hasNext()) {
            throw new NullPointerException();
        }

        return CollectionUtils.subtract(a, b);
    }

    @Override
    public <E> Comparator<E> chainedComparator(Comparator<E>... comparators) {
        if (comparators == null) {
            throw new NullPointerException();
        }

        return ComparatorUtils.chainedComparator(comparators);
    }

    @Override
    public boolean isSubCollection(Collection<?> a, Collection<?> b) {
        if (a.isEmpty() || b.isEmpty()) {
            throw new NullPointerException();
        }

        return CollectionUtils.isSubCollection(a, b);
    }
}
