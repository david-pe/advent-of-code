package com.advent.of.code.year2020.day08

import com.advent.of.code.year2020.day08.Day08.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day08Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Iterator[String] = {
      val bufferedSource = Source.fromResource("day08/input.txt")
      bufferedSource.getLines
    }
  }

  "Day Eight" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input =
            """nop +0
              |acc +1
              |jmp +4
              |acc +3
              |jmp -3
              |acc -99
              |acc +1
              |jmp -4
              |acc +6""".stripMargin.split('\n').toIterator

          solve1(input) must_=== 5
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 1816
      }
    }
  }

  "Part Two" should {

    "solve first example" in {
      val input =
        """nop +0
          |acc +1
          |jmp +4
          |acc +3
          |jmp -3
          |acc -99
          |acc +1
          |jmp -4
          |acc +6""".stripMargin.split('\n').toIterator

      solve2(input) must_=== 8
    }


    "solve it" in new Context {
      solve2(input) must_=== 1149
    }
  }
}

