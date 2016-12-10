package SDP

import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.List

// *  ATTN:  using Scala as a Script
// - http://alvinalexander.com/scala/how-to-write-scala-shell-scripts-scripting-language-examples

// * ATTN:  OReilly Scala Cookbook for DEFAULT PRIMARY CTOR vs AUXILIARY CTOR for class
// - https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s02.html
// - https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s04.html
//   QUESTION:  separation of App trait default scripting vs Default Ctor code;
//              vs public static main() method within singleton Object instantiation
//   - need to instantiate first object of class, using some DUMMY params before being able to invoke from MAIN tes harness?
//   SOLUTION:  going with CLASS default CTOR separation from main();
//   then instantiation from separate *.sc script for most reusability!
//   ATTN:  var DEFAULT CTOR args by default create access functions and equals/hashcode compares!

// * QUESTION:  IMMUTABLE vs MUTABLE accumulation of built path
//          Option1:  CHAINING output IMMUTABLE List RESULTs to INPUT of NEXT methods to simulate MUTABLE ListBuffer input params,
//          BUT then need to return TUPLES of data!  and ORDER is assuming PREPENDING, so walk path BACK from END, FASTER creation
//          vs
//          Option2: using MUTABLE ListBuffer, but then REMOVE from end is SLOW if found BAD path!

class  SDynamicPathfinder(var gridDimension: Int, var blockedSpots: Map[SPoint, Boolean]) {

  println("the default constructor begins")

  // ATTN:  Function declaration; and Map usage!
  // http://stackoverflow.com/questions/10567744/how-to-check-whether-key-or-value-exist-in-map
  def isFree(x: Int, y: Int):Boolean = {

    // TODO:  get rid of extra object creation which makes later membership test easier!
    val testPoint: SPoint = new SPoint(x,y)

    // ATTN:  Usage of If-Else with return value, NO return statement!
    // ATTN:  Test for key in set!
    if (blockedSpots.keySet.exists(_ == testPoint))
      false
    else
      true

  }

  /* ALGO:
  - build partialPath on Stack function input var
  - RETURNs extended path, and whether path is valid
  */

  // TODO:  verify VAL to COPY of input REFERENCES for input arglist
  //        and IMMUTABLE vs MUTABLE input params!
  def getPath(x: Int, y: Int, partialPath: ListBuffer[SPoint]): Boolean = {

    System.out.println("INPUT PARTIAL PATH is:  %s", partialPath)

    var success: Boolean = false

    if (!isFree(x, y)) {
      return false
    }

    if (x == 0 && y == 0) {
      return true
    }

    // TODO:  get rid of extra object creation
    val testPoint: SPoint = new SPoint(x, y)

    if ((x >= 1) && isFree(x - 1, y)) {
      success = getPath(x - 1, y, partialPath)
    }

    if (!success && (y >= 1) && isFree(x, y - 1)) {
      success = getPath(x, y - 1, partialPath)
    }

    if (!success) {
      // TODO:  this can get expensive for a large grid
      partialPath.remove(partialPath.length - 1)
    }

    return success
  } // end of getPath

  // ATTN:  Scala main signature without Unit like Java void, and isolates main code from default ctor code!
  def main(args: Array[String]) {

    println("Hello World!")

  }

  println("the default constructor ends WHERE?")

} // end App Dynamic Path Finder

/*
  *
  * - http://stackoverflow.com/questions/9535821/scala-mutable-var-method-parameter-reference
  * - http://stackoverflow.com/questions/10710954/how-do-i-remove-trailing-elements-in-a-scala-collection
  * - import scala.collection.mutable.ListBuffer
  * - http://alvinalexander.com/scala/how-to-delete-elements-from-list-listbuffer-scala-cookbook
  * - http://stackoverflow.com/questions/6557169/how-to-declare-empty-list-and-then-add-string-in-scala
  * - https://www.tutorialspoint.com/scala/scala_access_modifiers.htm
  * - http://alvinalexander.com/scala/scala-null-values-option-uninitialized-variables
  * - http://backtobazics.com/scala/scala-collections-map-and-tuple-with-code-examples/
  *
  * * TUPLES!  vs Shapeless!
  *  import shapeless.syntax.std.tuple._
  *  unpacking Tuple components; and Tuple assignment in Scala!
  * - https://www.dotnetperls.com/tuple-scala
  * - http://stackoverflow.com/questions/6196678/is-it-possible-to-have-tuple-assignment-to-variables-in-scala
  * - http://stackoverflow.com/questions/2743866/scala-return-type-for-tuple-functions
  * - http://stackoverflow.com/questions/6884298/why-is-scalas-syntax-for-tuples-so-unusual
  *
  *
*/
