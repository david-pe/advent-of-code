use itertools::Itertools;
use std::collections::HashSet;

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

pub fn solve(lines: Vec<&str>) -> usize {
    let _1_4_7_8 = vec![2, 4, 3, 7];

    lines
        .iter()
        .map(|line| {
            let (_, code): (&str, &str) = line.trim().split(" | ").next_tuple().unwrap();
            code.split(" ")
        })
        .flatten()
        .map(|wat| (wat.len(), wat))
        .into_group_map()
        .iter()
        .fold(0, |sum, (curr, occur)| {
            if _1_4_7_8.contains(curr) {
                sum + occur.len()
            } else {
                sum
            }
        })
}

pub fn solve_2(lines: Vec<&str>) -> i32 {
    lines
        .iter()
        .map(|line| {
            let (hints_str, code_str): (&str, &str) =
                line.trim().split(" | ").next_tuple().unwrap();

            let hints: Vec<String> = hints_str
                .split(" ")
                .map(|s| s.chars().sorted().join(""))
                .collect();

            let codes: Vec<String> = code_str
                .split(" ")
                .map(|s| s.chars().sorted().join(""))
                .collect();

            let one: HashSet<char> = hints
                .iter()
                .find(|x| x.len() == 2)
                .unwrap()
                .chars()
                .collect();
            let four: HashSet<char> = hints
                .iter()
                .find(|x| x.len() == 4)
                .unwrap()
                .chars()
                .collect();
            let seven: HashSet<char> = hints
                .iter()
                .find(|x| x.len() == 3)
                .unwrap()
                .chars()
                .collect();
            let eight: HashSet<char> = hints
                .iter()
                .find(|x| x.len() == 7)
                .unwrap()
                .chars()
                .collect();
            let nine: HashSet<char> = hints
                .iter()
                .find(|x| x.len() == 6 && four.is_subset(&x.chars().collect()))
                .unwrap()
                .chars()
                .collect();
            let six: HashSet<char> = hints
                .iter()
                .find(|x| x.len() == 6 && !one.is_subset(&x.chars().collect()))
                .unwrap()
                .chars()
                .collect();
            let zero: HashSet<char> = hints
                .iter()
                .find(|x| {
                    x.len() == 6 && !six.eq(&x.chars().collect()) && !nine.eq(&x.chars().collect())
                })
                .unwrap()
                .chars()
                .collect();
            let five: HashSet<char> = hints
                .iter()
                .find(|x| x.len() == 5 && six.is_superset(&x.chars().collect()))
                .unwrap()
                .chars()
                .collect();
            let three: HashSet<char> = hints
                .iter()
                .find(|x| x.len() == 5 && seven.is_subset(&x.chars().collect()))
                .unwrap()
                .chars()
                .collect();
            let two: HashSet<char> = hints
                .iter()
                .find(|x| {
                    x.len() == 5
                        && !three.eq(&x.chars().collect())
                        && !five.eq(&x.chars().collect())
                })
                .unwrap()
                .chars()
                .collect();

            let num_map = [
                (zero, 0),
                (one, 1),
                (two, 2),
                (three, 3),
                (four, 4),
                (five, 5),
                (six, 6),
                (seven, 7),
                (eight, 8),
                (nine, 9),
            ];

            codes
                .iter()
                .map(|word| {
                    num_map
                        .iter()
                        .find(|(key, _)| key.eq(&word.chars().collect()))
                        .unwrap()
                })
                .map(|(_, v)| v.to_string())
                .join("")
                .parse::<i32>()
                .unwrap()
        })
        .fold(0, |sum, curr| sum + curr)
}
