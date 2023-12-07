package cs3500.pa04.client.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.client.model.Coord;
import cs3500.pa04.client.model.GameResult;
import cs3500.pa04.client.model.Ship;
import cs3500.pa04.client.model.ShipType;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.ShipJson;
import cs3500.pa04.json.VolleyJson;
import cs3500.pa04.client.model.CoordStatus;
import cs3500.pa04.client.model.Player;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyController implements Controller {
  private final Socket server;
  private final InputStream input;
  private final PrintStream output;
  private final Player player;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  public ProxyController(Socket server, Player player) throws IOException {
    this.server = server;
    this.player = player;
    this.input = server.getInputStream();
    this.output = new PrintStream(server.getOutputStream());
  }

  /**
   * Listens for messages from the server as JSON in the format of a MessageJSON. When a complete
   * message is sent by the server, the message is parsed and then delegated to the corresponding
   * helper method for each message. This method stops when the connection to the server is closed
   * or an IOException is thrown from parsing malformed JSON.
   */
  @Override
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.input);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }
  }

  /**
   * determines which helper handler to pass given message to
   *
   * @param message message from server to be handled
   */
  private void delegateMessage(MessageJson message) {
    String name = message.messageName();
    JsonNode args = message.arguments();

    if (name.equals("join")) {
      handleJoin();
    } else if (name.equals("setup")) {
      handleSetup(args);
    } else if (name.equals("take-shots")) {
      getShots();
    } else if (name.equals("report-damage")) {
      handleReportDamage(args);
    } else if (name.equals("successful-hits")) {
      handleSuccessfulHits(args);
    } else if (name.equals("end-game")) {
      handleEndGame(args);
    } else {
      throw new IllegalArgumentException("Failed to parse server communication");
    }
  }

  /**
   * handles and responds to join message from server
   */
  private void handleJoin() {
    String str = "{\"name\":\"kyang04\",\"game-type\":\"SINGLE\"}";
    JsonNode arguments;
    try {
      arguments = mapper.readTree(str);
    } catch (IOException e) {
      //System.out.println("Unable to join server");
      throw new IllegalArgumentException("failed to join server");
    }

    MessageJson messageJson = new MessageJson("join", arguments);
    this.output.println(JsonUtils.serializeRecord(messageJson));
  }

  /**
   * handles responding to server request of setup
   *
   * @param args arguments of server request
   */
  private void handleSetup(JsonNode args) {
    int height = args.get("height").asInt();
    int width = args.get("width").asInt();
    Map<ShipType, Integer> spec = new HashMap<>();
    for (ShipType s : ShipType.values()) {
      spec.put(s, args.get("fleet-spec").get(s.toString().toUpperCase()).asInt());
    }
    List<Ship> playerShips = player.setup(height, width, spec);
    List<ShipJson> shipJsons = new ArrayList<>();
    for (Ship s : playerShips) {
      CoordJson coord = new CoordJson(s.getStart().getXPosn(), s.getStart().getYPosn());
      shipJsons.add(new ShipJson(coord, s.getCoordinates().size(), s.getShipDirection()));
    }
    JsonNode fleet = JsonUtils.serializeRecord(new FleetJson(shipJsons));
    MessageJson json = new MessageJson("setup", fleet);
    this.output.println(JsonUtils.serializeRecord(json));
  }

  /**
   * handles responding to take-shots request from server
   */
  private void getShots() {
    List<Coord> playerShots = player.takeShots();
    List<CoordJson> shots = new ArrayList<>();
    for (Coord c : playerShots) {
      shots.add(new CoordJson(c.getXPosn(), c.getYPosn()));
    }
    VolleyJson volley = new VolleyJson(shots);
    MessageJson messageJson = new MessageJson("take-shots", JsonUtils.serializeRecord(volley));
    this.output.println(JsonUtils.serializeRecord(messageJson));
  }

  /**
   * handles reportDamage request from server
   *
   * @param args arguments given by server
   */
  private void handleReportDamage(JsonNode args) {
    VolleyJson opponents = mapper.convertValue(args, VolleyJson.class);
    List<CoordJson> opponentShotsJson = opponents.volley();
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
    MessageJson messageJson = new MessageJson("report-damage", JsonUtils.serializeRecord(responseJson));
    this.output.println(JsonUtils.serializeRecord(messageJson));
  }

  private void handleSuccessfulHits(JsonNode args) {
    VolleyJson volley = mapper.convertValue(args, VolleyJson.class);
    List<CoordJson> successfulHitsJson = volley.volley();
    List<Coord> successfulHits = new ArrayList<>();
    for (CoordJson cj : successfulHitsJson) {
      successfulHits.add(new Coord(cj.x(), cj.y(), CoordStatus.Ship));
    }
    player.successfulHits(successfulHits);
    MessageJson response = new MessageJson("successful-hits", VOID_RESPONSE);
    this.output.println(JsonUtils.serializeRecord(response));
  }

  /**
   * handles endGame request from server
   *
   * @param args arguments given by server
   */
  private void handleEndGame(JsonNode args) {
    player.endGame(GameResult.valueOf(args.get("result").asText()), args.get("reason").asText());
    MessageJson response = new MessageJson("end-game", VOID_RESPONSE);
    this.output.println(JsonUtils.serializeRecord(response));
  }
}
