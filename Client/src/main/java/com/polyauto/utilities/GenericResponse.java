package com.polyauto.utilities;

import java.util.HashMap;

public class GenericResponse
{
    public String code = "200";
    public String message = "OK";
    public HashMap<String,Object> content;
    public GenericResponse()
    {
        content = new HashMap<>();
    }

    public void addToContent(String key, Object value)
    {
        content.put(key,value);
    }
}
