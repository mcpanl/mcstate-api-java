package com.zonhor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

@RestController
public class Test {
    @RequestMapping("/")
    public Object welcome() {
        Map<String, Object> res = new Hashtable<>();

        res.put("version", "1.0.3");
        res.put("hostName", "");
        res.put("hostAddress", "");
        res.put("nowTime", new Date());

        InetAddress localhost = null;
        try {
            localhost = InetAddress.getLocalHost();

            res.put("hostName", localhost.getHostName());
            res.put("hostAddress", localhost.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return res;
    }
}
