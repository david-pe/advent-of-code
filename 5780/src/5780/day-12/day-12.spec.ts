import 'basad';
import { solve, solve_2 } from './day-12';
import inputLoader from '../../input-loader';

const split = input =>
  input.split('\n').map((x: string) => {
    const cords = { x: 0, y: 0, z: 0 };
    const pattern = /([xyz])=(-?\d.)/g;
    let match = pattern.exec(x);
    while (match) {
      cords[match[1]] = parseInt(match[2], 10);
      match = pattern.exec(x);
    }
    return cords;
  });

describe('Day 12', () => {
  describe('Part 1', () => {
    it('solves example 1', () => {
      expect(
        solve(
          split(`<x=-1, y=0, z=2>
      <x=2, y=-10, z=-7>
      <x=4, y=-8, z=8>
      <x=3, y=5, z=-1>`),
          10,
        ),
      ).toBe(179);
    });

    it('solves example 2', () => {
      expect(
        solve(
          split(`<x=-8, y=-10, z=0>
          <x=5, y=5, z=10>
          <x=2, y=-7, z=3>
          <x=9, y=-8, z=-3>`),
          100,
        ),
      ).toBe(1940);
    });
    it('solves the input', () => {
      expect(solve(split(inputLoader('./input.txt')), 1000)).toBe(8625);
    });
  });

  describe('Part 2', () => {
    it('solves example 1', () => {
      expect(
        solve_2(
          split(`<x=-1, y=0, z=2>
      <x=2, y=-10, z=-7>
      <x=4, y=-8, z=8>
      <x=3, y=5, z=-1>`),
        ),
      ).toBe(2772);
    });

    it('solves example 2', () => {
      expect(
        solve_2(
          split(`<x=-8, y=-10, z=0>d
          <x=5, y=5, z=10>
          <x=2, y=-7, z=3>
          <x=9, y=-8, z=-3>`),
        ),
      ).toBe(4686774924);
    });

    it('solves the input', () => {
      expect(solve_2(split(inputLoader('./input.txt')))).toBe(332477126821644);
    });
  });
});
