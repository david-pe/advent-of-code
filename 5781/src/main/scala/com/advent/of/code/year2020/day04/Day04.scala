package com.advent.of.code.year2020.day04

import com.advent.of.code.year2020.utils.Utils._

import scala.util.matching.Regex

object Day04 {

  val codes = Set(
    "byr", // byr (Birth Year)
    "iyr", // iyr (Issue Year)
    "eyr", // eyr (Expiration Year)
    "hgt", // hgt (Height)
    "hcl", // hcl (Hair Color)
    "ecl", // ecl (Eye Color)
    "pid", // pid (Passport ID)
    //"cid" // cid (Country ID)
  )
  val idRegex = raw"\d{9}"
  val heightRegex: Regex = raw"(\d*)(cm|in)".r
  val colorRegex = "#[0-9|a-f]{6}"
  val eyeColors = Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

  val validators: Map[String, Option[String] => Boolean] = Seq(
    "byr" -> ((value: Option[String]) => value.exists(valueBetween(1920, 2002))),
    "iyr" -> ((value: Option[String]) => value.exists(valueBetween(2010, 2020))),
    "eyr" -> ((value: Option[String]) => value.exists(valueBetween(2020, 2030))),
    "hgt" -> ((value: Option[String]) => value.exists {
      case heightRegex(value, "cm") => valueBetween(150, 193)(value)
      case heightRegex(value, "in") => valueBetween(59, 76)(value)
      case _ => false
    }),
    "hcl" -> ((value: Option[String]) => value.exists(_.matches(colorRegex))),
    "ecl" -> ((value: Option[String]) => value.exists(eyeColors.contains)),
    "pid" -> ((value: Option[String]) => value.exists(_.matches(idRegex)))
  ).toMap

  def solve2(input: String): Long = {
    val passports = getPassports(input)

    passports.count {
      passport => {
        val fields = readPassport(passport)
        validators.forall {
          case (code, validator) => validator(fields.get(code))
        }
      }
    }
  }

  private def readPassport(passport: String) = {
    val fieldRegex = raw"([a-z]{3}):(.*)".r
    passport.splitAndTrim("\\s+").map({
      case fieldRegex(code, value) => code -> value
    }).toMap
  }

  private def valueBetween(min: Int, max: Int) =
    (value: String) => value.toInt >= min && value.toInt <= max

  def solve1(input: String): Int = {
    val passports = getPassports(input)

    passports.count(passport => {
      val fields = readPassport(passport)
      codes.forall(code => fields.contains(code))
    })
  }

  private def getPassports(input: String) = input.split("\n\n")
}
