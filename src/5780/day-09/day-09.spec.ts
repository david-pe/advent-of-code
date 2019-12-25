import 'basad';
import { solve, solve_2 } from './day-09';
import inputLoader from '../../input-loader';

const split = input => input.split(',').map(x => parseInt(x, 10));

describe('Day 09', () => {
  describe('Part 1', () => {
    it('solves example 1', () => {
      expect(
        solve(
          split('109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99'),
        ),
      ).toStrictEqual(
        split('109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99'),
      );
    });

    it('solves example 2', () => {
      expect(
        solve(split('1102,34915192,34915192,7,4,7,99,0'))
          .pop()
          .toString(),
      ).toHaveLength(16);
    });

    it('solves example 3', () => {
      expect(solve(split('104,1125899906842624,99'))).toStrictEqual([
        1125899906842624,
      ]);
    });

    it('solves the input', () => {
      expect(solve(split(inputLoader('./input.txt')))).toStrictEqual([
        3507134798,
      ]);
    });
  });

  describe('Part 2', () => {
    it('solves the input', () => {
      expect(solve_2(split(inputLoader('./input.txt')))).toStrictEqual([84513]);
    });
  });
});
