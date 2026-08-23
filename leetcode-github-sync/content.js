let lastAcceptedText = "";
let lastCode = "";

const observer = new MutationObserver(() => {
  const pageText = document.body.innerText;
  const accepted = /accepted/i.test(pageText) && pageText !== lastAcceptedText;
  if (!accepted) {
    return;
  }

  lastAcceptedText = pageText;
  captureAndSend();
});

observer.observe(document.body, { childList: true, subtree: true, characterData: true });

async function captureAndSend() {
  for (let attempt = 0; attempt < 5; attempt += 1) {
    const code = readEditorCode();
    if (code && code !== lastCode) {
      lastCode = code;
      chrome.runtime.sendMessage({
        type: "accepted-submission",
        submission: {
          title: readTitle(),
          language: readLanguage(),
          code,
          url: window.location.href
        }
      });
      return;
    }
    await new Promise((resolve) => setTimeout(resolve, 500));
  }
  chrome.runtime.sendMessage({ type: "sync-error", error: "Accepted, but the code editor was not readable." });
}

function readTitle() {
  const heading = document.querySelector("h1");
  return heading?.textContent?.replace(/^\d+\.\s*/, "").trim() || location.pathname.split("/")[2] || "leetcode-solution";
}

function readLanguage() {
  const languageButton = [...document.querySelectorAll("button")].find((button) => /^(C\+\+|Java|Python|Python3|JavaScript|TypeScript|Go|Rust|C#|Kotlin|Swift|Ruby|PHP)$/i.test(button.textContent.trim()));
  return languageButton?.textContent.trim() || "text";
}

function readEditorCode() {
  const lines = [...document.querySelectorAll(".view-lines .view-line, pre code, textarea")];
  const code = lines.map((line) => line.value ?? line.textContent ?? "").join("\n").trim();
  return code.length > 20 ? code : "";
}
