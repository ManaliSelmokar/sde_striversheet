# LeetCode GitHub Sync

A small Chrome/Edge extension that saves accepted LeetCode submissions to GitHub.

## Install locally

1. Open `chrome://extensions` or `edge://extensions`.
2. Enable **Developer mode**.
3. Choose **Load unpacked** and select this folder.
4. Open the extension settings and enter a GitHub fine-grained token with **Contents: Read and write** access to `ManaliSelmokar/sde_striversheet`.
5. Keep the branch set to `practice`.

Accepted submissions are saved as `solutions/<problem-slug>/solution.<extension>`.

## Current MVP limitations

- LeetCode DOM changes may require updates to `content.js`.
- The submitted code must be visible in the editor when the accepted result appears.
- The token is stored in Chrome extension storage. Use a repository-scoped token and revoke it from GitHub when finished.
