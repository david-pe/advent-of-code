#[cfg(test)]
#[path = "tests.rs"]
mod tests;

use std::collections::{HashMap, HashSet};

pub fn solve(input: impl Iterator<Item = i32>, num_size: u32) -> i32 {
    let mut map: HashMap<i32, i32> = HashMap::new();

    let bit_is_on = |a: i32, b: i32| if a & b == 0 { 0 } else { 1 };

    let mut size = 0;

    input.for_each(|num| {
        size += 1;
        for x in 0..num_size {
            let p = 2_i32.pow(x);
            *map.entry(p).or_insert(0) += bit_is_on(num, p);
        }
    });

    let mut gamma = 0;
    let mut epsi = 0;

    for x in 0..num_size {
        let p = 2_i32.pow(x);

        let y = map.get(&p).unwrap_or(&0);

        gamma += if y > &(size / 2) { p } else { 0 };
        epsi += if y <= &(size / 2) { p } else { 0 };
    }

    gamma * epsi
}

pub fn solve_2(input: impl Iterator<Item = i32>, num_size: u32) -> i32 {
    let set1: HashSet<i32> = input.collect();
    let set2 = set1.clone();

    let mut next_most_common = most_common_at(set1, 2_i32.pow(num_size - 1));
    let mut next_leest_common = least_common_at(set2, 2_i32.pow(num_size - 1));

    let mut pos = num_size - 1;
    let mut pos1 = num_size - 1;

    while pos > 0 {
        pos = pos - 1;
        if next_most_common.len() == 1 {
            break;
        };
        next_most_common = most_common_at(next_most_common, 2_i32.pow(pos));
    }

    while pos1 > 0 {
        pos1 = pos1 - 1;
        if next_leest_common.len() == 1 {
            break;
        };
        next_leest_common = least_common_at(next_leest_common, 2_i32.pow(pos1));
    }

    next_most_common.iter().take(1).next().unwrap_or(&0)
        * next_leest_common.iter().take(1).next().unwrap_or(&0)
}

fn most_common_at(input: HashSet<i32>, pos: i32) -> HashSet<i32> {
    let bit_is_on = |a: i32, b: i32| if a & b == 0 { 0 } else { 1 };

    let mut bit_on: HashSet<i32> = HashSet::new();
    let mut bit_off: HashSet<i32> = HashSet::new();

    input.iter().for_each(|num| {
        if bit_is_on(*num, pos) == 1 {
            bit_on.insert(*num);
        } else {
            bit_off.insert(*num);
        }
    });

    if bit_on.len() >= bit_off.len() {
        bit_on
    } else {
        bit_off
    }
}

fn least_common_at(input: HashSet<i32>, pos: i32) -> HashSet<i32> {
    let bit_is_on = |a: i32, b: i32| if a & b == 0 { 0 } else { 1 };

    let mut bit_on: HashSet<i32> = HashSet::new();
    let mut bit_off: HashSet<i32> = HashSet::new();

    input.iter().for_each(|num| {
        if bit_is_on(*num, pos) == 1 {
            bit_on.insert(*num);
        } else {
            bit_off.insert(*num);
        }
    });

    if bit_on.len() < bit_off.len() {
        bit_on
    } else {
        bit_off
    }
}
