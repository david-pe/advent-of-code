use super::*;
use crate::assets::Asset;
use crate::input_from_bits_as_int_iter;

fn example_data() -> String {
    String::from(
        "
              00100
              11110
              10110
              10111
              10101
              01111
              00111
              11100
              10000
              11001
              00010
              01010",
    )
}

#[test]
fn example_1() {
    let example = example_data();
    let input = input_from_bits_as_int_iter(&example);
    assert_eq!(solve(input, 5), 198);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let input = input_from_bits_as_int_iter(&file_data);
    assert_eq!(solve(input, "111000010001".len() as u32), 852500);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let input = input_from_bits_as_int_iter(&example);

    assert_eq!(solve_2(input, 5), 230);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let input = input_from_bits_as_int_iter(&file_data);

    assert_eq!(solve_2(input, "111000010001".len() as u32), 1007985);
}
