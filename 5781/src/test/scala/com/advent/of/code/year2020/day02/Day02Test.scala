package com.advent.of.code.year2020.day02

import com.advent.of.code.year2020.day01.Day01.Part1.solve
import org.specs2.mutable._

import scala.io.Source

class Day02Test extends SpecificationWithJUnit {

  "Day Two" should {
    "solve part 1" in {

      val bufferedSource = Source.fromResource("day02/input.txt")
      val input = bufferedSource.getLines.mkString.split(',').map(_.trim).toSeq
      bufferedSource.close

      solve(input) must beEqualTo(Seq("1", "2", "3", "4", "5"))
    }
  }

}
