#[cfg(test)]
#[path = "tests.rs"]
mod tests;

use itertools::Itertools;

fn parse_line(line: &str) -> (&str, i32) {
    let (instruction, value) = line.split(" ").next_tuple().unwrap();
    (instruction, value.parse::<i32>().unwrap())
}

pub fn solve<'a>(input: impl Iterator<Item = &'a str>) -> i32 {
    let (final_pos, final_depth) = input.map(parse_line).fold(
        (0, 0),
        |(pos, depth), (instruction, value)| match instruction {
            "forward" => (pos + value, depth),
            "down" => (pos, depth + value),
            "up" => (pos, depth - value),
            _ => (pos, depth),
        },
    );
    final_pos * final_depth
}

pub fn solve_2<'a>(input: impl Iterator<Item = &'a str>) -> i32 {
    let (final_pos, final_depth, _) =
        input
            .map(parse_line)
            .fold(
                (0, 0, 0),
                |(pos, depth, aim), (instruction, value)| match instruction {
                    "forward" => (pos + value, depth + aim * value, aim),
                    "down" => (pos, depth, aim + value),
                    "up" => (pos, depth, aim - value),
                    _ => (pos, depth, aim),
                },
            );
    final_pos * final_depth
}
