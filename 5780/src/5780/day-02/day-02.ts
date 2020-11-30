export function solve(input: number[]) {
  for (let i = 0; i < input.length; i += 4) {
    if (input[i] === 99) {
      return input[0];
    }
    if (input[i] === 1) {
      input[input[i + 3]] = input[input[i + 2]] + input[input[i + 1]];
    } else if (input[i] === 2) {
      input[input[i + 3]] = input[input[i + 2]] * input[input[i + 1]];
    }
  }
}

export function solve2(input: number[]) {
  const [first, , , ...ints] = input;
  for (let i = 0; i < 100; i++) {
    for (let j = 0; j < 100; j++) {
      const solution = solve([first, i, j, ...ints]);
      if (solution === 19690720) {
        return 100 * i + j;
      }
    }
  }
}
