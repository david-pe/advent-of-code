package com.advent.of.code.year2020.day13

import com.advent.of.code.year2020.day13.Day13.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day13Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Array[String] = {
      val bufferedSource = Source.fromResource("day13/input.txt")
      bufferedSource.getLines.toArray
    }
  }

  trait ExampleContext extends Scope {
    val exampleInput: Array[String] =
      """939
        |7,13,x,x,59,x,31,19""".stripMargin.split('\n')

    val exampleInput2: Array[String] =
      """939
        |17,x,13,19""".stripMargin.split('\n')
  }

  "Day Thirteen" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in new ExampleContext {
          solve1(exampleInput) must_=== 295
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== 2545
      }
    }


    "Part Two" should {

      "Examples" should {

        "solve first example" in new ExampleContext {
          solve2(exampleInput2, 1) must_=== 3417
        }

        "solve second example" in new ExampleContext {
          solve2(exampleInput, 1) must_=== 1068781
        }

      }

      "solve it" in new Context {
        solve2(input) must_=== 266204454441577L
      }
    }
  }
}