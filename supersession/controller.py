# SuperSession Controller v1 (Scaffold)
# This file receives tasks from the GitHub Actions workflow
# and performs operations inside the repository.

import os
import json
import base64
from pathlib import Path

# --- Helpers ------------------------------------------------------

def write_file(path, content_b64):
    """Write a file from base64 content."""
    content = base64.b64decode(content_b64).decode("utf-8")
    p = Path(path)
    p.parent.mkdir(parents=True, exist_ok=True)
    p.write_text(content, encoding="utf-8")
    print(f"[controller] wrote: {path}")

def delete_file(path):
    """Delete a file."""
    p = Path(path)
    if p.exists():
        p.unlink()
        print(f"[controller] deleted: {path}")
    else:
        print(f"[controller] not found: {path}")

def append_file(path, content_b64):
    """Append base64 content to an existing file."""
    content = base64.b64decode(content_b64).decode("utf-8")
    p = Path(path)
    p.parent.mkdir(parents=True, exist_ok=True)
    with open(path, "a", encoding="utf-8") as f:
        f.write(content)
    print(f"[controller] appended to: {path}")

# --- Main Dispatcher ----------------------------------------------

def main():
    task = os.getenv("SS_TASK")
    args_raw = os.getenv("SS_ARGS")

    print(f"[controller] task = {task}")
    print(f"[controller] args = {args_raw}")

    if not task:
        print("[controller] No task received.")
        return

    args = {}
    if args_raw:
        try:
            args = json.loads(args_raw)
        except Exception as e:
            print(f"[controller] failed to decode args JSON: {e}")

    # Task router
    if task == "write":
        write_file(args["path"], args["content_b64"])

    elif task == "delete":
        delete_file(args["path"])

    elif task == "append":
        append_file(args["path"], args["content_b64"])

    else:
        print(f"[controller] Unknown task: {task}")


if __name__ == "__main__":
    main()