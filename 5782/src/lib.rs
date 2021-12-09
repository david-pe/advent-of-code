mod main;

pub mod assets {
    use regex::Regex;
    use rust_embed::RustEmbed;

    #[derive(RustEmbed)]
    #[folder = "src/resources"]
    pub struct Asset;

    impl Asset {
        pub(crate) fn read_file(path: &str) -> String {
            let file = Asset::get(path).unwrap();
            String::from_utf8(file.data.to_vec()).unwrap()
        }

        pub(crate) fn read_input_file(path: &str) -> String {
            let re = Regex::new(r"::(day\d{2})::").unwrap();
            let real_path = re.captures(path).unwrap().get(1).unwrap().as_str();
            Asset::read_file(&format!("{}/input.txt", real_path))
        }
    }
}

fn input_as_int_iter(str: &str) -> impl Iterator<Item = i32> + '_ {
    str.trim()
        .split("\n")
        .map(|s: &str| s.trim())
        .map(|s: &str| s.parse::<i32>().unwrap())
}

fn split_as_int_iter(str: &str) -> impl Iterator<Item = i32> + '_ {
    str.trim()
        .split(",")
        .map(|s: &str| s.trim())
        .map(|s: &str| s.parse::<i32>().unwrap())
}

fn input_as_str_iter(str: &str) -> impl Iterator<Item = &str> + '_ {
    str.trim().split("\n").map(|s: &str| s.trim())
}

fn input_from_bits_as_int_iter(str: &str) -> impl Iterator<Item = i32> + '_ {
    str.trim()
        .split("\n")
        .map(|s: &str| s.trim())
        .map(|s: &str| i32::from_str_radix(s, 2).unwrap())
}
