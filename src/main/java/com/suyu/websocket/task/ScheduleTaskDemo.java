package com.suyu.websocket.task;

import com.suyu.websocket.server.SocketServer;
import com.suyu.websocket.service.InitDataService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskDemo {

    @Autowired
    private SocketServer socketServer;

    private int num1 = 0;
    private int num2 = 0;
    private int num3 = 0;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "*/1 * * * * ?")
    public void test3(){
        System.out.println("这是test3方法第"+ (++num3) + "次执行，执行时间："+df.format(new Date()));
        try{
            Object obj = InitDataService.getMyQueue().take();
            System.out.println("obj====" + obj);
            System.out.println("Consumed resource - Queue size now = "  + InitDataService.getMyQueue().size());
//            SocketServer.sendAll((String)obj);
            SocketServer.sendMessage((String)obj,"gps1001");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @Scheduled(cron = "*/5 * * * * ?")
    public void test1(){
        System.out.println("这是test1方法第"+ (++num1) + "次执行，执行时间："+df.format(new Date()));
    }

    @Scheduled(cron = "*/20 * * * * ?")
    public void test2(){
        System.out.println("这是test2方法第"+ (++num2) + "次执行，执行时间："+df.format(new Date()));
    }

}
