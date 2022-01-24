package com.project.campProject.api.controllers;

import com.project.campProject.entities.User;
import com.project.campProject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    /*    private UserRepository userRepository;
       @Autowired
        public UserController(UserRepository userRepository){
            super();
            this.userRepository = userRepository;
        } UserRepository übermitteln UserService und UserController verbinden wir UserService.
    */

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // return userRepository.findAll();
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        //  return userRepository.save(newUser);
        try {
            userService.saveOneUser(newUser);
            return new ResponseEntity<User>(
                    newUser,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<User>(
                    newUser,
                    HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {

        //  return userRepository.findById(userId).orElse(null);
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
     /*   Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;

        }else
            return null;  */

        return userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        // userRepository.deleteById(userId);
        userService.deleteById(userId);
    }



}
