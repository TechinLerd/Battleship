package battleship;

import lombok.Setter;

class Game {

    @Setter String playerName;
    final private static int extentSize = 2;
    final private static int fieldSize = 10;
    final private static int size = extentSize + fieldSize;
    String[][] gameField = new String[size][size];
    String[][] gameField_Fog = new String[size][size];
    String errorText;
    String shotText;

    Game() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.gameField[i][j] = "~";
                this.gameField_Fog[i][j] = "~";
            }
        }
    }

    boolean isValidShip(String firstCoor, String secondCoor, Ship ship) {
        Coordinates first_Coor = new Coordinates(firstCoor);
        Coordinates second_Coor = new Coordinates(secondCoor);
        if (first_Coor.y < 1 || first_Coor.x < 1 || first_Coor.y > fieldSize
                || second_Coor.y < 1 || second_Coor.y > fieldSize) {
            this.errorText = "Error! You entered the wrong coordinates!";
            return false;
        }
        if ( ( first_Coor.y - second_Coor.y == 0
                && Math.abs(first_Coor.x - second_Coor.x)  != ship.cells - 1 )
                || ( Math.abs(first_Coor.y - second_Coor.y) != ship.cells - 1
                && first_Coor.x - second_Coor.x == 0 ) ) {
            this.errorText = "Error! Wrong length of the " + ship.name + "!";
            return false;
        } else if (first_Coor.y - second_Coor.y != 0 && first_Coor.x - second_Coor.x != 0) {
            this.errorText = "Error! Wrong ship location!";
            return false;
        }

        boolean same_near = false;
        if (first_Coor.y < second_Coor.y || first_Coor.x < second_Coor.x) {
            for (int i = first_Coor.y - 1;
                 i <= second_Coor.y + 1 && i >= 0 && !same_near;
                 i++) {
                for (int j = first_Coor.x - 1;
                     j <= second_Coor.x + 1 && j < size && j >= 0 && !same_near;
                     j++) {
                    same_near = this.gameField[i][j].equals("O");
                }
            }
        } else if (first_Coor.y > second_Coor.y || first_Coor.x > second_Coor.x) {
            for (int i = first_Coor.y + 1;
                 i >= second_Coor.y - 1 && i < size && !same_near;
                 i--) {
                for (int j = first_Coor.x + 1;
                     j >= second_Coor.x - 1 && j < size && j >= 0 && !same_near;
                     j--) {
                    same_near = this.gameField[i][j].equals("O");
                }
            }
        }

        if (same_near) {
            this.errorText = "Error! You placed it too close to another one.";
            return false;
        }
        return true;
    }

    void insertShip(String firstCoor, String secondCoor, Ship ship) {
        Coordinates first_Coor = new Coordinates(firstCoor);
        Coordinates second_Coor = new Coordinates(secondCoor);
        if (this.isValidShip(firstCoor, secondCoor, ship)) {
            if (first_Coor.y < second_Coor.y || first_Coor.x < second_Coor.x) {
                for (int i = first_Coor.y; i <= second_Coor.y; i++) {
                    for (int j = first_Coor.x; j <= second_Coor.x; j++) {
                        this.gameField[i][j] = "O";
                    }
                }
            } else if (first_Coor.y > second_Coor.y || first_Coor.x > second_Coor.x) {
                for (int i = first_Coor.y; i >= second_Coor.y; i--) {
                    for (int j = first_Coor.x; j >= second_Coor.x; j--) {
                        this.gameField[i][j] = "O";
                    }
                }
            }
        }
    }

    boolean isValidTakeShot(String coordinates) {
        Coordinates coor = new Coordinates(coordinates);
        if (coor.y < 1 || coor.y > fieldSize || coor.x < 1 || coor.x > fieldSize) {
            this.errorText = "Error! You entered the wrong coordinates!";
            return false;
        }
        return true;
    }

    void takeShot(String coordinates) {
        Coordinates coor = new Coordinates(coordinates);
        if (this.isValidTakeShot(coordinates)) {
            if (this.gameField[coor.y][coor.x].equals("O")) {
                this.gameField[coor.y][coor.x] = "X";
                this.gameField_Fog[coor.y][coor.x] = "X";
                this.shotText = "You hit a ship!";
            } else if (this.gameField[coor.y][coor.x].equals("~")) {
                this.gameField[coor.y][coor.x] = "M";
                this.gameField_Fog[coor.y][coor.x] = "M";
                this.shotText = "You missed!";
            }
        }
    }

}
