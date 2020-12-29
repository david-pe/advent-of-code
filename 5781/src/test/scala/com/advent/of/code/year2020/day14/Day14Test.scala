package com.advent.of.code.year2020.day14

import com.advent.of.code.year2020.day14.Day14.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day14Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: String = {
      val bufferedSource = Source.fromResource("day14/input.txt")
      bufferedSource.mkString
    }
  }

  "Day Fourteen" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input =
            """mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
              |mem[8] = 11
              |mem[7] = 101
              |mem[8] = 0""".stripMargin

          solve1(input) must_=== 165
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== 10717676595607L
      }
    }


    "Part Two" should {

      "Examples" should {

        "solve first example" in {
          val input =
            """mask = 000000000000000000000000000000X1001X
              |mem[42] = 100
              |mask = 00000000000000000000000000000000X0XX
              |mem[26] = 1""".stripMargin

          solve2(input) must_=== 208
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== 3974538275659L
      }
    }
  }
}