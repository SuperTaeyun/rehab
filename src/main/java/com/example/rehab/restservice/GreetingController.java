package com.example.rehab.restservice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 별도의 이름을 지정하지 않을 경우 {@link com.example.rehab.servingwebcontent.GreetingController} 컨트롤러와 빈 이름이 중복되어
 * 오류가 발생하게 된다.
 */
@RestController("RestGreetingController")
public class GreetingController {
    public static final String template = "Hello %s!";

    private final AtomicLong counter = new AtomicLong();

    /**
     * 별도의 경로를 지정하지 않을 경우 {@link com.example.rehab.servingwebcontent.GreetingController#greeting(String, Model)}
     * 와/과 중복되어 오류가 발생하게 된다.
     *
     * @param name the name
     * @return formatted string
     */
    @GetMapping("/api/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
