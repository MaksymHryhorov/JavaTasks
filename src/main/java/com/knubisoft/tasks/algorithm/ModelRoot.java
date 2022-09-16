package com.knubisoft.tasks.algorithm;

import com.knubisoft.tasks.algorithm.reflection.AnnotationTest;
import lombok.*;

import java.util.List;

/**
 * TODO fix model
 *
 * @see json and xml file
 */
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelRoot {

    public List<Item> items;

    public static class Batter {
        public String id;
        public String type;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        @AnnotationTest
        public int id;
        public String type;
        public String name;
        public double ppu;
        public Batters batters;
        public List<Topping> topping;

        public Item(int id) {
            this.id = id;
        }

        public Item(int id, String type) {
            this.id = id;
            this.type = type;
        }

        public Item(int id, String type, String name, double ppu) {
            this.id = id;
            this.type = type;
            this.name = name;
            this.ppu = ppu;
        }

        public Item(int id, String type, String name) {
            this.id = id;
            this.type = type;
            this.name = name;
        }
    }

    public static class Batters {
        public List<Batter> batter;
    }

    public static class Topping {
        public String id;
        public String type;
    }

}
