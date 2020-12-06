package com.advent.of.code.year2020.day06

import com.advent.of.code.year2020.day06.Day06.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day06Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: String = {
      val bufferedSource = Source.fromResource("day06/input.txt")
      bufferedSource.mkString
    }
  }

  "Day Six" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input = """abc
                        |
                        |a
                        |b
                        |c
                        |
                        |ab
                        |ac
                        |
                        |a
                        |a
                        |a
                        |a
                        |
                        |b""".stripMargin

          solve1(input) must_=== 11
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 6335
      }
    }
  }

  "Part Two" should {

    "solve first example" in {
      val input = """abc
                    |
                    |a
                    |b
                    |c
                    |
                    |ab
                    |ac
                    |
                    |a
                    |a
                    |a
                    |a
                    |
                    |b""".stripMargin

      solve2(input) must_=== 6
    }

    "solve it" in new Context {
      solve2(input) must_=== 3392
    }
  }
}

