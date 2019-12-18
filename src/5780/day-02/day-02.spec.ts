import { solve, solve2 } from './day-02';
import inputLoader from '../../input-loader';

const split = input => input.split(',').map(x => parseInt(x, 10));

describe('Day 02', () => {
  describe('Part 1', () => {
    it('solves examples', () => {
      expect(solve(split('1,9,10,3,2,3,11,0,99,30,40,50'))).toBe(
        3500,
      );
      expect(solve(split('1,0,0,0,99'))).toBe(2);
      expect(solve(split('2,3,0,3,99'))).toBe(2);
      expect(solve(split('2,4,4,5,99,0'))).toBe(2);
      expect(solve(split('1,1,1,4,99,5,6,0,99'))).toBe(30);
    });

    it('solves the input', () => {
      const [first, , , ...input] = split(inputLoader('./input.txt'));
      const solution = solve([first, 12, 2, ...input]);
      expect(solution).toBe(4690667);
    });
  });

  describe('Part 2', () => {
    it('solves the input', () => {
      expect(solve2(split(inputLoader('./input.txt')))).toBe(6255);
    });
  });
});
