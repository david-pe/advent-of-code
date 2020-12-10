package com.advent.of.code.year2020.day10

import com.advent.of.code.year2020.day10.Day10.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day10Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Seq[Int] = {
      val bufferedSource = Source.fromResource("day10/input.txt")
      bufferedSource.getLines.map(_.toInt).toSeq
    }
  }

  "Day Ten" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input =
            """16
              |10
              |15
              |5
              |1
              |11
              |7
              |19
              |6
              |12
              |4
              |""".stripMargin.split('\n').map(_.toInt).toSeq

          solve1(input) must_=== 7 * 5
        }

        "solve second example" in {
          val input =
            """28
              |33
              |18
              |42
              |31
              |14
              |46
              |20
              |48
              |47
              |24
              |23
              |49
              |45
              |19
              |38
              |39
              |11
              |1
              |32
              |25
              |35
              |8
              |17
              |7
              |9
              |4
              |2
              |34
              |10
              |3""".stripMargin.split('\n').map(_.toInt).toSeq

          solve1(input) must_=== 22 * 10
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 3000
      }
    }


    "Part Two" should {

      "Examples" should {
        "solve first example" in {
          val input =
            """16
              |10
              |15
              |5
              |1
              |11
              |7
              |19
              |6
              |12
              |4
              |""".stripMargin.split('\n').map(_.toInt).toSeq

          solve2(input) must_=== 8
        }

        "solve second example" in {
          val input =
            """28
              |33
              |18
              |42
              |31
              |14
              |46
              |20
              |48
              |47
              |24
              |23
              |49
              |45
              |19
              |38
              |39
              |11
              |1
              |32
              |25
              |35
              |8
              |17
              |7
              |9
              |4
              |2
              |34
              |10
              |3""".stripMargin.split('\n').map(_.toInt).toSeq

          solve2(input) must_=== 19208
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== "193434623148032".toLong
      }
    }
  }
}