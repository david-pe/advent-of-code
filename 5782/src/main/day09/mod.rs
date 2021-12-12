use itertools::Itertools;
use std::borrow::BorrowMut;
use std::collections::HashSet;

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

pub fn solve(lines: Vec<&str>) -> usize {
    let rows: Vec<Vec<char>> = lines.iter().map(|line| line.chars().collect()).collect();

    let height: i32 = rows.len() as i32;
    let width: i32 = rows[0].len() as i32;

    let low_points = collect_low_points(&rows, &height, &width);

    low_points.iter().map(|(_, height)| height + 1).sum()
}

pub fn solve_2(lines: Vec<&str>) -> usize {
    let rows: Vec<Vec<char>> = lines.iter().map(|line| line.chars().collect()).collect();

    let height: i32 = rows.len() as i32;
    let width: i32 = rows[0].len() as i32;

    let low_points = collect_low_points(&rows, &height, &width);

    let basins = low_points.iter().map(|lp| {
        let mut basin: HashSet<(i32, i32)> = HashSet::new();

        collect_basin(lp.0, &rows, basin.borrow_mut(), &height, &width);

        basin
    });

    basins
        .sorted_by(|a, b| Ord::cmp(&b.len(), &a.len()))
        .take(3)
        .map(|x| x.len())
        .product()
}

fn collect_basin(
    point: (i32, i32),
    rows: &Vec<Vec<char>>,
    basin: &mut HashSet<(i32, i32)>,
    height: &i32,
    width: &i32,
) {
    basin.insert(point);

    let neighbors: Vec<(i32, i32)> = neighbor_addresses(point.0, point.1)
        .iter()
        .filter(|(y1, x1)| x1 >= &0 && y1 >= &0 && y1 < &height && x1 < &width)
        .filter(|neighbor| {
            !basin.contains(neighbor)
                && rows[neighbor.0 as usize][neighbor.1 as usize]
                    .to_digit(10)
                    .unwrap()
                    != 9
        })
        .map(|x| *x)
        .collect();

    for neighbor in neighbors {
        collect_basin(neighbor, &rows, basin, height, width)
    }
}

fn collect_low_points(
    rows: &Vec<Vec<char>>,
    height: &i32,
    width: &i32,
) -> Vec<((i32, i32), usize)> {
    let mut low_points: Vec<((i32, i32), usize)> = Vec::new();

    for y in 0..*height {
        for x in 0..*width {
            let value: usize = rows[y as usize][x as usize].to_digit(10).unwrap() as usize;

            if neighbor_addresses(y, x)
                .iter()
                .filter(|(y1, x1)| x1 >= &0 && y1 >= &0 && y1 < &height && x1 < &width)
                .all(|(y1, x1)| {
                    rows[*y1 as usize][*x1 as usize].to_digit(10).unwrap() as usize > value
                })
            {
                low_points.push(((y, x), value));
            }
        }
    }
    low_points
}

fn neighbor_addresses(y: i32, x: i32) -> [(i32, i32); 4] {
    [(y - 1, x), (y, x - 1), (y, x + 1), (y + 1, x)]
}
