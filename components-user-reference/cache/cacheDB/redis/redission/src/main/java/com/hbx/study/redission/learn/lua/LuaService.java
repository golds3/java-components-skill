package com.hbx.study.redission.learn.lua;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RScript;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 执行lua脚本
 */
@Service
public class LuaService extends BaseService {

    public <T>T luaWrite(String script, RScript.ReturnType returnType, List<Object> keys, Object... values){
        RScript lua = redissonClient.getScript();
        return lua.eval(RScript.Mode.READ_WRITE,script,returnType,keys,values);
    }

    public <T>T luaRead(String script, RScript.ReturnType returnType, List<Object> keys, Object... values){
        RScript lua = redissonClient.getScript();
        return lua.eval(RScript.Mode.READ_ONLY,script,returnType,keys,values);
    }
}
