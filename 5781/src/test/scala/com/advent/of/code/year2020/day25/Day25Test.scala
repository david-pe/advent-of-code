package com.advent.of.code.year2020.day25

import com.advent.of.code.year2020.day25.Day25.solve
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day25Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Array[Int] = {
      val bufferedSource = Source.fromResource("day25/input.txt")
      bufferedSource.mkString.split("\n").map(_.toInt)
    }
  }

  "Day Twenty Five" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input =
            """17807724
              |5764801""".stripMargin.split('\n').map(_.toInt)

          solve(input) must_=== 14897079
        }
      }

      "solve it" in new Context {
        solve(input) must_=== 4441893
      }
    }

  }
}

