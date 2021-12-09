use super::*;
use crate::assets::Asset;
use crate::split_as_int_iter;

fn example_data() -> String {
    String::from("16,1,2,0,4,2,7,1,2,14")
}

#[test]
fn example_1() {
    let example = example_data();
    let crabs = split_as_int_iter(&example);
    assert_eq!(solve(crabs.collect()), 37);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let crabs = split_as_int_iter(&file_data);
    assert_eq!(solve(crabs.collect()), 340056);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let crab = split_as_int_iter(&example);
    assert_eq!(solve_2(crab.collect()), 168);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let crabs = split_as_int_iter(&file_data);
    assert_eq!(solve_2(crabs.collect()), 96592275);
}
