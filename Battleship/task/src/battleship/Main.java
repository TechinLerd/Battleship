package battleship;

import java.util.*;
import java.io.*;

public class Main {

    final private static Scanner scanner = new Scanner(System.in);

    static void inputShip(Game game, Ship ship) {
        boolean finish = false;
        String firstCoor = "";
        String secondCoor = "";
        System.out.printf("Enter the coordinates of the %s (%d cells): %n", ship.name, ship.cells);
        while (!finish) {
            firstCoor = scanner.next();
            secondCoor = scanner.next();
            finish = game.isValidShip(firstCoor, secondCoor, ship);
            if (!finish) {
                System.out.println(game.errorText + " Try again:");
            }
        }
        game.insertShip(firstCoor, secondCoor, ship);
        ship.locateShip(firstCoor, secondCoor);
    }

    static void inputAllShip(Game game, Ship[] allShip) {
        printGameField(game.gameField);
        for (Ship ship : allShip) {
            System.out.println();
            inputShip(game, ship);
            System.out.println();
            printGameField(game.gameField);
        }
    }

    static void inputShot(Game game, Ship[] allShip) {
        boolean finish = false;
        String shotCoor = "";
        //System.out.println("Take a shot!");
        while (!finish) {
            shotCoor = scanner.next();
            finish = game.isValidTakeShot(shotCoor);
            if (!finish) {
                System.out.println(game.errorText + " Try again:");
            }
        }
        game.takeShot(shotCoor);
        for (Ship ship : allShip) {
            ship.hitShip(shotCoor);
        }
    }

    static void sinkCompletelyDestroyedShip(Ship[] allShip) {
        for (Ship ship : allShip) {
            if (ship.isShipDestroyed()) {
                ship.sinkShip();
            }
        }
    }

    static boolean isAllShipSunk(Ship[] allShip) {
        boolean allShipSank = true;
        for (int i = 0; i < allShip.length && allShipSank; i++) {
            allShipSank = allShip[i].status.equals("SUNK");
        }
        return allShipSank;
    }

    static void printGameField(String[][] gameField) {
        System.out.print("  ");
        for (int a = 1; a <= gameField.length - 2; a++) {
            System.out.print(a + " ");
        }
        System.out.println();
        for (int b = 1; b <= gameField.length - 2; b++) {
            System.out.print(Converter.convertToChar(b) + " ");
            for (int c = 1; c <= gameField[b].length - 2; c++) {
                System.out.print(gameField[b][c] + " ");
            }
            System.out.println();
        }
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.flush();
    }

    public static void main(String[] args) {

        final Player player_1 = new Player();
        final Player player_2 = new Player();

        final Ship[][] allShip = {player_1.allShip, player_2.allShip};
        final Game[] allGame = {player_1.game, player_2.game};
        final int[] playerNumber = {0, 1};

        for (int player : playerNumber) {
            System.out.printf("Player %d, place your ships on the game field%n%n", player + 1);
            inputAllShip(allGame[player], allShip[player]);
            System.out.println();
            promptEnterKey();
        }

        System.out.println();
        System.out.println("The game starts!");

        boolean finish = false;
        int player = playerNumber[0];
        int opponent = playerNumber[1];
        while (!finish) {

            System.out.println();
            printGameField(allGame[opponent].gameField_Fog);
            System.out.println("---------------------");
            printGameField(allGame[player].gameField);

            System.out.printf("%nPlayer %d, it's your turn:%n", player + 1);
            inputShot(allGame[opponent], allShip[opponent]);

            boolean shipIsDestroyed = false;
            for (Ship ship: allShip[opponent]) {
                if (ship.isShipDestroyed()) {
                        shipIsDestroyed = true;
                        System.out.println("\nYou sank a ship!");
                }
            }

            sinkCompletelyDestroyedShip(allShip[opponent]);

            if (!shipIsDestroyed) {
                System.out.println("\n" + allGame[opponent].shotText);
            }

            finish = isAllShipSunk(allShip[opponent]);

            if (finish) {
                System.out.println("\nYou sank the last ship. You won. Congratulations!");
            }

            player = player == playerNumber[0] ? playerNumber[1] : playerNumber[0];
            opponent = opponent == playerNumber[1] ? playerNumber[0] : playerNumber[1];

            promptEnterKey();

        }
    }
}
