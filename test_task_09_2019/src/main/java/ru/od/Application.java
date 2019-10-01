package ru.od;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.od.model.MainEntity;
import ru.od.model.SubEntity;
import ru.od.repository.MainEntityRepository;
import ru.od.repository.SubEntityRepository;

@EnableJpaRepositories
@SpringBootApplication
public class Application {
    private static int MAIN_ENTITY_NUMBER = 1000;
    private static int SUB_ENTITY_NUMBER = 10;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(MainEntityRepository mainEntityRepository, SubEntityRepository subEntityRepository) {
        return args -> {
            for (int a = 0; a < MAIN_ENTITY_NUMBER; a++) {
                MainEntity mainEntity = new MainEntity();
                StringBuilder mainEntityName = new StringBuilder("Entity name of ");
                mainEntity.setName(mainEntityName.append(Math.round(Long.MAX_VALUE * Math.random())).toString());
            for (int i = 0; i < SUB_ENTITY_NUMBER; i++) {
                SubEntity subEntity = new SubEntity();
                StringBuilder subEntityName = new StringBuilder(" i ");

                subEntity.setName(subEntityName.append(i).toString());
                subEntityRepository.save(subEntity);
                mainEntity.getSubEntities().add(subEntity);
            }
                mainEntityRepository.save(mainEntity);
            }
        };
    }
}
