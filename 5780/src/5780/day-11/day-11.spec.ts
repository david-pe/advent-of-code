// import 'basad';
import { solve, solve_2 } from './day-11';
import inputLoader from '../../input-loader';

const split = input => input.split(',').map(x => parseInt(x, 10));

describe('Day 11', () => {
  describe('Part 1', () => {
    it('solves the input', () => {
      expect(solve(split(inputLoader('./input.txt')))).toBe(2415);
    });
  });

  describe('Part 2', () => {
    it('solves the input', () => {
      expect(solve_2(split(inputLoader('./input.txt')))).toBe(
        [
          '###  #### ###  #  # #### #  # ###   ##',
          '#  # #    #  # #  #    # #  # #  # #  #',
          '###  ###  #  # #  #   #  #  # #  # #',
          '#  # #    ###  #  #  #   #  # ###  #',
          '#  # #    #    #  # #    #  # #    #  #',
          '###  #    #     ##  ####  ##  #     ##',
        ].join('\n'),
      );
    });
  });
});
