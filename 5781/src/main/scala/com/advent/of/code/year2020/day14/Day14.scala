package com.advent.of.code.year2020.day14

object Day14 {

  private val maskRegex = raw"mask = (.*)".r
  private val instructionRegex = raw"mem\[(\d*)\] = (\d*)".r

  def solve1(input: String): Long = {
    parseInput(input).flatMap(commandGroup => {
      (commandGroup match {
        case mask +: instructions => CommandGroup(mask, instructions)
      }) match {
        case CommandGroup(mask, instructions) =>
          instructions.map {
            case Instruction(address, value) =>
              address -> mask.foldLeft(value) {
                case (newValue, Mask(0, bit)) => newValue - (value & bit)
                case (newValue, Mask(1, bit)) => if ((value & bit) == 0) newValue + bit else newValue
                case (_, Mask(invalid, _)) => throw new RuntimeException(s"invalid mask instruction, $invalid")
              }
          }
      }
    }).toMap.values.sum
  }

  private def parseInput(input: String) = input.split("(?=mask)").map(x => x.split('\n').toSeq)

  def solve2(input: String): Long = {
    parseInput(input).flatMap(floatingCommandGroup => {
      (floatingCommandGroup match {
        case mask +: instructions => FloatingMaskCommandGroup(mask, instructions)
      }) match {
        case FloatingMaskCommandGroup(masks, instructions) =>
          instructions.map {
            case Instruction(address, value) =>
              masks.map { mask =>
                mask.masks.foldLeft(address.toLong) {
                  case (newAddress, Mask(0, bit)) =>
                    val bitValue = math.pow(2, bit).toLong
                    newAddress - (address & bitValue)
                  case (newAddress, Mask(1, bit)) =>
                    val bitValue = math.pow(2, bit).toLong
                    if ((address & bitValue) == 0) newAddress + bitValue else newAddress
                  case (_, Mask(invalid, _)) => throw new RuntimeException(s"invalid mask instruction, $invalid")
                } -> value
              }
          }
      }
    }).flatten.toMap.values.sum
  }

  private def buildInstructions(instructions: Seq[String]) = instructions.map {
    case instructionRegex(address, value) => Instruction(address = address.toInt, value = value.toLong)
  }

  case class Mask(bit: Int, value: Long)

  case class FloatingMask(masks: Seq[Mask])

  case class Instruction(address: Int, value: Long)

  case class CommandGroup(mask: Seq[Mask], instructions: Seq[Instruction])

  case class FloatingMaskCommandGroup(masks: Seq[FloatingMask], instructions: Seq[Instruction])

  object CommandGroup {
    def apply(mask: String, instructions: Seq[String]): CommandGroup = {

      CommandGroup(
        mask = mask match {
          case maskRegex(_mask) =>
            _mask.reverse.zipWithIndex.filter(_._1 != 'X').map({
              case (bit, value) => Mask(bit = bit.asDigit, value = Math.pow(2, value).toLong)
            })
        },
        instructions = buildInstructions(instructions)
      )
    }
  }

  object FloatingMaskCommandGroup {
    def apply(mask: String, instructions: Seq[String]): FloatingMaskCommandGroup = {
      FloatingMaskCommandGroup(
        masks = mask match {
          case maskRegex(mask2) =>
            val exes = mask2.reverse.zipWithIndex.filter(_._1 == 'X')
            val ones = mask2.reverse.zipWithIndex.filter(_._1 == '1').map {
              case (bit, value) => Mask(bit = bit.asDigit, value = value)
            }
            val x_count = math.pow(2, exes.length).toInt
            for {
              i <- 1 to x_count
            } yield {
              val binary = (("0" * x_count) + i.toBinaryString) takeRight x_count
              FloatingMask(ones ++ exes.zipWithIndex.map(x => {
                Mask(bit = binary.reverse.charAt(x._2).asDigit, value = x._1._2)
              }))
            }
        },
        instructions = buildInstructions(instructions)
      )
    }
  }

}