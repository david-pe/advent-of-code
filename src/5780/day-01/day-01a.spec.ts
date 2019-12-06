import { solve_a as solve } from './day-01';
import inputLoader from '../../input-loader';

describe('Day 01 - Part 2', () => {
  it('solves examples', () => {
    expect(solve(14)).toBe(2);
    expect(solve(1969)).toBe(966);
    expect(solve(100756)).toBe(50346);
  });

  it('solves the input', () => {
    const input = inputLoader('./input.txt');
    const solution = input
      .split('\n')
      .filter(x => x)
      .reduce((acc, x) => acc + solve(parseInt(x, 10)), 0);

    expect(solution).toBe(4973616);
  });
});
