public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int x1 = x;
        int y1 = y;
        return Math.abs(x1 - y1) == 1;
    }
}
