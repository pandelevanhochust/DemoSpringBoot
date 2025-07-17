package com.example.DemoSpring.service;
import com.example.DemoSpring.entity.UserModel;
import com.example.DemoSpring.repository.UserRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.example.DemoSpring.entity.UserPrinciple;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //Ignore the constructor for UserService
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4); // Higher strength slower request

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username + "not found");
        }
        return new UserPrinciple(user);
    }

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
        userModel.setPassword(encoder.encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }

    public UserModel updateUser(Integer id, UserModel userModel) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel userFound = optionalUser.get();
            userFound.setFullname(userModel.getFullname());
            userFound.setUsername(userModel.getUsername());
            userFound.setPassword(encoder.encode(userModel.getPassword()));
            userFound.setMessage(userModel.getMessage());
            userFound.setBirthday(userModel.getBirthday());
            userFound.setUpdatedAt(LocalDateTime.now());
            userFound.setEmail(userModel.getEmail());
            return userRepository.save(userFound);
        } else {
            return null;
        }
    }


    public void deleteUser(Integer id){
            userRepository.deleteById(id);
    }

    //Pagination & Sorting
    public Page<UserModel> getUsers(int page,int size, String sortBy, String order,String pattern){
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page,size,sort);

        String new_pattern = '%' + pattern + '%';
        return userRepository.findByFullnameContaining(pattern,pageable);
//        return userRepository.findByFullnameIsLike(new_pattern,pageable);
    }

    //Debugging
    public void catcall(){
        System.out.println("hello how low");
    }
}
