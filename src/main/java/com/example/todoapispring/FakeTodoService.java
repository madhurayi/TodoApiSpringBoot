package com.example.todoapispring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("faketodoservice")
public class FakeTodoService implements TodoService{
    @TimeMonitor // the time monitor annotation will executes before below method executes because we me metiond before as advice
    public String doSomething(){ // here doSomething is joint Point
        for(int i=0;i<=1000000;i++){

        }
        return "Something";
    }
}
