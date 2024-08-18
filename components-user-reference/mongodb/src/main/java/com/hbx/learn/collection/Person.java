package com.hbx.learn.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("person")
public class Person {
    @Id
    private String id;
    private String name;
    private int age;

}
