package com.unipi.msc.raiseupapi.Controller;

import com.unipi.msc.raiseupapi.Interface.IUser;
import com.unipi.msc.raiseupapi.Request.EditUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final IUser iUser;
    @GetMapping
    public ResponseEntity<?> getUser(){
        return iUser.getUser();
    }
    @GetMapping("image/{userId}")
    public ResponseEntity<?> getUserImage(@PathVariable Long userId){
        return iUser.getUserImage(userId);
    }
    @PatchMapping
    public ResponseEntity<?> editUser(@ModelAttribute EditUserRequest request){
        return iUser.editUser(request);
    }
}
