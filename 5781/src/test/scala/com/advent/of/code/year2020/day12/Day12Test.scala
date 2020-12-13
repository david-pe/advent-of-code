package com.advent.of.code.year2020.day12

import com.advent.of.code.year2020.day12.Day12.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day12Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Array[String] = {
      val bufferedSource = Source.fromResource("day12/input.txt")
      bufferedSource.getLines.toArray
    }
  }

  "Day Twelve" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input =
            """F10
              |N3
              |F7
              |R90
              |F11""".stripMargin.split('\n')

          solve1(input) must_=== 25
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== 1221
      }
    }


    "Part Two" should {

      "Examples" should {
        "solve first example" in {
          val input =
            """F10
              |N3
              |F7
              |R90
              |F11""".stripMargin.split('\n')

          solve2(input) must_=== 286
        }

      }

      "solve it" in new Context {
        solve2(input) must_=== 59435
      }
    }
  }
}