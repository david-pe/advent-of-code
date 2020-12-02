package com.advent.of.code.year2020.utils

object Utils {

  implicit class StringExt(val self: String) extends AnyVal {
    def cleanInput = self.stripMargin.split('\n').map(_.trim).filterNot(_ == "").toIterator;
  }

}
