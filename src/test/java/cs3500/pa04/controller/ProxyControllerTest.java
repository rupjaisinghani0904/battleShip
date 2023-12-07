package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa04.client.controller.ProxyController;
import cs3500.pa04.client.model.GameResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.client.model.AiPlayer;
import cs3500.pa04.client.model.Board;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.JsonUtils;
import org.junit.jupiter.api.Test;
import java.util.*;
import cs3500.pa04.client.model.Player;
import cs3500.pa04.client.model.Coord;
import cs3500.pa04.client.model.CoordStatus;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.VolleyJson;
import cs3500.pa04.client.model.ShipType;

/**
 * tests for the proxycontroller
 */
public class ProxyControllerTest {
  private ByteArrayOutputStream testLog;
  private ProxyController proxyController;
  private final ObjectMapper mapper = new ObjectMapper();

  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * creates a sample message to respond to the server
   *
   * @param messageName name of method to be sent
   * @param messageObject record to be seralized into a json object
   * @return JsonNode that was serialized
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson = new MessageJson(messageName,
        JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }

  /**
   * converts test log into string
   *
   * @param classRef class to convert test stream to
   * @param <T> type of object to convert to
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);
      // No error thrown when parsing to a GuessJson, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }

  /**
   * tests for handleJoin
   */
  @Test
  public void testHandleJoin() {
    String str = "{\"name\":\"kyang04\",\"game-type\":\"SINGLE\"}";
    JsonNode arguments = null;
    try {
      arguments = mapper.readTree(str);
    } catch (IOException e) {
      fail();
    }
    MessageJson messageJson = new MessageJson("join", arguments);
    JsonNode sampleMessage = createSampleMessage("join", messageJson);
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    try {
      this.proxyController = new ProxyController(socket,
          new AiPlayer(new Random(), new Board(6, 6)));
    } catch (IOException e) {
      fail();
    }

    this.proxyController.run();
    String expected = "{\"method-name\""
        + ":\"join\",\"arguments\":{\"name\":\"kyang04\",\"game-type\":\"SINGLE\"}}\n";
    assertEquals(expected.replaceAll("\\R", "\n"),
        logToString().replaceAll("\\R", "\n"));
  }


