package com.suyu.websocket.controller;

import com.suyu.websocket.server.SocketServer;
import com.suyu.websocket.service.InitDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("api/manage")
public class TestController {

    @Autowired
    private InitDataService initDataService;

    /**
     * 推送给所有在线用户
     * @return
     */
    @GetMapping("/init")
    public String initData(){
        System.out.println("testController init...");
        initDataService.extracted();
        return "success";
    }
}
