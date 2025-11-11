# iOS Shortcut: One‑Tap GitOps

This repo supports a **one‑tap trigger** from your phone to run CI, Preflight, apply patches, open PRs, etc.
It uses GitHub's `repository_dispatch` event and a fine‑grained Personal Access Token (PAT).

## 1) Create a fine‑grained PAT (once)
- GitHub → **Settings → Developer settings → Personal access tokens → Fine‑grained tokens**
- **Resource access:** Only this repository
- **Permissions:** Actions (Read & write), Contents (Read & write), Pull requests (Read & write)

## 2) Create iOS Shortcut (once)
- Add **“Run GitOps”**
- Action: **Get Contents of URL**
  - Method: **POST**
  - URL: `https://api.github.com/repos/<owner>/<repo>/dispatches`
  - Headers:
    - `Authorization: Bearer <YOUR_PAT>`
    - `Accept: application/vnd.github+json`
  - JSON Body:
  ```json
  {
    "event_type":"ops-run",
    "client_payload":{"task":"${TASK}","args":${ARGS}}
  }
  ```
- Make `TASK` and `ARGS` variables so you can reuse for any operation.

## 3) Example payloads
- Preflight:
  ```json
  {"task":"preflight","args":{}}
  ```
- CI (tests):
  ```json
  {"task":"ci","args":{}}
  ```
- Apply a patch (branch + commit):
  ```json
  {
    "task":"apply_patch",
    "args":{
      "branch":"feature/xyz",
      "commit_msg":"Apply patch from chat",
      "patch_b64":"<BASE64-OF-UNIFIED-DIFF>"
    }
  }
  ```
- Open PR:
  ```json
  {
    "task":"open_pr",
    "args":{"branch":"feature/xyz","title":"Feature XYZ","body":"Auto‑opened from chat"}
  }
  ```
- Merge PR:
  ```json
  {"task":"merge_pr","args":{"number":42}}
  ```

## Notes
- Your PAT **never** goes in the repo. It stays inside your iOS Shortcut.
- You can also trigger from desktop via `curl`:
  ```bash
  curl -s -X POST     -H "Authorization: Bearer $GITHUB_PAT"     -H "Accept: application/vnd.github+json"     https://api.github.com/repos/<owner>/<repo>/dispatches     -d '{"event_type":"ops-run","client_payload":{"task":"preflight","args":{}}}'
  ```
