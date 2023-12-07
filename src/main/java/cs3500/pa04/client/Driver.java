package cs3500.pa04.client;

import cs3500.pa04.client.controller.BattleSalvoController;
import cs3500.pa04.client.controller.ProxyController;
import cs3500.pa04.client.model.AiPlayer;
import cs3500.pa04.client.model.Board;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

/**
 * This is the main driver of this project.
 *
 */
public class Driver {

  /**
   * This method connects to the server at the given host and port, builds a proxy referee
   * to handle communication with the server, and sets up a client player.
   *
   * @param host the server host
   * @param port the server port
   * @throws IOException if there is a communication issue with the server
   */
  private static void runClient(String host, int port)
      throws IOException, IllegalStateException {
    Socket server = new Socket(host, port);
    ProxyController proxyDealer = new ProxyController(server, new AiPlayer(new Random(),
        new Board(6, 6)));
    proxyDealer.run();
  }

  /**
   * The main entrypoint into the code as the Client. Given a host and port as parameters, the
   * client is run. If there is an issue with the client or connecting,
   * an error message will be printed.
   *
   * @param args The expected parameters are the servers host and port
   */
  public static void main(String[] args) {
    if (args.length == 2) {
      String host = args[0];
      int port = Integer.parseInt(args[1]);
      try {
        Driver.runClient(host, port);
      } catch (IOException e) {
        System.out.println("failed to connect to server.");
      }
    } else if (args.length == 0) {
      BattleSalvoController controller = new BattleSalvoController(new PrintStream(System.out),
          new InputStreamReader(System.in), new Random(2));
      controller.run();
    } else {
      System.out.println("Entered illegal arguments.");
    }
  }
}