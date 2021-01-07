package com.advent.of.code.year2020.day21

import com.advent.of.code.year2020.day21.Day21.{solve1, solve2}
import org.specs2.matcher.Scope
import org.specs2.mutable._

import scala.io.Source

class Day21Test extends SpecificationWithJUnit {

  trait Context extends Scope {
    val input: Array[String] = {
      val bufferedSource = Source.fromResource("day21/input.txt")
      bufferedSource.mkString.split("\n")
    }
  }

  trait ExampleContext extends Scope {
    val exampleInput: Array[String] = """mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
                  |trh fvjkl sbzzf mxmxvkd (contains dairy)
                  |sqjhc fvjkl (contains soy)
                  |sqjhc mxmxvkd sbzzf (contains fish)""".stripMargin.split("\n")
  }

  "Day Twenty One" should {

    "Part One" should {
      "Examples" should {

        "solve first example" in new ExampleContext {
          solve1(exampleInput) must_=== 5
        }

      }

      "solve it" in new Context {
        solve1(input) must_=== 2542
      }
    }


    "Part Two" should {

      "Examples" should {

        "solve first example" in new ExampleContext {
          solve2(exampleInput) must_=== "mxmxvkd,sqjhc,fvjkl"
        }
      }

      "solve it" in new Context {
        solve2(input) must_=== "hkflr,ctmcqjf,bfrq,srxphcm,snmxl,zvx,bd,mqvk"
      }
    }
  }
}
