use rand::Rng;
use std::collections::HashSet;

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

pub struct Bingo {
    numbers: Vec<i32>,
    boards: Vec<Board>,
}

#[derive(Default)]
pub struct Board {
    id: i32,
    rows: Vec<HashSet<i32>>,
    cols: Vec<HashSet<i32>>,
}

impl Bingo {
    fn from_string(str: &str) -> Bingo {
        let mut lines = str.trim().split("\n\n").map(|s: &str| s.trim()).into_iter();

        let numbers: Vec<i32> = lines
            .next()
            .unwrap_or("")
            .split(",")
            .map(|s: &str| s.trim().parse::<i32>().unwrap())
            .collect();

        let boards = lines
            .map(|line| {
                let rows: Vec<Vec<i32>> = line
                    .trim()
                    .split("\n")
                    .map(|s: &str| s.trim())
                    .map(|row| -> Vec<i32> {
                        row.split_whitespace()
                            .map(|s: &str| s.parse::<i32>().unwrap())
                            .collect()
                    })
                    .collect();

                let row_size = rows[0].len();

                let cols: Vec<Vec<i32>> = (0..row_size)
                    .map(|col| -> Vec<i32> {
                        rows.iter()
                            .map(|row: &Vec<i32>| -> i32 { row[col] })
                            .collect()
                    })
                    .collect();

                let mut rng = rand::thread_rng();

                Board {
                    id: rng.gen(),
                    rows: rows
                        .iter()
                        .map(|row| -> HashSet<i32> { row.iter().map(|x| *x).into_iter().collect() })
                        .collect(),
                    cols: cols
                        .iter()
                        .map(|col| -> HashSet<i32> { col.iter().map(|x| *x).into_iter().collect() })
                        .collect(),
                }
            })
            .collect();

        Bingo { numbers, boards }
    }
}

pub fn solve(bingo: Bingo) -> i32 {
    let mut called_out: HashSet<i32> = HashSet::new();
    let mut last_called = 0;
    let mut winner: &Board = &Default::default();

    for num in bingo.numbers {
        last_called = num;
        called_out.insert(num);
        let found = bingo.boards.iter().find(|board| {
            board
                .rows
                .iter()
                .find(|row| called_out.is_superset(*row))
                .is_some()
                || board
                    .cols
                    .iter()
                    .find(|col| called_out.is_superset(*col))
                    .is_some()
        });
        if found.is_some() {
            winner = found.unwrap();
            break;
        }
    }

    let uncalled = winner.rows.iter().fold(0, |sum, row| {
        sum + row.iter().fold(0, |row_sum, num| {
            if called_out.contains(num) {
                row_sum
            } else {
                row_sum + num
            }
        })
    });

    uncalled * last_called
}

pub fn solve_2(bingo: Bingo) -> i32 {
    let mut called_out: HashSet<i32> = HashSet::new();
    let mut last_called = 0;
    let mut winners: HashSet<i32> = HashSet::new();
    let mut winner: &Board = &Default::default();
    let mut last_called_out: HashSet<i32> = HashSet::new();

    for num in bingo.numbers {
        called_out.insert(num);
        let found: Vec<&Board> = bingo
            .boards
            .iter()
            .filter(|&board| {
                !winners.contains(&board.id)
                    && (board
                        .rows
                        .iter()
                        .find(|row| called_out.is_superset(*row))
                        .is_some()
                        || board
                            .cols
                            .iter()
                            .find(|col| called_out.is_superset(*col))
                            .is_some())
            })
            .collect();
        if found.len() > 0 {
            winner = found.last().unwrap();
            last_called = num;
            found.iter().for_each(|f| {
                winners.insert(f.id);
            });
            last_called_out = called_out.clone();
            // break;
        }
    }

    let uncalled = winner.rows.iter().fold(0, |sum, row| {
        sum + row.iter().fold(0, |row_sum, num| {
            if last_called_out.contains(num) {
                row_sum
            } else {
                row_sum + num
            }
        })
    });

    uncalled * last_called
}
