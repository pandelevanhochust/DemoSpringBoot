package com.example.DemoSpring.controller;

import com.example.DemoSpring.entity.UserModel;
import com.example.DemoSpring.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@Validated

//@CrossOrigin(origins = "") / cors for FE
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("")
    public UserModel createUsers(@RequestBody UserModel user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable int id,@RequestBody UserModel user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        try{
            userService.deleteUser(id);
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("Delete user successfully");
    }

    @GetMapping("/page")
    public Page<UserModel> getUsers(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int  size,
                                    @RequestParam(defaultValue =  "id") String sortBy,
                                    @RequestParam(defaultValue =  "desc") String order,
                                    @RequestParam(defaultValue = "") String pattern){
        return userService.getUsers(page,size,sortBy,order,pattern);
    }
//    @PostMapping("/login")
//    public void login(@RequestBody ){}

}
