package com.example.demoapp.utils.model;

import java.util.HashMap;
import java.util.Map;

public class Todo{
    public int userId;
    public int id;
    public String title;
    public boolean completed;

   public Map<String ,String> toJson(){
        Map<String ,String> map = new HashMap<>();
        map.put("userId",String.valueOf(this.userId));
        map.put("id",String.valueOf(this.id));
        map.put("title",this.title);
        map.put("completed",String.valueOf(this.completed));
        return map;
    }
}