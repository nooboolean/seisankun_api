package com.dededesignworkshop.seisankun_api.application.controller;


import com.dededesignworkshop.seisankun_api.domain.object.Travel;
import com.dededesignworkshop.seisankun_api.domain.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/get_travel_info")
public class TravelController {
    @NotNull
    private final TravelService travelService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Travel findById(@PathVariable("id") Integer id) {
        return this.travelService.findById(id).orElseThrow(RuntimeException::new);
    }
}
