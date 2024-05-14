package com.example.rehab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <a href="https://spring.io/guides/gs/spring-boot">Building an Application with Spring Boot</a>
 * <p>
 * {@link RestController @RestController} annotation을 통해 web requests를 제어하기 위한 Spring MVC로 사용되도록 설정.
 */
@RestController
public class HelloController {

    /**
     * {@link GetMapping @GetMapping}으로 브라우저 혹은 command line의 curl 요청에 pure text를 반환하도록 하였다. 이는
     * {@link RestController @RestController}가 {@link org.springframework.stereotype.Controller @Controller}와
     * {@link org.springframework.web.bind.annotation.ResponseBody @ResponseBody}를 포함하고 있어 웹 요청이 뷰가 아닌
     * 데이터를 반환하도록 하기 때문이다.
     *
     * <p>
     * 이 메서드는 추후 비활성화 될 수 있다.
     *
     * @return pure text "Greetings from Spring Boot!"
     */
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
