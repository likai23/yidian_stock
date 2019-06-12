package com.ydsh.stock;

import com.alibaba.fastjson.JSON;
import com.ydsh.stock.common.bean.Result;
import com.ydsh.stock.common.db.DBKeyGenerator;
import com.ydsh.stock.common.enums.DBBusinessKeyTypeEnums;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void dbkeyTest() {

        String key = DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.C, null);
        System.out.println(key);
    }

    public static void main(String[] args) {
        Result<String> result=new Result<String>(0,"success","");
        System.out.println(JSON.toJSONString(result));
    }
}
