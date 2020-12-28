package com.advent.of.code.year2020.day08

import scala.collection.mutable

object Day08 {

  type Ops = Map[Int, (String, Int)]

  val (jmp, nop, acc) = ("jmp", "nop", "acc")

  def solve1(input: Iterator[String]): Int = {
    computer(parseInput(input)).result
  }

  def solve2(input: Iterator[String]): Int = {
    val ops = parseInput(input)

    Seq(jmp -> nop, nop -> jmp).flatMap {
      case (from, to) => for {
        maybeFix <- ops.filter(_._2._1 == from)
        result <- maybeFix match {
          case (idx, _) => computer(runFix(ops, to, idx)) match {
            case Output(result, true) => Some(result)
            case _ => None
          }
        }
      } yield result
    }.head
  }

  private def computer(ops: Ops): Output = {
    var accumulator = 0
    var position = 0

    val visited = mutable.Set[Int]()

    while (!visited.contains(position) && position < ops.size) {
      visited += position
      ops.get(position) match {
        case Some((`acc`, arg)) =>
          accumulator += arg
          position += 1
        case Some((`jmp`, input)) => position += input
        case Some((`nop`, _)) => position += 1
        case Some(_) | None =>
      }
    }

    Output(accumulator, position >= ops.size)
  }

  private def parseInput(input: Iterator[String]): Ops = {
    val regex = raw"([a-z]{3}) ([-+]\d*)".r
    input.map {
      case regex(op, input) => op -> input.toInt
    }.zipWithIndex.map(_.swap).toMap
  }

  private def runFix(allOps: Ops, opToFix: String, idx: Int): Ops =
    allOps.map({
      case (`idx`, (_: String, arg: Int)) => idx -> (opToFix -> arg)
      case _@op => op
    })

  case class Output(result: Int, success: Boolean)

}
