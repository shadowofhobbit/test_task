package ru.od;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.od.model.MainEntity;
import ru.od.model.SubEntity;
import ru.od.repository.MainEntityRepository;
import ru.od.repository.SubEntityRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootApplication
public class Application {

    private static final int DATA_SIZE = 1000;
    private static final int SUBENTITIES_COUNT = 10;

    @Bean
    public CommandLineRunner loadData(MainEntityRepository mainEntityRepository, SubEntityRepository subEntityRepository) {
        return (args) -> {
            List<String> names = IntStream.range(0, SUBENTITIES_COUNT)
                    .mapToObj(i -> " i " + i)
                    .collect(Collectors.toList());
            for (int j = 0; j < DATA_SIZE; j++) {
                MainEntity mainEntity = new MainEntity();
                mainEntity.setName(String.format("Entity name of %d", ThreadLocalRandom.current().nextLong(Long.MAX_VALUE)));
                for (int i = 0; i < SUBENTITIES_COUNT; i++) {
                    SubEntity subEntity = new SubEntity();
                    subEntity.setName(names.get(i));
                    subEntityRepository.save(subEntity);
                    mainEntity.getSubEntities().add(subEntity);
                }
                mainEntityRepository.save(mainEntity);
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
