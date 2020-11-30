import { computer } from '../computer';

function turn(dir: number, direction: string) {
  switch (dir) {
    case 1:
      if (direction === 'up') {
        return 'right';
      }
      if (direction === 'right') {
        return 'down';
      }
      if (direction === 'down') {
        return 'left';
      }
      return 'up';
    case 0:
    default:
      if (direction === 'up') {
        return 'left';
      }
      if (direction === 'left') {
        return 'down';
      }
      if (direction === 'down') {
        return 'right';
      }
      return 'up';
  }
}

function move(direction: string, x: number, y: number) {
  switch (direction) {
    case 'up':
      return [x, --y];
    case 'right':
      return [++x, y];
    case 'down':
      return [x, ++y];
    case 'left':
    default:
      return [--x, y];
  }
}

function getOutput(
  input: number[],
  defaultColor = 0,
): [number[][], Map<string, number>] {
  let direction: 'up' | 'right' | 'down' | 'left' = 'up';
  let i = 1;
  let x = 0;
  let y = 0;
  const matrix: number[][] = [];
  const inputStream = function*() {
    yield defaultColor;
    // tslint:disable-next-line:no-constant-condition
    while (true) {
      if (matrix[x] && matrix[x][y]) {
        yield matrix[x][y];
      } else {
        yield 0;
      }
    }
  };

  const output: number[] = [];
  const outputSet = new Map<string, number>();
  for (const o of computer([...input], inputStream)) {
    output.push(o);
    if (i % 2 === 0) {
      const [color, dir] = output.splice(0, 2);
      outputSet.set(`${x},${y}`, color);
      matrix[x] = matrix[x] || [];
      matrix[x][y] = color;
      direction = turn(dir, direction);
      [x, y] = move(direction, x, y);
    }
    i++;
  }
  return [matrix, outputSet];
}

export function solve(input: number[]) {
  const [_, outputSet] = getOutput(input);
  return outputSet.size;
}

export function solve_2(input: number[]) {
  const [matrix, outputSet] = getOutput(input, 1);
  const height = Math.max(...matrix.map(x => x.length));
  const width = matrix.length;

  const drawing: string[][] = [];

  for (let y = 0; y < height; y++) {
    for (let x = 0; x < width; x++) {
      drawing[y] = drawing[y] || [];
      drawing[y][x] = outputSet.get(`${x},${y}`) === 1 ? '#' : ' ';
    }
  }
  return drawing.map(x => x.join('').trim()).join('\n');
}
