package com.advent.of.code.year2020.day06

object Day06 {
  def solve1(input: String): Int = getGroups(input).map(questions).map(_.length).sum

  private def questions(input: String) = input.replaceAll("\\s+", "").distinct

  def solve2(input: String): Int =
    getGroups(input).map(group => {
      val groupSize = group.split('\n').length
      group.groupBy(_.toChar).mapValues(_.length).values.count(_ == groupSize)
    }).sum

  private def getGroups(input: String) = input.trim.split("\n\n")
}
