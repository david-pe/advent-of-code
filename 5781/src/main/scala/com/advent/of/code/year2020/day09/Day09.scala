package com.advent.of.code.year2020.day09

object Day09 {

  def solve1(input: Seq[Long], preamble: Int = 25): Long =
    input.sliding(preamble + 1).find(section =>
      !section.init.exists(first =>
        section.exists(second =>
          section.last == first + second))).head.last

  def solve2(input: Seq[Long], target: Long): Long = {
    (for {
      size <- Iterator.from(2).toSeq
      found <- input.sliding(size)
        .find(_.sum == target)
        .map(x => x.min + x.max)
    } yield found).head
  }
}
