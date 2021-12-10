use super::*;
use crate::assets::Asset;
use crate::input_as_str_iter;

fn example_data() -> String {
    String::from(
        "2199943210
            3987894921
            9856789892
            8767896789
            9899965678",
    )
}

#[test]
fn example_1() {
    let example = example_data();
    let lines = input_as_str_iter(&example);
    assert_eq!(solve(lines.collect()), 15);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let lines = input_as_str_iter(&file_data.trim());
    assert_eq!(solve(lines.collect()), 580);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let lines = input_as_str_iter(&example);
    assert_eq!(solve_2(lines.collect()), 1134);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let lines = input_as_str_iter(&file_data.trim());
    assert_eq!(solve_2(lines.collect()), 856716);
}
