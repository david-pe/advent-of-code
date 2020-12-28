package com.advent.of.code.year2020.day12


object Day12 {

  def solve1(input: Array[String]): Long = {
    val initialShip = Ship(0, Pos(0, 0))

    val finalShip = parseInput(input)
      .foldLeft(initialShip)(
        (ship, command) => {
          command match {
            case ("N", value) => ship.moveYBy(value)
            case ("S", value) => ship.moveYBy(-value)
            case ("E", value) => ship.moveXBy(value)
            case ("W", value) => ship.moveXBy(-value)
            case ("L", value) => ship.turnLeft(value)
            case ("R", value) => ship.turnRight(value)
            case ("F", value) => ship.moveForward(value)
          }
        })

    finalShip.distance
  }

  def solve2(input: Array[String]): Long = {
    val initialState = ShipState(ship = Pos(0, 0), waypoint = Pos(10, 1))

    val finalState = parseInput(input).foldLeft(initialState)((currentState, instruction) => {
      instruction match {
        case ("N", value) => currentState.moveWaypointY(value)
        case ("S", value) => currentState.moveWaypointY(-value)
        case ("E", value) => currentState.moveWaypointX(value)
        case ("W", value) => currentState.moveWaypointX(-value)
        case ("L", value) => currentState.rotateWaypoint(360 - value)
        case ("R", value) => currentState.rotateWaypoint(value)
        case ("F", value) => currentState.moveShipBy(value)
      }
    })

    finalState.ship.distance
  }

  private def parseInput(input: Array[String]) = {
    val opRegex = raw"([A-Z])(\d*)".r
    for {
      instruction <- input flatMap {
        case opRegex(action, value) => Some(action -> value.toInt)
      }
    } yield instruction
  }

  case class Ship(direction: Int, pos: Pos) {
    def distance: Int = pos.distance

    def turnLeft(angle: Int): Ship = copy(direction = (direction + 360 - angle) % 360)

    def turnRight(angle: Int): Ship = copy(direction = (direction + angle) % 360)

    def moveForward(value: Int): Ship = {
      this.direction match {
        case 0 => moveXBy(value)
        case 90 => moveYBy(-value)
        case 180 => moveXBy(-value)
        case 270 => moveYBy(value)
      }
    }

    def moveXBy(x: Int): Ship = copy(pos = pos.moveXBy(x))

    def moveYBy(y: Int): Ship = copy(pos = pos.moveYBy(y))
  }

  case class Pos(x: Int, y: Int) {
    def distance: Int = Math.abs(x) + Math.abs(y)

    def moveXBy(value: Int): Pos = copy(x = x + value)

    def moveYBy(value: Int): Pos = copy(y = y + value)

    def rotateBy(angle: Int): Pos = {
      val rads = angle * Math.PI / 180
      val rotatedX = Math.cos(rads) * x + Math.sin(rads) * y
      val rotatedY = Math.cos(rads) * y - Math.sin(rads) * x
      copy(
        x = Math.round(rotatedX).toInt,
        y = math.round(rotatedY).toInt
      )
    }
  }

  case class ShipState(ship: Pos, waypoint: Pos) {
    def moveWaypointX(x: Int): ShipState = copy(waypoint = waypoint.moveXBy(x))

    def moveWaypointY(y: Int): ShipState = copy(waypoint = waypoint.moveYBy(y))

    def rotateWaypoint(angle: Int): ShipState = copy(waypoint = waypoint.rotateBy(angle))

    def moveShipBy(distance: Int): ShipState = copy(ship =
      ship.moveXBy(distance * waypoint.x).moveYBy(distance * waypoint.y)
    )
  }

}
