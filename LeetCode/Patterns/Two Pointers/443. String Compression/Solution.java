class Solution {
    public int compress(char[] chars) {
        int readIndex = 0;
        int writeIndex = 0;

        while (readIndex < chars.length) {
            char currChar = chars[readIndex];
            int count = 0;

            // counts duplicate character 
            while (readIndex < chars.length && currChar == chars[readIndex]) {
                readIndex++;
                count++;
            }

            // ab mere pass current character and uska count dono available hai
            chars[writeIndex] = currChar;
            writeIndex++;
            // now insert the count
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char digit : countStr.toCharArray()) {
                    chars[writeIndex] = digit;
                    writeIndex++;
                }
            }
        }
        // return the length of compressed String
        return writeIndex;
    }
}