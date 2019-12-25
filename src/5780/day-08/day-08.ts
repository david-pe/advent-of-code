const countDigits = (digit: number) => (count: number, x: number) =>
  x === digit ? ++count : count;

const toLayers = (input: number[], layerSize: number) => {
  const layers: number[][] = [];
  while (input.length) {
    layers.push(input.splice(0, layerSize));
  }
  return layers;
};

const toLines = (layer: number[], width: number) => {
  const lines: number[][] = [];
  while (layer.length) {
    lines.push(layer.splice(0, width));
  }
  return lines;
};

export function solve({
  height,
  width,
  input,
}: {
  height: number;
  width: number;
  input: number[];
}) {
  const layers = toLayers(input, height * width);

  const fewestZeros = layers.reduce((fewest, current) => {
    return fewest.reduce(countDigits(0)) < current.reduce(countDigits(0))
      ? fewest
      : current;
  });

  return (
    fewestZeros.reduce(countDigits(1), 0) *
    fewestZeros.reduce(countDigits(2), 0)
  );
}

export function solve_2({
  height,
  width,
  input,
}: {
  height: number;
  width: number;
  input: number[];
}) {
  const layerSize = height * width;
  const layers = toLayers(input, layerSize);
  const image: number[] = [];

  for (let index = 0; index < layerSize; index++) {
    const pixel = layers.reduce(
      (color, layer) => (color !== 2 ? color : layer[index]),
      2,
    );
    image.push(pixel);
  }

  return toLines(image, width)
    .map(line =>
      line
        .join('')
        .replace(/0/g, ' ')
        .replace(/1/g, '#'),
    )
    .join('\n');
}
