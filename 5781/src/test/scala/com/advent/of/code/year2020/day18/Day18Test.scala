package com.advent.of.code.year2020.day18

import com.advent.of.code.year2020.day18.Day18.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day18Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Seq[String] = {
      val bufferedSource = Source.fromResource("day18/input.txt")
      bufferedSource.getLines.toSeq
    }
  }

  "Day Eighteen" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input = Array("2 * 3 + (4 * 5)")
          solve1(input) must_=== 26
        }

        "solve last example" in {
          val input = Array("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")
          solve1(input) must_=== 13632
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== "4297397455886".toLong
      }
    }

    "Part Two" should {

      "Examples" should {

        "solve first example" in {
          val input = Array("1 + (2 * 3) + (4 * (5 + 6))")
          solve2(input) must_=== 51
        }

        "solve second example" in {
          val input = Array("2 * 3 + (4 * 5)")
          solve2(input) must_=== 46
        }

        "solve third example" in {
          val input = Array("5 + (8 * 3 + 9 + 3 * 4 * 3)")
          solve2(input) must_=== 1445
        }

        "solve forth example" in {
          val input = Array("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")
          solve2(input) must_=== 669060
        }


        "solve last example" in {
          val input = Array("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")
          solve2(input) must_=== 23340
        }
      }

      "solve it" in new Context {
        solve2(input.toArray) must_=== "93000656194428".toLong
      }
    }
  }
}