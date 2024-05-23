package com.example.rehab;

import com.example.rehab.accessingdatajpa.Customer;
import com.example.rehab.accessingdatajpa.CustomerRepository;
import com.example.rehab.consumingrest.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

/**
 * {@link SpringBootApplication @SpringBootApplication}은 다음과 같은 어노테이션을 포함하고 있다:
 *
 * <li>{@link org.springframework.context.annotation.Configuration @Configuration}: 이 클래스를 application context를 위한
 * bean difinitions의 source로 지정한다.</li>
 * <li>{@link org.springframework.boot.autoconfigure.EnableAutoConfiguration @EnableAutoConfiguration}: Spring Boot가
 * classpath settings, other beans, various property settings를 기반으로 빈을 추가하게 한다. 예를 들어 {@code spring-webmvc}
 * 가 classpath에 존재할 경우 어플리케이션을 웹 어플리케이션으로 지정하고 Dispather Servlet과 같은 key behaviors를 활성화한다.</li>
 * <li>{@link org.springframework.context.annotation.ComponentScan @ComponentScan}: Spring이 빈으로 등록될 수 있는
 * 클래스를 찾아 빈으로 등록하게 한다.</li>
 */
@SpringBootApplication
public class RehabApplication {

    public static final Logger log = LoggerFactory.getLogger(RehabApplication.class);

    public static void main(String[] args) {
        /*
        SpringApplication.run() 메서드를 통해 어플리케이션을 실행한다.
         */
        SpringApplication.run(RehabApplication.class, args);
    }

    /**
     * method marked as a {@link Bean @Bean}. 어플리케이션이 시작할 때 수행되며 어플리케이션에 작성된 빈과 Spring Boot에 의해
     * 자동으로 추가된 모든 빈을 구해온 후 정렬해 출력한다.
     *
     * @param ctx {@link ApplicationContext context}
     * @return print bean names
     */
    @Bean
    @SuppressWarnings("all")
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            var beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (var beanName : beanNames) {
                // reload, refresh 과정에서 심히 거슬린다.
//                System.out.println(beanName);
            }
        };
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            Quote quote = restTemplate.getForObject("http://localhost:8080/api/random", Quote.class);
            log.info(Objects.requireNonNull(quote).toString());
        };
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return args -> {
            // save a few customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> {
                log.info(customer.toString());
            });
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("-------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("-------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");
        };
    }

}
