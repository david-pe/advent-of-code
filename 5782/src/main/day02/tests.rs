use super::*;
use crate::assets::Asset;
use crate::input_as_str_iter;

fn example_data() -> String {
    String::from(
        "
              forward 5
              down 5
              forward 8
              up 3
              down 8
              forward 2",
    )
}

#[test]
fn example_1() {
    let example = example_data();
    let input = input_as_str_iter(&example);
    assert_eq!(solve(input), 150);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let input = input_as_str_iter(&file_data);
    assert_eq!(solve(input), 2272262);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let input = input_as_str_iter(&example);

    assert_eq!(solve_2(input), 900);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let input = input_as_str_iter(&file_data);

    assert_eq!(solve_2(input), 2134882034);
}
