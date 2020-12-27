package com.advent.of.code.year2020.day03

import com.advent.of.code.year2020.day03.Day03.{solve1, solve2}
import com.advent.of.code.year2020.utils.Utils._
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day03Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Seq[String] = {
      val bufferedSource = Source.fromResource("day03/input.txt")
      bufferedSource.getLines
    }.toSeq
  }

  trait ExampleContext extends Scope {
    protected val exampleInput: Seq[String] =
      """
              ..##.......
        |#...#...#..
        |.#....#..#.
        |..#.#...#.#
        |.#...##..#.
        |..#.##.....
        |.#.#.#....#
        |.#........#
        |#.##...#...
        |#...##....#
        |.#..#...#.#"""
        .cleanInput.toSeq
  }

  "Day Three" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in new ExampleContext {
          solve1(exampleInput) must_=== 7
        }
      }

      "solve it" in new Context {
        solve1(input) must_=== 259
      }
    }

    "Part Two" should {
      "Examples" should {
        "solve first example" in new ExampleContext {
          solve2(exampleInput) must_=== 336
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== "2224913600".toLong
      }
    }
  }
}

