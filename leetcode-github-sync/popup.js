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
