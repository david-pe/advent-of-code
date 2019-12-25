import { solve as computer } from '../day-05/day-05';

function getOutput(input: number[], setting: number) {
  const inputStream = function*() {
    yield setting;
  };

  const output: number[] = [];
  for (const x of computer([...input], inputStream, true)) {
    output.push(x);
  }
  return output;
}

export function solve(input: number[]) {
  return getOutput(input, 1);
}

export function solve_2(input: number[]) {
  return getOutput(input, 2);
}
