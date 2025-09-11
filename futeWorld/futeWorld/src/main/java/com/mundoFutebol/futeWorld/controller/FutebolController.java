package com.mundoFutebol.futeWorld.controller;

import com.mundoFutebol.futeWorld.service.FutebolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/futeWorld")
public class FutebolController {
    @Autowired
    private FutebolService futebolService;

    public FutebolController(FutebolService futebolService) {
        this.futebolService = futebolService;
    }

}
