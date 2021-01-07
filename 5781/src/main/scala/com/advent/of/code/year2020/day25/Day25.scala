package com.advent.of.code.year2020.day25

object Day25 {

  def solve(input: Array[Int]): Long =
    input match {
      case Array(dpk, cpk) => transform(cpk, crackLoopSize(dpk))
    }

  private def transform(subject: Long, loopSize: Long) = {
    var value = 1L
    for {
      _ <- 1L to loopSize
    } {
      value = value * subject
      value = value % 20201227L
    }
    value
  }

  private def crackLoopSize(pk: Long): Long = {
    var value = 1L
    (1L to Int.MaxValue takeWhile { _ =>
      value = value * 7
      value = value % 20201227L
      value != pk
    }).last + 1
  }
}