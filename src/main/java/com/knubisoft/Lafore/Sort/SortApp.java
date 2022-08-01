package com.knubisoft.Lafore.Sort;


import com.knubisoft.Lafore.Arrays.Person;
import org.checkerframework.checker.units.qual.A;

public class SortApp {

    public static void main(String[] args) {
        int maxSize = 100;
        ArraySort arraySort = new ArraySort(maxSize);
        ObjectSort objectSort = new ObjectSort(maxSize);

        arraySort.insert(79);
        arraySort.insert(99);
        arraySort.insert(44);
        arraySort.insert(55);
        arraySort.insert(52);

        arraySort.insert(88);
        arraySort.insert(11);
        arraySort.insert(0);
        arraySort.insert(66);
        arraySort.insert(33);

        arraySort.display();
        //arraySort.bubbleSort();
        //arraySort.selectionSort();
        arraySort.insertionSort();
        arraySort.display();


        objectSort.insert("Evans", "Party", 28);
        objectSort.insert("Smith", "Tom", 22);
        objectSort.insert("Yee", "Sat", 43);
        objectSort.insert("Adams", "Jose", 64);
        objectSort.insert("Stimson", "Henry", 21);
        objectSort.insert("Vang", "Hose", 54);
        objectSort.insert("Cress-well", "Henry", 18);

        System.out.println("Before sorting");
        objectSort.display();
        objectSort.insertionSort();

        System.out.println("After sorting");
        objectSort.display();
    }
}

class ArraySort {
    private long[] a;
    private int nElems;

    public ArraySort(int max) {
        a = new long[max];
        nElems = 0;
    }

    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }

    /**
     * O(N^2)
     * <p>
     * 1. Сравнить два элемента.
     * 2. Если первый элемент больше, поменять их местами.
     * 3. Перейти на одну позицию вправо.
     */
    public void bubbleSort() {
        int out, in;

        for (out = nElems - 1; out > 1; out--) {
            for (in = 0; in < out; in++) {
                if (a[in] > a[in + 1]) { // порядок не правильный?
                    swap(in, in + 1); // меняем местами
                }
            }
        }
    }

    /**
     * O(N^2)
     * <p>
     * Выполняется быстрее, чем bubble sort, из-за меньшего количества перестановок.
     * 1. Выбор наименшего элемента и меняется с местами с 0 элементом
     * 2. Первый элемент больше не меняется, элемент + 1 -> 1.
     */
    public void selectionSort() {
        int out, in, min;

        for (out = 0; out < nElems - 1; out++) {
            min = out;
            for (in = out + 1; in < nElems - 1; in++) {
                if (a[in] < a[min]) { // если значение min больше
                    min = in;         // найден новый минимум
                }
            }
            swap(out, min);           // меняем их
        }
    }

    /**
     * O(N^2)
     * <p>
     * Количество операций копирования приблизительно совпадает с количеством
     * сравнений. Однако копирование занимает меньше времени, чем перестановка, так
     * что для случайных данных этот алгоритм работает вдвое быстрее пузырьковой
     * сортировки и быстрее сортировки методом выбора
     * <p>
     * Частичная сортировка
     * 1. Выбираем где-то середину
     */
    public void insertionSort() {
        int in, out;

        for (out = 0; out < nElems; out++) {
            long temp = a[out];
            in = out;

            while (in > 0 && a[in - 1] >= temp) {
                a[in] = a[in - 1];
                --in;
            }
            a[in] = temp;
        }

    }

    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }

}

class ObjectSort {
    private Person[] people;
    private int nElems;

    public ObjectSort(int max) {
        people = new Person[max];
        nElems = 0;
    }

    public void insert(String last, String first, int age) {
        people[nElems] = new Person(last, first, age);
        nElems++;

    }

    public void display() {
        for (int i = 0; i < nElems; i++) {
            people[i].displayPerson();
        }
        System.out.println("");
    }

    public void insertionSort() {
        int in, out;

        for (out = 1; out < nElems; out++) {
            Person temp = people[out];
            in = out;

            while (in > 0 && people[in-1].getLastName().compareTo(temp.getLastName()) > 0) {
                people[in] = people[in - 1];
                --in;
            }
            people[in] = temp;
        }

    }

}