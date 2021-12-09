use super::*;
use crate::assets::Asset;
use crate::split_as_int_iter;

fn example_data() -> String {
    String::from(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
            edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
            fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
            fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
            aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
            fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
            dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
            bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
            egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
            gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce",
    )
}

#[test]
fn example_1() {
    let example = example_data();
    let lines = example.split("\n");
    assert_eq!(solve(lines.collect()), 26);
}

#[test]
fn solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let lines = file_data.trim().split("\n");
    assert_eq!(solve(lines.collect()), 409);
}

#[test]
fn part_2_example_1() {
    let example = example_data();
    let lines = example.split("\n");
    assert_eq!(solve_2(lines.collect()), 61229);
}

#[test]
fn part_2_solve_it() {
    let file_data = Asset::read_input_file(module_path!());
    let lines = file_data.trim().split("\n");
    assert_eq!(solve_2(lines.collect()), 1024649);
}
