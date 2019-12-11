export function solve({ min, max = min }: { min: number; max?: number }) {
  let validCount = 0;
  for (let i = min; i <= max; i++) {
    const password = i.toString().split('');
    let x = 0;
    let doubles = false;
    let ascending = true;
    do {
      const char = password[x];
      const next = password[x + 1];
      if (next < char) {
        ascending = false;
      }
      if (next === char) {
        doubles = true;
      }
      x++;
    } while (x < 6);
    if (ascending && doubles) {
      validCount++;
    }
  }
  return validCount;
}

export function solve2({ min, max = min }: { min: number; max?: number }) {
  let validCount = 0;
  for (let i = min; i <= max; i++) {
    const password = i.toString().split('');
    let index = 0;
    const doubles = new Map<string, number>();
    let ascending = true;
    do {
      const char = password[index];
      const next = password[index + 1];
      if (next < char) {
        ascending = false;
      }
      if (next === char) {
        const count = doubles.get(char) || 1;
        doubles.set(char, count + 1);
      }
      index++;
    } while (index < 6);

    if (ascending && Array.from(doubles.values()).find(x => x === 2)) {
      validCount++;
    }
  }
  return validCount;
}
