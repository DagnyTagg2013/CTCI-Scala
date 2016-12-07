package STRING;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 11/16/16.
 *
 * PROBLEM:  REVERSE WORDS in A SENTENCE
 *
 * APPROACH:
 * - SYMMETRIC-SWAP
 * - MULTIPLE-PASSES!
 * - Reverse One Word
 * - Reverse Entire Sentence
 * - Find
 *
 */
public class Reverser {

      public String reverseOneWord(String word) {
          // ATTN:  Need to initialize!
          StringBuilder reversed = new StringBuilder(word);
          int fwdIdx = 0;
          int backIdx = word.length() - 1;
          char tmp = '0';
          // ATTENTION!  For EVEN-Number of Characters, CROSS-POINT is NOT AT EQUALITY!, so use INEQUALITY!
          while (fwdIdx <= backIdx) {
              tmp = word.charAt(fwdIdx);
              reversed.setCharAt(fwdIdx, word.charAt(backIdx));
              reversed.setCharAt(backIdx, tmp);
              fwdIdx++;
              backIdx--;
          }
          return reversed.toString();
      }

      public String reverseOneWordByIndexes(String sentence, int startIdx, int  endIdx) {

            // ATTN:  Need to initialize!
            StringBuilder reversed = new StringBuilder(sentence);
            int fwdIdx = startIdx;
            int backIdx = endIdx;
            char tmp = '0';
            // ATTENTION!  For EVEN-Number of Characters, CROSS-POINT is NOT AT EQUALITY!, so use INEQUALITY!
            while (fwdIdx <= backIdx) {
                tmp = sentence.charAt(fwdIdx);
                reversed.setCharAt(fwdIdx, sentence.charAt(backIdx));
                reversed.setCharAt(backIdx, tmp);
                fwdIdx++;
                backIdx--;
            }
            return reversed.toString();
      }

      // NOTE:  simplify with String split!
      // NOTE:  ASSUME NO TRAILING SPACES for Sentence!
      // NOTE:  can use array to simulate Tuple function arg returns from Python!
      // NOTE:  END index is index of last letter of word
      // NOTE:  SCAN NEXT START index of NEXT word after ' ' found!
      public int findEndIndexCurrentWord(String sentence, int nextWordStart) {

          int scanIndex = nextWordStart;  // START position of first letter of next word, moving towards END position of CURRENT word
          int endWordIndex = -1;          // initialize END index to -1

          // EXIT AT BOL
          if (scanIndex < 0) {
              throw new IllegalArgumentException("nextWordStart should be > 0");
          }

          // EXIT AT EOL
          // ATTN > or = length boundary check!
          if (scanIndex > sentence.length()) {
              throw new IllegalArgumentException("nextWordStart should be <= length - 1 of sentence");
          }

          // ATTN:  returns -1 on EOL DETECTION!
          while (   scanIndex <= (sentence.length() - 1) ) {
              // STOPs when BLANK found, detecting TRAIILNG blank!
              if (sentence.charAt(scanIndex) == ' ') {
                      endWordIndex = scanIndex - 1;
                      break;
              }
              scanIndex++;
          }

          // ATTN:  HANDLE END CASE with NO TERMINATING BLANK!
          // NOTE:  OTHERWISE, checking for just -1 will FAIL to pickup last index of line, and RESTART on endIndex -1!
          if (scanIndex == sentence.length()) {
              endWordIndex = sentence.length() - 1;
          }

          // THUS ALWAYS finds end of current word if there is one!
          return endWordIndex;

      }

    public String reverseWordsInSentence(String input) {

        int scanIndex = 0, endIndexCurrentWord = 0;

        // ATTN INITIALIZING first aggregate result; then CHAIN later!
        String reversedString = input;

        // ATTN:  handle case of advancing past EOL in OUTER loop update!
        while (scanIndex < reversedString.length()) {

            endIndexCurrentWord = findEndIndexCurrentWord(reversedString, scanIndex);

            // NOTE:  CHAINING PRIOR OUTPUT TO CURRENT INPUT TO ITERATE RESULTS - IN-PLACE
            reversedString = reverseOneWordByIndexes(reversedString, scanIndex, endIndexCurrentWord);
            System.out.println(reversedString);

            // ATTN:  ADVANCE to NEXT location, but next WHILE checks for bounds to terminate THIS outer WHILE loop,
            scanIndex = endIndexCurrentWord + 2;

        }

        return reversedString;

      }

      public static void main(String args[]) {

          Reverser reverser = new Reverser();

          // TEST 1:  ODD char numbers
          String test1Data = "Quick";
          String reversed1 = reverser.reverseOneWord(test1Data);
          System.out.println(String.format("TEST1:  ODD-chars Reversed Word is %s", reversed1));

          // TEST 2:  EVEN char numbers
          String test2Data = "Even";
          String reversed2 = reverser.reverseOneWord(test2Data);
          System.out.println(String.format("TEST2:  EVEN-chars Reversed Word is %s", reversed2));

          // TEST 3: ENTIRE SENTENCE
          String test3Data = "The Quick Brown Fox Jumped Over the Fence";
          String reversed3 = reverser.reverseOneWord(test3Data);
          System.out.println(String.format("TEST3:  Full sentence reversed is %s", reversed3));

          // TEST 4: REVERSE word by DELIMITERS
          System.out.println("***** TEST 4");
          String test4Data = "The Fox";
          // ATTN:  reverse word requires index exactly AT the first and last char!
          String reversedOneWordInSentence = reverser.reverseOneWordByIndexes(test4Data, 4, 6);
          System.out.println(reversedOneWordInSentence);


          // TEST 5: FIND DELIMITERS for WORDS in SENTENCE SIMULATING SPLIT!
          //         * INIT START; then get END
          //         * FUNCTION HANDLES START AND TRAILING BLANK SPECIALLY
          //         * CHAIN LAST result as String START for NEXT word!
          System.out.println("***** TEST 5");
          String test5Data = "The Quick Brown Fox Jumped Over the Fence";
          // ATTN:  track with lookback index to beginning of word,
          //        then CHAIN to input of START index for NEXT iteration
          int scanIndex = 0, endIndexCurrentWord = 0;
          // ATTN INITIALIZING first aggregate result!
          String reversedString = test5Data;

          // ATTN:  handle case of advancing past EOL in OUTER loop update!
          while (scanIndex < reversedString.length()) {

              endIndexCurrentWord = reverser.findEndIndexCurrentWord(reversedString, scanIndex);

              // NOTE:  CHAINING PRIOR OUTPUT TO CURRENT INPUT TO ITERATE RESULTS
              reversedString = reverser.reverseOneWordByIndexes(reversedString, scanIndex, endIndexCurrentWord);
              System.out.println(reversedString);

              // ATTN:  ADVANCE to NEXT location, but next WHILE checks for bounds to terminate THIS outer WHILE loop,
              scanIndex = endIndexCurrentWord + 2;

          }

          // TEST 6:  REVERSE ALL WORDS AS PRIOR IN ABOVE WHILE LOOP; SUCCESS IN REVERSING WORDS IN SENTENCE!
          System.out.println("***** TEST 6");
          String test6Data = "The Quick Brown Fox Jumped Over the Fence";
          String reversePass1 =  reverser.reverseOneWord(test6Data);
          System.out.println("Reverse Pass 1");
          System.out.println(reversePass1);
          System.out.println("Reverse Pass 2");
          String reversePass2 = reverser.reverseWordsInSentence(reversePass1);
          System.out.println("FINAL RESULT");
          System.out.println(reversePass2);


      }
}
