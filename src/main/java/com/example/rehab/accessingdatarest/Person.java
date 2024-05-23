package com.example.rehab.accessingdatarest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {

    /**
     * 스프링 부트 3.2.5 버전에서 설치되는 JPA 라이브러리의 경우 GeneratedValue의 strategy 기본 값이
     * GenerationType.AUTO이므로 이렇게 명시를 하던 안하던 달라질게 없다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
