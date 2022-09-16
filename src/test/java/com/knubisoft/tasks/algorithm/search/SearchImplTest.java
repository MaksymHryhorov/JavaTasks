package com.knubisoft.tasks.algorithm.search;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchImplTest {
    private final SearchImpl search = new SearchImpl();

    @Test
    @SneakyThrows
    void binarySearch() {
        long[] arrayTest = {1, 3, 4, 5, 6};
        long[] arrayTest0 = {};
        long[] arrayTest1 = {-100, 0, 7, 8, 9, 10, 11};


        assertThrows(NullPointerException.class, () -> search.binarySearch(null, 0));
        assertThrows(NullPointerException.class, () -> search.binarySearch(arrayTest0, 0));

        assertEquals(2, search.binarySearch(arrayTest, 4));
        assertEquals(4, search.binarySearch(arrayTest, 6));
        assertEquals(0, search.binarySearch(arrayTest1, -100));
        assertEquals(1, search.binarySearch(arrayTest1, 0));
        assertEquals(5, search.binarySearch(arrayTest1, 10));
    }

}
