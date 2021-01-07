package com.advent.of.code.year2020.day24

import scala.collection.mutable

object Day24 {

  val neighbors = Seq("se", "sw", "ne", "nw", "w", "e")

  def solve1(input: Array[String]): Long = {
    val grid = buildFloor(input)
    grid.count(x => x._2)
  }

  private def buildFloor(input: Array[String]) = {
    val myRegex = raw"(se|sw|ne|nw|w|e)(.*)".r
    val grid = mutable.Map[(Int, Int), Boolean]()
    for {
      line <- input
    } {
      var remaining = line
      var address = 0 -> 0
      while (remaining.nonEmpty) {
        remaining match {
          case myRegex(next, tile) =>
            if (address._2 % 2 == 0) {
              next match {
                case "se" => address = address.copy(_1 = address._1 + 1, _2 = address._2 + 1)
                case "sw" => address = address.copy(_2 = address._2 + 1)
                case "ne" => address = address.copy(_1 = address._1 + 1, _2 = address._2 - 1)
                case "nw" => address = address.copy(_2 = address._2 - 1)
                case "w" => address = address.copy(_1 = address._1 - 1)
                case "e" => address = address.copy(_1 = address._1 + 1)
              }
            } else {
              next match {
                case "se" => address = address.copy(_2 = address._2 + 1)
                case "sw" => address = address.copy(_1 = address._1 - 1, _2 = address._2 + 1)
                case "ne" => address = address.copy(_2 = address._2 - 1)
                case "nw" => address = address.copy(_1 = address._1 - 1, _2 = address._2 - 1)
                case "w" => address = address.copy(_1 = address._1 - 1)
                case "e" => address = address.copy(_1 = address._1 + 1)
              }
            }
            remaining = tile
        }
      }
      if (grid.contains(address)) {
        grid(address) = !grid(address)
      } else {
        grid(address) = true
      }
    }
    grid
  }

  def solve2(input: Array[String]): Long = {
    var floor = mutable.Map(buildFloor(input).toSeq: _*)

    for {
      _ <- 0 until 100
    } floor = mutable.Map(day(floor).toSeq: _*)

    floor.values.count(x => x)
  }

  private def day(grid: mutable.Map[(Int, Int), Boolean]) = {
    val left = grid.keys.map(_._1).min - 2
    val right = grid.keys.map(_._1).max + 2
    val top = grid.keys.map(_._2).min - 2
    val bottom = grid.keys.map(_._1).max + 2

    def countNeighbors(x: Int, y: Int) =
      collectNeighbors(x, y).map(tile => grid.getOrElse(tile, false)).count(x => x)

    (for {
      y <- top to bottom
      x <- left to right
    } yield {
      val neighbors = countNeighbors(x, y)
      val isBlack = grid.getOrElse(x -> y, false)
      if (isBlack) {
        if (neighbors == 0 || neighbors > 2) {
          (x, y) -> false
        } else (x, y) -> true
      } else {
        if (neighbors == 2) {
          (x, y) -> true
        } else (x, y) -> false
      }
    }).toMap
  }

  def collectNeighbors(x: Int, y: Int): Seq[(Int, Int)] = neighbors.map {
    neighbor => {
      if (y % 2 == 0) {
        neighbor match {
          case "se" => (x + 1, y + 1)
          case "sw" => (x, y + 1)
          case "ne" => (x + 1, y - 1)
          case "nw" => (x, y - 1)
          case "w" => (x - 1, y)
          case "e" => (x + 1, y)
        }
      } else {
        neighbor match {
          case "se" => (x, y + 1)
          case "sw" => (x - 1, y + 1)
          case "ne" => (x, y - 1)
          case "nw" => (x - 1, y - 1)
          case "w" => (x - 1, y)
          case "e" => (x + 1, y)
        }
      }
    }
  }
}
