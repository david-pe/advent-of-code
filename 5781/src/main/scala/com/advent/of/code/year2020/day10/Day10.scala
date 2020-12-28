package com.advent.of.code.year2020.day10

import scala.collection.mutable

object Day10 {
  def solve1(input: Seq[Int]): Long = {
    val sorted = Seq(0) ++ input.sorted
    sorted.indices.init.foldLeft(0 -> 0) {
      case (prev, x) => sorted(x + 1) - sorted(x) match {
        case 1 => prev.copy(_1 = prev._1 + 1)
        case 3 => prev.copy(_2 = prev._2 + 1)
        case _ => prev
      }
    } match {
      case (diffs_1, diffs_3) => diffs_1 * (diffs_3 + 1)
    }
  }

  def solve2(input: Seq[Int]): Long = {
    val sorted = Seq(0) ++ input.sorted
    val visited = mutable.Map[Int, Long]()

    val childMap = sorted
      .map(num1 =>
        num1 -> sorted
          .filter(
            num2 => num1 < num2 && num2 - num1 < 4
          )
      ).toMap

    def sumChildCounts(children: Seq[Int]): Long = {
      if (children.isEmpty) 1 else children.map(child => {
        visited.getOrElseUpdate(child, sumChildCounts(childMap(child)))
      }).sum
    }

    sumChildCounts(childMap(0))
  }
}
