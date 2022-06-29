package com.suyu.websocket.service;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

//@Component
public class InitService {
    //静态代码块会在依赖注入后自动执行,并优先执行
    static{
        System.out.println("---InitService static--");
    }
    /**
     *  @Postcontruct’在依赖注入完成后自动调用
     */
    @PostConstruct
    public static void haha(){
        System.out.println("InitService haha() @Postcontruct’在依赖注入完成后自动调用");
    }

}
