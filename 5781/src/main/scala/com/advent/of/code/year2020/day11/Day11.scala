package com.advent.of.code.year2020.day11

import scala.collection.mutable

object Day11 {

  def solve1(input: Seq[String]): Long = solve(input, limit = 3, extend = false)

  def solve2(input: Seq[String]): Long = solve(input, limit = 4, extend = true)

  private def solve(input: Seq[String], limit: Int, extend: Boolean) = {
    val nextFloor = nextFloorFactory(limit, extend)

    val height = input.size
    val width = input.head.length
    var floor = nextFloor(setupFloor(input), height, width)
    var lastSize = 0

    do {
      lastSize = floor.values.count(_ == '#')
      floor = nextFloor(floor, height, width)
    } while (floor.values.count(_ == '#') != lastSize)

    lastSize
  }

  private def setupFloor(input: Seq[String]) = {
    val floor = input.zipWithIndex.flatMap {
      case (next, row) => next.zipWithIndex.map({
        case (char, col) => (row -> col) -> char
      })
    }.toMap
    floor
  }

  private def nextFloorFactory(limit: Int, extend: Boolean) = (floor: Map[(Int, Int), Char], height: Int, width: Int) => {
    def adjacent(row: Int, col: Int) = {
      val found = mutable.Map[String, Char]()

      def collectNeighbors(row: Int, col: Int, dist: Int) = {
        val neighbors = getNeighbors(row, col, dist)
        def isInFloor(address: (Int, Int)) = floor.contains(address)

        for {
          (direction, address) <- neighbors
          if !found.contains(direction)
        } yield {
          if (!isInFloor(address)) Some(direction -> '.')
          else if (!extend || floor(address) != '.') Some(direction -> floor(address))
          else None
        }
      }

      var distance = 1
      while (found.size < 8) {
        found ++= collectNeighbors(row, col, distance).flatten
        distance += 1
      }

      found.values.count(_ == '#')
    }

    def nextCellState(row: Int, col: Int) = {
      if (floor(row -> col) == '.')
        '.'
      else
        adjacent(row, col) match {
          case 0 => '#'
          case x if x > limit => 'L'
          case _ => floor(row -> col)
        }
    }

    for {
      row <- 0 until height
      col <- 0 until width
    } yield (row -> col) -> nextCellState(row, col)

  }.toMap

  private def getNeighbors(row: Int, col: Int, dist: Int) =
    Seq(
      "TL" -> ((row - dist) -> (col - dist)),
      "T" -> ((row - dist) -> col),
      "TR" -> ((row - dist) -> (col + dist)),
      "L" -> (row -> (col - dist)),
      "BL" -> ((row + dist) -> (col - dist)),
      "B" -> ((row + dist) -> col),
      "BR" -> ((row + dist) -> (col + dist)),
      "R" -> (row -> (col + dist))
    ).toMap
}
