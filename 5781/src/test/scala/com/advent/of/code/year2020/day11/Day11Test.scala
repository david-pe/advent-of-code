package com.advent.of.code.year2020.day11

import com.advent.of.code.year2020.day11.Day11.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day11Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Seq[String] = {
      val bufferedSource = Source.fromResource("day11/input.txt")
      bufferedSource.getLines.toSeq
    }
  }

  "Day Eleven" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input =
            """L.LL.LL.LL
              |LLLLLLL.LL
              |L.L.L..L..
              |LLLL.LL.LL
              |L.LL.LL.LL
              |L.LLLLL.LL
              |..L.L.....
              |LLLLLLLLLL
              |L.LLLLLL.L
              |L.LLLLL.LL""".stripMargin.split('\n').toSeq

          solve1(input) must_=== 37
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== 3000
      }
    }


    "Part Two" should {

      "Examples" should {
        "solve first example" in {
          val input =
            """L.LL.LL.LL
              |LLLLLLL.LL
              |L.L.L..L..
              |LLLL.LL.LL
              |L.LL.LL.LL
              |L.LLLLL.LL
              |..L.L.....
              |LLLLLLLLLL
              |L.LLLLLL.L
              |L.LLLLL.LL""".stripMargin.split('\n').toSeq


          solve2(input) must_=== 26
        }

      }

      "solve it" in new Context {
        solve2(input) must_=== 1995
      }
    }
  }
}