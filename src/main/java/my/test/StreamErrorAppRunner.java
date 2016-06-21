package my.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
@EnableScheduling
public class StreamErrorAppRunner {

    public static void main(String[] args) {
        SpringApplication.run(StreamErrorAppRunner.class);
    }

    @Bean
    public CommandLineRunner demo(TestEntityRepository testEntityRepository) {
        return (args) -> {
            testEntityRepository.save(new TestEntity("test_one"));
            testEntityRepository.save(new TestEntity("test_two"));
            testEntityRepository.save(new TestEntity("test_three"));
            testEntityRepository.save(new TestEntity("test_four"));
            testEntityRepository.save(new TestEntity("test_five"));
            testEntityRepository.save(new TestEntity("test_six"));
            testEntityRepository.save(new TestEntity("test_seven"));
        };
    }

}

@Component
class TestEntityLogger {
    private static final Logger logger = LoggerFactory.getLogger(TestEntityLogger.class);

    private final TestEntityRepository testEntityRepository;

    @Autowired
    TestEntityLogger(TestEntityRepository testEntityRepository) {
        this.testEntityRepository = testEntityRepository;
    }

    @Scheduled(fixedDelay = 1100, initialDelay = 1100)
    //@Transactional // <- Uncomment this line and the error will go away.
    public void logAllTestEntities() {
        try (Stream<TestEntity> entities = testEntityRepository.findEntities()) {
            entities.forEach(e -> {
                // It's actually not important if you do or do not modify the db record in this method.
                // It just as well might be a readOnly method.
                e.setName("new name" + Math.random());
                testEntityRepository.save(e);
                logger.info("Entity: " + e.toString());
            });
        }
        logger.info("Finished reading the stream. It should be closed by now.");
    }
}

@Entity
class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public TestEntity() {
    }

    public TestEntity(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

interface TestEntityRepository extends JpaRepository<TestEntity, Long> {

    @Query("select t from TestEntity t")
    Stream<TestEntity> findEntities();

}