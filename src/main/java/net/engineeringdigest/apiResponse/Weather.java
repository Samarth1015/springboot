package net.engineeringdigest.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data

    public class Weather{
        public Location location;
@Data
    public  static  class Location{
        public String name;
        public String region;
        public String country;
        public double lat;
        public double lon;
        @JsonProperty("tz_id")
        public String tzId;

    }

    }









