use super::*;
use crate::assets::Asset;
use crate::input_as_str_iter;

fn example_data() -> String {
    String::from(
        "5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526",
    )
}

#[test]
fn example_1() {
    let example = example_data();
    let octopuses: Vec<Vec<i32>> = input_as_str_iter(&example)
        .map(|line| {
            line.chars()
                .map(|c| c.to_digit(10).unwrap() as i32)
                .collect()
        })
        .collect();
    assert_eq!(solve(octopuses), 1656);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let octopuses: Vec<Vec<i32>> = input_as_str_iter(&file_data)
        .map(|line| {
            line.chars()
                .map(|c| c.to_digit(10).unwrap() as i32)
                .collect()
        })
        .collect();
    assert_eq!(solve(octopuses), 1608);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let octopuses: Vec<Vec<i32>> = input_as_str_iter(&example)
        .map(|line| {
            line.chars()
                .map(|c| c.to_digit(10).unwrap() as i32)
                .collect()
        })
        .collect();
    assert_eq!(solve_2(octopuses), 195);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let octopuses: Vec<Vec<i32>> = input_as_str_iter(&file_data)
        .map(|line| {
            line.chars()
                .map(|c| c.to_digit(10).unwrap() as i32)
                .collect()
        })
        .collect();
    assert_eq!(solve_2(octopuses), 214);
}
