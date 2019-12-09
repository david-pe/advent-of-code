export function solve(input: number) {
  return Math.floor(input / 3) - 2;
}

export function solve2(input: number) {
  const fuel = solve(input);
  return calcFuel(fuel);
}

function calcFuel(fuel: number) {
  const additionalFuel = solve(fuel);
  if (additionalFuel > 0) {
    return calcFuel(additionalFuel) + fuel;
  }
  return fuel;
}
