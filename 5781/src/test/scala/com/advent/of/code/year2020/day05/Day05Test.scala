package com.advent.of.code.year2020.day05

import com.advent.of.code.year2020.day05.Day05.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day05Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Iterator[String] = {
      val bufferedSource = Source.fromResource("day05/input.txt")
      bufferedSource.getLines
    }
  }

  "Day Five" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input1 = Seq("BFFFBBFRRR").toIterator // : row 70, column 7, seat ID 567.
          val input2 = Seq("FFFBBBFRRR").toIterator // : row 14, column 7, seat ID 119.
          val input3 = Seq("BBFFBBFRLL").toIterator // : row 102, column 4, seat ID 820.


          solve1(input1) must_=== 567
          solve1(input2) must_=== 119
          solve1(input3) must_=== 820
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 838
      }
    }
  }

  "Part Two" should {

    "solve it" in new Context {
      solve2(input) must_=== 714
    }
  }
}

