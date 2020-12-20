package com.moneylion.test0.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public @Data class Feature {

    @EmbeddedId
    private FeatureId id;

    private boolean enabled;
}
