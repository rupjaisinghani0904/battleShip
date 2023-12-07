package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * json representation of a coord object
 *
 * @param x x position of a coord
 * @param y position of a coord
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y) {
}
