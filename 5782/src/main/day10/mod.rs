use itertools::Itertools;
use std::collections::HashMap;

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

pub fn solve(lines: Vec<&str>) -> usize {
    let mut failing_chars: Vec<char> = Vec::new();

    let openers = vec!['(', '[', '{', '<'];
    let closers = vec![')', ']', '}', '>'];
    let pairs: HashMap<&char, char> = closers.iter().zip(openers).collect();

    for line in lines {
        let mut stack: Vec<char> = Vec::new();
        for char in line.chars() {
            match char {
                opener @ ('(' | '[' | '{' | '<') => {
                    stack.push(opener);
                }
                closer @ (')' | ']' | '}' | '>') => {
                    let expected = pairs.get(&closer).unwrap();
                    if expected == stack.last().unwrap() {
                        stack.pop();
                    } else {
                        failing_chars.push(closer);
                        break;
                    }
                }
                _ => continue,
            };
        }
    }

    failing_chars
        .iter()
        .map(|c| match c {
            ')' => 3,
            ']' => 57,
            '}' => 1197,
            '>' => 25137,
            _ => 0,
        })
        .sum()
}

pub fn solve_2(lines: Vec<&str>) -> usize {
    let openers = vec!['(', '[', '{', '<'];
    let closers = vec![')', ']', '}', '>'];

    let openers_1 = vec!['(', '[', '{', '<'];
    let closers_1 = vec![')', ']', '}', '>'];

    let pairs: HashMap<&char, char> = closers.iter().zip(openers).collect();
    let reverse_pairs: HashMap<&char, char> = openers_1.iter().zip(closers_1).collect();

    let incomplete_lines = lines.iter().filter(|line| {
        let mut is_corrupt = false;
        let mut stack: Vec<char> = Vec::new();
        for char in line.chars() {
            match char {
                opener @ ('(' | '[' | '{' | '<') => {
                    stack.push(opener);
                }
                closer @ (')' | ']' | '}' | '>') => {
                    let expected = pairs.get(&closer).unwrap();
                    if expected == stack.last().unwrap() {
                        stack.pop();
                    } else {
                        is_corrupt = true;
                        break;
                    }
                }
                _ => continue,
            };
        }
        !is_corrupt
    });

    let mut completions: Vec<Vec<char>> = Vec::new();

    for line in incomplete_lines {
        let mut stack: Vec<char> = Vec::new();
        for char in line.chars() {
            match char {
                opener @ ('(' | '[' | '{' | '<') => {
                    stack.push(opener);
                }
                ')' | ']' | '}' | '>' => {
                    stack.pop();
                }
                _ => continue,
            };
        }

        completions.push(
            stack
                .iter()
                .map(|opener| *reverse_pairs.get(opener).unwrap())
                .rev()
                .collect(),
        );
    }

    let completions_sorted: Vec<usize> = completions
        .iter()
        .map(|v| {
            v.iter().fold(0, |sum, c| {
                (sum * 5)
                    + match c {
                        ')' => 1,
                        ']' => 2,
                        '}' => 3,
                        '>' => 4,
                        _ => 0,
                    }
            })
        })
        .sorted_by(|a, b| Ord::cmp(&b, &a))
        .collect();

    completions_sorted[(completions_sorted.len() / 2)]
}
