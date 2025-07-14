package com.example.DemoSpring.service;
import com.example.DemoSpring.entity.UserModel;
import com.example.DemoSpring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Ignore the constructor for UserService
@Slf4j

public class UserService {
    private final UserRepository userRepository;

//    public UserService(UserRepository userRepo, UserRepository userRepo1) {
//        this.userRepo = userRepo1;
//    }

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public UserModel getUserById(Integer id){
        Optional<UserModel> userFound = userRepository.findById(id);
        if(userFound.isPresent()){
            System.out.printf("User found with id %d%n",id);
            return userFound.get();
        }
        return null;
    }

    public UserModel createUser(UserModel userModel){
        userModel.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(userModel);
    }

    public UserModel updateUser(Integer id, UserModel userModel) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel userFound = optionalUser.get();
            userFound.setFullname(userModel.getFullname());
            userFound.setUsername(userModel.getUsername());
            userFound.setPassword(userModel.getPassword());
            userFound.setMessage(userModel.getMessage());
            userFound.setBirthday(userModel.getBirthday());
            userFound.setUpdatedAt(userModel.getUpdatedAt());
            return userRepository.save(userFound);
        } else {
            return null;
        }
    }


    public  void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

}
