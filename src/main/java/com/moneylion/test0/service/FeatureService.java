package com.moneylion.test0.service;

import com.moneylion.test0.model.Feature;
import com.moneylion.test0.model.FeatureId;
import com.moneylion.test0.repository.FeatureRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class FeatureService {

    @Autowired
    FeatureRepository repo;

    public boolean getFeature(String email, String featureName) {
        log.info("getting feature...");
        FeatureId id = new FeatureId(email,featureName);
        log.info("searching feature: "+id);
        Optional<Feature> featureOpt = repo.findById(id);
        if(featureOpt.isPresent()) {
            log.info("feature found: "+featureOpt.get());
            return featureOpt.get().isEnabled();
        }
        log.info("feature not found");
        return false;
    }

    public boolean setFeature(String email, String featureName, Boolean enable) {
        log.info("setting feature...");
        FeatureId id = new FeatureId(email,featureName);
        Optional<Feature> featureOpt = repo.findById(id);
        Feature feature;
        if(featureOpt.isPresent()) {
            log.info("feature found: "+featureOpt.get());
            feature = featureOpt.get();
            if(enable == feature.isEnabled()) return false;
            feature.setEnabled(enable);
        }
        else {
            log.info("feature not found");
            feature = new Feature(id, enable);
            log.info("new feature created: "+feature);
        }
        repo.save(feature);
        return true;
    }
}
