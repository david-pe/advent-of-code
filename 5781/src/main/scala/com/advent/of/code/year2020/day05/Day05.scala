package com.advent.of.code.year2020.day05

object Day05 {

  private val allSeats = (for {
    row <- (0 to 127)
    col <- (0 to 7)
  } yield row * 8 + col).toSet

  def solve2(input: Iterator[String]): Long = {
    val seats = input.map(getId).toSet
    val emptySeats = allSeats.diff(seats)

    emptySeats.find(seat => seats.contains(seat + 1) && seats.contains(seat - 1)).get
  }

  def solve1(input: Iterator[String]): Int = {
    input.map(getId).max
  }

  private def getId(input: String) = {
    val (row, col) = input
      .replaceAll("[FL]", "0")
      .replaceAll("[BR]", "1")
      .splitAt(7)

    b2d(row) * 8 + b2d(col)
  }

  private def b2d(binary: String) = Integer.parseInt(binary, 2)

}
