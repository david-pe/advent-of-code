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

export function* computer(
  input: number[],
  inStream: () => IterableIterator<number>,
) {
  let i = 0;
  let relativeOffset = 0;
  const stream = inStream();

  const read = (index: number, mode: number) => {
    switch (mode) {
      case 1:
        return input[index] || 0;
      case 2:
        return input[input[index] + relativeOffset] || 0;
      default:
        return input[input[index]] || 0;
    }
  };

  const write = (index: number, value: number) => (input[index] = value);

  let [op, modes] = nextOp(input, 0);

  while (op !== 99) {
    switch (op) {
      case 1:
        const offset1 = modes[2] === 2 ? relativeOffset : 0;
        input[input[i + 3] + offset1] =
          read(i + 1, modes[0]) + read(i + 2, modes[1]);
        i += 4;
        break;
      case 2:
        const offset2 = modes[2] === 2 ? relativeOffset : 0;
        input[input[i + 3] + offset2] =
          read(i + 1, modes[0]) * read(i + 2, modes[1]);
        i += 4;
        break;
      case 2:
      case 3:
        const offset3 = modes[0] === 2 ? relativeOffset : 0;
        input[input[i + 1] + offset3] = stream.next().value;
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
        const offset7 = modes[2] === 2 ? relativeOffset : 0;
        write(read(i + 3, 1) + offset7, param7_1 < param7_2 ? 1 : 0);
        i += 4;
        break;
      case 8:
        const param8_1 = read(i + 1, modes[0]);
        const param8_2 = read(i + 2, modes[1]);
        const offset8 = modes[2] === 2 ? relativeOffset : 0;
        write(read(i + 3, 1) + offset8, param8_1 === param8_2 ? 1 : 0);
        i += 4;
        break;
      case 9:
        const offsetOffset = read(i + 1, modes[0]);
        relativeOffset += offsetOffset;
        i += 2;
        break;
      default:
        console.log(op, '>', JSON.stringify(input));
        throw new Error(`bad op ${op}`);
    }
    [op, modes] = nextOp(input, i);
  }
}
