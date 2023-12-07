# battleShip
Modified version of battleship on the command line.

This project was developed fully in Java, and employed JSON to communicate with the server

# BACKGROUND

While growing up, you may have played the game of **[Battleship](https://en.wikipedia.org/wiki/Battleship_(game))**! Battleship is a classic two-player board game which simulates naval warfare.  The game revolves around strategy, deduction, and a bit of luck. Each player has their own private grid with various ships placed on it. The objective is to guess the location of the opponent's ships and sink them before they sink yours.

The game board consists of two grids, typically marked by letters for columns and numbers for rows. The grids are often represented by 10x10 squares, but other variations exist. Each player's grid is divided into rows and columns, allowing players to strategically place their ships on their board. The types and sizes of ships can vary, but common examples include the carrier, battleship, destroyer, submarine, and patrol boat.

Players take turns calling out coordinates on the opponent's grid, attempting to hit their ships. The opponent responds with "hit" or "miss" based on the accuracy of the guess. If a hit is recorded, the attacking player marks the location on their own tracking grid to keep track of successful hits.

As the game progresses, players use deduction and logic to narrow down the possible locations of the opponent's ships based on the hits and misses they've observed. Once all the squares of a ship have been hit, it is considered sunk. The first player to sink all of the opponent's ships wins the game.

### Board Size

BattleSalvo grids, instead of being 10x10, can have height and width dimensions of any value between 6 and 15 (inclusive)! Height and width dimensions do not need to match. For example, 6x10 and 9x6 would both be valid board dimensions for a game. The size of the board for each player, however, must be identical.

### Ship/Boat Sizes

In Battleship, each boat is positioned horizontally in one row or vertically in one column, and covers a specific number of cells on the board.

In traditional Battleship, the boat sizes are:

- Carrier: Size 5
- Battleship: Size 4
- Destroyer: Size 4
- Submarine: Size 3
- Patrol Boat: Size 2

**BattleSalvo is a bit different**:

- Carrier: Size 6
- Battleship: Size 5
- Destroyer: Size 4
- Submarine: Size 3

### Fleet Size

In Battleship, there is one of each boat type. But, in BattleSalvo, there may be several of each boat type.

The total fleet size may not exceed the smaller board dimension, but must have at least one of each boat type. Therefore, for a board of size 8x11, there should be no more than 8 total boats. Fleet size and boat types will be identical between players. 

### Number of Shots

In traditional Battleship, a player launches a missile once per turn. In BattleSalvo, for each turn, each player launches one missile per non-sunk boat remaining in their fleet.  For example, if you currently have 3 remaining ships in your fleet, you would launch 3 missiles. At the same point in time, if your opponent had 5 ships remaining, they would be able to launch 5 missiles.

### Shooting Order

In BattleSalvo, both players select their shots (target locations), and the shots are exchanged simultaneously. Information about hits is then exchanged, surviving ships are updated, and the process is repeated until one (or both) players have no more surviving ships. Importantly, this means some games will end in ties!

More specifically, the steps for the shooting stage of Salvo are laid out below:

1. Both Players shoot their “Salvo’s”
2. Both Players receive the “Incoming” salvo that their opponent fired
3. Both Players update their ships accordingly, and communicate which of the incoming shots hit
4. Repeat

# HOW TO PLAY: 
### Single Player
1. Make sure there are no command line arguments, run the main method
   
### AI Multiplayer
1. Right-click the `Server.jar` in the project root directory
2. Select `Run 'Server.jar'`
3. Wait for the message `"Accepting clients ..."` to appear
5. Run the `main()` method with host and port as command line arguments. ('0.0.0.0 35001')
