package com.advent.of.code.year2020.day02

object Day02 {

  def parseInput(input: Iterator[String]): Seq[(PasswordPolicy, String)] = {
    val regex = raw"(\d*)-(\d*) (.): (.*)".r

    input.map {
      case regex(min, max, char, pwd) => PasswordPolicy(min, max, char) -> pwd
    }.toSeq
  }

  def solve1(input: Seq[(PasswordPolicy, String)]): Int = {

    input.count {
      case (PasswordPolicy(min, max, char), pwd) => {
        val charCount = pwd.count(_ == char)
        charCount >= min && charCount <= max
      }
    }

  }

  def solve2(input: Seq[(PasswordPolicy, String)]): Int = {

    input.count {
      case (PasswordPolicy(min, max, char), pwd) => {
        val pos1 = pwd.charAt(min - 1) == char
        val pos2 = pwd.charAt(max - 1) == char
        pos1 ^ pos2
      }
    }
  }

  case class PasswordPolicy(min: Int, max: Int, char: Char)

  object PasswordPolicy {
    def apply(min: String, max: String, char: String): PasswordPolicy = PasswordPolicy(min.toInt, max.toInt, char.head)
  }

}
