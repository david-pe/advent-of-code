package com.advent.of.code.year2020.day07

import com.advent.of.code.year2020.day07.Day07.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day07Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Iterator[String] = {
      val bufferedSource = Source.fromResource("day07/input.txt")
      bufferedSource.getLines
    }
  }

  "Day Seven" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in {
          val input =
            """light red bags contain 1 bright white bag, 2 muted yellow bags.
              |dark orange bags contain 3 bright white bags, 4 muted yellow bags.
              |bright white bags contain 1 shiny gold bag.
              |muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
              |shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
              |dark olive bags contain 3 faded blue bags, 4 dotted black bags.
              |vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
              |faded blue bags contain no other bags.
              |dotted black bags contain no other bags.""".stripMargin.split('\n').toIterator

          solve1(input) must_=== 4
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 164
      }
    }
  }

  "Part Two" should {

    "solve first example" in {
      val input =
        """light red bags contain 1 bright white bag, 2 muted yellow bags.
          |dark orange bags contain 3 bright white bags, 4 muted yellow bags.
          |bright white bags contain 1 shiny gold bag.
          |muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
          |shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
          |dark olive bags contain 3 faded blue bags, 4 dotted black bags.
          |vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
          |faded blue bags contain no other bags.
          |dotted black bags contain no other bags.""".stripMargin.split('\n').toIterator

      solve2(input) must_=== 32
    }

    "solve second example" in {
      val input =
        """shiny gold bags contain 2 dark red bags.
          |dark red bags contain 2 dark orange bags.
          |dark orange bags contain 2 dark yellow bags.
          |dark yellow bags contain 2 dark green bags.
          |dark green bags contain 2 dark blue bags.
          |dark blue bags contain 2 dark violet bags.
          |dark violet bags contain no other bags.""".stripMargin.split('\n').toIterator

      solve2(input) must_=== 126
    }

    "solve it" in new Context {
      solve2(input) must_=== 7872
    }
  }
}

