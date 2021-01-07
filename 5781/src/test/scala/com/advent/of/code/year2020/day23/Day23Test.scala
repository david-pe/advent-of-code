package com.advent.of.code.year2020.day23

import com.advent.of.code.year2020.day23.Day23.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day23Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: String = {
      val bufferedSource = Source.fromResource("day23/input.txt")
      bufferedSource.mkString.split("\n").head
    }
  }

  trait ExampleContext extends Scope {
    val exampleInput: String = "389125467"
  }

  "Day Twenty Three" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in new ExampleContext {
          solve1(exampleInput, 10) must_=== "92658374"
        }

        "solve second example" in new ExampleContext {
          solve1(exampleInput) must_=== "67384529"
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== "28793654"
      }
    }

    "Part Two" should {

      "Examples" should {

        "solve first example" in new ExampleContext {
          solve2(exampleInput) must_=== 149245887792L
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== 359206768694L
      }
    }
  }
}
