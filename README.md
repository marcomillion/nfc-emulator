# TapForge V2 Android — custom HCE test card

This native Android companion uses Host Card Emulation (HCE) for one custom demo-card protocol and a compatible NFC reader.

## Limitations
- Does not clone arbitrary NFC tags, bank cards, transit cards, building badges, or protected credentials.
- HCE requires a compatible ISO-DEP / ISO 14443-4 reader; it does not emulate every NFC tag type.
- HCE availability depends on phone firmware. This project must still be tested on the target Redmi firmware.

## Build
Pushes to `main` run the GitHub Actions workflow and publish a debug APK artifact. Open the repository's Actions tab, select the latest successful run, and download `tapforge-v2-debug-apk`.

## Demo protocol
AID: `F0010203040506`

1. SELECT: `00 A4 04 00 07 F0 01 02 03 04 05 06` → `90 00`
2. GET DEMO DATA: `80 CA 00 00 00` → UTF-8 `TAPFORGE|CARD=DEMO-001|VERSION=2.0` + `90 00`

The payload is a proprietary test string, not a real credential.
