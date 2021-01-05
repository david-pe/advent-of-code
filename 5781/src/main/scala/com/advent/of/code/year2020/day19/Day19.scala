package com.advent.of.code.year2020.day19

import scala.collection.mutable

object Day19 {

  def solve1(input: Array[String]): Long = {
    val (messages, ruleMap) = parseInput(input)

    val rulePatterns = allCombinations(ruleMap(0), ruleMap)

    messages.count { message =>
      rulePatterns.exists(pattern => pattern.contains(message))
    }
  }

  def combinationIterator(lists: Iterable[List[String]], combinations: List[mutable.StringBuilder] = List()): List[String] = {
    lists match {
      case Nil => List()
      case head :: tail =>
        head flatMap { element =>
          if (tail.isEmpty)
            if (combinations.isEmpty) List(element) else combinations map {
              case combination => combination.mkString + element
            }
          else
            combinationIterator(tail, if (combinations.isEmpty) List(new mutable.StringBuilder(element)) else combinations map {
              case combination => combination.clone.append(element)
            })
        }
    }
  }

  def allCombinations(rule: Rule, map: Map[Int, Rule]): List[String] = {
    def mapPart(part: String) = if (part.matches("[ab]")) List(part) else allCombinations(map(part.toInt), map)

    val partA = combinationIterator(rule.partA.map(mapPart).toList)
    val partB = combinationIterator(rule.partB.map(mapPart).toList)

    partA ++ partB
  }

  def solve2(input: Array[String]): Long = {
    val (messages, ruleMap) = parseInput(input)

    val patterns_42 = allCombinations(ruleMap(42), ruleMap)
    val patterns_31 = allCombinations(ruleMap(31), ruleMap)

    messages.count(message => {
      val messageParts = message.grouped(8).toArray

      val parts_42 = messageParts.zipWithIndex.filter(a => patterns_42.contains(a._1)).map(_._2)
      val parts_32 = messageParts.zipWithIndex.filter(a => patterns_31.contains(a._1)).map(_._2)

      val badStructured = parts_42.length < 2 || parts_32.isEmpty
      val wellStructured = !badStructured && parts_32.length < parts_42.length && parts_32.min > parts_42.max

      wellStructured
    })

  }

  private def parseInput(input: Array[String]) = {
    val ruleRegex = raw"(\d*): (.*)".r
    val charRegex = raw""""([ab])"""".r

    val (rules, messages) = input match {
      case Array(rules, data) => rules.split('\n') -> data.split('\n')
    }

    val ruleMap = rules.map {
      case ruleRegex(id, rule) => rule match {
        case charRegex(char) => id.toInt -> Rule(Seq(char))
        case str => id.toInt -> {
          str.split('|') match {
            case Array(partA, partB) => Rule(partA, partB)
            case Array(partA) => Rule(partA)
          }
        }
      }
    }.toMap

    messages -> ruleMap
  }

  case class Rule(partA: Seq[String], partB: Seq[String] = Seq.empty)

  object Rule {
    def apply(partA: String, partB: String): Rule = Rule(clean(partA), clean(partB))

    def apply(partA: String): Rule = Rule(clean(partA))

    private def clean(rulePart: String) = rulePart.split(' ').filter(_.nonEmpty)
  }
}

