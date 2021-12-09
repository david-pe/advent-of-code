use std::collections::HashMap;

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

pub fn solve(crabs: Vec<i32>) -> i64 {
    inner_solve(crabs, |cost| cost)
}

pub fn solve_2(crabs: Vec<i32>) -> i64 {
    inner_solve(crabs, cost_multiplied)
}

fn cost_multiplied(cost: i32) -> i32 {
    cost * (cost + 1) / 2
}

fn inner_solve(crabs: Vec<i32>, calculator: fn(i32) -> i32) -> i64 {
    let min = *crabs.iter().min().unwrap();
    let max = *crabs.iter().max().unwrap();
    let mut costs: HashMap<i32, i32> = HashMap::new();

    for x in min..max {
        costs.insert(
            x,
            crabs
                .iter()
                .fold(0, |cost, crab| cost + calculator((x - crab).abs())),
        );
    }

    *costs.iter().map(|(_, cost)| cost).min().unwrap() as i64
}
