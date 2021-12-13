use super::*;
use crate::assets::Asset;
use crate::input_as_str_iter;

fn example_data() -> String {
    String::from(
        "6,10
            0,14
            9,10
            0,3
            10,4
            4,11
            6,0
            6,12
            4,1
            0,13
            10,12
            3,4
            3,0
            8,4
            1,10
            2,14
            8,10
            9,0

            fold along y=7
            fold along x=5",
    )
}

#[test]
fn example_1() {
    let example = example_data();
    let lines: Vec<&str> = input_as_str_iter(&example).collect();
    assert_eq!(solve(lines), 17);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let lines: Vec<&str> = input_as_str_iter(&file_data).collect();

    assert_eq!(solve(lines), 770);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let lines: Vec<&str> = input_as_str_iter(&example).collect();
    let example_solution = String::from(
        "#####
#...#
#...#
#...#
#####",
    );
    assert_eq!(solve_2(lines), example_solution);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let lines: Vec<&str> = input_as_str_iter(&file_data).collect();
    let solution = String::from(
        "####.###..#..#.####.#....###..###..###.
#....#..#.#..#.#....#....#..#.#..#.#..#
###..#..#.#..#.###..#....#..#.###..#..#
#....###..#..#.#....#....###..#..#.###.
#....#....#..#.#....#....#....#..#.#.#.
####.#.....##..####.####.#....###..#..#",
    );
    assert_eq!(solve_2(lines), solution);
}
