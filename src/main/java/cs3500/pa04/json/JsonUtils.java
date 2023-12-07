package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * utils class to help serialize Records into JsonNodes
 */

public class JsonUtils {
  /**
   * converts Record into JsonNode
   *
   * @param record to be converted
   * @return the converted record to JsonNode
   * @throws IllegalArgumentException if unable to convert
   */
  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }
}