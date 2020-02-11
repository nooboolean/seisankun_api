package com.dededesignworkshop.seisankun_api.application.controller;


import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.domain.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class TravelController {
    @NotNull
    private final TravelService travelService;

    @RequestMapping(value = "/v1/travel/list/{user_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Stream<Travel> findByUserId(@PathVariable("user_id") Integer user_id){
        return this.travelService.findByUserId(user_id);
    }

    @RequestMapping(value = "/v1/travel/info/{travel_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Travel> findByTravelId(@PathVariable("travel_id") Integer travel_id){
        return this.travelService.findByTravelId(travel_id);
    }

    @RequestMapping(value = "/v1/travel/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Travel createTravel(@RequestBody Travel travel, BindingResult result){
        this.travelService.createTravel(travel);
        return travel;
    }

    @RequestMapping(value = "v1/travel/info/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Travel> updateTravel(@RequestBody Travel travel, BindingResult result){
        Optional<Travel> updatedTravel = this.travelService.updateTravel(travel);
        return updatedTravel;
    }
}
