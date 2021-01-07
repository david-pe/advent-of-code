package com.advent.of.code.year2020.day20

import com.advent.of.code.year2020.day20.Day20.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day20Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Array[String] = {
      val bufferedSource = Source.fromResource("day20/input.txt")
      bufferedSource.mkString.split("\n\n")
    }
  }

  trait ExampleContext extends Scope {
    val exampleInput: Array[String] = """Tile 2311:
                  |..##.#..#.
                  |##..#.....
                  |#...##..#.
                  |####.#...#
                  |##.##.###.
                  |##...#.###
                  |.#.#.#..##
                  |..#....#..
                  |###...#.#.
                  |..###..###
                  |
                  |Tile 1951:
                  |#.##...##.
                  |#.####...#
                  |.....#..##
                  |#...######
                  |.##.#....#
                  |.###.#####
                  |###.##.##.
                  |.###....#.
                  |..#.#..#.#
                  |#...##.#..
                  |
                  |Tile 1171:
                  |####...##.
                  |#..##.#..#
                  |##.#..#.#.
                  |.###.####.
                  |..###.####
                  |.##....##.
                  |.#...####.
                  |#.##.####.
                  |####..#...
                  |.....##...
                  |
                  |Tile 1427:
                  |###.##.#..
                  |.#..#.##..
                  |.#.##.#..#
                  |#.#.#.##.#
                  |....#...##
                  |...##..##.
                  |...#.#####
                  |.#.####.#.
                  |..#..###.#
                  |..##.#..#.
                  |
                  |Tile 1489:
                  |##.#.#....
                  |..##...#..
                  |.##..##...
                  |..#...#...
                  |#####...#.
                  |#..#.#.#.#
                  |...#.#.#..
                  |##.#...##.
                  |..##.##.##
                  |###.##.#..
                  |
                  |Tile 2473:
                  |#....####.
                  |#..#.##...
                  |#.##..#...
                  |######.#.#
                  |.#...#.#.#
                  |.#########
                  |.###.#..#.
                  |########.#
                  |##...##.#.
                  |..###.#.#.
                  |
                  |Tile 2971:
                  |..#.#....#
                  |#...###...
                  |#.#.###...
                  |##.##..#..
                  |.#####..##
                  |.#..####.#
                  |#..#.#..#.
                  |..####.###
                  |..#.#.###.
                  |...#.#.#.#
                  |
                  |Tile 2729:
                  |...#.#.#.#
                  |####.#....
                  |..#.#.....
                  |....#..#.#
                  |.##..##.#.
                  |.#.####...
                  |####.#.#..
                  |##.####...
                  |##..#.##..
                  |#.##...##.
                  |
                  |Tile 3079:
                  |#.#.#####.
                  |.#..######
                  |..#.......
                  |######....
                  |####.#..#.
                  |.#...#.##.
                  |#.#####.##
                  |..#.###...
                  |..#.......
                  |..#.###...""".stripMargin.split("\n\n")
  }

  "Day Twenty" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in new ExampleContext {
          solve1(exampleInput) must_=== "20899048083289".toLong
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== "18482479935793".toLong
      }
    }


    "Part Two" should {

      "Examples" should {

        "solve first example" in new ExampleContext {
          solve2(exampleInput) must_=== 273
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== 2118
      }
    }
  }
}
