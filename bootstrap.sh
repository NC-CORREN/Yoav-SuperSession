#!/usr/bin/env bash
set -euo pipefail

# Create default node & python scaffolding if missing.
if [ ! -f package.json ]; then
cat > package.json <<'JSON'
{
  "name": "amk-bootstrap",
  "version": "0.1.0",
  "private": true,
  "type": "module",
  "scripts": {
    "test": "node --test tests/js/*.test.js"
  },
  "devDependencies": {
    "eslint": "^9.13.0"
  }
}
JSON
fi

if [ ! -f ".eslintrc.json" ]; then
cat > .eslintrc.json <<'JSON'
{
  "env": { "es2022": true, "node": true },
  "extends": "eslint:recommended",
  "rules": {}
}
JSON
fi

mkdir -p src tests/js tests/python

# Sample code
if [ ! -f "src/example.py" ]; then
cat > src/example.py <<'PY'
def add(a, b):
    return a + b
PY
fi

if [ ! -f "tests/python/test_example.py" ]; then
cat > tests/python/test_example.py <<'PY'
from src.example import add

def test_add():
    assert add(2, 3) == 5
PY
fi

if [ ! -f "src/example.js" ]; then
cat > src/example.js <<'JS'
export function add(a, b) {
  return a + b;
}
JS
fi

if [ ! -f "tests/js/example.test.js" ]; then
cat > tests/js/example.test.js <<'JS'
import { test, strict as assert } from 'node:test';
import { add } from '../../src/example.js';

test('add', () => {
  assert.equal(add(2, 3), 5);
});
JS
fi

echo "Bootstrap complete."
