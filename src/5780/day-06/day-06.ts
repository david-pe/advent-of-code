export function solve(input: string[]) {
  const nodes = getNodes(input);

  return Array.from(nodes.values()).reduce((count, parent) => {
    let parents = 0;
    while (parent) {
      parents++;
      parent = nodes.get(parent);
    }
    return parents + count;
  }, 0);
}

export function solve_2(input: string[]) {
  const nodes = getNodes(input);

  let moves = 0;
  let youDepth = getDepth(nodes, 'YOU');
  let sanDepth = getDepth(nodes, 'SAN');

  while (youDepth > sanDepth) {
    const grandparent = nodes.get(nodes.get('YOU'));
    nodes.set('YOU', grandparent);
    youDepth = getDepth(nodes, 'YOU');
    moves++;
  }

  while (sanDepth > youDepth) {
    const grandparent = nodes.get(nodes.get('SAN'));
    nodes.set('SAN', grandparent);
    sanDepth = getDepth(nodes, 'SAN');
    moves++;
  }

  while (nodes.get('YOU') !== nodes.get('SAN')) {
    const sanGp = nodes.get(nodes.get('SAN'));
    nodes.set('SAN', sanGp);
    const youGp = nodes.get(nodes.get('YOU'));
    nodes.set('YOU', youGp);
    moves += 2;
  }

  return moves;
}

const orbit = entry => entry.split(')').reverse();
const getNodes = (input: string[]) => new Map<string, string>(input.map(orbit));

const getDepth = (nodes: Map<string, string>, key: string) => {
  let node = nodes.get(key);
  let depth = 0;
  while (node) {
    node = nodes.get(node);
    depth++;
  }
  return depth;
};
