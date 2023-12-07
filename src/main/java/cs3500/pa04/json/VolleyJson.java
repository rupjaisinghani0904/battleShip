package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * json representation of a list of coordinates
 */
public record VolleyJson(
    @JsonProperty("coordinates") List<CoordJson> volley) {
}
