package com.moneylion.test0.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.moneylion.test0.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Email;
import java.util.Collections;
import java.util.Map;

@RestController
@Validated
public class FeatureController {

    @Autowired
    FeatureService service;

    @RequestMapping(value="/feature", method=GET, produces = "application/json")
    public Map<String, Boolean> getFeature(@Email @RequestParam("email") String email,
                                           @RequestParam("featureName") String featureName) {
        boolean featureValue = service.getFeature(email,featureName);
        return Collections.singletonMap("canAccess", featureValue);
    }

    @RequestMapping(value="/feature", method=POST)
    public ResponseEntity<?> setFeature(@RequestParam String featureName, @RequestParam @Email String email, @RequestParam Boolean enable) {
        boolean upsertSuccess = service.setFeature(email,featureName,enable);
        return upsertSuccess? ResponseEntity.ok().build() : ResponseEntity.status(304).build();
    }


}