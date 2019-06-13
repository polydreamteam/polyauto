package com.polyauto.dto;

import java.io.Serializable;

/**
 * Objet permettant la communication avec le serveur JMS
 */
public class ObjectMessageSend implements Serializable {

    public static final String UPDATE = "update";
    public static final String INSERT = "insert";

    private String action;
    private Object object;

    public ObjectMessageSend(String action, Object object) {
        this.action = action;
        this.object = object;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
