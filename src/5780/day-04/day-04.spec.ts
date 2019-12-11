import { solve, solve2 } from './day-04';

describe('Day 03', () => {
  describe('Part 1', () => {
    it('solves examples', () => {
      expect(solve({ min: 111111 })).toBe(1);
      expect(solve({ min: 223450 })).toBe(0);
      expect(solve({ min: 123789 })).toBe(0);
    });

    it('solves the input', () => {
      expect(solve({ min: 171309, max: 643603 })).toBe(1625);
    });
  });

  describe('Part 2', () => {
    it('solves the examples', () => {
      expect(solve2({ min: 112233 })).toBe(1);
      expect(solve2({ min: 123444 })).toBe(0);
      expect(solve2({ min: 111122 })).toBe(1);
    });

    it('solves the input', () => {
      expect(solve2({ min: 171309, max: 643603 })).toBe(1111);
    });
  });
});
