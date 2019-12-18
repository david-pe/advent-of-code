import { solve } from './day-05';
import inputLoader from '../../input-loader';
import 'basad';

const split = input => input.split(',').map(x => parseInt(x, 10));

describe('Day 05', () => {
  describe('Part 1', () => {
    it('solves example 1', () => {
      const input = 17;
      const inputStream = function*() {
        yield input;
      };
      const output = solve(split('3,0,4,0,99'), inputStream);
      expect(output.next().value).toBe(input);
    });

    it('solves the input', () => {
      const inputStream = function*() {
        yield 1;
      };
      const input = split(inputLoader('./input.txt'));
      const solution = solve(input, inputStream);
      let result = solution.next();
      let prevResult = result.value;
      while (!result.done) {
        prevResult = result.value;
        result = solution.next();
      }
      expect(prevResult).toBe(14522484);
    });
  });

  describe('Part 2', () => {
    it('solves example 1 (true)', () => {
      const result = diagnose(8, '3,9,8,9,10,9,4,9,99,-1,8');
      expect(result).toBe(1);
    });

    it('solves example 1 (false)', () => {
      const result = diagnose(9, '3,9,8,9,10,9,4,9,99,-1,8');
      expect(result).toBe(0);
    });

    it('solves example 2 (true)', () => {
      const result = diagnose(7, '3,9,7,9,10,9,4,9,99,-1,8');
      expect(result).toBe(1);
    });

    it('solves example 2 (false)', () => {
      const result = diagnose(9, '3,9,7,9,10,9,4,9,99,-1,8');
      expect(result).toBe(0);
    });

    it('solves example 3 (true)', () => {
      const result = diagnose(8, '3,3,1108,-1,8,3,4,3,99');
      expect(result).toBe(1);
    });

    it('solves example 3 (false)', () => {
      const result = diagnose(9, '3,3,1108,-1,8,3,4,3,99');
      expect(result).toBe(0);
    });

    it('solves example 4 (true)', () => {
      const result = diagnose(7, '3,3,1107,-1,8,3,4,3,99');
      expect(result).toBe(1);
    });

    it('solves example 4 (false)', () => {
      const result = diagnose(9, '3,3,1107,-1,8,3,4,3,99');
      expect(result).toBe(0);
    });
    it('solves example 5 (false)', () => {
      const result = diagnose(0, '3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9');
      expect(result).toBe(0);
    });

    it('solves example 5 (true)', () => {
      const result = diagnose(1, '3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9');
      expect(result).toBe(1);
    });

    describe('example 6', () => {
      const input =
        '3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99';
      it('solves example 6 (999)', () => {
        const result = diagnose(7, input);
        expect(result).toBe(999);
      });

      it('solves example 6 (1000)', () => {
        const result = diagnose(8, input);
        expect(result).toBe(1000);
      });

      it('solves example 6 (1001)', () => {
        const result = diagnose(9, input);
        expect(result).toBe(1001);
      });

      it('solves the input', () => {
        const result = diagnose(5, inputLoader('./input.txt'));
        expect(result).toBe(4655956);
      });
    });
  });
});

function diagnose(inputParam: number, input: string) {
  const inputStream = function*() {
    yield inputParam;
  };
  const solution = solve(split(input), inputStream);
  let result = solution.next();
  let prevResult = result.value;

  while (!result.done) {
    prevResult = result.value;
    result = solution.next();
  }
  return prevResult;
}
