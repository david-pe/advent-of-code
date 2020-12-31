package com.advent.of.code.year2020.day17

object Day17 {

  def solve1(input: Seq[String]): Long = {
    var world = input.zipWithIndex.flatMap {
      case (line, y) => line.zipWithIndex.map {
        case (char, x) if char == '#' => Some((x, y, 0))
        case _ => None
      }
    }.flatten.toSet

    for {
      _ <- 0 to 5
    } world = nextWorld(world)

    world.size
  }

  private def nextWorld(world: Set[(Int, Int, Int)]): Set[(Int, Int, Int)] = {
    val xBoundary = (world.map(_._1).toSeq.min - 1) -> (world.map(_._1).toSeq.max + 1)
    val yBoundary = (world.map(_._2).toSeq.min - 1) -> (world.map(_._2).toSeq.max + 1)
    val zBoundary = (world.map(_._3).toSeq.min - 1) -> (world.map(_._3).toSeq.max + 1)

    def getNeighbors(x: Int, y: Int, z: Int) = {
      (for {
        x1 <- -1 to 1
        y1 <- -1 to 1
        z1 <- -1 to 1
        if !(x1 == 0 && y1 == 0 && z1 == 0)
      } yield {
        world(x + x1, y + y1, z + z1)
      }).count(x => x)
    }

    (for {
      x <- xBoundary._1 to xBoundary._2
      y <- yBoundary._1 to yBoundary._2
      z <- zBoundary._1 to zBoundary._2
    } yield {
      getNeighbors(x, y, z) match {
        case 2 | 3 if world(x, y, z) => Some((x, y, z))
        case 3 => Some((x, y, z))
        case _ => None
      }
    }).flatten.toSet

  }


  def solve2(input: Seq[String]): Long = {
    var world = input.zipWithIndex.flatMap {
      case (line, y) => line.zipWithIndex.map {
        case (char, x) if char == '#' => Some((x, y, 0, 0))
        case _ => None
      }
    }.flatten.toSet

    for {
      _ <- 0 to 5
    } world = nextWorld4(world)

    world.size
  }

  private def nextWorld4(world: Set[(Int, Int, Int, Int)]): Set[(Int, Int, Int, Int)] = {
    val xBoundary = (world.map(_._1).toSeq.min - 1) -> (world.map(_._1).toSeq.max + 1)
    val yBoundary = (world.map(_._2).toSeq.min - 1) -> (world.map(_._2).toSeq.max + 1)
    val zBoundary = (world.map(_._3).toSeq.min - 1) -> (world.map(_._3).toSeq.max + 1)
    val aBoundary = (world.map(_._4).toSeq.min - 1) -> (world.map(_._4).toSeq.max + 1)

    def getNeighbors(x: Int, y: Int, z: Int, a: Int) = {
      (for {
        x1 <- -1 to 1
        y1 <- -1 to 1
        z1 <- -1 to 1
        a1 <- -1 to 1
        if !(x1 == 0 && y1 == 0 && z1 == 0 && a1 == 0)
      } yield world(x + x1, y + y1, z + z1, a + a1)
        ).count(x => x)
    }

    (for {
      x <- xBoundary._1 to xBoundary._2
      y <- yBoundary._1 to yBoundary._2
      z <- zBoundary._1 to zBoundary._2
      a <- aBoundary._1 to aBoundary._2
    } yield {
      getNeighbors(x, y, z, a) match {
        case 2 | 3 if world(x, y, z, a) => Some((x, y, z, a))
        case 3 => Some((x, y, z, a))
        case _ => None
      }
    }).flatten.toSet
  }

}