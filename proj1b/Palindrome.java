public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> arrDeq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            arrDeq.addLast(word.charAt(i));
        }
        return arrDeq;
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);

        Boolean res = true;
        while (d.size() > 1) {
            if (d.removeFirst() != d.removeLast()) {
                res = false;
            }
        }
        return res;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);

        Boolean res = true;
        while (d.size() > 1) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                res = false;
            }
        }
        return res;
    }
}
