package com.advent.of.code.year2020.day10

import scala.collection.mutable

object Day10 {
  def solve1(input: Seq[Int]): Long = {
    val sorted = Seq(0) ++ input.sorted
    val diff1 = sorted.indices.init.count(x => sorted(x + 1) - sorted(x) == 1)
    val diff3 = sorted.indices.init.count(x => sorted(x + 1) - sorted(x) == 3)

    diff1 * (diff3 + 1)
  }

  def solve2(input: Seq[Int]): Long = {
    val sorted = (Seq(0) ++ input.sorted)
    val visited = mutable.Map[Int, Long]()

    val childMap = sorted.map(x => x -> sorted.filter(y => y > x && y - x < 4)).toMap

    def sumChildCounts(children: Seq[Int]): Long = {
      if (children.isEmpty) 1 else children.map(child => {
        visited.getOrElseUpdate(child, sumChildCounts(childMap(child)))
      }).sum
    }

    sumChildCounts(childMap(0))
  }
}
