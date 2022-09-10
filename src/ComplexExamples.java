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


        /*List<Person> list = Stream.of(person).collect(Collectors.toList());
        list.stream().distinct().forEach((x) -> System.out.println(x.id + " " + x.name)); // добавить notNull

        List<String> books = list.stream().map(author -> author.getName())
                .flatMap(l -> list.stream())
                .map(book -> book.getName())
                .collect(Collectors.toList());*/


        Map<String, List<Integer>> mp = Stream.of(person)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.mapping(Person::getId, Collectors.toList())));
        Stream.of(mp).forEach(System.out::println);

        Map<String, Long> mp1 = Stream.of(person).distinct().collect(Collectors.groupingBy(Person::getName,
                Collectors.counting()));
        System.out.println(mp1);


        // List<Person> names = (List<Person>) Arrays.stream(RAW_DATA).filter(Objects::nonNull).map(author -> author.getName()).peek(name -> System.out.println(name))).collect(Collectors.toList()); // log.debug("")

        //System.out.println(names);


        /*Map<Person, Integer> counter = new HashMap<>();
        for (Person x : list) {
            int newValue = counter.getOrDefault(x, 0) + 1;
            counter.put(x, newValue);
        }*/


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

//    @Override
//    public String toString() {
//        System.out.println("[");
//        System.out.println();
//        System.out.println("]");
//        return super.toString();
//    }

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

        sortByName(RAW_DATA);

        System.out.println(outSumma(new Integer[]{3, 4, 2, 7}, 10));
        //System.out.println(outSumma(new Integer[]{3, 4, 2, 7, 8}, 10));

        System.out.println(fuzzySearch("car", "ca6$$#_rtwheel"));


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

        if (array.equals(null)) return null;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (summa - array[j] == array[i]) {
                    listFromArray.add(array[i]);
                    listFromArray.add(array[j]);
                }
            }
        }
        return listFromArray.stream().distinct().collect(Collectors.toList());
    }

    static boolean fuzzySearch(String pattern, String chars) {
        List<Character> str = pattern.chars().mapToObj(w -> (char) w).collect(Collectors.toList());
        List<Character> str1 = chars.chars().mapToObj(w -> (char) w).collect(Collectors.toList());
        System.out.println(str);
        System.out.println(str1);
        for (int i = 0; i < pattern.length(); i++) {
            for (int j = 0; j < chars.length(); j++) {

            }
        }


        return false;
    }
}
