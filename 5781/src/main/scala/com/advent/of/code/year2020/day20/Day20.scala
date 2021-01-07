package com.advent.of.code.year2020.day20

object Day20 {

  def solve1(input: Seq[String]): Long = {
    val puzzle = assemblePuzzle(input)

    Seq(
        puzzle.find(_.isTopLeft).map(_.id),
        puzzle.find(_.isTopRight).map(_.id),
        puzzle.find(_.isBottomRight).map(_.id),
        puzzle.find(_.isBottomLeft).map(_.id))
      .flatten
      .product

  }

  def solve2(input: Seq[String]): Int = {
    var puzzle = stripBorders(positionTiles(assemblePuzzle(input)))

    val dragon = """                  #
                     |#    ##    ##    ###
                     | #  #  #  #  #  #   """.stripMargin.split('\n')

    val firstOffsets = dragon.head.zipWithIndex.filter(_._1 == '#')
    val secondOffsets = dragon(1).zipWithIndex.filter(_._1 == '#')
    val thirdOffsets = dragon.last.zipWithIndex.filter(_._1 == '#')

    val size = math.sqrt(puzzle.size).toInt
    val limit = math.sqrt(puzzle.size).toInt - 1

    def rotate: Unit = {
      puzzle = (for {
        x <- 0 to limit
        y <- 0 to limit
      } yield {
        (x, limit - y) -> puzzle(x -> y)
      }).toMap
    }

    def flip1: Unit = {
      puzzle = (for {
        x <- 0 to limit
        y <- 0 to limit
      } yield {
        (limit - x, limit - y) -> puzzle(x -> y)
      }).toMap
    }

    def flip2: Unit = {
      puzzle = (for {
        x <- 0 to limit
        y <- 0 to limit
      } yield {
        (limit - x, y) -> puzzle(x -> y)
      }).toMap
    }

    def flip3: Unit = {
      puzzle = (for {
        x <- 0 to limit
        y <- 0 to limit
      } yield {
        (x, y) -> puzzle(y -> x)
      }).toMap
    }

    def flip4: Unit = {
      puzzle = (for {
        x <- 0 to limit
        y <- 0 to limit
      } yield {
        (limit - x, y) -> puzzle(y -> x)
      }).toMap
    }

    def flip5: Unit = {
      puzzle = (for {
        x <- 0 to limit
        y <- 0 to limit
      } yield {
        (x, limit - y) -> puzzle(y -> x)
      }).toMap
    }

    def flip6: Unit = {
      puzzle = (for {
        x <- 0 to limit
        y <- 0 to limit
      } yield {
        (limit - x, limit - y) -> puzzle(y -> x)
      }).toMap
    }

    val transforms = Seq(rotate _, flip1 _, flip2 _, flip3 _, flip4 _, flip5 _, flip6 _)

    var found: Option[Seq[Int]] = None

    for {
      transform <- transforms if found.isEmpty
    } {
      val dragons = for {
        lineIndex <- 0 to size - 3
        charIndex <- 0 to size - 20
      } yield {
        if (firstOffsets.forall(x => puzzle(charIndex + x._2, lineIndex) == '#')
          && secondOffsets.forall(x => puzzle(charIndex + x._2, lineIndex + 1) == '#')
          && thirdOffsets.forall(x => puzzle(charIndex + x._2, lineIndex + 2) == '#')) 1 else 0
      }

      if (dragons.sum > 0) {
        found = Some(dragons)
      } else {
        transform()
      }
    }

    puzzle.values.count(_ == '#') - (dragon.flatten.count(_ == '#') * found.map(_.sum).get)
  }


  private def stripBorders(tiles: Map[Long, Tile]) = {
    tiles.values.flatMap {
      case tile => tile.withoutBorder.map {
        case ((x, y), char) =>
          val x1 = x + (tile.position.get._1 * 8)
          val y1 = y + (tile.position.get._2 * 8)
          (x1, y1) -> char
      }
    }.toMap
  }

  private def positionTiles(tiles: Seq[Tile]) = {
    val tilesMap = tiles.map(t => t.id -> t).toMap

    def unpositioned = tilesMap.values.filter(tile => tile.position.isEmpty)

    tilesMap.values.find(_.isTopLeft) foreach {
      tile => tile.position = Some((0, 0))
    }

    while (unpositioned.nonEmpty) {
      for {
        tile <- unpositioned
      } {
        if (tilesMap.get(tile.neighbors.top).flatMap(_.position).isDefined) {
          val neighbor = tilesMap(tile.neighbors.top).position.get
          tile.position = Some(neighbor.copy(_2 = neighbor._2 + 1))
        } else if (tilesMap.get(tile.neighbors.right).flatMap(_.position).isDefined) {
          val neighbor = tilesMap(tile.neighbors.right).position.get
          tile.position = Some(neighbor.copy(_1 = neighbor._1 - 1))
        } else if (tilesMap.get(tile.neighbors.bottom).flatMap(_.position).isDefined) {
          val neighbor = tilesMap(tile.neighbors.bottom).position.get
          tile.position = Some(neighbor.copy(_2 = neighbor._2 - 1))
        } else if (tilesMap.get(tile.neighbors.left).flatMap(_.position).isDefined) {
          val neighbor = tilesMap(tile.neighbors.left).position.get
          tile.position = Some(neighbor.copy(_1 = neighbor._1 + 1))
        }
      }
    }
    tilesMap
  }

