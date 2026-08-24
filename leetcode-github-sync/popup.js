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
  try {
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });
    if (!tab?.id || !tab.url?.startsWith("https://leetcode.com/problems/")) {
      throw new Error("Open a LeetCode problem first.");
    }

    const [result] = await chrome.scripting.executeScript({
      target: { tabId: tab.id },
      func: () => {
        const editor = document.querySelector('textarea[aria-label="Code editor"]');
        const heading = document.querySelector("h1");
        const languageButton = [...document.querySelectorAll("button")].find((button) => /^(C\+\+|Java|Python|Python3|JavaScript|TypeScript|Go|Rust|C#|Kotlin|Swift|Ruby|PHP)$/i.test(button.textContent.trim()));
        return {
          title: heading?.textContent?.replace(/^\d+\.\s*/, "").trim() || location.pathname.split("/")[2],
          language: languageButton?.textContent.trim() || "text",
          code: editor?.value?.trim() || ""
        };
      }
    });

    if (!result.result.code) {
      throw new Error("The code editor is empty or not available. Refresh LeetCode and try again.");
    }

    status.textContent = "Uploading solution...";
    const response = await chrome.runtime.sendMessage({
      type: "accepted-submission",
      submission: { ...result.result, url: tab.url }
    });
    if (!response?.ok) {
      throw new Error(response?.error || "The upload failed.");
    }
    status.textContent = `Saved ${response.result.path}`;
  } catch (error) {
    status.textContent = error.message;
    status.style.color = "#a73e23";
  }
});
