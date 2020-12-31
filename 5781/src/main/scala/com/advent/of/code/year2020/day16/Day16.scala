package com.advent.of.code.year2020.day16

import scala.collection.mutable

object Day16 {

  def solve1(input: String): Long = {
    input.split("\n\n") match {
      case Array(_rules, _, _nearby) =>
        val rules = _rules.split('\n').map(Rule(_))
        val nearby = _nearby.split('\n').tail.flatMap(_.split(',').map(_.toInt))
        nearby.filter {
          pos => !rules.exists(rule => rule.values.contains(pos))
        }.sum
    }
  }

  def solve2(input: String): Long = {
    input.split("\n\n") match {
      case Array(_rules, _ticket, _nearby) =>
        val rules = _rules.split('\n').map(Rule(_))
        val ticket = _ticket.split('\n').last.split(',').map(_.toInt)
        val nearby = _nearby.split('\n').tail.map(_.split(',').map(_.toInt))
          .filter(
            nearbyTicket => nearbyTicket.forall(
              pos => rules.exists(rule => rule.values.contains(pos)))
          )

        val found: mutable.Map[String, Int] = mutable.Map.empty

        while (found.size < nearby.head.length) {
          (for {
            rule <- rules.filterNot(x => found.contains(x.name))
            index <- nearby.head.indices.filterNot(x => found.values.toSeq.contains(x))
            if nearby.forall(num => rule.values.contains(num(index)))
          } yield {
            rule.name -> index
          }).groupBy(_._1).filter(x => x._2.length == 1).foreach {
            case (ruleName, Array((_, position))) => found(ruleName) = position
          }
        }

        found.filter(x => x._1.contains("departure")).map {
          case (_, value) => ticket(value).toLong
        }.product
    }
  }

  case class Rule(name: String, values: Seq[Int])

  object Rule {
    private val rulesRegex = raw"(.*): (\d*)-(\d*) or (\d*)-(\d*)".r

    def apply(ruleStr: String): Rule = ruleStr match {
      case rulesRegex(name, min1, max1, min2, max2) => Rule(name, (min1.toInt to max1.toInt) ++ (min2.toInt to max2.toInt))
    }
  }

}