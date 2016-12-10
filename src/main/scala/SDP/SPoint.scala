package SDP

// TODO:  Point is used in SDynamicPathfinder, but then need to pull it out as EXTERNAL class for ctor initialization!

// TODO:  verify default ACCESS of Case class and Pathfinder class is within package or WHAT?

// ATTN:  benefits of CASE class, with DEFAULT val-types!
// - super-easy auto-class creation!
//   auto-generation of equals, hash, ctor
//   permits pattern-matching on components!
// ATTN:
// - uses REVERSE type definition from Java as type on RHS; and colon separator!
case class SPoint(x: Integer, y: Integer)

