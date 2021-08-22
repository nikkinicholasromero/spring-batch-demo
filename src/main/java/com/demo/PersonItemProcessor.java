package com.demo;

import org.springframework.batch.item.ItemProcessor;

import java.util.Locale;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(Person person) throws Exception {
        return Person.builder()
                .firstName(person.getFirstName().toUpperCase(Locale.ROOT))
                .lastName(person.getLastName().toUpperCase(Locale.ROOT))
                .build();
    }
}
