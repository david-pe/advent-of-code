package com.advent.of.code.year2020.day21

import scala.collection.mutable

object Day21 {

  def solve1(input: Array[String]): Long = {
    val allergens = allergenDictionary(input)

    val allIngredients = input.flatMap {
      case line => line.split("contains ") match {
        case Array(ingredients, _) => ingredients.replace("(", "").split(' ').map(_.trim)
      }
    }

    val withAllergy = allergens.values.flatten.toArray.distinct

    allIngredients.count(x => !withAllergy.contains(x))
  }

  def solve2(input: Array[String]): String = {
    val allergens = allergenDictionary(input)

    val withAllergy = allergens.values.flatten.toArray.distinct
    val ingredientsWithAllergy = mutable.Map[String, String]()

    while (ingredientsWithAllergy.size < withAllergy.length) {

      allergens.foreach {
        case (allergen, ingredients) =>
          if (ingredients.filterNot(ingredient => ingredientsWithAllergy.keys.exists(b => ingredient == b)).length == 1) {
            ingredientsWithAllergy += ingredients.filterNot {
              ingredient => ingredientsWithAllergy.keys.exists(b => ingredient == b)
            }.head -> allergen
          }
      }
    }

    ingredientsWithAllergy.toArray.sortBy(_._2).map(_._1).mkString(",")
  }

  private def allergenDictionary(input: Array[String]) = {
    val dictionary = input.flatMap {
      case line => line.split("contains ") match {
        case Array(ingredients, allergens) => allergens.replace(")", "").split(',').map(_.trim).map {
          case allergen => allergen -> ingredients.replace("(", "").split(' ').map(_.trim)
        }
      }
    }
    dictionary.groupBy(_._1).map {
      case (key, arrays) => key -> arrays.map(_._2).reduce((a, b) => a.intersect(b))
    }
  }

}