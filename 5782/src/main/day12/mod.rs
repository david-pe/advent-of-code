use std::collections::{HashMap, HashSet};

#[cfg(test)]
#[path = "tests.rs"]
mod tests;

pub fn solve(connections: Vec<&str>) -> usize {
    let caves: HashSet<&str> = connections
        .iter()
        .map(|connection| connection.split("-"))
        .flatten()
        .collect();

    let mut links: HashMap<&str, Vec<&str>> = HashMap::new();

    for cave in &caves {
        let neighbors = connections
            .iter()
            .filter(|connection| {
                connection.contains(&format!("-{}", cave))
                    || connection.contains(&format!("{}-", cave))
            })
            .map(|connection| connection.split("-"))
            .flatten()
            .filter(|x| x != cave)
            .collect();
        links.insert(cave, neighbors);
    }

    let paths: Vec<Vec<&str>> = vec![vec!["start"]];

    fn build_paths<'a>(
        cave: &'a str,
        visited: HashSet<&str>,
        inner_paths: Vec<Vec<&'a str>>,
        links: &'a HashMap<&str, Vec<&str>>,
    ) -> Vec<Vec<&'a str>> {
        let mut neighbors = links
            .get(cave)
            .unwrap()
            .iter()
            .filter(|id| String::from(**id) != "start");

        let mut current_visited: HashSet<&str> = visited.iter().clone().map(|x| *x).collect();

        if cave.to_lowercase() == cave {
            current_visited.insert(cave);
        }

        let mut next_collected_paths: Vec<Vec<&str>> = Vec::new();

        while let Some(neighbor) = neighbors.next() {
            if visited.iter().find(|a| a == &neighbor).is_some() {
                continue;
            }
            let mut next_paths: Vec<Vec<&str>> = inner_paths
                .iter()
                .map(|path| {
                    let mut new_path = path.clone();
                    new_path.push(neighbor);
                    new_path
                })
                .collect();

            if *neighbor == "end" {
                next_collected_paths.append(&mut next_paths);
                continue;
            }

            let next_current_visited: HashSet<&str> = current_visited.iter().map(|x| *x).collect();

            let mut collected_paths: Vec<Vec<&str>> =
                build_paths(neighbor, next_current_visited, next_paths, links)
                    .iter()
                    .map(|x| x.iter().map(|x| *x).collect())
                    .collect();

            next_collected_paths.append(&mut collected_paths);
        }

        next_collected_paths
            .iter()
            .map(|x| x.iter().map(|x| *x).collect())
            .clone()
            .collect()
    }

    let visited = HashSet::new();

    let all_paths = build_paths("start", visited, paths, &links);

    all_paths.len()
}

pub fn solve_2(connections: Vec<&str>) -> usize {
    let caves: HashSet<&str> = connections
        .iter()
        .map(|connection| connection.split("-"))
        .flatten()
        .collect();

    let mut links: HashMap<&str, Vec<&str>> = HashMap::new();

    for cave in &caves {
        let neighbors = connections
            .iter()
            .filter(|connection| {
                connection.contains(&format!("-{}", cave))
                    || connection.contains(&format!("{}-", cave))
            })
            .map(|connection| connection.split("-"))
            .flatten()
            .filter(|x| x != cave)
            .collect();
        links.insert(cave, neighbors);
    }

    let paths: Vec<Vec<&str>> = vec![vec!["start"]];

    fn build_paths<'a>(
        cave: &'a str,
        visited: HashSet<&str>,
        inner_paths: Vec<Vec<&'a str>>,
        links: &'a HashMap<&str, Vec<&str>>,
        small_cave_visited_twice: bool,
    ) -> Vec<Vec<&'a str>> {
        let mut neighbors = links
            .get(cave)
            .unwrap()
            .iter()
            .filter(|id| String::from(**id) != "start");

        let mut current_visited: HashSet<&str> = visited.iter().clone().map(|x| *x).collect();

        if cave.to_lowercase() == cave {
            current_visited.insert(cave);
        }

        let mut next_collected_paths: Vec<Vec<&str>> = Vec::new();

        while let Some(neighbor) = neighbors.next() {
            let mut next_small_cave_visited_twice = small_cave_visited_twice;
            if visited.iter().find(|a| a == &neighbor).is_some() {
                if small_cave_visited_twice {
                    continue;
                }
                next_small_cave_visited_twice = true;
            }
            let mut next_paths: Vec<Vec<&str>> = inner_paths
                .iter()
                .map(|path| {
                    let mut new_path = path.clone();
                    new_path.push(neighbor);
                    new_path
                })
                .collect();

            if *neighbor == "end" {
                next_collected_paths.append(&mut next_paths);
                continue;
            }

            let next_current_visited: HashSet<&str> = current_visited.iter().map(|x| *x).collect();

            let mut collected_paths: Vec<Vec<&str>> = build_paths(
                neighbor,
                next_current_visited,
                next_paths,
                links,
                next_small_cave_visited_twice,
            )
            .iter()
            .map(|x| x.iter().map(|x| *x).collect())
            .collect();

            next_collected_paths.append(&mut collected_paths);
        }

        next_collected_paths
            .iter()
            .map(|x| x.iter().map(|x| *x).collect())
            .clone()
            .collect()
    }

    let visited = HashSet::new();

    let all_paths = build_paths("start", visited, paths, &links, false);

    all_paths.len()
}
