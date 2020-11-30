export function solve(wire1: string[], wire2: string[]) {
  const [pathA, pathB] = [wire1, wire2].map(buildPath);
  const crossings = gatherCrossings(pathA, pathB);
  return Math.min(...crossings.map(({ x, y }) => Math.abs(x) + Math.abs(y)));
}

export function solve2(wire1: string[], wire2: string[]) {
  const [pathA, pathB] = [wire1, wire2].map(buildPath);
  return Math.min(...gatherCrossings(pathA, pathB).map(x => x.steps));
}

function gatherCrossings(
  pathA: Map<string, number>,
  pathB: Map<string, number>,
) {
  const crossings = [];
  for (const cords of Array.from(pathA.keys())) {
    if (pathB.has(cords)) {
      const stepsA = pathA.get(cords);
      const stepsB = pathB.get(cords);
      const [x, y] = cords.split(',');
      crossings.push({ x, y, steps: stepsA + stepsB });
    }
  }
  return crossings;
}

function buildPath(wire: string[]) {
  let pos = { x: 0, y: 0, steps: 0 };
  const path = new Map<string, number>();
  for (const vector of wire) {
    const { x, y, steps } = pos;
    const direction = vector[0];
    const magnitude = parseInt(vector.substring(1), 10);
    switch (direction) {
      case 'R':
        for (let i = x + 1, j = 1; i <= x + magnitude; i++, j++) {
          path.set(`${i},${y}`, steps + j);
          pos = { x: i, y, steps: steps + j };
        }
        break;
      case 'D':
        for (let i = y + 1, j = 1; i <= y + magnitude; i++, j++) {
          path.set(`${x},${i}`, steps + j);
          pos = { x, y: i, steps: steps + j };
        }
        break;
      case 'L':
        for (let i = x - 1, j = 1; i >= x - magnitude; i--, j++) {
          path.set(`${i},${y}`, steps + j);
          pos = { x: i, y, steps: steps + j };
        }
        break;
      case 'U':
        for (let i = y - 1, j = 1; i >= y - magnitude; i--, j++) {
          path.set(`${x},${i}`, steps + j);
          pos = { x, y: i, steps: steps + j };
        }
        break;
      default:
    }
  }
  return path;
}
