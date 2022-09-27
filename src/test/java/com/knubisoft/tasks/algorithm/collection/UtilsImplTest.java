package com.knubisoft.tasks.algorithm.collection;

import org.apache.commons.collections4.comparators.ComparatorChain;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UtilsImplTest {
    UtilsImpl utils = new UtilsImpl();

    @Test
    void invertMap() {
        Map<Integer, String> map = new HashMap<>();

        assertThrows(NullPointerException.class, () -> utils.invertMap(map));

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        assertEquals("[one=1, two=2, three=3]", utils.invertMap(map).entrySet().toString());
        assertEquals(3, utils.invertMap(map).get("three"));
        assertEquals(2, utils.invertMap(map).get("two"));
        assertEquals(1, utils.invertMap(map).get("one"));
    }

    @Test
    void union() {
        assertThrows(NullPointerException.class, () -> utils.union(List.of(), List.of()));
        assertThrows(NullPointerException.class, () -> utils.union(List.of(), List.of("A", "B", "C")));
        assertThrows(NullPointerException.class, () -> utils.union(List.of("A", "B", "C"), List.of()));

        assertEquals("[A, B, C, D, E, F]", utils.union(List.of("A", "B", "C"),
                List.of("D", "E", "F")).toString());

        assertEquals("[1, 2, 3, D, E, F]", utils.union(List.of(1, 2, 3),
                List.of("D", "E", "F")).toString());
    }

    @Test
    void isEqualList() {
        assertThrows(NullPointerException.class, () -> utils.isEqualList(List.of(), List.of()));
        assertThrows(NullPointerException.class, () -> utils.isEqualList(List.of(), List.of(1)));
        assertThrows(NullPointerException.class, () -> utils.isEqualList(List.of(1), List.of()));

        assertTrue(utils.isEqualList(List.of(1, 2, 3), List.of(1, 2, 3)));
        assertTrue(utils.isEqualList(List.of("1", "2", "3"), List.of("1", "2", "3")));
        assertFalse(utils.isEqualList(List.of("1", "2", "3"), List.of(1, 2, 3)));
        assertFalse(utils.isEqualList(List.of(1, 2), List.of(1, 2, 3)));
    }

    @Test
    void synchronizedMap() {

        assertThrows(NullPointerException.class, () -> utils.synchronizedMap(Map.of()));

        assertEquals("{1=one, 2=two}", utils.synchronizedMap(Map.of(1, "one", 2, "two")).toString());
        assertEquals("{1=one}", utils.synchronizedMap(Map.of(1, "one")).toString());
    }

    @Test
    void disjunction() {
        assertThrows(NullPointerException.class, () -> utils.disjunction(List.of(), List.of()));

        assertEquals("[B, C]", utils.disjunction(List.of("A", "B"), List.of("A", "C")).toString());
        assertEquals("[C]", utils.disjunction(List.of("A", "B"), List.of("A", "B", "C")).toString());
        assertEquals("[B, C, X]", utils.disjunction(List.of("A", "B", "C"), List.of("A", "X")).toString());
    }

    @Test
    void subtract() {
        assertThrows(NullPointerException.class, () -> utils.subtract(List.of(), List.of()));

        assertEquals("[A]", utils.subtract(List.of("A"), List.of("X")).toString());
        assertEquals("[B]", utils.subtract(List.of("A", "B"), List.of("A")).toString());
        assertEquals("[C]", utils.subtract(List.of("A", "B", "C"), List.of("A", "B")).toString());
    }

    @Test
    void chainedComparator() {
        ComparatorChain comparatorChain = new ComparatorChain();

        assertThrows(NullPointerException.class, () -> utils.chainedComparator(null));
    }

    @Test
    void isSubCollection() {
        assertThrows(NullPointerException.class, () -> utils.isSubCollection(List.of(), List.of()));
        assertThrows(NullPointerException.class, () -> utils.isSubCollection(List.of(), List.of("A")));
        assertThrows(NullPointerException.class, () -> utils.isSubCollection(List.of("A"), List.of()));

        assertTrue(utils.isEqualList(List.of("A", "B"), List.of("A")));
        assertTrue(utils.isEqualList(List.of("A"), List.of("A")));
        assertFalse(utils.isEqualList(List.of("A", "B"), List.of("C")));
        assertFalse(utils.isEqualList(List.of("A", "B"), List.of("Y")));

    }
}