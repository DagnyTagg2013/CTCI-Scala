/**
  *
  * Created on 11/2/16.
  *
  * DYNAMIC PROGRAMMING:  Pathfinder exercise
  *
  * PROBLEM 1:  Find one Pathway in Grid from (0,0) to (X,Y) around BLOCKED spots
  * APPROACH:   Start at DESTINATION; then move BACKWARDS (first LEFT, then UP)
  *             Build Path as you go, and recursively!
  *
  */
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
  */

import scala.collection.immutable.Map

// list spots that are BLOCKED
object  SDyanmicPathfinder extends App {

  // ATTN:  super-easy auto-class creation!
  case class Point(x: Integer, y: Integer)

  // TODO:  make this configurable INPUT value?
  val blockedSpots: Map[Point, Boolean] = Map( Point(1, 1) -> true,
                                          Point(2, 2) -> true )

  // ATTN:  Function declaration; and Map usage!
  // http://stackoverflow.com/questions/10567744/how-to-check-whether-key-or-value-exist-in-map
  def isFreeSpot(testPoint:Point):Boolean = {

    // ATTN:  Usage of If-Else with return value, NO return statement!
    if (blockedSpots.keySet.exists(_ == testPoint))
      false
    else
      true

  }

  // ATTN:  Unit is like Java void!
  // TODO:  using Stack ListBuffer, vs instead return newly-built List returned in TUPLE?
  //        BUT APPEND on Buffer gets expensive?

  /* ALGO:
  - build partialPath on Stack function input var
  - RETURN extended path, and whether path is valid
  */

  // PROBLEM:  trying to do this Scala-ic by NOT mutating input List while building via RETURN value
  // def buildPath(x: Integer, y: Integer, partialPath: ListBuffer[Point]): Boolean = {
  def buildPath(x: Integer, y: Integer, partialPath: List[Point]): ( List[Point], Boolean) = {

    // ATTN:  auto-type inference!
    val currentPoint = new Point(x, y)
    // TODO:  is there an easy way to NOT use var here?
    var isValidPath = false

    // add current point to buildPath
    // TODO:  prepends Element to List should be FAST, otherwise use Buffer;
    //        THEREFORE, FINAL list will be in the correct order since we START from END!
    val extendedPartialPath: List[Point] = currentPoint :: partialPath

    // BASE degenerate case:  if AT origin, FOUND path
    if ( x == 0 && y == 0)
      true

    // FIRST:  Try LEFT
    // ATTN:  Initialization of Empty List!
    // val leftPartialPath: List[Point] = List[Point]()
    if (x >= 1 && isFreeSpot( Point(x - 1, y) ) ) {
      val (isValidPath, leftPartialPath)  = buildPath(x, y - 1, extendedPartialPath)
      // val leftResult: (List[Point], Boolean) = buildPath(x - 1, y, extendedPartialPath)
      // Q:  cannot resolve symbol leftResult OR isValidPathLeft!, AND cannot extend its scope OUTSIDE of if block to NEXT dependent block!
      // println leftResult._1
      // println isValidPath
    }

    // PROBLEM1:  want to reference leftPartialPath from above, but it's out of scope!
    // PROBLEM2:  mutate isValidPath!

    // SECOND:  Try UP
    //if (!isValidPath && y >= 1 && isFreeSpot( Point(x, y - 1) ) )
      //val (isValidPathUp, upPartialPath) = buildPath(x, y - 1, leftPartialPath)

    // BACKOUT BAD MOVE!  Wrong way!  Stop going this way!
    if (!isValidPath)
      (partialPath, true)
    else
      (extendedPartialPath, true)

  } // end of buildPath

  // *********** MAIN EXECUTION *****************
  // ATTN:  Scala Access Levels!
  val startPath: List[Point] = List()
  val foundPath: List[Point] = List()
  val isValidPath: Boolean = false

  val blockedPath: List[Point] = List(Point(1,1), Point(2,2))
  val gridDimension: Integer = 3
  // ATTN:  unpacking tuples via tuple declaration with interior values!
  // TODO:  need to create NEW Returned values!
  val (foundPathRtn, isValidPathRtn) = buildPath(gridDimension, gridDimension, startPath)

  // ATTN:  Printing results from List here:
  println("RESULTING PATH FOUND!")
  println("Version 1:  ")
  foundPath.foreach(println)
  println("Version 2:  ")
  println (foundPath mkString "\n")

} // end App Dynamic Path Finder

