const numberToDigits = num => {
  const digits: number[] = [];
  while (num) {
    digits.push(num % 10);
    num = Math.floor(num / 10);
  }
  return digits;
};

const nextOp: (input: number[], index: number) => [number, number[]] = (
  input,
  index,
) => {
  const o = input[index] % 100;
  const m = numberToDigits(Math.floor(input[index] / 100));
  return [o, m];
};

const add = (a: number, b: number) => a + b;
const multiply = (a: number, b: number) => a * b;

const calculate: (
  input: number[],
  modes: number[],
  index: number,
  method: (a: number, b: number) => number,
  initial: number,
) => [number, number] = (input, modes, index, method, initial) => {
  const size = Math.max(4, modes.length);
  const params = input.slice(index + 1, index + size - 1);
  return [
    params.reduce(
      (agg, param, i) => method(modes[i] ? param : input[param], agg),
      initial,
    ),
    size,
  ];
};

export function* solve(
  input: number[],
  inStream: () => IterableIterator<number>,
) {
  let i = 0;

  const read = (index: number, mode: number) =>
    mode ? input[index] : input[input[index]];

  const write = (index: number, value: number) => (input[index] = value);

  let [op, modes] = nextOp(input, 0);

  while (op !== 99) {
    switch (op) {
      case 1:
        const [result1, size1] = calculate(input, modes, i, add, 0);
        input[input[i + size1 - 1]] = result1;
        i += size1;
        break;
      case 2:
        const [result2, size2] = calculate(input, modes, i, multiply, 1);
        input[input[i + size2 - 1]] = result2;
        i += size2;
        break;
      case 3:
        input[input[i + 1]] = inStream().next().value;
        i += 2;
        break;
      case 4:
        yield read(i + 1, modes[0]);
        i += 2;
        break;
      case 5:
        if (read(i + 1, modes[0])) {
          i = read(i + 2, modes[1]);
        } else {
          i += 3;
        }
        break;
      case 6:
        if (!read(i + 1, modes[0])) {
          i = read(i + 2, modes[1]);
        } else {
          i += 3;
        }
        break;
      case 7:
        const param7_1 = read(i + 1, modes[0]);
        const param7_2 = read(i + 2, modes[1]);
        write(read(i + 3, 1), param7_1 < param7_2 ? 1 : 0);
        i += 4;
        break;
      case 8:
        const param8_1 = read(i + 1, modes[0]);
        const param8_2 = read(i + 2, modes[1]);
        write(read(i + 3, 1), param8_1 === param8_2 ? 1 : 0);
        i += 4;
        break;
      default:
        console.log(op, '>', JSON.stringify(input));
        throw new Error(`bad op ${op}`);
    }
    [op, modes] = nextOp(input, i);
  }
}
