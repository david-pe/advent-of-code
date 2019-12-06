import * as fs from 'fs';
import * as path from 'path';

export default function(filename: string) {
  return fs.readFileSync(path.join(module.parent.filename, '..', filename), {
    encoding: 'utf8',
  });
}
