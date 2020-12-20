package com.moneylion.test0.repository;

import com.moneylion.test0.model.Feature;
import com.moneylion.test0.model.FeatureId;
import org.springframework.data.repository.CrudRepository;

public interface FeatureRepository extends CrudRepository<Feature, FeatureId> {
}
