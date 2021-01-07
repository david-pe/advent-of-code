package com.advent.of.code.year2020.day24

import com.advent.of.code.year2020.day24.Day24.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day24Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Array[String] = {
      val bufferedSource = Source.fromResource("day24/input.txt")
      bufferedSource.mkString.split("\n")
    }
  }

  trait ExampleContext extends Scope {
    val exampleInput: Array[String] =
      """sesenwnenenewseeswwswswwnenewsewsw
        |neeenesenwnwwswnenewnwwsewnenwseswesw
        |seswneswswsenwwnwse
        |nwnwneseeswswnenewneswwnewseswneseene
        |swweswneswnenwsewnwneneseenw
        |eesenwseswswnenwswnwnwsewwnwsene
        |sewnenenenesenwsewnenwwwse
        |wenwwweseeeweswwwnwwe
        |wsweesenenewnwwnwsenewsenwwsesesenwne
        |neeswseenwwswnwswswnw
        |nenwswwsewswnenenewsenwsenwnesesenew
        |enewnwewneswsewnwswenweswnenwsenwsw
        |sweneswneswneneenwnewenewwneswswnese
        |swwesenesewenwneswnwwneseswwne
        |enesenwswwswneneswsenwnewswseenwsese
        |wnwnesenesenenwwnenwsewesewsesesew
        |nenewswnwewswnenesenwnesewesw
        |eneswnwswnwsenenwnwnwwseeswneewsenese
        |neswnwewnwnwseenwseesewsenwsweewe
        |wseweeenwnesenwwwswnew""".stripMargin.split('\n')

  }

  "Day Twenty Four" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in new ExampleContext {
          solve1(exampleInput) must_=== 10
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== 375L
      }
    }


    "Part Two" should {

      "Examples" should {

        "solve first example" in new ExampleContext {
          solve2(exampleInput) must_=== 2208
        }

      }

      "solve it" in new Context {
        solve2(input) must_=== 3937
      }
    }
  }
}

