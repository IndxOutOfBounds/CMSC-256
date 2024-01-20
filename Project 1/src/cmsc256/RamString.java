package cmsc256;

import java.util.ArrayList;


public class RamString implements WackyStringInterface {
    private String ramString;

    public RamString() {
        setWackyString("CS@VCU!");
    }
    public RamString(String ramString) {
        setWackyString(ramString);
    }

    public void setWackyString(String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException();
        }
        ramString = string;
    }

    public String getWackyString() {
        return ramString;
    }

    public String getEveryFourthCharacter() {
        String endString = "";
        for (int i = 3; i < ramString.length(); i+=4) {
                endString += ramString.charAt(i);
        }
        return endString;
    }

    public String getEvenOrOddCharacters(String evenOrOdd) {
        String endString = "";
        if (!evenOrOdd.equals("odd") && !evenOrOdd.equals("even")) {
            throw new IllegalArgumentException("Parameter must be either odd or even");
        }
        if (evenOrOdd.equals("odd")) {
            for (int i = 0; i < ramString.length(); i++) {
                if ((i+1) % 2 != 0) {
                    endString += ramString.charAt(i);
                }
            }
        } else {
            for (int i = 0; i < ramString.length(); i++) {
                if ((i+1) % 2 == 0) {
                    endString += ramString.charAt(i);
                }
            }
        }
        return endString;
    }

    public int countDigits() {
        int numDigits = 0;
        for (int i = 0; i < ramString.length(); i++) {
            if (Character.isDigit(ramString.charAt(i))) {
                numDigits++;
            }
        }
        return numDigits;
    }

    public boolean isValidVCUEmail() {
        String end = ramString.substring((ramString.length() - 8));
        String start = ramString.substring(0,(ramString.length()-8));
        if (end.equals("@vcu.edu")) {
            for (int i = 0; i < start.length(); i++) {
                if (!Character.isDigit(start.charAt(i)) && !Character.isLetter(start.charAt(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public String standardizePhoneNumber() {
        ArrayList<String> phoneNumber = new ArrayList<>();
        for (int i = 0; i < ramString.length(); i++) {
            if (Character.isDigit(ramString.charAt(i))) {
                phoneNumber.add(String.valueOf(ramString.charAt(i)));
            }
        }
        phoneNumber.add(0, "(");
        phoneNumber.add(4, ")");
        phoneNumber.add(9, "-");
        String endingString = "";
        for (int i = 0; i < phoneNumber.size(); i++) {
            endingString += phoneNumber.get(i);
        }
        return endingString;
    }

    public void ramifyString() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < ramString.length(); i++) {
            list.add(String.valueOf(ramString.charAt(i)));
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == "0") {
                if ((i + 1 < list.size()) && list.get(i + 1) == "0") {
                    list.set(i, "CS@VCU");
                    list.remove(i+1);
                    continue;
                }
                list.set(i, "Go Rams");
            }
        }
        String endString = "";
        for (int i = 0; i < list.size(); i++) {
            endString += list.get(i);
        }
        ramString = endString;
    }

    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws IllegalArgumentException, IndexOutOfBoundsException {
        String endString = "";
        if (startPosition>endPosition) {
            throw new IllegalArgumentException();
        }
        if ((startPosition < 0 ) || (endPosition > ramString.length())) {
            throw new IndexOutOfBoundsException();
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < ramString.length(); i++) {
            list.add(String.valueOf(ramString.charAt(i)));
        }
        for (int i = startPosition-1; i < endPosition ; i++) {
            switch (list.get(i)) {
                case "1" -> list.set(i, "I");
                case "2" -> list.set(i, "II");
                case "3" -> list.set(i, "III");
                case "4" -> list.set(i, "IV");
                case "5" -> list.set(i, "V");
                case "6" -> list.set(i, "VI");
                case "7" -> list.set(i, "VII");
                case "8" -> list.set(i, "VIII");
                case "9" -> list.set(i, "IX");
            }
        }
        for (int i = 0; i < list.size(); i++) {
            endString += list.get(i);
        }
        ramString = endString;
    }
}
