# One-Time Activation

This repository includes a **One-Time Activator** workflow that configures repo workflow permissions
so automation jobs can commit, open PRs, and manage branches.

## Steps (once)
1. Create a **fine-grained Personal Access Token** (PAT) limited to this repo with permissions:
   - **Actions: Read & write**
   - **Contents: Read & write**
   - *(Optional)* **Administration: repo_hooks** if you plan to manage hooks via API
2. In your repo: **Settings → Secrets and variables → Actions → New repository secret**
   - Name: `ADMIN_PAT`
   - Value: your token
3. Go to the **Actions** tab → select **“One-Time Activator”** → **Run workflow**.
4. After success, your repo will have **Workflow permissions = Read & write**.
5. You can now use the **Ops Dispatch** workflows with only `GITHUB_TOKEN`, or keep using `repository_dispatch` from your iOS Shortcut.

> Note: This does **not** grant ChatGPT any background access. It only enables **GitHub Actions**
> to write to the repo under the built-in `GITHUB_TOKEN` for this repository.
