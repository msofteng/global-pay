import templateFiles from "./templates.js";

export async function loadTemplates() {
  const list = await Promise.all(
    templateFiles.map(path =>
      fetch(path).then(res => {
        if (!res.ok) console.error("ERRO carregando:", path);
        return res.text();
      })
    )
  );

  document.getElementById("templates").innerHTML = list.join("\n");
}