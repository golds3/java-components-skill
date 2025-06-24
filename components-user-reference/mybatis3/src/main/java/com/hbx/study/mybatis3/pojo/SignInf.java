package com.hbx.study.mybatis3.pojo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SignInf implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String acctNo;
    private String regType;
    private String emailFlag;
    private String billRegType;
}
