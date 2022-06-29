package com.suyu.websocket.service;

import static com.suyu.websocket.util.Test.testReadFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InitDataService {
    private static BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>(1000);
    //静态代码块会在依赖注入后自动执行,并优先执行
    static{
        System.out.println("---InitService static--");
//        extracted();
        System.out.println("---InitService static finish--");
    }

    public void extracted(){
        try{
            String readFile = testReadFile();
            JSONObject json1 = JSONObject.parseObject(readFile);
            String string = json1.getString("145083");
            String trackData = JSONObject.parseObject(string).getString("trackData");
            JSONArray jsonArray = JSONObject.parseArray(trackData);
            String string1 = jsonArray.getString(0);
            JSONArray jsonArray1 = JSONObject.parseArray(string1);
            int size = jsonArray1.size();
            System.out.println("size==" + size);
            for (int i = 0; i < jsonArray1.size(); i++) {
                myQueue.put(jsonArray1.getString(i));
            }
            System.out.println("myQueue.size====" + myQueue.size());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static String testReadFile() throws IOException {
        String fileName = "/opt/data/201.txt";
        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(fileName));
        List<String> list = lines.collect(Collectors.toList());
        StringBuffer stringBuffer = new StringBuffer();
        for (String ele : list) {
            stringBuffer.append(ele);
        }
//        System.out.println("stringbuffer=====" + stringBuffer);
        return stringBuffer.toString();
    }


    public static BlockingQueue<Object> getMyQueue() {
        return myQueue;
    }

    /**
     *  @Postcontruct’在依赖注入完成后自动调用
     */
    @PostConstruct
    public void initData(){
        System.out.println("InitService initData() @Postcontruct’在依赖注入完成后自动调用");
        extracted();
        System.out.println("InitService initData() finish @Postcontruct’在依赖注入完成后自动调用");
    }

}
