package com.sim.cacheredis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserProfile(
    @JsonProperty String name,
    @JsonProperty int age
) {
}
