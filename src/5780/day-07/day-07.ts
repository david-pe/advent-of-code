import { solve as computer } from '../day-05/day-05';

const phaseInput = (setting: number, input: number) => {
  return function*() {
    yield setting;
    yield input;
  };
};

const phaseInputStream = (setting: number, input: IterableIterator<number>) => {
  return function*() {
    yield setting;
    yield* input;
  };
};

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

export function solve({
  settings,
  input,
}: {
  settings: number[];
  input: number[];
}) {
  const output = [0];
  for (const option of permute(settings)) {
    output.push(
      getOutput({
        settings: option,
        input,
      }),
    );
  }
  return Math.max(...output);
}

function getOutput({
  settings,
  input,
}: {
  settings: number[];
  input: number[];
}) {
  let nextInput = 0;
  for (const setting of settings) {
    nextInput =
      computer(input, phaseInput(setting, nextInput)).next().value || 0;
  }
  return nextInput;
}

function getOutputWithStream({
  settings,
  input,
}: {
  settings: number[];
  input: number[];
}) {
  const engineE_output = [];

  const firstInput = function*() {
    yield settings[0];
    yield 0;
    while (engineE_output.length) {
      yield engineE_output.pop();
    }
  };

  const engineA = computer([...input], firstInput);
  const engineB = computer([...input], phaseInputStream(settings[1], engineA));
  const engineC = computer([...input], phaseInputStream(settings[2], engineB));
  const engineD = computer([...input], phaseInputStream(settings[3], engineC));
  const engineE = computer([...input], phaseInputStream(settings[4], engineD));

  for (const output of engineE) {
    engineE_output.unshift(output);
  }

  return engineE_output.pop();
}

export function solve_2({
  settings,
  input,
}: {
  settings: number[];
  input: number[];
}) {
  const output = [0];
  for (const permutation of permute(settings)) {
    output.push(
      getOutputWithStream({
        settings: permutation,
        input,
      }),
    );
  }
  return Math.max(...output);
}
