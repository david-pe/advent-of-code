import { solve, solve2 } from './day-03';
import inputLoader from '../../input-loader';

const split = input => input.split(',');

describe('Day 03', () => {
  describe('Part 1', () => {
    it('solves examples', () => {
      expect(solve(split('R8,U5,L5,D3'), split('U7,R6,D4,L4'))).toBe(6);
      expect(
        solve(
          split('R75,D30,R83,U83,L12,D49,R71,U7,L72'),
          split('U62,R66,U55,R34,D71,R55,D58,R83'),
        ),
      ).toBe(159);
      expect(
        solve(
          split('R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51'),
          split('U98,R91,D20,R16,D67,R40,U7,R15,U6,R7'),
        ),
      ).toBe(135);
    });

    it('solves the input', () => {
      const [wire1, wire2] = inputLoader('./input.txt').split('\n');
      expect(solve(split(wire1), split(wire2))).toBe(1064);
    });
  });

  describe('Part 2', () => {
    it('solves the examples', () => {
      expect(solve2(split('R8,U5,L5,D3'), split('U7,R6,D4,L4'))).toBe(30);
      expect(
        solve2(
          split('R75,D30,R83,U83,L12,D49,R71,U7,L72'),
          split('U62,R66,U55,R34,D71,R55,D58,R83'),
        ),
      ).toBe(610);
      expect(
        solve2(
          split('R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51'),
          split('U98,R91,D20,R16,D67,R40,U7,R15,U6,R7'),
        ),
      ).toBe(410);
    });

    it('solves the input', () => {
      const [wire1, wire2] = inputLoader('./input.txt').split('\n');
      expect(solve2(split(wire1), split(wire2))).toBe(25676);
    });
  });
});
