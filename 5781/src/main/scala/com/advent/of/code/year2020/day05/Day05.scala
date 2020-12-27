package com.advent.of.code.year2020.day05

object Day05 {

  private val allSeats =  (0 to 127 * 8).toSet

  def solve2(input: Iterator[String]): Long = {
    val seats = input.map(getId).toSet
    val emptySeats = allSeats.diff(seats)

    emptySeats
      .find(seat => seats.contains(seat + 1) && seats.contains(seat - 1))
      .get
  }

  def solve1(input: Iterator[String]): Int = {
    input.map(getId).max
  }

  private def getId(input: String) = {
    val seat = input
      .replaceAll("[FL]", "0")
      .replaceAll("[BR]", "1")

    b2d(seat)
  }

  private def b2d(binary: String) = Integer.parseInt(binary, 2)

}
