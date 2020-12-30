package com.advent.of.code.year2020.day15

import com.advent.of.code.year2020.day15.Day15.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day15Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Seq[Int] = {
      val bufferedSource = Source.fromResource("day15/input.txt")
      bufferedSource.getLines.toSeq.head.split(',').map(_.toInt)
    }
  }

  "Day Fifteen" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input = """0,3,6""".stripMargin.split(',').map(_.toInt)

          solve1(input) must_=== 436
        }
        "solve 2nd example" in {
          val input = """1,3,2""".stripMargin.split(',').map(_.toInt)

          solve1(input) must_=== 1
        }
        "solve 3rd example" in {
          val input = """2,1,3""".stripMargin.split(',').map(_.toInt)

          solve1(input) must_=== 10
        }
        "solve 4th example" in {
          val input = """1,2,3""".stripMargin.split(',').map(_.toInt)

          solve1(input) must_=== 27
        }
        "solve 5th example" in {
          val input = """2,3,1""".stripMargin.split(',').map(_.toInt)

          solve1(input) must_=== 78
        }
        "solve 6th example" in {
          val input = """3,2,1""".stripMargin.split(',').map(_.toInt)

          solve1(input) must_=== 438
        }
        "solve 7th example" in {
          val input = """3,1,2""".stripMargin.split(',').map(_.toInt)

          solve1(input) must_=== 1836
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 1015
      }
    }


    "Part Two" should {

      "Examples" should {

        "solve first example" in {
          val input =
            """0,3,6""".stripMargin.split(',').map(_.toInt)

          solve2(input) must_=== 175594
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== 201
      }
    }
  }
}