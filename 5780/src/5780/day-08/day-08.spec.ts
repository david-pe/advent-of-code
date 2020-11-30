// import 'basad';
import { solve, solve_2 } from './day-08';
import inputLoader from '../../input-loader';

const split = input => input.split('').map(x => parseInt(x, 10));

describe('Day 07', () => {
  describe('Part 1', () => {
    it('solves example 1', () => {
      expect(
        solve({
          input: split('123456789012'),
          width: 3,
          height: 2,
        }),
      ).toBe(1);
    });

    it('solves the input', () => {
      expect(
        solve({
          input: split(inputLoader('./input.txt')),
          width: 25,
          height: 6,
        }),
      ).toBe(2520);
    });
  });

  describe('Part 2', () => {
    it('solves example 1', () => {
      expect(
        solve_2({
          input: split('0222112222120000'),
          width: 2,
          height: 2,
        }),
      ).toBe(' #\n# ');
    });

    it('solves the input', () => {
      expect(
        solve_2({
          input: split(inputLoader('./input.txt')),
          width: 25,
          height: 6,
        }),
      ).toBe(
        [
          '#    ####  ##    ## #   #',
          '#    #    #  #    # #   #',
          '#    ###  #       #  # # ',
          '#    #    # ##    #   #  ',
          '#    #    #  # #  #   #  ',
          '#### ####  ###  ##    #  ',
        ].join('\n'),
      );
    });
  });
});
