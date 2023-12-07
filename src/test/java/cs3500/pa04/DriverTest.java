package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa04.client.Driver;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * test for driver class exception
 */
public class DriverTest {

  @Test
  public void testMainException() {
    String[] args = {};
    assertThrows(NoSuchElementException.class, () -> Driver.main(args));
  }
}
