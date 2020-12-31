package com.advent.of.code.year2020.day16

import com.advent.of.code.year2020.day16.Day16.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day16Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: String = {
      val bufferedSource = Source.fromResource("day16/input.txt")
      bufferedSource.mkString
    }
  }

  "Day Sixteen" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input = """class: 1-3 or 5-7
                        |row: 6-11 or 33-44
                        |seat: 13-40 or 45-50
                        |
                        |your ticket:
                        |7,1,14
                        |
                        |nearby tickets:
                        |7,3,47
                        |40,4,50
                        |55,2,20
                        |38,6,12""".stripMargin

          solve1(input) must_=== 71
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 18142
      }
    }


    "Part Two" should {
      "solve it" in new Context {
        solve2(input) must_=== "1069784384303".toLong
      }
    }
  }
}