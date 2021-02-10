package com.springproject.clientmanager.domains;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessResponse {

    private final String status = "success";
    private Map data = new HashMap();

    public SuccessResponse(Map map) {
        this.data = map;
    }

    public SuccessResponse(List list){

    }


    public String getStatus() {
        return status;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
