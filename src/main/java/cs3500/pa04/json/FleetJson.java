package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Json representation of a fleet
 *
 * @param fleet list of ship jsons
 */
public record FleetJson(
    @JsonProperty("fleet") List<ShipJson> fleet) {
}
