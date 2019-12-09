import { solve, solve2 } from './day-01';
import inputLoader from '../../input-loader';

describe('Day 01', () => {
  describe('Part 1', () => {
    it('solves examples', () => {
      expect(solve(12)).toBe(2);
      expect(solve(14)).toBe(2);
      expect(solve(1969)).toBe(654);
      expect(solve(100756)).toBe(33583);
    });

    it('solves the input', () => {
      const input = inputLoader('./input.txt');
      const solution = input
        .split('\n')
        .filter(x => x)
        .reduce((acc, x) => acc + solve(parseInt(x, 10)), 0);

      expect(solution).toBe(3317659);
    });
  });

  describe('Part 2', () => {
    it('solves examples', () => {
      expect(solve2(14)).toBe(2);
      expect(solve2(1969)).toBe(966);
      expect(solve2(100756)).toBe(50346);
    });

    it('solves the input', () => {
      const input = inputLoader('./input.txt');
      const solution = input
        .split('\n')
        .filter(x => x)
        .reduce((acc, x) => acc + solve2(parseInt(x, 10)), 0);

      expect(solution).toBe(4973616);
    });
  });
});
