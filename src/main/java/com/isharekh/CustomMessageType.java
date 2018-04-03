package com.isharekh;


import java.util.List;

/**
 * Created by : Ron Rith
 * Create Date: 01/12/2018.
 */

public class CustomMessageType {

    private String message;
    private Integer code;
    private List<? extends Object> objects;
    private Object object;


    public CustomMessageType(String message, Integer code, List<? extends Object> genericsTypes){
        this.message = message;
        this.code = code;
        this.objects = genericsTypes;
    }

    public CustomMessageType(String message, Integer code, Object object){
        this.message = message;
        this.code = code;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<? extends Object> getObjects() {
        return objects;
    }

    public void setObjects(List<? extends Object> objects) {
        this.objects = objects;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
