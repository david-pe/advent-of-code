package com.advent.of.code.year2020.day22

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Day22 {

  def solve1(input: Array[String]): Long = {
    val (player1, player2) = parseInput(input)

    while (player1.nonEmpty && player2.nonEmpty) {

      val card1 = player1.remove(0)
      val card2 = player2.remove(0)

      if (card1 > card2) {

        player1.append(card1, card2)
      } else {
        player2.append(card2, card1)
      }
    }

    if (player1.nonEmpty)
      sumCards(player1)
    else
      sumCards(player2)
  }

  private def parseInput(input: Array[String]) = {
    val player1 = input.head.split('\n').tail.map(_.toInt).to[ListBuffer]
    val player2 = input.last.split('\n').tail.map(_.toInt).to[ListBuffer]
    player1 -> player2
  }

  private def sumCards(player: ListBuffer[Int]) =
    player.reverse.zipWithIndex.map(x => x._1 * (x._2 + 1)).sum


  def solve2(input: Array[String]): Long = {
    val (player1, player2) = parseInput(input)

    recursiveGame(player1, player2, outerGame = true)
  }

  def recursiveGame(player1: ListBuffer[Int], player2: ListBuffer[Int], outerGame: Boolean = false): Int = {

    val playerOneDecks = mutable.Set[String]()
    val playerTwoDecks = mutable.Set[String]()

    while (player1.nonEmpty && player2.nonEmpty) {

      playerOneDecks += player1.mkString(",")
      playerTwoDecks += player2.mkString(",")

      val card1 = player1.remove(0)
      val card2 = player2.remove(0)

      if (player1.length >= card1 && player2.length >= card2) {
        if (recursiveGame(player1.take(card1), player2.take(card2)) == 1) {
          player1.append(card1, card2)
        } else {
          player2.append(card2, card1)
        }
      } else if (card1 > card2) {
        player1.append(card1, card2)
      } else {
        player2.append(card2, card1)
      }

      if (playerOneDecks.contains(player1.mkString(",")) || playerTwoDecks.contains(player2.mkString(","))) {
        return if (outerGame) sumCards(player1) else 1
      }
    }

    if (player1.nonEmpty)
      if (outerGame) sumCards(player1) else 1
    else if (outerGame) sumCards(player2) else 2
  }

}