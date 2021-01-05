package com.advent.of.code.year2020.day19

import com.advent.of.code.year2020.day19.Day19.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day19Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Array[String] = {
      val bufferedSource = Source.fromResource("day19/input.txt")
      bufferedSource.mkString.split("\n\n")
    }
  }

  "Day Nineteen" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input = """0: 4 1 5
                        |1: 2 3 | 3 2
                        |2: 4 4 | 5 5
                        |3: 4 5 | 5 4
                        |4: "a"
                        |5: "b"
                        |
                        |ababbb
                        |bababa
                        |abbbab
                        |aaabbb
                        |aaaabbb""".stripMargin.split("\n\n")

          solve1(input) must_=== 2
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== 122
      }
    }


    "Part Two" should {
      "solve it" in new Context {
        solve2(input) must_=== 287
      }
    }
  }
}
