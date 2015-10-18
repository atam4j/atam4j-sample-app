package me.atam.atam4jsampleapp.configuration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TestConfiguration {

    private String googleURL;

    public String getGoogleURL() {
        return googleURL;
    }


}
