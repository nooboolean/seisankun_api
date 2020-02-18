package com.dededesignworkshop.seisankun_api.application.controller;

import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class UserController {
    @NotNull
    final private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/v1/user/info/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User createUserInfo(@RequestBody User user, BindingResult result){
        this.userService.createUserInfo(user);
        return user;
    }
}
