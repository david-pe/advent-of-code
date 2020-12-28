package com.advent.of.code.year2020.day13

object Day13 {

  def solve1(input: Array[String]): Long = {
    val (earliest, busLines) = input match {
      case Array(_earliest, _busLines) =>
        _earliest.toInt -> _busLines.split(',').filter(_ != "x").map(_.toInt)
    }

    val finds = for {
      timestamp <- Iterator.from(earliest).toSeq
      find <- busLines.find(busLine => timestamp % busLine == 0)
    } yield find -> timestamp

    finds.head match {
      case (busLine, timestamp) => (timestamp - earliest) * busLine
    }
  }

  def solve2(input: Array[String], min: Long = 100000000000000L): Long = {
    // https://github.com/shahata/adventofcode-solver/blob/master/src/2020/day13.js#L34
    val busLines = input match {
      case Array(_, _busLines) =>
        _busLines.split(',')
          .zipWithIndex
          .filter(_._1 != "x")
          .map {
            case (_offset, _id) => _id -> _offset.toLong
          }
    }

    var result = min
    var step = 1.toLong

    busLines.foreach {
      case (index, value) =>
        while ((result + index) % value != 0) {
          result += step
        }
        // not that I fully understand this, but a big thanks to @shahata for the direction
        // https://github.com/shahata/adventofcode-solver/blob/master/src/2020/day13.js#L34
        step *= value

    }

    result
  }
}