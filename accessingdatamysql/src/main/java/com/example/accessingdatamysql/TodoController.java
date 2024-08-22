package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoRepository todorepository;

    @GetMapping("all")
    public @ResponseBody Iterable<Todo> getAllTodos() {
        return todorepository.findAll();
    }

    @PostMapping("add")
    public @ResponseBody String addTodos(@RequestParam String name,@RequestParam boolean isCompleted){
        Todo newTodo = new Todo();
        newTodo.setName(name);
        newTodo.setIscompleted(isCompleted);
        todorepository.save(newTodo);
        return "Saved";

    }

    @PostMapping("delete")
    public @ResponseBody String deleteTodos(@RequestParam Integer id) {
        //todorepository.delete(todo);
        todorepository.deleteById(id);
        return "deleted";
    }

    @PostMapping("update")
    public @ResponseBody String updateTodo(
            @RequestParam Integer id,
            @RequestParam String name){

        Optional<Todo> oldtodo = todorepository.findById(id);
        if(todorepository.findById(id).isPresent()){
            Todo newTodo = new Todo();
            newTodo.setId(id);
            newTodo.setName(name);
            newTodo.setIscompleted(oldtodo.get().isIscompleted());
            todorepository.save(newTodo);
        }

        return "Updated";

    }

    @PostMapping("changecompleted")
    public @ResponseBody String changecompletedTodo(
            @RequestParam Integer id,
            @RequestParam String name){

        Optional<Todo> oldtodo = todorepository.findById(id);
        if(todorepository.findById(id).isPresent()){
            Todo newTodo = new Todo();
            newTodo.setId(id);
            newTodo.setName(name);
            newTodo.setIscompleted(oldtodo.get().isIscompleted());
            todorepository.save(newTodo);
        }
        else {
            return "This id does not exist";
        }
        return "completed changed!";
    }
}
