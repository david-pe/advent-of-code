package com.advent.of.code.year2020.day22

import com.advent.of.code.year2020.day22.Day22.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day22Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Array[String] = {
      val bufferedSource = Source.fromResource("day22/input.txt")
      bufferedSource.mkString.split("\n\n")
    }
  }

  trait ExampleContext extends Scope {
    val exampleInput: Array[String] =
      """Player 1:
        |9
        |2
        |6
        |3
        |1
        |
        |Player 2:
        |5
        |8
        |4
        |7
        |10""".stripMargin.split("\n\n")
  }

  "Day Twenty Two" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in new ExampleContext {
          solve1(exampleInput) must_=== 306
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== 33434
      }
    }


    "Part Two" should {

      "Examples" should {

        "solve first example" in new ExampleContext {
          solve2(exampleInput) must_=== 291
        }

        "solve infinite example" in {

          val input =
            """Player 1:
              |43
              |19
              |
              |Player 2:
              |2
              |29
              |14""".stripMargin.split("\n\n")

          solve2(input) must_=== 105

        }
      }

      "solve it" in new Context {
        solve2(input) must_=== 31657
      }
    }
  }
}
