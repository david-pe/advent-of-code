package com.advent.of.code.year2020.day17

import com.advent.of.code.year2020.day17.Day17.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day17Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Seq[String] = {
      val bufferedSource = Source.fromResource("day17/input.txt")
      bufferedSource.getLines.toSeq
    }
  }

  trait ExampleContext extends Scope {
    val exampleInput: Seq[String] =
    """.#.
      |..#
      |###""".stripMargin.split('\n').toSeq
  }

  "Day Seventeen" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in new ExampleContext {
          solve1(exampleInput) must_=== 112
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 313
      }
    }


    "Part Two" should {

      "Examples" should {

        "solve first example" in new ExampleContext {
          solve2(exampleInput) must_=== 848
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== 2640
      }
    }
  }
}