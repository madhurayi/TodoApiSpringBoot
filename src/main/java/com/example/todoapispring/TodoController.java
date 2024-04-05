package com.example.todoapispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {


    private TodoService todoService; //anotherTodoService
    private TodoService todoService2; //FakeTodoService
    private static List<Todo> todoList;

    public TodoController( @Qualifier("anothertodoservice") TodoService todoService ,
                           @Qualifier("faketodoservice") TodoService todoService2    ){
        this.todoService = todoService;
        this.todoService2=todoService2;
        todoList= new ArrayList<>();
        todoList.add(new Todo(1,true,"Todo 1",1));
        todoList.add(new Todo(2,false,"Todo 2",2));
        //this.todoService= new TodoService();
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) boolean iscompleted){
        System.out.println("Incoming query params: "+ iscompleted+" "+this.todoService2.doSomething());
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){

        /**
         * We can use this annotation to set the status code @ResponseStatus(HttpStatus.CREATED)
         */
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity getTodoById(@PathVariable long todoId){

        for(Todo todo: todoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo Not Found");
    }

    @DeleteMapping("/{todoId}")
    public long deleteTodoById(@PathVariable long todoId){
        for(Todo todo: todoList){
            if(todo.getId()==todoId){
                todoList.remove(todo);
                System.out.println("deleted"+todo);
                return todoId;
            }
        }
        return 0;
    }

//    @PatchMapping("/{todoId}")
//
//    public ResponseEntity<Todo> updateEntityById(){
//
//    }

}
