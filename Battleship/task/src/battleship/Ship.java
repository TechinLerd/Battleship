package battleship;

class Ship {
    String name;
    int cells;
    String[] location;
    String status;

    Ship(String name, int cells) {
        this.name = name;
        this.cells = cells;
        this.location = new String[cells];
        this.status = "SWIM";
    }

    Ship() {
        this("Unknown", 1);
    }

    void locateShip(String firstCoor, String secondCoor) {
        Coordinates first_Coor = new Coordinates(firstCoor);
        Coordinates second_Coor = new Coordinates(secondCoor);
        if (first_Coor.y < second_Coor.y || first_Coor.x < second_Coor.x) {
            int index = 0;
            for (int i = first_Coor.y; i <= second_Coor.y; i++) {
                for (int j = first_Coor.x; j <= second_Coor.x; j++) {
                    this.location[index] = Converter.convertToChar(i) + "%d".formatted(j);
                    index += 1;
                }
            }
        } else if (first_Coor.y > second_Coor.y || first_Coor.x > second_Coor.x) {
            int index = this.cells - 1;
            for (int i = first_Coor.y; i >= second_Coor.y; i--) {
                for (int j = first_Coor.x; j >= second_Coor.x; j--) {
                    this.location[index] = Converter.convertToChar(i) + "%d".formatted(j);
                    index -= 1;
                }
            }
        }
    }

    void hitShip(String coordinates) {
        for (int i = 0; i < this.location.length; i++) {
            String shipLoc = this.location[i];
            this.location[i] = coordinates.equals(this.location[i]) ? "X" : shipLoc;
        }
    }

    boolean isShipDestroyed() {
        boolean destroy = true;
        for (int i = 0; i < this.location.length && destroy; i++) {
            destroy = this.location[i].equals("X");
        }
        return destroy;
    }

    void sinkShip() {
        if (this.isShipDestroyed()) {
            for (int i = 0; i < this.location.length; i++) {
                this.location[i] = "SUNK";
                this.status = "SUNK";
            }
        }
    }

}
