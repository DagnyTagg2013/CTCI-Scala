

println("Hello Scala")
// type switched, type inference, no semicolon
var name1: String = "Ned"
// non-changeable val
val name2 = "Daphne"

// QUESTIONS:  REFERENCES VAL and VAR?
// CAN CHANGE UNDERLYING THING VS CHANGE THE REFERENCE ITSELF?

// TODO:  no primitives?
def doubler(amount: Int): Int = {
  return amount * 2
}
// TODO:  Java needs equals and hashcode
case class Node(name:String)

// implement unapply method etc

// LANGUAGE
// object-oriented and functional
// valid, expression that returns stuff
"Hello,  Scala"

// VAR, can change REFERENCE
// VAL, can change VALUE
// multiline string
"""
    Hello,

    World
"""

// html.replaceAll("""/>?""")

// QUESTION:  What about BigInteger and Dates
// square brackets for generics
List(1, 3, 20): List[Int]

// QUESTION:
// scala math library
// BigDecimal vs BigInteger
// java.time.LocalDate
// JodaDatetime


// QUESTION:
// INHERITANCE w GENERICS
// can you get operations on BASECLASS
// EQUALS operators on Object vs in Scala?

val a = List(1,2)


// SWITCHES ETC
val hex = color match {
  case "read' => "#f00""
}

val b = a :+ 3

// QUESTION:
// REFERENCE CANNOT CHANGE, BUT UNDERLYING VALUE CAN CHANGE??
// ANY type
// like for Object


def CentigradeToFarenheit(centigrade: Int): Double = {
  centigrade * 9/5 + 32
}

val x = CentigradeToFarenheit(6)

def fTOC(F: Double) = {
  F/2
}

// QUESTION:  garbage collection, efficiency, new values get create

// TODO RUN THIS CHAINED
// val y = fTOC(CentigrateToFarenheit(6))

// run Hello

// VS Single Expression vs def that can be reusable
val y = {
    val x = 25
    x * 2
}

// if (boolean) expression else expression

// INTELLIJ IDEA SELECT ALL to FORMAT
// NO ELSEIF in Scala -- nested expression

// IMPLICIT TYPES

val test = "bogus"

val x = test match {
  case "" => "invalid_empty"
  case _ =>
    "n/a"
}

// if (boolean) expression else expression

// INTELLIJ IDEA SELECT ALL to FORMAT
// NO ELSEIF in Scala -- nested expression

// IMPLICIT TYPES
}                                                                                                   www.