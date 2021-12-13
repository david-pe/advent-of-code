use std::collections::HashMap;

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

struct Octopus {
    value: i32,
    did_flash: bool,
}

pub fn solve(octopuses: Vec<Vec<i32>>) -> usize {
    let mut state: HashMap<(i32, i32), Octopus> = HashMap::new();

    for x in 0..10 {
        for y in 0..10 {
            state.insert(
                (x, y),
                Octopus {
                    value: octopuses[x as usize][y as usize],
                    did_flash: false,
                },
            );
        }
    }

    let mut flash_count_total = 0;
    for _ in 0..100 {
        for x in 0..10 {
            for y in 0..10 {
                let address = (x, y);
                state.entry(address).and_modify(|n| n.value += 1);
            }
        }

        loop {
            let mut flash_count = 0;
            for x in 0..10 {
                for y in 0..10 {
                    let address = (x, y);
                    let octopus = state.get(&address).unwrap();
                    if octopus.value > 9 && !octopus.did_flash {
                        flash_count += 1;
                        state.entry(address).and_modify(|n| n.did_flash = true);
                        let neighbor_addresses = neighbor_addresses(address);
                        for neighbor_address in neighbor_addresses {
                            state
                                .entry(neighbor_address)
                                .and_modify(|neighbor| neighbor.value += 1);
                        }
                    }
                }
            }

            flash_count_total += flash_count;
            if flash_count == 0 {
                for x in 0..10 {
                    for y in 0..10 {
                        let address = (x, y);
                        let octopus = state.get(&address).unwrap();
                        if octopus.value > 9 {
                            state.entry(address).and_modify(|n| {
                                n.value = 0;
                                n.did_flash = false;
                            });
                        }
                    }
                }
                break;
            }
        }
    }

    flash_count_total
}

pub fn solve_2(octopuses: Vec<Vec<i32>>) -> usize {
    let mut state: HashMap<(i32, i32), Octopus> = HashMap::new();

    for x in 0..10 {
        for y in 0..10 {
            state.insert(
                (x, y),
                Octopus {
                    value: octopuses[x as usize][y as usize],
                    did_flash: false,
                },
            );
        }
    }

    let mut steps = 0;
    'solution: loop {
        steps += 1;
        for x in 0..10 {
            for y in 0..10 {
                let address = (x, y);
                state.entry(address).and_modify(|n| n.value += 1);
            }
        }

        loop {
            let mut flash_count = 0;
            for x in 0..10 {
                for y in 0..10 {
                    let address = (x, y);
                    let octopus = state.get(&address).unwrap();
                    if octopus.value > 9 && !octopus.did_flash {
                        flash_count += 1;
                        state.entry(address).and_modify(|n| n.did_flash = true);
                        let neighbor_addresses = neighbor_addresses(address);
                        for neighbor_address in neighbor_addresses {
                            state
                                .entry(neighbor_address)
                                .and_modify(|neighbor| neighbor.value += 1);
                        }
                    }
                }
            }

            if flash_count == 0 {
                if state.iter().all(|(_, octopus)| octopus.did_flash) {
                    break 'solution;
                }
                for x in 0..10 {
                    for y in 0..10 {
                        let address = (x, y);
                        let octopus = state.get(&address).unwrap();
                        if octopus.value > 9 {
                            state.entry(address).and_modify(|n| {
                                n.value = 0;
                                n.did_flash = false;
                            });
                        }
                    }
                }
                break;
            }
        }
    }

    steps
}

fn neighbor_addresses((x, y): (i32, i32)) -> Vec<(i32, i32)> {
    [
        (x, y - 1),
        (x - 1, y - 1),
        (x - 1, y + 1),
        (x + 1, y - 1),
        (x + 1, y + 1),
        (x - 1, y),
        (x + 1, y),
        (x, y + 1),
    ]
    .iter()
    .filter(|(x, y)| x >= &0 && y >= &0 && x < &10 && y < &10)
    .map(|n| *n)
    .collect()
}
