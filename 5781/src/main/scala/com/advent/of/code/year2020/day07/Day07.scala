package com.advent.of.code.year2020.day07

object Day07 {
  private val empty = "empty"
  private val shinyGold = "shiny gold"

  def solve1(input: Iterator[String]): Int = {

    val bags = parseInput(input)

    def containsShinyGold(bag: (String, Map[String, Int])): Boolean = bag match {
      case (_, contents) if contents.contains(shinyGold) => true
      case (_, contents) => contents.exists {
        case (`empty`, 0) => false
        case (name: String, _: Int) => containsShinyGold(name, bags(name))
      }
    }

    bags.count(containsShinyGold)
  }

  private def parseInput(input: Iterator[String]) = {
    val keyRegex = raw"(.*) bags".r
    val valueRegex = raw"(\d*|no) (.*) bag.*?".r

    def stripKey(key: String) = key.trim match {
      case keyRegex(stripped) => stripped.trim
    }

    def stripValue(key: String) = key.trim match {
      case valueRegex("no", _) => empty -> 0
      case valueRegex(count, stripped) => stripped.trim -> count.toInt
    }

    input.map(_.split("contain|,").toList)
      .map {
        case key :: value => stripKey(key) -> value.map(stripValue).toMap
        case _ => throw new RuntimeException("bad input")
      }.toMap

  }

  def solve2(input: Iterator[String]): Int = {
    val bags = parseInput(input)

    def countBags(bagName: String): Int =
      bags(bagName).foldLeft(0) { (total, subRule) =>
        total + (subRule match {
          case (`empty`, 0) => 0
          case (name, count) => count + count * countBags(name)
        })
      }

    countBags(shinyGold)
  }
}
