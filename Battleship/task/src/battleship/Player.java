package battleship;

import lombok.Getter;
import lombok.Setter;

public class Player {

    @Setter @Getter private String name;
    final Game game = new Game();
    final Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
    final Ship battleship = new Ship("Battleship", 4);
    final Ship submarine = new Ship("Submarine", 3);
    final Ship cruiser = new Ship("Cruiser", 3);
    final Ship destroyer = new Ship("Destroyer", 2);
    final Ship[] allShip = {aircraftCarrier, battleship, submarine, cruiser, destroyer};

    Player(String name) {
        this.name = name;
    }

    Player() {
        this("Unknown");
    }

}
