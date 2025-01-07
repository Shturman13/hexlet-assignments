package exercise;


// BEGIN
public class ReversedSequence implements CharSequence {
    private static String initialString;

    ReversedSequence(String initialString) {
        this.initialString = initialString;
    }

    public static String getInitialString() {
        return initialString;
    }

    @Override
    public int length() {
        return getInitialString().length();
    }

    @Override
    public char charAt(int index) {
        return getInitialString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return getInitialString().subSequence(start, end);
    }

    public static String reversedSequence(String initString) {
        return new StringBuilder(initString).reverse().toString();
    }



}
// END