  /**
   * tests for handleSetup
   */
  @Test
  public void testSetup() {
    String serverMessage = "{\"width\":10,\"height\":10,\"fleet-spec\""
        + ":{\"CARRIER\":2,\"BATTLESHIP\":4,\"DESTROYER\":1,\"SUBMARINE\":3}}}";
    JsonNode arguments = null;
    try {
      arguments = mapper.readTree(serverMessage);
    } catch (IOException e) {
      fail();
    }

    MessageJson messageJson = new MessageJson("setup", arguments);
    JsonNode sampleMessage = JsonUtils.serializeRecord(messageJson);
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));


    try {
      this.proxyController = new ProxyController(socket,
          new AiPlayer(new Random(), new Board(6, 6)));
    } catch (IOException e) {
      fail();
    }

    this.proxyController.run();
    responseToClass(MessageJson.class);
  }

  /**
   * tests for getShots in proxyController
   */
  @Test
  public void testGetShots() {
    Player player = new AiPlayer(new Random(3), new Board(6, 6));
    Map<ShipType, Integer> spec = new HashMap<>();
    for (ShipType s : ShipType.values()) {
      spec.put(s, 1);
    }
    player.setup(6, 6, spec);
    List<Coord> playerShots = player.takeShots();
    List<CoordJson> expectedShots = new ArrayList<>();
    for (Coord c : playerShots) {
      expectedShots.add(new CoordJson(c.getXPosn(), c.getYPosn()));
    }
    VolleyJson expectedVolley = new VolleyJson(expectedShots);
    MessageJson expectedMessageJson = new MessageJson("take-shots",
        JsonUtils.serializeRecord(expectedVolley));
    JsonNode expectedMessage = JsonUtils.serializeRecord(expectedMessageJson);

    Mocket socket = new Mocket(this.testLog, List.of(expectedMessage.toString()));

    try {
      this.proxyController = new ProxyController(socket, player);
    } catch (IOException e) {
      fail();
    }

    this.proxyController.run();
    String expected = "{\"method-name\":\"take-shots\",\"arguments\":"
        + "{\"coordinates\":[{\"x\":5,\"y\":4},{\"x\":1,\"y\":5},"
        + "{\"x\":5,\"y\":2},{\"x\":5,\"y\":0}]}}\n";
    assertEquals(expected.replaceAll("\\R", "\n"),
        logToString().replaceAll("\\R", "\n"));

  }

  /**
   * tests for handleReportDamage
   */
  @Test
  public void testHandleReportDamage() {
    Player player = new AiPlayer(new Random(3), new Board(6, 6));
    Map<ShipType, Integer> spec = new HashMap<>();
    for (ShipType s : ShipType.values()) {
      spec.put(s, 1);
    }
    player.setup(6, 6, spec);
    List<Coord> playerShots = player.takeShots();
    List<CoordJson> expectedShots = new ArrayList<>();
    for (Coord c : playerShots) {
      expectedShots.add(new CoordJson(c.getXPosn(), c.getYPosn()));
    }
    VolleyJson expectedVolley = new VolleyJson(expectedShots);
    List<CoordJson> opponentShotsJson = expectedVolley.volley();
    List<Coord> opponentShots = new ArrayList<>();
    for (CoordJson cj : opponentShotsJson) {
      opponentShots.add(new Coord(cj.x(), cj.y(), CoordStatus.Shot));
    }
    List<Coord> successfulOppHits = player.reportDamage(opponentShots);
    List<CoordJson> successfulOppHitsJson = new ArrayList<>();
    for (Coord c : successfulOppHits) {
      successfulOppHitsJson.add(new CoordJson(c.getXPosn(), c.getYPosn()));
    }
    VolleyJson responseJson = new VolleyJson(successfulOppHitsJson);
    MessageJson messageJson = new MessageJson("report-damage",
        JsonUtils.serializeRecord(responseJson));
    JsonNode expectedMessage = JsonUtils.serializeRecord(messageJson);

    Mocket socket = new Mocket(this.testLog, List.of(expectedMessage.toString()));

    try {
      this.proxyController = new ProxyController(socket, player);
    } catch (IOException e) {
      fail();
    }

    this.proxyController.run();
    String expected = "{\"method-name\":\"report-damage\",\"arguments\""
        + ":{\"coordinates\":[{\"x\":4,\"y\":0},{\"x\":3,\"y\":4},{\"x\":1,\"y\":1}]}}\n";
    assertEquals(expected.replaceAll("\\R", "\n"),
        logToString().replaceAll("\\R", "\n"));
  }

  /**
   * tests for handleSucessfulHits
   */
  @Test
  public void testHandleSuccessfulHits() {
    Player player = new AiPlayer(new Random(3), new Board(6, 6));
    Map<ShipType, Integer> spec = new HashMap<>();
    for (ShipType s : ShipType.values()) {
      spec.put(s, 1);
    }
    player.setup(6, 6, spec);
    List<Coord> playerShots = player.takeShots();
    List<CoordJson> expectedShots = new ArrayList<>();
    for (Coord c : playerShots) {
      expectedShots.add(new CoordJson(c.getXPosn(), c.getYPosn()));
    }

    VolleyJson responseJson = new VolleyJson(expectedShots);
    MessageJson messageJson = new MessageJson("successful-hits",
        JsonUtils.serializeRecord(responseJson));
    JsonNode expectedMessage = JsonUtils.serializeRecord(messageJson);

    Mocket socket = new Mocket(this.testLog, List.of(expectedMessage.toString()));

    try {
      this.proxyController = new ProxyController(socket, player);
    } catch (IOException e) {
      fail();
    }

    this.proxyController.run();
    String expected = "{\"method-name\":\"successful-hits\",\"arguments\":\"void\"}\n";
    assertEquals(expected.replaceAll("\\R",
        "\n"), logToString().replaceAll("\\R", "\n"));
  }

  /**
   * tests for handleEndGame
   */
  @Test
  public void testHandleEndGameWin() {
    String serverMessage = "{\"result\":\"WIN\","
        + "\"reason\":\"Player 1 sank all of Player 2's ships\"}";
    JsonNode arguments = null;
    try {
      arguments = mapper.readTree(serverMessage);
    } catch (IOException e) {
      fail();
    }
    Player player = new AiPlayer(new Random(3), new Board(6, 6));
    player.endGame(GameResult.WIN, "Player 1 sank all of Player 2's ships");

    MessageJson messageJson = new MessageJson("end-game", arguments);
    JsonNode sampleMessage = JsonUtils.serializeRecord(messageJson);
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    try {
      this.proxyController = new ProxyController(socket, new AiPlayer(new Random(),
          new Board(6, 6)));
    } catch (IOException e) {
      fail();
    }

    this.proxyController.run();
    String expected = "{\"method-name\":\"end-game\",\"arguments\":\"void\"}\n";
    assertEquals(expected.replaceAll("\\R", "\n"),
        logToString().replaceAll("\\R", "\n"));
  }

}