  private def assemblePuzzle(input: Seq[String]) = {
    val tiles = input.reverse.zipWithIndex.map {
      case (tile, index) => tile.split('\n').toList match {
        case a :: b => Tile(a, b, index == 0)
      }
    }

    def unlocked = tiles.filterNot(_.isLocked)

    def locked = tiles.filter(_.isLocked)

    while (unlocked.nonEmpty) {

      var found = false

      for {
        remaining <- unlocked if !found
      } {
        for {
          _ <- 0 to 1 if !found
        } {
          for {
            _ <- 0 to 1 if !found
          }  {
              for {
                _ <- 0 to 1 if !found
              } {
                for {
                  maybe <- locked
                } {
                  if (remaining.top == maybe.bottom) {
                    found = true
                    remaining.setTop(maybe.id)
                    maybe.setBottom(remaining.id)
                  } else if (remaining.bottom == maybe.top) {
                    found = true
                    remaining.setBottom(maybe.id)
                    maybe.setTop(remaining.id)
                  } else if (remaining.left == maybe.right) {
                    found = true
                    remaining.setLeft(maybe.id)
                    maybe.setRight(remaining.id)
                  } else if (remaining.right == maybe.left) {
                    found = true
                    remaining.setRight(maybe.id)
                    maybe.setLeft(remaining.id)
                  }
                }
                if (!found) remaining.rotate
              }
            if (!found) remaining.flip
          }
          if (!found) remaining.mirror
        }
      }
    }
    tiles
  }

  case class Neighbors(top: Long, right: Long, bottom: Long, left: Long)

  case class Tile(id: Long,
                  var body: Map[(Int, Int), Char],
                  var neighbors: Neighbors = Neighbors(0, 0, 0, 0),
                  var position: Option[(Int, Int)] = None,
                  isAnchor: Boolean = false) {


    def rotate: Unit = {
      body = (for {
        x <- 0 to 9
        y <- 0 to 9
      } yield {
        (x, 9 - y) -> body(x -> y)
      }).toMap
    }

    def flip: Unit = {
      body = (for {
        x <- 0 to 9
        y <- 0 to 9
      } yield {
        (9 - x, y) -> body(x -> y)
      }).toMap
    }

    def mirror: Unit = {
      body = (for {
        x <- 0 to 9
        y <- 0 to 9
      } yield {
        (x, y) -> body(y -> x)
      }).toMap
    }

    def top = (0 to 9).map(x => body(x, 0)).mkString

    def right = (0 to 9).map(y => body(9, y)).mkString

    def bottom = (0 to 9).map(x => body(x, 9)).mkString

    def left = (0 to 9).map(y => body(0, y)).mkString

    def setTop(neighbor: Long) =
      neighbors = neighbors.copy(top = neighbor)

    def setRight(neighbor: Long) =
      neighbors = neighbors.copy(right = neighbor)

    def setBottom(neighbor: Long) =
      neighbors = neighbors.copy(bottom = neighbor)

    def setLeft(neighbor: Long) =
      neighbors = neighbors.copy(left = neighbor)

    def isLocked = isAnchor || isTopLocked || isRightLocked || isBottomLocked || isLeftLocked

    def isTopLeft = !isLeftLocked && !isTopLocked

    def isTopRight = !isRightLocked && !isTopLocked

    def isBottomRight = !isRightLocked && !isBottomLocked

    def isBottomLeft = !isLeftLocked && !isBottomLocked

    private def isTopLocked = neighbors.top != 0

    private def isRightLocked = neighbors.right != 0

    private def isBottomLocked = neighbors.bottom != 0

    private def isLeftLocked = neighbors.left != 0

    def withoutBorder = (for {
      x <- 1 to 8
      y <- 1 to 8
    } yield {
      (x - 1, y - 1) -> body(x -> y)
    }).toMap
  }

  object Tile {
    def apply(title: String, source: Seq[String], isAnchor: Boolean): Tile = {
      val titleRegex = raw"Tile (\d*):".r
      val id = title match {
        case titleRegex(idPart) => idPart.toLong
      }

      val body = source.zipWithIndex.flatMap {
        case (line, y) => line.zipWithIndex.map {
          case (char, x) => (x -> y) -> char
        }
      }.toMap

      Tile(id, body, isAnchor = isAnchor)
    }
  }

}
