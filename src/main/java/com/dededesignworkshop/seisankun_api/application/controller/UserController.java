package com.dededesignworkshop.seisankun_api.application.controller;

import com.dededesignworkshop.seisankun_api.domain.object.User;
import com.dededesignworkshop.seisankun_api.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class UserController {
    @NotNull
    final private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/v1/user/info/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public User createUserInfo(@RequestBody User user, BindingResult result) {
        this.userService.createUserInfo(user);
        return user;
    }

    @CrossOrigin
    @RequestMapping(value = "/v1/user/info/{uid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<User> findByFirebaseUid(@PathVariable("uid") String uid) {
        return this.userService.findByUid(uid);
    }

    @CrossOrigin
    @RequestMapping(value = "/v1/traveler/{travel_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> findByTravelId(@PathVariable("travel_id") Integer travel_id) {
        return this.userService.findByTravelId(travel_id);
    }
}
