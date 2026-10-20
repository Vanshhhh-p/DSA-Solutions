class Solution {
    static void solve (String digits, int index, String[] mapping, List<String> result, StringBuilder output) {
        // base case -> jaise hi index input string k bahar nikal jayega 
        // iska mtlb output string ready h to push insede result wali strong
        if (index >= digits.length()) {
            result.add(output.toString());
            return;
        }

        // 1 case mujhe solve krna h iska mtlb ek block me fill krdeta hu
        // and baaki recursion sambhal lega
        int value = digits.charAt(index) - '0';
        String mappedString = mapping[value];
        // example value = 5 , mappedString = "jkl"
        // current value and uski mapped string mere pass ready h 
        // current block pr use rkhdete h and baaki recursion ko dedennge 
        for (int i = 0; i < mappedString.length(); i++) {
            output.append(mappedString.charAt(i));
            solve(digits , index +1, mapping, result, output);
            // backtracking or undo step
            output.deleteCharAt(output.length() -1);
        }
    }
    public List<String> letterCombinations(String digits) {
        String[] mapping = {"" , "" , "abc", "def" , "ghi" , "jkl" , "mno" , "pqrs" , "tuv" , "wxyz"};

        List<String> result = new ArrayList<>();
        int index = 0;
        StringBuilder output = new StringBuilder();
        solve(digits, index, mapping, result ,output);
        return result;
    }
}