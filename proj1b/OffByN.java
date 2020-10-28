public class OffByN implements CharacterComparator {
    private final int num;

    public OffByN(int n) {
        num = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int x1 = x;
        int y1 = y;
        return Math.abs(x1 - y1) == num;
    }
}

