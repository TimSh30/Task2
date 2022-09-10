import java.util.*;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComplexExamples {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            return getId() == this.getId() && getName().equals(this.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    static void sortByName(Person[] person) {
        try {
            System.out.println(Stream.of(person).distinct().collect(Collectors.groupingBy(Person::getName,
                    Collectors.counting())));
        } catch (NullPointerException NPE) {
            System.out.println("person has null");
            NPE.printStackTrace();
        }
    }

        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */


    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        System.out.print("Test 1: ");
        sortByName(RAW_DATA);

        System.out.print("Test 2: ");
        System.out.println(outSumma(new Integer[]{3, 4, 2, 7}, 10));

        System.out.print("Test 3: ");
        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));
        System.out.println(fuzzySearch("cwhl", "cartwheel")); // true
        System.out.println(fuzzySearch("cwhee", "cartwheel")); // true
        System.out.println(fuzzySearch("cartwheel", "cartwheel")); // true
        System.out.println(fuzzySearch("cwheeel", "cartwheel")); // false
        System.out.println(fuzzySearch("lw", "cartwheel")); // false

        testMethod();


        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться
                Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */



        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */



        /*
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */


    }

    static List<Integer> outSumma(Integer[] array, int summa) {

        List<Integer> listFromArray = new ArrayList<>();
        try {
            for (Integer integer : array) {
                int j = 1;
                while (j < array.length) {
                    if (summa - array[j] == integer) {
                        listFromArray.add(integer);
                        listFromArray.add(array[j]);
                    }
                    j++;
                }
            }
            return listFromArray.stream().distinct().collect(Collectors.toList());
        } catch (NullPointerException NPE) {
            System.out.println("array has null");
            return null;
        }
    }

    static boolean fuzzySearch(String pattern, String chars) {
        try {
            List<Character> listFromPattern = pattern.chars().mapToObj(w -> (char) w).collect(Collectors.toList());
            List<Character> randomSequence = chars.chars().mapToObj(w -> (char) w).collect(Collectors.toList());

            List<Character> res = new ArrayList<>();
            for (int i = 0, j = 0; j < chars.length() & i < pattern.length(); j++)
                if (listFromPattern.get(i) == randomSequence.get(j)) {
                    res.add(listFromPattern.get(i));
                    i++;
                }
            return res.equals(listFromPattern);
        } catch (NullPointerException NPE) {
            System.out.println("pattern has null");
            NPE.printStackTrace();
            return false;
        }
    }

    static void testMethod() {
        System.out.print("\nTests: ");
        assert fuzzySearch("yes", "pydge@js") : "fuzzySearch  test failed";
        // assert fuzzySearch(null, "pydge@js")==false:"fuzzySearch  null test failed";
        assert (outSumma(new Integer[]{1, 5, 15, 25}, 16)).stream().reduce(Integer::sum).get() == 16 : "outSumma  test failed";
        System.out.print("Tests passed");
    }
}
