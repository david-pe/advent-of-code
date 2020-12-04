package com.advent.of.code.year2020.utils

object Utils {

  implicit class StringExt(val self: String) extends AnyVal {
    def cleanInput: Iterator[String] = splitAndTrim()

    def splitAndTrim(newLine: String = "\n"): Iterator[String] =
      self.stripMargin.split(newLine).map(_.trim).filterNot(_ == "").toIterator
  }

}
