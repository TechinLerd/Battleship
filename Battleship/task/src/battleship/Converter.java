package battleship;

class Converter {
    final static String latinCharUpper = "ABCDEFGHIJ";

    final static String[] numbers = {"1","2","3","4","5","6","7","8","9","10"};

    static int convertToInt(char ch) {
        return latinCharUpper.indexOf(ch) + 1;
    }

    static int convertToInt(String ch) {
        return latinCharUpper.indexOf(ch) + 1;
    }

    static char convertToChar(int number) throws ArrayIndexOutOfBoundsException {
        if (number > 10 || number < 1) {
            throw new ArrayIndexOutOfBoundsException("The number of this method must be 1-10.");
        }
        return latinCharUpper.charAt(number - 1);
    }

    static char convertToChar(char number) throws ArrayIndexOutOfBoundsException {
        int number_Int = Integer.parseInt(String.valueOf(number));
        if (number_Int > 10 || number_Int < 1) {
            throw new ArrayIndexOutOfBoundsException("The number of this method must be 1-10.");
        }
        return convertToChar(number_Int);
    }

    static int returnValidNumber(String str) {
        int number_return = 0;
        for (String number : numbers) {
            number_return = str.equals(number) ? Integer.parseInt(number) : number_return;
        }
        return number_return;
    }
}
