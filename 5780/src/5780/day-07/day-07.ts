import { computer } from '../computer';

function* permute(a: number[], n = a.length): IterableIterator<number[]> {
  if (n <= 1) {
    yield a.slice();
  } else {
    for (let i = 0; i < n; i++) {
      yield* permute(a, n - 1);
      const j = n % 2 ? 0 : i;
      [a[n - 1], a[j]] = [a[j], a[n - 1]];
    }
  }
}

const inputStream = (setting: number, input: IterableIterator<number>) => {
  return function*() {
    yield setting;
    yield* input;
  };
};

function getOutput({
  settings,
  input,
}: {
  settings: number[];
  input: number[];
}) {
  const amplifierE_output = [];

  const firstInput = function*() {
    yield settings[0];
    yield 0;
    while (amplifierE_output.length) {
      yield amplifierE_output.pop();
    }
  };

  const amplifierA = computer([...input], firstInput);
  const amplifierB = computer([...input], inputStream(settings[1], amplifierA));
  const amplifierC = computer([...input], inputStream(settings[2], amplifierB));
  const amplifierD = computer([...input], inputStream(settings[3], amplifierC));
  const amplifierE = computer([...input], inputStream(settings[4], amplifierD));

  for (const output of amplifierE) {
    amplifierE_output.unshift(output);
  }

  return amplifierE_output.pop();
}

export function solve({
  settings,
  input,
}: {
  settings: number[];
  input: number[];
}) {
  const output = [0];
  for (const permutation of permute(settings)) {
    output.push(
      getOutput({
        settings: permutation,
        input,
      }),
    );
  }
  return Math.max(...output);
}
