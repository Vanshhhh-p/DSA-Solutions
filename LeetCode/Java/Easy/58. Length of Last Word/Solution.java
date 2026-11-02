class Solution {
    public int lengthOfLastWord(String s) {
        String[] arr = s.split(" ");
        char[] ch = arr[arr.length-1].toCharArray();
        return ch.length;
    }
}