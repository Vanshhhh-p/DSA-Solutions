import java.util.*;

class Solution {
    public String removeDuplicateLetters(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        // Store last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        HashSet<Character> set = new HashSet<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (set.contains(ch)) {
                continue;
            }

            while (ans.length() > 0 &&
                   ans.charAt(ans.length() - 1) > ch &&
                   map.get(ans.charAt(ans.length() - 1)) > i) {

                char removed = ans.charAt(ans.length() - 1);

                ans.deleteCharAt(ans.length() - 1);
                set.remove(removed);
            }

            ans.append(ch);
            set.add(ch);
        }

        return ans.toString();
    }
}