package com.advent.of.code.year2020.day23

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object Day23 {

  def solve1(input: String, moves: Int = 100): String = {

    val cups = arrangeCups(input.split("").map(_.toInt).to[ArrayBuffer], moves)

    (cups.splitAt(cups.indexOf(1)) match {
      case (last, first) => Array.concat(first.tail.toArray, last.toArray)
    }).mkString
  }

  private def arrangeCups(cups: ArrayBuffer[Int], moves: Int) = {

    var current = cups.head

    for {
      _ <- 0 until moves
    } {
      val currIndex = cups.indexOf(current)
      val removed = cups.slice(currIndex + 1, currIndex + 4)
      if (removed.length < 3) {
        removed.appendAll(cups.slice(0, 3 - removed.length))
      }

      cups --= removed

      val sorted = cups.filter(s => s < current)
      val insertCup = if (sorted.isEmpty) cups.max else sorted.max
      val insertIndex = cups.indexOf(insertCup)

      cups.insertAll(insertIndex + 1, removed)

      val newCurrIndex = cups.indexOf(current)
      current = if (newCurrIndex == cups.length - 1) cups.head else cups(newCurrIndex + 1)
    }

    cups
  }

  def solve2(input: String): Long = {
    val count = 1000 * 1000
    val firstCups = input.split("").map(_.toInt)
    val cups = mutable.Map[Int, Cup]()
    val head = Cup(firstCups.head)

    cups(firstCups.head) = head

    Array.concat(firstCups, (firstCups.max + 1 to count).toArray) reduce {
      (previous, index) => {
        cups(index) = Cup(index)
        cups(previous).next = cups(index)
        index
      }
    }

    cups(count).next = head
    var current = head

    for {
      _ <- 0 to count * 10
    } {
      val toRemove = current.nextThree
      current.next = current.newNeighbor

      val insertAt = {
        val removed = toRemove.map(_.label)
        val candidate = 1 to 3 find { i =>
          !removed.contains(current.label - i)
        } getOrElse 4

        cups.getOrElse(current.label - candidate, cups(count))
      }

      toRemove.last.next = insertAt.next
      insertAt.next = toRemove.head

      current = current.next
    }

    cups(1).next.label.toLong * cups(1).next.next.label.toLong
  }

  case class Cup(label: Int, var next: Cup = null) {
    def nextThree = Seq(next, next.next, next.next.next)

    def newNeighbor: Cup = next.next.next.next
  }

}

