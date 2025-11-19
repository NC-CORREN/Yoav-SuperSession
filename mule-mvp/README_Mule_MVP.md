# Mule MVP – Codebase Root

This folder hosts the **stand-alone Mule MVP**, designed to:
- Wrap ANY service (physical or not) and ANY machine
- Integrate them safely via SB → ORCO → MM → MAIN → IDP → IDB → Ledger → CAPMULE
- Work **with** AF examples, but **not depend** on AF being present

First goal:
- Spring Boot backend services under `services/`
- React + TypeScript console under `ui/console/`
- Local dev: `./gradlew build`, `./scripts/run-all.sh`, `./scripts/smoke.sh`

Do NOT add ET or full Clearing yet.
- CLIN/CLOUT exist **inside Mule** as simple internal clearing stubs, unlocked by code `123456`.
- Names stay **CLIN / CLOUT**, but behaviour is: “accept all” once configured.