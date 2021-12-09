use super::*;
use crate::assets::Asset;
use crate::split_as_int_iter;

fn example_data() -> String {
    String::from("3,4,3,1,2")
}

#[test]
fn example_1() {
    let example = example_data();
    let fish = split_as_int_iter(&example);
    assert_eq!(solve(fish.collect(), 80), 5934);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let fish = split_as_int_iter(&file_data);
    assert_eq!(solve(fish.collect(), 80), 373378);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let fish = split_as_int_iter(&example);
    assert_eq!(solve(fish.collect(), 256), 26984457539);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let fish = split_as_int_iter(&file_data);
    assert_eq!(solve(fish.collect(), 256), 1682576647495);
}
