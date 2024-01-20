public class test {
    public static void main(String[] args) {
            System.out.println(containsDigits("60"));
        }
    public static boolean containsDigits(String text) {
        char[] textChaArray = text.toCharArray();
        for (char c: textChaArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
