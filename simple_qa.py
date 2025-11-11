import json, sys, os

def main():
    gate_path = sys.argv[1]
    py_exit = int(sys.argv[2])
    js_exit = int(sys.argv[3])
    with open(gate_path, "r", encoding="utf-8") as f:
        gate = json.load(f)

    # Interpret non-zero as failure for minimal tests; gate can be expanded later.
    failures = []
    if gate.get("python_required") and py_exit != 0 and os.path.isdir("tests/python"):
        failures.append(f"Python tests failed (exit {py_exit}).")
    if gate.get("node_required") and js_exit != 0 and os.path.isdir("tests/js"):
        failures.append(f"Node tests failed (exit {js_exit}).")

    if failures:
        print("PRELIGHT: ❌ Gate failed")
        for f in failures:
            print(" -", f)
        sys.exit(1)
    else:
        print("PRELIGHT: ✅ Gate passed")
        sys.exit(0)

if __name__ == "__main__":
    main()
