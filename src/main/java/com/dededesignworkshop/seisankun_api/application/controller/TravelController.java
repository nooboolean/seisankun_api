package com.dededesignworkshop.seisankun_api.application.controller;


import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.domain.object.TravelList;
import com.dededesignworkshop.seisankun_api.domain.object.UserTravel;
import com.dededesignworkshop.seisankun_api.domain.object.UserTravelByHashId;
import com.dededesignworkshop.seisankun_api.domain.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TravelController {
    @NotNull
    private final TravelService travelService;

    @RequestMapping(value = "/v1/travel/list/{user_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TravelList> findByUserId(@PathVariable("user_id") Integer user_id){
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
    public Travel createTravel(@RequestBody @Validated Travel travel, BindingResult result){
        this.travelService.createTravel(travel);
        return travel;
    }

    @RequestMapping(value = "/v1/travel/info/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Optional<Travel> updateTravel(@RequestBody @Validated Travel travel, BindingResult result){
        Optional<Travel> updatedTravel = this.travelService.updateTravel(travel);
        return updatedTravel;
    }

    @RequestMapping(value = "v1/travel/delete/{archive_flag}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer deleteTravel(@RequestBody Travel travel, @PathVariable("archive_flag") Integer archiveFlag){
        if(archiveFlag == 1){
            this.travelService.softDeleteTravel(travel);
            return travel.getId();
        }
        this.travelService.deleteTravel(travel.getId());
        return travel.getId();
    }

    @RequestMapping(value = "/v1/travel/join", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void joinTravel(@RequestBody UserTravel userTravel, BindingResult result){
        this.travelService.joinTravel(userTravel);
    }

    @RequestMapping(value = "/v1/travel/secession", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserTravel secessionTravel(@RequestBody UserTravel userTravel, BindingResult result){
        this.travelService.deleteUserTravel(userTravel);
        return userTravel;
    }

    @RequestMapping(value = "/v1/travel/id/{hash_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Integer getTravelId(@PathVariable("hash_id") String hash_id){
        Integer travel_id = this.travelService.getTravelId(hash_id);
        return travel_id;
    }

    @RequestMapping(value = "/v1/travel/{hash_id}/exist/member/{user_uid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserTravel existTravel(@PathVariable("hash_id") String hashId, @PathVariable("user_uid") String uid){
        return this.travelService.existTravel(hashId, uid);
    }

    @RequestMapping(value = "/v1/travel/join/hashId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void joinTravelByHashId(@RequestBody UserTravelByHashId userTravelByHashId, BindingResult result){
        this.travelService.joinTravelByHashId(userTravelByHashId);
    }
}
