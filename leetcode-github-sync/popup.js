const fields = ["token", "owner", "repo", "branch", "basePath"];
const defaults = { owner: "ManaliSelmokar", repo: "sde_striversheet", branch: "practice", basePath: "solutions" };

chrome.storage.local.get({ ...defaults, token: "", lastSync: null }).then((settings) => {
  for (const field of fields) {
    document.getElementById(field).value = settings[field] || "";
  }
  if (settings.lastSync) {
    const lastSync = document.getElementById("lastSync");
    lastSync.textContent = settings.lastSync.message;
    lastSync.classList.toggle("error", !settings.lastSync.ok);
  }
});

document.getElementById("save").addEventListener("click", async () => {
  const settings = Object.fromEntries(fields.map((field) => [field, document.getElementById(field).value.trim()]));
  await chrome.storage.local.set(settings);
  const status = document.getElementById("status");
  status.textContent = "Settings saved. Accepted submissions will sync automatically.";
  status.style.color = "#28734b";
});

document.getElementById("syncCurrent").addEventListener("click", async () => {
  const status = document.getElementById("status");
  const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
  if (!tab?.id || !tab.url?.startsWith("https://leetcode.com/problems/")) {
    status.textContent = "Open a LeetCode problem first.";
    return;
  }
  const response = await chrome.tabs.sendMessage(tab.id, { type: "sync-current" });
  status.textContent = response?.ok ? "Sync started. Check the result below." : response.error;
});
