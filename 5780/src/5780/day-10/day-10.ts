interface Astroid {
  distance: number;
  angle: number;
  x: number;
  y: number;
}

function deltaToAngle(x1: number, y1: number, x2: number, y2: number) {
  const deltaX = x2 - x1;
  const deltaY = y2 - y1;
  const distance = Math.abs(deltaX) + Math.abs(deltaY);
  const rad = Math.atan2(deltaY, deltaX);
  let angle = rad * (180 / Math.PI) - 180;
  angle = angle === 0 ? angle : 360 - (angle < 0 ? angle + 360 : angle);
  return { distance, angle, x: x2, y: y2 };
}

function angleOfNeighbors(x: number, y: number, input: string[][]) {
  const angles: Astroid[] = [];
  for (let x1 = 0; x1 < input.length; x1++) {
    for (let y1 = 0; y1 < input[x1].length; y1++) {
      if (!(x === x1 && y === y1) && input[x1][y1] === '#') {
        angles.push(deltaToAngle(x, y, x1, y1));
      }
    }
  }
  return angles;
}

function groupByNeighbors(input: string[][]) {
  const map = new Map<string, number>();
  for (let x = 0; x < input.length; x++) {
    for (let y = 0; y < input[x].length; y++) {
      if (input[x][y] === '#') {
        map.set(
          `${x},${y}`,
          angleOfNeighbors(x, y, input).reduce(
            (set, neighbor) => set.add(neighbor.angle),
            new Set<number>(),
          ).size,
        );
      }
    }
  }
  return map;
}

export function solve(input: string[][]) {
  const neighbors = groupByNeighbors(input);
  return Math.max(...Array.from(neighbors.values()));
}

export function solve_2(input: string[][]) {
  const map = groupByNeighbors(input);
  const center = Array.from(map).reduce(
    (max, [key, count]) => {
      if (count > max.count) {
        return { key, count };
      }
      return max;
    },
    { key: null, count: 0 },
  );
  const [x, y] = center.key.split(',');
  const neighbors = angleOfNeighbors(x, y, input)
    .filter(({ distance }) => distance > 0)
    .reduce((agg, astroid) => {
      const astroids = agg.get(astroid.angle) || [];
      return agg.set(
        astroid.angle,
        astroids.concat(astroid).sort((a, b) => a.distance - b.distance),
      );
    }, new Map<number, Astroid[]>());

  const sortedByAngle = new Map(
    Array.from(neighbors.entries()).sort(([a], [b]) => a - b),
  );

  let i = 0;
  for (const [_, astroids] of sortedByAngle.entries()) {
    const astroid = astroids.pop();
    if (astroid && i === 199) {
      return astroid.y * 100 + astroid.x;
    }
    i++;
  }
}
