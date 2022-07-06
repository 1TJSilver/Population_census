import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++){
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        Stream<Person> find_minor = persons.stream();
        Stream<Person> find_conscript = persons.stream();
        Stream<Person> find_workable = persons.stream();

        long minor_amount = find_minor
                .filter(x -> x.getAge() >= 18 && x.getAge() < 27 && x.getSex() == Sex.MEN)
                .count();
        System.out.println("Minor amount = " + minor_amount);

        List<String> conscript = find_conscript
                .map(x -> x.getFamily())
                .collect(Collectors.toList());

        List<Person> workable = find_workable
                .filter(x -> {
                    if (x.getSex() == Sex.WOMEN && x.getEducation() == Education.HIGHER && x.getAge() >= 18 && x.getAge() < 60) {
                        return true;
                    }else if (x.getSex() == Sex.MEN && x.getEducation() == Education.HIGHER && x.getAge() >= 18 && x.getAge() < 65) {
                        return true;
                    }
                    return false;
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
