package battleship;
import static battleship.Converter.convertToInt;
import static battleship.Converter.returnValidNumber;

class Coordinates {
    int y;
    int x;

    Coordinates() {
        this.y = 0;
        this.x = 0;
    }

    Coordinates(int y, int x) {
        this.y = y;
        this.x = x;
    }

    Coordinates(String y, String x) {
        this(convertToInt(y), returnValidNumber(x));
    }

    Coordinates(String coordinates) {
        String[] coordinatesArr = coordinates.split("", 2);
        this.y = convertToInt(coordinatesArr[0]);
        this.x = returnValidNumber(coordinatesArr[1]);
    }
}
