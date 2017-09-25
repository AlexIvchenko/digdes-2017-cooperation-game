package project;

/**
 * @author Alex Ivchenko
 */
public class Main {
    public static void main(final String... args) {
        Tournament.Settings settings = new Tournament.Settings(100, 10, 10);
        Statistic statistic = Statistic.builder()
                .experiments(1000)
                .settings(settings)
                .build();
        statistic.get().forEach(System.out::println);
    }
}
