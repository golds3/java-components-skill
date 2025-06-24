package com.hbx.study.mybatis3.controller;

import com.hbx.study.mybatis3.mapper.AIMapper;
import com.hbx.study.mybatis3.pojo.SignInf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AIController {
    private AIMapper aiMapper;
    public AIController(AIMapper aiMapper){
        this.aiMapper = aiMapper;
    }

    @GetMapping("mb")
    public List<SignInf> mb() {
        log.info("mb...");
        return aiMapper.getSignInf();
    }
}
