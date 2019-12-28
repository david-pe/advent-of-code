// import 'basad';
import { solve, solve_2 } from './day-06';
import inputLoader from '../../input-loader';

const split = input => input.split('\n').map(x => x.trim());

describe('Day 06', () => {
  describe('Part 1', () => {
    it('solves example 1', () => {
      const input = split(`COM)B
      B)C
      C)D
      D)E
      E)F
      B)G
      G)H
      D)I
      E)J
      J)K
      K)L`);

      expect(solve(input)).toEqual(42);
    });

    it('solves the input', () => {
      const input = split(inputLoader('./input.txt'));
      expect(solve(input)).toEqual(147807);
    });
  });

  describe('Part 2', () => {
    it('solves example 1', () => {
      const input = split(`COM)B
      B)C
      C)D
      D)E
      E)F
      B)G
      G)H
      D)I
      E)J
      J)K
      K)L
      K)YOU
      I)SAN`);
      expect(solve_2(input)).toEqual(4);
    });

    it('solves the input', () => {
      const input = split(inputLoader('./input_2.txt'));
      expect(solve_2(input)).toEqual(229);
    });
  });
});
