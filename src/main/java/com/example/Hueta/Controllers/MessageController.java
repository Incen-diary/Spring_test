package com.example.Hueta.Controllers;

import com.example.Hueta.Domain.Message;
import com.example.Hueta.Domain.User;
import com.example.Hueta.repos.MessageRepo;
import com.example.Hueta.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    private MessageRepo messageRepo ;
    private UserRepo userRepo;
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
    @PostMapping("/")
    public String add_users(@RequestParam String first_name , @RequestParam String last_name , @RequestParam Integer user_id , Map <String , Object > model){
        User user = new User(first_name , last_name , user_id);
        userRepo.save(user);
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        return "/";
    }
     @GetMapping("/main")
        public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        Iterable<User> users = userRepo.findAll();
        model.put("messages", messages);
        model.put("users", users);
        return "main";
     }
    @PostMapping("/main")
            public String add(@RequestParam String text, @RequestParam String tag , Map <String , Object> model){
             Message message = new Message(text , tag);
             messageRepo.save(message);
             Iterable<Message> messages = messageRepo.findAll();
             model.put("messages" , messages);
             return "main";
         }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map <String , Object> model){
        Iterable<Message> messages;
        if (filter!= null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages" , messages);
        return "main";
    }
}
