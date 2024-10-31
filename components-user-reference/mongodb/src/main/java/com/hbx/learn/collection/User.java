package com.hbx.learn.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Document("User")
public class User implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    @Id
    private Long id;
    private String userName;
    private String passWord;
    //getter、setter 省略
}