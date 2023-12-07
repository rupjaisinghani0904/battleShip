package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa04.client.controller.BattleSalvoController;
import cs3500.pa04.client.controller.Controller;
import java.io.StringReader;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * test for BattleSalvoController class
 */
class BattleSalvoControllerTest {
  /**
   * test for run method
   */
  @Test
  void run() {
    Appendable sb = new StringBuilder();
    String s = "6 6\n"
        + "1 1 1 1\n"
        + "0 0 1 1 2 2 3 3 \n"
        + "2 0 2 1 2 3 2 4\n"
        + "0 2 1 2 3 2 4 2\n"
        + "1 0 3 0 4 0 5 0\n"
        + "0 4 1 4 3 4 4 4 \n"
        + "5 2 5 4 0 5 1 5\n"
        + "2 5 3 5 4 5 5 5\n"
        + "5 1 5 3 4 1 4 3\n";
    Readable input = new StringReader(s);
    Controller controller = new BattleSalvoController(sb, input, new Random(2));
    controller.run();
    String expected = "Hello! Welcome to the OOD BattleSalvo Game!\n" +
        "Please enter a valid height and width below:\n" +
        "------------------------------------------------------\n" +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
        "Remember, your fleet may not exceed size 6.\n" +
        "--------------------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S S _ \n" +
        "S S S S S _ \n" +
        "S S S S S S \n" +
        "_ S S S S _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ _ _ _ _ \n" +
        "_ M _ _ _ _ \n" +
        "_ _ H _ _ _ \n" +
        "_ _ _ M _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S S _ \n" +
        "S S S S S _ \n" +
        "S S S S S S \n" +
        "_ S S S S _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H _ _ _ \n" +
        "_ M M _ _ _ \n" +
        "_ _ H _ _ _ \n" +
        "_ _ M M _ _ \n" +
        "_ _ H _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S S _ \n" +
        "S S S S S _ \n" +
        "S S S S H H \n" +
        "_ S S S S _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H _ _ _ \n" +
        "_ M M _ _ _ \n" +
        "M H H H H _ \n" +
        "_ _ M M _ _ \n" +
        "_ _ H _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S S _ \n" +
        "S S S S H _ \n" +
        "S S S H H H \n" +
        "_ S S S H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "_ M M _ _ _ \n" +
        "M H H H H _ \n" +
        "_ _ M M _ _ \n" +
        "_ _ H _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S H _ \n" +
        "S S S H H _ \n" +
        "S S S H H H \n" +
        "_ S S S H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "_ M M _ _ _ \n" +
        "M H H H H _ \n" +
        "_ _ M M _ _ \n" +
        "H H H H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S H _ \n" +
        "S S S H H _ \n" +
        "S S H H H H \n" +
        "_ S S H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "_ M M _ _ _ \n" +
        "M H H H H H \n" +
        "_ _ M M _ _ \n" +
        "H H H H H H \n" +
        "M M _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S H _ \n" +
        "S S S H H _ \n" +
        "S S H H H H \n" +
        "_ S S H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "_ M M _ _ _ \n" +
        "M H H H H H \n" +
        "_ _ M M _ _ \n" +
        "H H H H H H \n" +
        "M M M M M H \n" +
        "Player Board:\n" +
        "_ _ S H H _ \n" +
        "S S S H H _ \n" +
        "S S H H H H \n" +
        "_ S S H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "_ M M _ M H \n" +
        "M H H H H H \n" +
        "_ _ M M M H \n" +
        "H H H H H H \n" +
        "M M M M M H \n" +
        "Player Board:\n" +
        "_ _ S H H _ \n" +
        "S S H H H _ \n" +
        "S S H H H H \n" +
        "_ S S H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "WIN";
    assertEquals(sb.toString(), expected);

    Appendable sb1 = new StringBuilder();
    String s1 = "6 6\n"
        + "0 0 0 1\n"
        + "1 1 1 1\n"
        + "0 0 0 1 0 2 0 3 0 4 0 5\n"
        + "0 0 0 1 0 2 0 3 0 4 0 5\n"
        + "0 0 0 8 0 2 0 3 0 4 0 9\n"
        + "1 0 1 1 1 2 1 3 1 4 1 5\n"
        + "2 0 2 1 2 2 2 3 2 4 2 5\n"
        + "3 0 3 1 3 2 3 3 3 4 3 5\n"
        + "4 0 4 1 4 2 4 3 4 4 4 5\n"
        + "5 0 5 1 5 2 5 3 5 4 5 5\n";
    Readable input1 = new StringReader(s1);
    Controller controller1 = new BattleSalvoController(sb1, input1, new Random(1));
    controller1.run();
    String expected1 = "Hello! Welcome to the OOD BattleSalvo Game!\n" +
        "Please enter a valid height and width below:\n" +
        "------------------------------------------------------\n" +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
        "Remember, your fleet may not exceed size 6.\n" +
        "--------------------------------------------------------------------------------\n" +
        "Uh Oh! You've entered invalid fleet sizes.\n" +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
        "Remember, your fleet may not exceed size 6.\n" +
        "--------------------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "S S S S S _ \n" +
        "S S S S S S \n" +
        "_ _ S S S S \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ _ _ _ _ \n" +
        "M _ _ _ _ _ \n" +
        "H _ _ _ _ _ \n" +
        "H _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "H S S S S _ \n" +
        "S S S S S S \n" +
        "_ _ S H S S \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Uh Oh! You've entered invalid shots.\n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Uh Oh! You've entered invalid shots.\n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Uh Oh! You've entered invalid shots.\n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Uh Oh! You've entered invalid shots.\n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ _ _ _ _ \n" +
        "M _ _ _ _ _ \n" +
        "H H _ _ _ _ \n" +
        "H H _ _ _ _ \n" +
        "_ M _ _ _ _ \n" +
        "_ M _ _ _ _ \n" +
        "Player Board:\n" +
        "H H S S S _ \n" +
        "H S S H S S \n" +
        "_ _ S H H S \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H _ _ _ \n" +
        "M _ M _ _ _ \n" +
        "H H H _ _ _ \n" +
        "H H H _ _ _ \n" +
        "_ M _ _ _ _ \n" +
        "_ M _ _ _ _ \n" +
        "Player Board:\n" +
        "H H H S S _ \n" +
        "H H S H S S \n" +
        "_ _ H H H S \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H _ _ \n" +
        "M _ M M _ _ \n" +
        "H H H _ _ _ \n" +
        "H H H _ _ _ \n" +
        "_ M M _ _ _ \n" +
        "_ M M _ _ _ \n" +
        "Player Board:\n" +
        "H H H S S _ \n" +
        "H H S H H S \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 3 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H _ _ \n" +
        "M _ M M _ _ \n" +
        "H H H H _ _ \n" +
        "H H H M _ _ \n" +
        "_ M M M _ _ \n" +
        "_ M M _ _ _ \n" +
        "Player Board:\n" +
        "H H H H S _ \n" +
        "H H H H H S \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 3 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H H _ \n" +
        "M _ M M M _ \n" +
        "H H H H _ _ \n" +
        "H H H M _ _ \n" +
        "_ M M M _ _ \n" +
        "_ M M M _ _ \n" +
        "Player Board:\n" +
        "H H H H S _ \n" +
        "H H H H H H \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 2 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H H _ \n" +
        "M _ M M M _ \n" +
        "H H H H H _ \n" +
        "H H H M M _ \n" +
        "_ M M M _ _ \n" +
        "_ M M M _ _ \n" +
        "Player Board:\n" +
        "H H H H H _ \n" +
        "H H H H H H \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 1 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H H _ \n" +
        "M _ M M M _ \n" +
        "H H H H H _ \n" +
        "H H H M M _ \n" +
        "_ M M M M _ \n" +
        "_ M M M _ _ \n" +
        "Player Board:\n" +
        "H H H H H _ \n" +
        "H H H H H H \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 1 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H H _ \n" +
        "M _ M M M _ \n" +
        "H H H H H _ \n" +
        "H H H M M _ \n" +
        "_ M M M M _ \n" +
        "_ M M M M _ \n" +
        "Player Board:\n" +
        "H H H H H _ \n" +
        "H H H H H H \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 1 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H H H \n" +
        "M _ M M M _ \n" +
        "H H H H H _ \n" +
        "H H H M M _ \n" +
        "_ M M M M _ \n" +
        "_ M M M M _ \n" +
        "Player Board:\n" +
        "H H H H H _ \n" +
        "H H H H H H \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 1 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H H H \n" +
        "M _ M M M H \n" +
        "H H H H H _ \n" +
        "H H H M M _ \n" +
        "_ M M M M _ \n" +
        "_ M M M M _ \n" +
        "Player Board:\n" +
        "H H H H H _ \n" +
        "H H H H H H \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ S S S _ _ \n" +
        "Please Enter 1 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H H H \n" +
        "M _ M M M H \n" +
        "H H H H H H \n" +
        "H H H M M _ \n" +
        "_ M M M M _ \n" +
        "_ M M M M _ \n" +
        "Player Board:\n" +
        "H H H H H _ \n" +
        "H H H H H H \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ H H S _ _ \n" +
        "Please Enter 1 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ H H H H \n" +
        "M _ M M M H \n" +
        "H H H H H H \n" +
        "H H H M M H \n" +
        "_ M M M M _ \n" +
        "_ M M M M _ \n" +
        "Player Board:\n" +
        "H H H H H _ \n" +
        "H H H H H H \n" +
        "_ _ H H H H \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ H H H _ _ \n" +
        "LOSE";
    assertEquals(sb1.toString(), expected1);

    Appendable sb2 = new StringBuilder();
    String s2 = "6 6\n"
        + "1 1 1 1\n"
        + "0 0 0 1 0 2 0 3 \n"
        + "0 4 0 5 1 0 1 1\n"
        + "1 2 1 3 1 4 1 5\n"
        + "2 0 2 1 2 2 2 3\n"
        + "2 4 3 4 4 4 5 4 \n"
        + "3 0 4 0 5 0\n"
        + "3 2 4 2 5 2\n"
        + "5 1 4 3\n"
        + "5 5\n"
        + "4 5\n"
        + "5 3";
    Readable input2 = new StringReader(s2);
    Controller controller2 = new BattleSalvoController(sb2, input2, new Random(2));
    controller2.run();
    String expected2 = "Hello! Welcome to the OOD BattleSalvo Game!\n" +
        "Please enter a valid height and width below:\n" +
        "------------------------------------------------------\n" +
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
        "Remember, your fleet may not exceed size 6.\n" +
        "--------------------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S S _ \n" +
        "S S S S S _ \n" +
        "S S S S S S \n" +
        "_ S S S S _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M _ _ _ _ _ \n" +
        "M _ _ _ _ _ \n" +
        "M _ _ _ _ _ \n" +
        "M _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S S _ \n" +
        "S S S S S _ \n" +
        "S S S S S S \n" +
        "_ S S S S _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M _ _ _ _ \n" +
        "M M _ _ _ _ \n" +
        "M _ _ _ _ _ \n" +
        "M _ _ _ _ _ \n" +
        "H _ _ _ _ _ \n" +
        "M _ _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S S _ \n" +
        "S S S S S _ \n" +
        "S S S S H H \n" +
        "_ S S S S _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M _ _ _ _ \n" +
        "M M _ _ _ _ \n" +
        "M H _ _ _ _ \n" +
        "M M _ _ _ _ \n" +
        "H H _ _ _ _ \n" +
        "M M _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S S _ \n" +
        "S S S S H _ \n" +
        "S S S H H H \n" +
        "_ S S S H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H _ _ _ \n" +
        "M M M _ _ _ \n" +
        "M H H _ _ _ \n" +
        "M M M _ _ _ \n" +
        "H H _ _ _ _ \n" +
        "M M _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S S H _ \n" +
        "S S S H H _ \n" +
        "S S H H H H \n" +
        "_ S S S H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H _ _ _ \n" +
        "M M M _ _ _ \n" +
        "M H H _ _ _ \n" +
        "M M M _ _ _ \n" +
        "H H H H H H \n" +
        "M M _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ S H H _ \n" +
        "S S H H H _ \n" +
        "S S H H H H \n" +
        "_ S S H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 4 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "M M M _ _ _ \n" +
        "M H H H _ _ \n" +
        "M M M _ _ _ \n" +
        "H H H H H H \n" +
        "M M _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ H H H _ \n" +
        "S S H H H _ \n" +
        "S H H H H H \n" +
        "_ S H H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 3 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "M M M _ _ H \n" +
        "M H H H H H \n" +
        "M M M _ _ _ \n" +
        "H H H H H H \n" +
        "M M _ _ _ _ \n" +
        "Player Board:\n" +
        "_ _ H H H _ \n" +
        "S H H H H _ \n" +
        "H H H H H H \n" +
        "_ S H H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 2 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "M M M _ _ H \n" +
        "M H H H H H \n" +
        "M M M _ M _ \n" +
        "H H H H H H \n" +
        "M M _ _ _ H \n" +
        "Player Board:\n" +
        "_ _ H H H _ \n" +
        "S H H H H _ \n" +
        "H H H H H H \n" +
        "_ H H H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 1 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "M M M _ _ H \n" +
        "M H H H H H \n" +
        "M M M _ M _ \n" +
        "H H H H H H \n" +
        "M M _ _ M H \n" +
        "Player Board:\n" +
        "_ _ H H H _ \n" +
        "S H H H H _ \n" +
        "H H H H H H \n" +
        "_ H H H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "Please Enter 1 Shots:\n" +
        "------------------------------------------------------------------\n" +
        "Opponent Board:\n" +
        "M M H H H H \n" +
        "M M M _ _ H \n" +
        "M H H H H H \n" +
        "M M M _ M H \n" +
        "H H H H H H \n" +
        "M M _ _ M H \n" +
        "Player Board:\n" +
        "_ _ H H H _ \n" +
        "H H H H H _ \n" +
        "H H H H H H \n" +
        "_ H H H H _ \n" +
        "_ _ _ _ _ _ \n" +
        "_ _ _ _ _ _ \n" +
        "DRAW";
    assertEquals(sb2.toString(), expected2);
  }
}