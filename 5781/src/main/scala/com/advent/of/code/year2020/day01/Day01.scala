package com.advent.of.code.year2020.day01

object Day01 {

  def solve1(input: Seq[Int]): Int =
    input.combinations(2).find(_.sum == 2020).head.product

  def solve2(input: Seq[Int]): Int =
    input.combinations(3).find(_.sum == 2020).head.product

}
