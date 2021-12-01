mod main;

pub mod assets {
    use rust_embed::RustEmbed;

    #[derive(RustEmbed)]
    #[folder = "src/resources"]
    pub struct Asset;

    impl Asset {
        pub(crate) fn read_file(path: &str) -> String {
            let file = Asset::get(path).unwrap();
            String::from_utf8(file.data.to_vec()).unwrap()
        }
    }
}

fn input_as_int_arr(str: &str) -> impl Iterator<Item = i32> + '_ {
    str.trim()
        .split("\n")
        .map(|s: &str| s.trim())
        .map(|s: &str| s.parse::<i32>().unwrap())
}
