package com.advent.of.code.year2020.day15

import scala.collection.mutable

object Day15 {

  def solve2(input: Seq[Int]): Long = {
    solve1(input, 30000000)
  }

  def solve1(input: Seq[Int], end: Int = 2020): Long = {
    var init = false
    var last = input.last

    val positions = mutable.Map() ++
      input.zipWithIndex.map(x => x._1 -> (x._2 -> x._2))

    for {
      index <- input.size until end
    } yield {
      last = if (!positions.contains(last) || !init) {
        init = true
        0
      } else {
        positions(last) match {
          case (previous, previousPrevious) => previousPrevious - previous
        }
      }
      positions(last) = positions.getOrElseUpdate(last, index -> index) match {
        case (_, previous) => previous -> index
      }
    }
    last
  }
}