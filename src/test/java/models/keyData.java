package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class keyData {
    @JsonProperty("label")
    private String label;
    @JsonProperty("value")
    private String value;
}
