use std::collections::HashMap;

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

// thanks: shahata - https://github.com/shahata/adventofcode-solver/blob/master/src/2021/day06.js
pub fn solve(fish: Vec<i32>, days: i32) -> i64 {
    let mut current_fish: HashMap<i32, i64> =
        fish.iter()
            .fold(HashMap::new(), |mut map: HashMap<i32, i64>, f| {
                *map.entry(*f).or_insert(0) += 1;
                map
            });

    for _ in 0..days {
        let mut next_fish: HashMap<i32, i64> = HashMap::new();
        current_fish.iter().for_each(|(x, y)| match x {
            0 => {
                *next_fish.entry(6).or_insert(0) += y;
                *next_fish.entry(8).or_insert(0) += y;
            }
            num => {
                *next_fish.entry(num - 1).or_insert(0) += y;
            }
        });
        current_fish = next_fish.clone();
    }

    current_fish.iter().fold(0, |sum, (_, count)| sum + count)
}
