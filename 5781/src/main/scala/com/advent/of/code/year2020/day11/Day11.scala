package com.advent.of.code.year2020.day11

import scala.collection.mutable

object Day11 {
  def solve1(input: Seq[String]): Long = {
    val floor = input.zipWithIndex.flatMap {
      case (next, row) => next.zipWithIndex.map({
        case (char, col) => s"$row:$col" -> char
      })
    }.toMap


    var ttt = Set[String]()
    var lastOcu = 0
    var done = 0

    while (done < 3) {
      ttt = nextWaitingArea(ttt, floor)
      if (ttt.size == lastOcu) done += 1
      lastOcu = ttt.size
      println(ttt.size)
    }

    ttt.size
  }

  def nextWaitingArea(lastWaitingArea: Set[String], floor: Map[String, Char]): Set[String] = {
    val grid = mutable.Set[String]()

    def adjacent(row: Int, col: Int) = {
      val topLeft = s"${row-1}:${col-1}"
      val top = s"${row-1}:${col}"
      val topRight = s"${row-1}:${col+1}"
      val left = s"${row}:${col-1}"
      val bottomLeft = s"${row+1}:${col-1}"
      val bottom = s"${row+1}:${col}"
      val bottomRight = s"${row+1}:${col+1}"
      val right = s"${row}:${col+1}"

      val all = Seq(topLeft, top, topRight, left, bottomLeft, bottom, bottomRight, right)

      all.count(lastWaitingArea.contains)
    }

    def isAlive(row: Int, col: Int) = {
      if (floor(s"$row:$col") == '.') false
      else adjacent(row, col) match {
        case 0 => true
        case x if x > 3 => false
        case _ => lastWaitingArea(s"$row:$col")
      }
    }

    grid ++= (for {
      Array(row, col) <- floor.keys.map(_.split(':').map(_.toInt))
      if isAlive(row, col)
    } yield s"$row:$col")

    grid.toSet
  }

  def solve2(input: Seq[String]): Long = {
    var floor = input.zipWithIndex.flatMap {
      case (next, row) => next.zipWithIndex.map({
        case (char, col) => (row, col) -> char
      })
    }.toMap

    val height = input.size
    val width = input.head.length

    var lastOcu = 0
    var done = 0

    while (done < 3) {
      floor = nextWaitingArea2(floor, height, width)
      val seated = floor.count(x => x._2 == '#')
      if (seated == lastOcu) done += 1
      lastOcu = seated
      println(seated)
    }

   lastOcu

  }

  def nextWaitingArea2(floor: Map[(Int, Int), Char], height: Int, width: Int): Map[(Int, Int), Char] = {
    val nexFloorMap = mutable.Map[(Int, Int), Char]()

    def adjacent(row: Int, col: Int) = {
      val found = mutable.Map[String, Char]()

      def innerAdjacent(row: Int, col: Int, dist: Int) =
      {
        val topLeft = (row - dist) -> (col - dist)
        val top = (row - dist) -> col
        val topRight = row - dist -> (col + dist)
        val left = row -> (col - dist)
        val bottomLeft = (row + dist) -> (col - dist)
        val bottom = (row + dist) -> col
        val bottomRight = (row + dist) -> (col + dist)
        val right = row -> (col + dist)

        val TL = "TL"
        val T = "T"
        val TR = "TR"
        val L = "L"
        val BL = "BL"
        val B = "B"
        val BR = "BR"
        val R = "R"

        if (!found.contains(TL)) {
          if (!floor.contains(topLeft)) found += (TL -> '.') else if (floor(topLeft) != '.') found += (TL -> floor(topLeft))
        }
        if (!found.contains(T)) {
          if (!floor.contains(top)) found += (T -> '.') else if (floor(top) != '.') found += (T -> floor(top))
        }
        if (!found.contains(TR)) {
          if (!floor.contains(topRight)) found += (TR -> '.') else if (floor(topRight) != '.') found += (TR -> floor(topRight))
        }
        if (!found.contains(L)) {
          if (!floor.contains(left)) found += (L -> '.') else if (floor(left) != '.') found += (L -> floor(left))
        }
        if (!found.contains(BL)) {
          if (!floor.contains(bottomLeft)) found += (BL -> '.') else if (floor(bottomLeft) != '.') found += (BL -> floor(bottomLeft))
        }
        if (!found.contains(B)) {
          if (!floor.contains(bottom)) found += (B -> '.') else if (floor(bottom) != '.') found += (B -> floor(bottom))
        }
        if (!found.contains(BR)) {
          if (!floor.contains(bottomRight)) found += (BR -> '.') else if (floor(bottomRight) != '.') found += (BR -> floor(bottomRight))
        }
        if (!found.contains(R)) {
          if (!floor.contains(right)) found += (R -> '.') else if (floor(right) != '.') found += (R -> floor(right))
        }
      }

      var distance = 1
      while (found.size < 8) {

        innerAdjacent(row, col, distance)

        distance += 1
      }

      found.count(_._2 == '#')

    }

    def nextCellState(row: Int, col: Int) = {
      if (floor(row -> col) == '.') '.'
      else adjacent(row, col) match {
        case 0 => '#'
        case x if x > 4 => 'L'
        case _ => floor(row -> col)
      }
    }

    nexFloorMap ++= (for {
      row <- 0 until height
      col <- 0 until width
    } yield (row -> col) -> nextCellState(row, col))

    nexFloorMap.toMap
  }

}
