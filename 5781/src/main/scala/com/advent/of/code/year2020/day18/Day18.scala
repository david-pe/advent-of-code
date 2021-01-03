package com.advent.of.code.year2020.day18

import scala.collection.mutable

object Day18 {

  private val nextRegex = raw" ?([\(\)\+\*\d*])(.*)".r

  def solve1(input: Seq[String]): Long = input.map(calc1).map(_._1).sum

  def solve2(input: Array[String]): Long = input.map(calc2).map(_._1).sum

  private def calc1(expression: String): (Long, String) = {
    var remainingExp = expression
    var exit = false

    var currOp = "+"
    var sum = 0L

    while (remainingExp.nonEmpty && !exit) {

      remainingExp match {
        case nextRegex(member, next) => member match {
          case "(" =>
            sum = calc1(next) match {
              case (innerSum, innerZtring) =>
                remainingExp = innerZtring
                if (currOp == "+") sum + innerSum else sum * innerSum
            }

          case ")" =>
            exit = true
            remainingExp = next

          case "+" | "*" =>
            currOp = member
            remainingExp = next

          case num =>
            sum = if (currOp == "+") sum + num.toLong else sum * num.toLong
            remainingExp = next
        }
      }
    }

    sum -> remainingExp
  }

  private def calc2(expression: String): (Long, String) = {
    var remainingExp = expression
    var exit = false
    val additions = mutable.MutableList[Value](Value(0))

    var currOp = "+"

    while (remainingExp.nonEmpty && !exit) {

      remainingExp match {
        case nextRegex(member, next) => member match {
          case "(" =>
            calc2(next) match {
              case (innerSum, innerRemainingExp) =>
                remainingExp = innerRemainingExp
                if (currOp == "+")
                  additions.last += innerSum
                else
                  additions += Value(innerSum)
            }
          case ")" =>
            remainingExp = next
            exit = true
          case "+" | "*" =>
            remainingExp = next
            currOp = member
          case num =>
            remainingExp = next
            if (currOp == "+")
              additions.last += num.toLong
            else
              additions += Value(num.toLong)
        }
      }
    }

    additions.map(_.value).product -> remainingExp
  }

  case class Value(var value: Long) {
    def +=(that: Long): Unit = value += that
  }

}