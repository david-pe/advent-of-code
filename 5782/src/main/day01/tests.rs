use super::*;
use crate::assets::Asset;
use crate::input_as_int_arr;

fn example_data() -> String {
    String::from(
        "
              199
              200
              208
              210
              200
              207
              240
              269
              260
              263",
    )
}

#[test]
fn example_1() {
    let example = example_data();
    let input = input_as_int_arr(&example);
    assert_eq!(solve(input), 7);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_file("day01/input.txt");
    let input = input_as_int_arr(&file_data);
    assert_eq!(solve(input), 1583);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let input = input_as_int_arr(&example);

    assert_eq!(solve_2(input), 5);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_file("day01/input.txt");
    let input = input_as_int_arr(&file_data);

    assert_eq!(solve_2(input), 1627);
}
