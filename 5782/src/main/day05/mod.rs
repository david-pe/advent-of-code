use itertools::{zip, Itertools};
use std::collections::{HashMap, HashSet};

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

#[derive(Eq, PartialEq, Hash)]
pub struct Line(Point, Point);

// A struct with two fields
#[derive(Eq, PartialEq, Hash, Copy, Clone, Debug)]
struct Point {
    x: i32,
    y: i32,
}

impl Line {
    fn from_string(str: &str) -> Line {
        let (a, b) = str
            .split(" -> ")
            .map(|p| Point::from_string(p))
            .next_tuple()
            .unwrap();

        Line(a, b)
    }
}

impl Point {
    fn from_string(str: &str) -> Point {
        let (x, y): (i32, i32) = str
            .split(",")
            .map(|s| s.trim().parse::<i32>().unwrap())
            .next_tuple()
            .unwrap();

        Point { x, y }
    }
}

pub fn solve(lines: HashSet<Line>) -> i32 {
    inner_solve(lines, false)
}

pub fn solve_2(lines: HashSet<Line>) -> i32 {
    inner_solve(lines, true)
}

fn inner_solve(lines: HashSet<Line>, do_diagonals: bool) -> i32 {
    lines
        .iter()
        .fold(HashMap::new(), |mut map: HashMap<Point, i32>, line| {
            let [x1, x2] = [line.0.x, line.1.x];
            let [y1, y2] = [line.0.y, line.1.y];

            let xs: Vec<i32> = if x2 < x1 {
                (x2..=x1).rev().collect()
            } else if x2 == x1 {
                vec![x1; ((y2 - y1).abs() + 1) as usize]
            } else {
                (x1..=x2).collect()
            };

            let ys: Vec<i32> = if y2 < y1 {
                (y2..=y1).rev().collect()
            } else if y2 == y1 {
                vec![y1; ((x2 - x1).abs() + 1) as usize]
            } else {
                (y1..=y2).collect()
            };

            let points: Vec<Point> = zip(xs, ys).map(|(x, y)| Point { x, y }).collect();
            points.iter().for_each(|point| {
                let not_diagonal = x1 == x2 || y1 == y2;
                if not_diagonal || do_diagonals {
                    *map.entry(*point).or_insert(0) += 1
                }
            });

            map
        })
        .iter()
        .filter(|(_, count)| **count > 1)
        .count() as i32
}
