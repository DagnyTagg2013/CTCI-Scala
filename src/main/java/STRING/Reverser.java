package STRING;

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
      // NOTE:  ASSUME NO TRAILING SPACES for Sentence, or this will NOT work!
      // NOTE:  use array to simulate Tuple returns from Python!
      // NOTE:  END of substring is 1 index AFTER last letter in Java!
      // NOTE:  SCAN NEXT ONE; initialize START index for FIRST WORD
      // NOTE:  HANDLE TRAILING String!
      public int[] findStartStopIndexNextWord(String sentence, int lastWordStart, int scanIndex) {


          int [] nextWordDelimits = new int[3];
          nextWordDelimits[0] = lastWordStart;
          nextWordDelimits[1] = -1;   // start next word, or end of current word
          nextWordDelimits[2] = scanIndex;  // current scan index, or space between words each time

          // EXIT with false code if invalid scanIndex position
          if ((scanIndex < 0) || (scanIndex >= sentence.length() - 1)) {
            return nextWordDelimits;
          }

          while (   scanIndex < (sentence.length() - 1) ) {
              if (sentence.charAt(scanIndex) == ' ') {
                      nextWordDelimits[1] = scanIndex + 1;  // NOTE:  save one PAST BLANK position HERE!
                    // NOTE:  Java substring END index is one PAST last char in String; which is exactly space index here!
                      // BREAKOUT of scan once LAST delimiting index is found!
                      // CACHE word end
                      break;
              }
              // ATTN:  ITERATE on condition!
              scanIndex++;
          }

          // ALWAYS save NEXT position to examine!
          nextWordDelimits[2] = scanIndex;

          // ATTN:  handle TRAILING case, where EXIT scan early as LAST terminating SPACE at EOL NOT found; so SET END of last word to END of sentence!
          if ((nextWordDelimits[1] == -1) && (scanIndex == (sentence.length() - 1))) {
              nextWordDelimits[1] = sentence.length();
          }

          return nextWordDelimits;

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
          String reversed3= reverser.reverseOneWord(test3Data);
          System.out.println(String.format("TEST3:  Full sentence reversed is %s", reversed3));

          // TEST 4: DELIMITERS for WORDS in SENTENCE SIMULATING SPLIT!
          //         * INIT START; then get END
          //         * HANDLE TRAILING CASE SPECIALLY
          //         * HANDLE INIT CASE SPECIALLY to CHAIN LAST result as String START of NEXT result!
          System.out.println("***** TEST 4");
          String test4Data = "The Fox";
          int[] nextWordDelimits = new int[3];
          nextWordDelimits[0] = 0;  // START scan at 0
          nextWordDelimits[1] = -1; // INIT word scan
          nextWordDelimits[2] = 0;
          while (nextWordDelimits[2] < (test4Data.length() - 1)) {

              // NOTE:  CHAIN-PASS-IN LATEST scanIndex!
              if (nextWordDelimits[1] == -1) {
                  nextWordDelimits = reverser.findStartStopIndexNextWord(test4Data, nextWordDelimits[0], nextWordDelimits[2]);
              }
              else {
                  nextWordDelimits = reverser.findStartStopIndexNextWord(test4Data, (nextWordDelimits[1] + 1), nextWordDelimits[2]);
              }
              System.out.println(String.format("FOUND word at boundaries:  (%d, %d); WORD %s", nextWordDelimits[0], nextWordDelimits[1], test4Data.substring(nextWordDelimits[0], nextWordDelimits[1])));

              // ATTN:  iterate to NEXT scan point!
              nextWordDelimits[2] = nextWordDelimits[2] + 1;
          }

          // TEST 5: REVERSE words by DELIMITERS
          System.out.println("***** TEST 5");
          // ATTN:  substring requires index one PAST last char; BUT reverse word requires index exactly AT the last char!
          String reversedOneWordInSentence = reverser.reverseOneWordByIndexes(test4Data, 4, 6);
          System.out.println(reversedOneWordInSentence);

          // TEST 6:  REVERSE ALL WORDS AS PRIOR IN ABOVE WHILE LOOP; AFTER REVERSING ENTIRE SENTENCE!
          // TODO:

      }
}
