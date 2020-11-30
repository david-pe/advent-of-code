package com.advent.of.code.year2020.day01

import com.advent.of.code.year2020.day01.Day01.Part1.solve
import org.specs2.mutable._

import scala.io.Source

class Day01Test extends SpecificationWithJUnit {

  "Day One" should {
    "solve part 1" in {

      val bufferedSource = Source.fromResource("day01/input.txt")
      val input = bufferedSource.getLines.mkString.split(',').map(_.trim).toSeq
      bufferedSource.close

      solve(input) must beEqualTo(Seq("5", "4", "3", "2", "1"))
    }
  }

}
