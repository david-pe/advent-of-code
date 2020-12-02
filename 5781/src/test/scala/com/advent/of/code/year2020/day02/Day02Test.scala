package com.advent.of.code.year2020.day02

import com.advent.of.code.year2020.day02.Day02.{parseInput, solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day02Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Iterator[String] = {
      val bufferedSource = Source.fromResource("day02/input.txt")
      bufferedSource.getLines
    }
  }

  "Day Two" should {

    "Part One" should {
      "Examples" should {

        "solve first" in {
          val input =
            """
              |1-3 a: abcde
              |1-3 b: cdefg
              |2-9 c: ccccccccc""".stripMargin.split('\n').map(_.trim).filterNot(_ == "").toIterator;

          solve1(parseInput(input)) must_=== 2
        }
      }

      "solve it" in new Context {
        solve1(parseInput(input)) must_=== 580
      }
    }

    "Part Two" should {
      "Examples" should {
        "solve first" in {
          val input =
            """
              |1-3 a: abcde
              |1-3 b: cdefg
              |2-9 c: ccccccccc""".stripMargin.split('\n').map(_.trim).filterNot(_ == "").toIterator;

          solve2(parseInput(input)) must_=== 1
        }

        "solve it" in new Context {
          solve2(parseInput(input)) must_=== 611
        }
      }
    }
  }
}
