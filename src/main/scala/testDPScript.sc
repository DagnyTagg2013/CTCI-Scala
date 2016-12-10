
import SDP.{SPoint, SDynamicPathfinder}
import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer
// ATTN:  precompile dependencies before accessing via script!
// - http://stackoverflow.com/questions/12028590/scala-not-a-member-of-package
// ATTN: println
// - http://stackoverflow.com/questions/7219316/println-vs-system-out-println-in-scala
// ******* TEST 1:  BLOCKED PATH ***********
// ATTN:  initializing a LIST
// - http://alvinalexander.com/scala/how-create-scala-list-range-fill-tabulate-constructors
// ATTN:  initializing a MAP
// -  http://alvinalexander.com/scala/scala-maps-map-class-examples

// INITIALIZE Blocked Points
System.out.println("TEST 1")
val blockedSpots1: Map[SPoint, Boolean] = Map( SPoint(1, 1) -> true,
                                              SPoint(2, 2) -> true )

val aDynamicPathfinder1: SDynamicPathfinder = new SDynamicPathfinder(3, blockedSpots1)
val partialPath1: ListBuffer[SPoint] = new ListBuffer[SPoint]()
var isFoundPath1: Boolean = aDynamicPathfinder1.getPath(3, 3, partialPath1)
if (isFoundPath1) {
  System.out.println("FOUND Path is:  ")
  // ATTN: printing elements of list!
  System.out.println(partialPath1.foreach(println))
}
else {
  System.out.print("Path NOT Found!")
}
/*
// ****** TEST 2: VALID PATH ***************
// INITIALIZE Blocked Points
System.out.println("\nTEST 2")
val blockedSpots2: Map[Point, Boolean] = Map( Point(1, 1) -> true,
  Point(2, 2) -> true )

val aDynamicPathfinder2: SDynamicPathfinder = new SDynamicPathfinder(3, blockedSpots2)
val partialPath2: ListBuffer[Point] = new ListBuffer[Point]()
var isFoundPath2: Boolean = aDynamicPathfinder2.getPath(3, 3, partialPath2)
if (isFoundPath2) {
  System.out.println("FOUND Path is:  ")
  System.out.println(partialPath2)
}
else {
  System.out.print("Path NOT Found!")
}
*/