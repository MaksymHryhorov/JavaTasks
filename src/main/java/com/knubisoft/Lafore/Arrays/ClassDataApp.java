package com.knubisoft.Lafore.Arrays;

import com.knubisoft.Lafore.Sort.SortApp;

/**
 * Массивы в Java являются объектами и создаются оператором new.
 * В неупорядоченных массивах вставка выполняется быстрее, а поиск и удаление — медленнее.
 * Инкапсуляция массива в классе защищает массив от случайных изменений.
 * В упорядоченных массивах может применяться двоичный поиск.
 * Логарифм числа A по основанию B равен количеству последовательных делений A на B, пока результат не станет меньше 1.
 * Линейный поиск выполняется за время, пропорциональное количеству элементов в массиве.
 * Двоичный поиск выполняется за время, пропорциональное логарифму количества элементов.
 * O-синтаксис предоставляет удобный способ сравнения скорости алгоритмов.
 * Алгоритм, выполняемый за время O(1), является самым эффективным;
 * за время O(log N) — хорошим, O(N) — неплохим и за время O(N2) — достаточно плохим.
 */

public class ClassDataApp {
    public static void main(String[] args) {
        int maxSize = 100;
        ClassDataArray cda = new ClassDataArray(maxSize);

        cda.insert("Evans", "Party", 28);
        cda.insert("Smith", "Tom", 22);
        cda.insert("Yee", "Sat", 43);
        cda.insert("Adams", "Jose", 64);
        cda.insert("Stimson", "Henry", 21);
        cda.insert("Vang", "Hose", 54);
        cda.insert("Cress-well", "Henry", 18);

        cda.displayA();

        String searchKey = "Stimson";

        Person found = cda.find(searchKey);

        if (found != null) {
            System.out.print("Found ");
            found.displayPerson();
        } else {
            System.out.println("Can't find " + searchKey);
        }


        System.out.println("Deleting Smith, Yee, and Cress-well");
        cda.delete("Smith");
        cda.delete("Yee");
        cda.delete("Cress-well");

        cda.displayA();
    }
}
