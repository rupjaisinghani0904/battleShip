package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * json representation of a ship
 *
 * @param start starting coordinate of a ship
 * @param length length of ship
 * @param direction either horizontal or vertical
 */
public record ShipJson(
    @JsonProperty("coord") CoordJson start,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction) {
}
