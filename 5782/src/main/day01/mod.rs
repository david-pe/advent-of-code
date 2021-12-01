#[cfg(test)]
#[path = "tests.rs"]
mod tests;

use itertools::Itertools;

pub fn solve(input: impl Iterator<Item = i32>) -> i32 {
    input.tuple_windows().filter(|(x, y)| x < y).count() as i32
}

pub fn solve_2(input: impl Iterator<Item = i32>) -> i32 {
    solve(input.tuple_windows().map(|(x, y, z)| x + y + z))
}
