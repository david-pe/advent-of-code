package com.advent.of.code.year2020.day01

import com.advent.of.code.year2020.day01.Day01.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day01Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Seq[Int] = {
      val bufferedSource = Source.fromResource("day01/input.txt")
      bufferedSource.getLines.toSeq.map(_.trim).filterNot(_ == "").map(_.toInt)
    }
  }

  "Day One" should {

    "Part One" should {
      "Examples" should {

        "solve first" in {
          val input =
            """
              |1721
              |979
              |366
              |299
              |675
              |1456""".stripMargin.split('\n').map(_.trim).filterNot(_ == "").map(_.toInt)

          solve1(input) must_=== 514579
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 1016131
      }
    }

    "Part Two" should {
      "Examples" should {
        "solve first" in {
          val input =
            """
              |1721
              |979
              |366
              |299
              |675
              |1456""".stripMargin.split('\n').map(_.trim).filterNot(_ == "").map(_.toInt)

          solve2(input) must_=== 241861950
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== 276432018
      }
    }
  }
}