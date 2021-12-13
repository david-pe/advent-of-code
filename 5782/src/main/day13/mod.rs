use itertools::Itertools;
use regex::Regex;
use std::collections::HashSet;

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

pub fn solve(lines: Vec<&str>) -> usize {
    let (points, instructions): (HashSet<(i32, i32)>, Vec<(char, i32)>) = lines
        .split(|line| line.len() == 0)
        .next_tuple()
        .map(|(p, i)| -> (HashSet<(i32, i32)>, Vec<(char, i32)>) {
            (
                p.iter()
                    .map(|cell| {
                        cell.split(",")
                            .next_tuple()
                            .map(|(a, b)| (a.parse::<i32>().unwrap(), b.parse::<i32>().unwrap()))
                            .unwrap()
                    })
                    .collect(),
                i.iter().map(extract_fold).collect(),
            )
        })
        .unwrap();

    let fold = instructions[0];

    match fold {
        ('x', seam) => {
            let mut stationary_part: HashSet<&(i32, i32)> =
                points.iter().filter(|(x, _)| x < &seam).collect();

            let moving_part: HashSet<(i32, i32)> = points
                .iter()
                .filter(|(x, _)| x > &seam)
                .map(|(x, y)| ((seam * 2) - x, *y))
                .collect();

            stationary_part.extend(moving_part.iter());

            stationary_part.len()
        }
        ('y', seam) => {
            let mut stationary_part: HashSet<&(i32, i32)> =
                points.iter().filter(|(_, y)| y < &seam).collect();

            let moving_part: HashSet<(i32, i32)> = points
                .iter()
                .filter(|(_, y)| y > &seam)
                .map(|(x, y)| (*x, (seam * 2) - y))
                .collect();

            stationary_part.extend(moving_part.iter());

            stationary_part.len()
        }
        _ => 0,
    }
}

pub fn solve_2(lines: Vec<&str>) -> String {
    let (points, instructions): (HashSet<(i32, i32)>, Vec<(char, i32)>) = lines
        .split(|line| line.len() == 0)
        .next_tuple()
        .map(|(p, i)| -> (HashSet<(i32, i32)>, Vec<(char, i32)>) {
            (
                p.iter()
                    .map(|cell| {
                        cell.split(",")
                            .next_tuple()
                            .map(|(a, b)| (a.parse::<i32>().unwrap(), b.parse::<i32>().unwrap()))
                            .unwrap()
                    })
                    .collect(),
                i.iter().map(extract_fold).collect(),
            )
        })
        .unwrap();

    let result: HashSet<(i32, i32)> = instructions.iter().fold(
        points.iter().map(|x| *x).collect(),
        |next_points, instruction| match instruction {
            ('x', seam) => {
                let mut stationary_part: HashSet<&(i32, i32)> =
                    next_points.iter().filter(|(x, _)| x < &seam).collect();

                let moving_part: HashSet<(i32, i32)> = next_points
                    .iter()
                    .filter(|(x, _)| x > &seam)
                    .map(|(x, y)| ((seam * 2) - x, *y))
                    .collect();

                stationary_part.extend(moving_part.iter());

                stationary_part.iter().map(|(x, y)| (*x, *y)).collect()
            }
            ('y', seam) => {
                let mut stationary_part: HashSet<&(i32, i32)> =
                    next_points.iter().filter(|(_, y)| y < &seam).collect();

                let moving_part: HashSet<(i32, i32)> = next_points
                    .iter()
                    .filter(|(_, y)| y > &seam)
                    .map(|(x, y)| (*x, (seam * 2) - y))
                    .collect();

                stationary_part.extend(moving_part.iter());

                stationary_part.iter().map(|(x, y)| (*x, *y)).collect()
            }
            _ => unreachable!(),
        },
    );

    let width = result
        .iter()
        .max_by(|(x1, _), (x2, _)| Ord::cmp(&x1, &x2))
        .unwrap()
        .0;
    let height = result
        .iter()
        .max_by(|(_, y1), (_, y2)| Ord::cmp(&y1, &y2))
        .unwrap()
        .1;

    let mut paper: Vec<Vec<char>> = Vec::new();

    for y in 0..=height {
        let mut line: Vec<char> = Vec::new();
        for x in 0..=width {
            if result.contains(&(x, y)) {
                line.push('#');
            } else {
                line.push('.');
            }
        }
        paper.push(line);
    }

    paper
        .iter()
        .map(|line| -> String { line.iter().collect() })
        .join("\n")
}

fn extract_fold(fold_str: &&str) -> (char, i32) {
    let rg = Regex::new(r"fold along ([xy])=(\d+)").unwrap();

    match rg.captures(fold_str) {
        Some(cap) => (
            cap.get(1)
                .map_or('x', |m| m.as_str().chars().next().unwrap()),
            cap.get(2).map_or(0, |m| m.as_str().parse::<i32>().unwrap()),
        ),
        None => unreachable!(),
    }
}
