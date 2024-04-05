package com.example.todoapispring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("anothertodoservice")
@Primary
public class AnotherTodoService implements TodoService{
    @Override
    public String doSomething(){
        return "Something from Another Todo Service";
    }
}
