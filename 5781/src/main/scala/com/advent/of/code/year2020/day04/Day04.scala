package com.advent.of.code.year2020.day04

import com.advent.of.code.year2020.utils.Utils._

// byr (Birth Year)
// iyr (Issue Year)
// eyr (Expiration Year)
// hgt (Height)
// hcl (Hair Color)
// ecl (Eye Color)
// pid (Passport ID)
// cid (Country ID)

object Day04 {

  val codes = Set("byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid",
    //    "cid"
  )

  def solve2(input: String): Long = {
    val passports = input.split("\n\n")
    val idRegex = raw"\d{9}"
    val heightRegex = raw"(\d*)(cm|in)".r
    val colorRegex = "#[0-9|a-f]{6}"
    val eyeColors = Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    passports.count(passport => {
      val fields = readPassport(passport)

      codes.forall {
        case "byr" => fields.get("byr").exists(valueBetween(1920, 2002))
        case "iyr" => fields.get("iyr").exists(valueBetween(2010, 2020))
        case "eyr" => fields.get("eyr").exists(valueBetween(2020, 2030))
        case "hgt" =>
          fields.get("hgt").exists {
            case heightRegex(value, "cm") => valueBetween(150, 193)(value)
            case heightRegex(value, "in") => valueBetween(59, 76)(value)
            case _ => false
          }
        case "hcl" => fields.get("hcl").exists(_.matches(colorRegex))
        case "ecl" => fields.get("ecl").exists(eyeColors.contains)
        case "pid" => fields.get("pid").exists(_.matches(idRegex))
      }
    })
  }

  private def readPassport(passport: String) = {
    val fieldRegex = raw"([a-z]{3}):(.*)".r
    passport.splitAndTrim("\\s+").map({
      case fieldRegex(code, value) => code -> value
    }).toMap
  }

  private def valueBetween(min: Int, max: Int) = (value: String) => value.toInt >= min && value.toInt <= max

  def solve1(input: String): Int = {
    val passports = getPassports(input)

    passports.count(passport => {
      val fields = readPassport(passport)
      codes.forall(code => fields.keys.toSeq.contains(code))
    })
  }

  private def getPassports(input: String) = input.split("\n\n")
}
