use super::*;
use crate::assets::Asset;

fn example_data() -> String {
    String::from(
        "  0,9 -> 5,9
              8,0 -> 0,8
              9,4 -> 3,4
              2,2 -> 2,1
              7,0 -> 7,4
              6,4 -> 2,0
              0,9 -> 2,9
              3,4 -> 1,4
              0,0 -> 8,8
              5,5 -> 8,2",
    )
}

fn read_lines(str: String) -> HashSet<Line> {
    str.trim()
        .split("\n")
        .map(|line| Line::from_string(line))
        .collect()
}

#[test]
fn example_1() {
    let example = example_data();
    let lines = read_lines(example);
    assert_eq!(solve(lines), 5);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let lines = read_lines(file_data);
    assert_eq!(solve(lines), 5690);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let lines: HashSet<Line> = read_lines(example);
    assert_eq!(solve_2(lines), 12);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let lines = read_lines(file_data);
    assert_eq!(solve_2(lines), 17741);
}
