package com.huifer.source.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(maxAge = 3600)
@RequestMapping("/")
@RestController
public class JSONController {
    @ResponseBody
    @GetMapping(value = "/json")
    public Object ob() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "a");
        return hashMap;
    }
}
