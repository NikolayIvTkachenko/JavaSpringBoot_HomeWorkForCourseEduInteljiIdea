package com.rsh.tkachenko.microservice.AppToDo.utilities;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

//@AllArgsConstructor
public class JsonResponseBody {

    //@Getter
    //@Setter
    private  int server;
    //@Getter @Setter
    private Object response;

    public JsonResponseBody(int server, Object response) {
        this.server = server;
        this.response = response;
    }

    public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
