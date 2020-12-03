package com.advent.of.code.year2020.day03

object Day03 {

  def solve2(input: Seq[String]): Long = {
    Seq(Slope(1, 1)
      , Slope(3, 1)
      , Slope(5, 1)
      , Slope(7, 1)
      , Slope(1, 2))
      .map {
        case Slope(right, down) => solve1(input, right, down)
      }
      .map(_.toLong)
      .product
  }

  def solve1(input: Seq[String], right: Int = 3, down: Int = 1): Int = {
    Range(0, input.length, down).zipWithIndex.count {
      case (row, step) => didHitTree(input(row), step * right)
    }
  }

  private def didHitTree(row: String, colIndex: Int): Boolean = row.charAt(colIndex % row.length) == '#'

  case class Slope(right: Int, down: Int)

}
