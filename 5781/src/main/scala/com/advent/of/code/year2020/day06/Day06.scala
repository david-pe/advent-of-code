package com.advent.of.code.year2020.day06

object Day06 {

  def solve1(input: String): Int = {
    val groups = getGroups(input)
    groups.map(questions).map(_.length).sum
  }

  def solve2(input: String): Int = {
    val groups = getGroups(input)

    groups.map { group =>
      val groupSize = group.split('\n').length
      questions(group).count(question => groupSize == group.count(_ == question))
    }.sum
  }

  def getGroups(input: String) = input.split("\n\n")

  def questions(input: String) = input.replaceAll("\\s+", "").distinct

}
