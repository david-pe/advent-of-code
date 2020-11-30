export interface Position {
  x: number;
  y: number;
  z: number;
}

const sum = (position: Position) =>
  Math.abs(position.x) + Math.abs(position.y) + Math.abs(position.z);

const moonMap = (input: Position[]) =>
  input
    .map(moon => ({ ...moon }))
    .reduce(
      (map, moon, index) => map.set(index, moon),
      new Map<number, Position>(),
    );

const energy = (
  moons: Map<number, Position>,
  velocities: Map<number, Position>,
) =>
  Array.from(moons).reduce((energyAcc, [index, moon]) => {
    energyAcc += sum(moon) * sum(velocities.get(index));
    return energyAcc;
  }, 0);

const unique = (
  moons: Map<number, Position>,
  velocities: Map<number, Position>,
  axis: keyof Position,
) =>
  Array.from(moons).reduce((energyAcc, [index, moon]) => {
    energyAcc += `${moon[axis]},${velocities.get(index)[axis]}`;
    return energyAcc;
  }, '');

//thanks @shahata https://github.com/shahata/adventofcode-solver/blob/d0948d5cab9fb4b1a05cc107c793c2f5a693dc9a/src/2019/day12.js#L37
function lcm({ x, y, z }: Position) {
  return [x, y, z]
    .map(axis => Math.abs(axis))
    .reduce((a, b) => {
      const m = a * b;
      while (b) {
        const t = b;
        b = a % b;
        a = t;
      }
      return m / a;
    });
}

export function solve(input: Position[], steps: number) {
  let velocities = new Map<number, Position>();
  let moons = moonMap(input);

  for (let step = 0; step < steps; step++) {
    ({ moons, velocities } = moveMoons(moons, velocities));
  }
  return energy(moons, velocities);
}

export function solve_2(input: Position[]) {
  let velocities = new Map<number, Position>();
  let moons = moonMap(input);

  const xSet = new Set<string>();
  const ySet = new Set<string>();
  const zSet = new Set<string>();

  const repeats: Position = { x: 0, y: 0, z: 0 };

  let steps = 0;
  do {
    ({ moons, velocities } = moveMoons(moons, velocities));
    const x = unique(moons, velocities, 'x');
    const y = unique(moons, velocities, 'y');
    const z = unique(moons, velocities, 'z');
    if (!repeats.x && xSet.has(x)) {
      repeats.x = steps;
    } else {
      xSet.add(x);
    }
    if (!repeats.y && ySet.has(y)) {
      repeats.y = steps;
    } else {
      ySet.add(y);
    }
    if (!repeats.z && zSet.has(z)) {
      repeats.z = steps;
    } else {
      zSet.add(z);
    }
    steps++;
  } while (repeats.x === 0 || repeats.y === 0 || repeats.z === 0);

  return lcm(repeats);
}

function moveMoons(
  moons: Map<number, Position>,
  velocities: Map<number, Position>,
) {
  for (const [index1, moon1] of moons) {
    for (const [index2, moon2] of moons) {
      if (index1 !== index2) {
        const velocity = velocities.get(index1) || { x: 0, y: 0, z: 0 };
        velocities.set(index1, {
          x: velocity.x + (moon1.x < moon2.x ? 1 : moon2.x < moon1.x ? -1 : 0),
          y: velocity.y + (moon1.y < moon2.y ? 1 : moon2.y < moon1.y ? -1 : 0),
          z: velocity.z + (moon1.z < moon2.z ? 1 : moon2.z < moon1.z ? -1 : 0),
        });
      }
    }
  }

  for (const [index, moon] of moons) {
    const { x, y, z } = velocities.get(index);
    moon.x += x;
    moon.y += y;
    moon.z += z;
  }
  return {
    moons,
    velocities,
  };
}
