use super::*;
use crate::assets::Asset;
use crate::input_as_str_iter;

fn example_data_1() -> String {
    String::from(
        "start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end",
    )
}

fn example_data_2() -> String {
    String::from(
        "dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc",
    )
}

fn example_data_3() -> String {
    String::from(
        "fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW",
    )
}

#[test]
fn example_1() {
    let example = example_data_1();
    let connections: Vec<&str> = input_as_str_iter(&example).collect();
    assert_eq!(solve(connections), 10);
}

#[test]
fn example_2() {
    let example = example_data_2();
    let connections: Vec<&str> = input_as_str_iter(&example).collect();
    assert_eq!(solve(connections), 19);
}

#[test]
fn example_3() {
    let example = example_data_3();
    let connections: Vec<&str> = input_as_str_iter(&example).collect();
    assert_eq!(solve(connections), 226);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let connections: Vec<&str> = input_as_str_iter(&file_data).collect();

    assert_eq!(solve(connections), 5157);
}

#[test]
fn part_2_example_1() {
    let example = example_data_1();
    let connections: Vec<&str> = input_as_str_iter(&example).collect();
    assert_eq!(solve_2(connections), 36);
}

#[test]
fn part_2_example_2() {
    let example = example_data_2();
    let connections: Vec<&str> = input_as_str_iter(&example).collect();
    assert_eq!(solve_2(connections), 103);
}

#[test]
fn part_2_example_3() {
    let example = example_data_3();
    let connections: Vec<&str> = input_as_str_iter(&example).collect();
    assert_eq!(solve_2(connections), 3509);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let connections: Vec<&str> = input_as_str_iter(&file_data).collect();

    assert_eq!(solve_2(connections), 144309);
}
