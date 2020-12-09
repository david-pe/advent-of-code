package com.advent.of.code.year2020.day09

import com.advent.of.code.year2020.day09.Day09.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day09Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Seq[Long] = {
      val bufferedSource = Source.fromResource("day09/input.txt")
      bufferedSource.getLines.map(_.toLong).toSeq
    }
  }

  "Day Nine" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input =
            """35
              |20
              |15
              |25
              |47
              |40
              |62
              |55
              |65
              |95
              |102
              |117
              |150
              |182
              |127
              |219
              |299
              |277
              |309
              |576""".stripMargin.split('\n').map(_.toLong)

          solve1(input, 5) must_=== 127
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 400480901
      }
    }


    "Part Two" should {

      "solve first example" in {

        val input =
          """35
            |20
            |15
            |25
            |47
            |40
            |62
            |55
            |65
            |95
            |102
            |117
            |150
            |182
            |127
            |219
            |299
            |277
            |309
            |576""".stripMargin.split('\n').map(_.toLong)

        solve2(input, solve1(input, 5)) must_=== 62
      }

      "solve it" in new Context {
        solve2(input, solve1(input)) must_=== 67587168
      }
    }
  }
}