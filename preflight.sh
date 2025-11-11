#!/usr/bin/env bash
set -euo pipefail

echo "== Preflight: environment =="
python3 --version || true
node --version || true
npm --version || true
pip --version || true

echo "== Install tooling =="
pip install -q pytest flake8
npm ci --silent || npm install --silent

echo "== Lint Python =="
if [ -d "src" ] && [ -n "$(ls -A src/*.py 2>/dev/null || true)" ]; then
  flake8 src || true
fi

echo "== Lint Node =="
if [ -f "package.json" ]; then
  npx eslint . || true
fi

echo "== Run tests =="
PY_OK=0
JS_OK=0

if [ -d "tests/python" ]; then
  echo "Running pytest..."
  pytest -q || PY_OK=$?
fi

if [ -d "tests/js" ]; then
  echo "Running node tests..."
  npm test --silent || JS_OK=$?
fi

echo "== Evaluate gate =="
python3 tools/simple_qa.py qa/preflight_gate.json "$PY_OK" "$JS_OK"
