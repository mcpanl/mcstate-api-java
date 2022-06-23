package com.zonhor.controller;

import com.google.gson.Gson;
import com.zonhor.utils.CacheService;
import com.zonhor.utils.CoreMC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/mc")
@RestController
public class Mc {

    @Autowired
    CacheService cacheService;

    @RequestMapping("/getServerInfo")
    public Object getServerInfo(String ip, Integer port, HttpServletResponse response) {
        response.addHeader("Content-Disposition", "inline;fileName=McStateApi");

        String cacheKey = "api-server_" + ip + ":" + port;

        String cache = cacheService.getObject(cacheKey, String.class);

        String serverInfo;

        if (cache == null) {
            serverInfo = CoreMC.getServerInfo(ip, port);
            cacheService.add(cacheKey, serverInfo, 5, TimeUnit.SECONDS);
        } else {
            serverInfo = cache;
        }


        Gson gson = new Gson();

        Map map = gson.fromJson(serverInfo, Map.class);

        return map;
    }
}
