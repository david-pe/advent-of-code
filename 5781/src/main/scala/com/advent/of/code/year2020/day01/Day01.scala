package com.advent.of.code.year2020.day01

object Day01 {

  def solve1(input: Seq[Int]): Int = {

    (for {
      x <- input
      y <- input if x + y == 2020
    } yield x * y).head

  }

  def solve2(input: Seq[Int]): Int = {

    (for {
      x <- input
      y <- input
      z <- input if x + y + z == 2020
    } yield x * y * z).head

  }

}
