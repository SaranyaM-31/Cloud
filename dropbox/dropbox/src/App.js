import React, { useState } from "react";

function App() {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState("");

  const ACCESS_TOKEN = "sl.u.AGAzR63a38MYXTp5sl6Aq7Qx44bf7GOdG8gww_2uBuL6y8lKUVmu7CIqiobFd5JfJmL3ORWlgJiG5HTRozM8VKQXxWs9H6OmNBZ5xrLv1Q81g_Npq-znwSR1M7JrUDcz7I3Y9C5HxiOZjlfhgxaAx0cwligfhBn8T0mqNw4t7CFuCUpyyV37iMyX40bFxM-nG184DwXEZmYLuMFiSmHV6dRLRBKEnx_e8AsabTjT_x4RyNns6Js_H4659HRbW14qA9ptzH3uQ1_pBhoUxnzwv1sAKR9prkMsm-ZqJ8prq7IBgtMLvWQ3iXDR4pckw90BrQH1hAWjLcDyvlqUjletx9kTY3QmjVMFcJIvjH9__0942Zxwdu7nZ9F7jeBjS0t9yL0cri8DTgLrsHnMCFVF8PnnfAxf245Ufs9xjPTsuO8X3J-x_eifqYppTJr6NGZP9rOV2mFOOsz_wwJQe3YmZWE1OIlavfA2Hdt2vcswXR816StG6QiKt-zl_a7ZOukKwuv7pwKdTxY83WQ2rBD5gGLWoXxiweTsxq0dlktVbkZrbiI31cvhTSPdplL2LG0HaHrljvCsE-Jtred9nViqM6iwnbSXVna4F4k0NuC-JJpvZEV1KdRwhlA-0bS6HMs52OL8jK9NO7zGEd6LVY8VlmhSA7Q9l-gd5XN4odr8TbyddchvqYpvQbbEG-aUFjY4jDAV0wrYPrtblYm26FT5Eu40YmqFa2_BeiM0e4ySUUguUKz1-J2kStF5HttrhhxWgQVu5ZKT9ZfLiDHvHKPPpIVy5jnwfybXlDhEAxrR-ujY8Hvz9rd7dKZvpI11yW4KQIbj_qrJF62bdkvDDUSO_VcWOcO3ti4VazHHAS2EknVN7p0kWZId97_HW-axSJxem0BYjpza3u6ZmMCb4ymEBZs3pcdNOSFMEQU29fHPRhPCSRBAIgRaiZ_DDT-GC1jT1XhgZ0p8mDWNjriQfG6ZMboNU095smVZlTOhDnTDXJagO4sTEEHgnofJxWELPsIZoywJCGBQAx6GV3MoOmxBV4chQ7aR7XzBVvFDWwSDuYhG39LcxxZXa10kwYuVyT0XJwIYAIz9T3tWA5x8L894gHEB2DxJ7In2F3RObzp3izlKSS-Ep_4ih9yO8C4g0KJ_n1fVgp7dodDuHRxOTCmdxewEL5cMDQ16IzRVQwEpdsPVCvRbbymKiyu208Waz8r2EwSb1HRPZlgAf9mB0O73-ZU1CuwMSbwLnSfs-FeNwEseyHuckTuz8S4S-4xMTgC23zrCq0zhFPGykuQm2jfOVch5fhfBanqo0ip6gKD8_ssE0dkzvcTTWlIbEhedfV6aafI7WpctEnETKt1EhOckZE5Pp-2k9ScTessoRXREGZSo-9IIO3GkuHIITfxi1AsXONQCOotJN1oN3TV5vrUyuHfU"
  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleUpload = async () => {
  if (!file) return alert("Please select a file first!");

  try {
    const res = await fetch("https://content.dropboxapi.com/2/files/upload", {
      method: "POST",
      headers: {
        "Authorization": `Bearer ${ACCESS_TOKEN}`,
        "Dropbox-API-Arg": JSON.stringify({
          path: "/" + file.name,
          mode: "add",
          autorename: true,
          mute: false,
        }),
        "Content-Type": "application/octet-stream",
      },
      body: file, // raw File object
    });

    // Check if response is JSON
    const text = await res.text();
    try {
      const data = JSON.parse(text); // parse if valid JSON
      console.log(data);
      if (data.id) setMessage("File uploaded successfully!");
      else setMessage("Upload failed, check console.");
    } catch {
      console.error("Dropbox API returned:", text);
      setMessage("Upload failed: " + text);
    }
  } catch (err) {
    console.error(err);
    setMessage("Error: " + err);
  }
};


  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Dropbox Assignment Upload</h1>
      <input type="file" onChange={handleFileChange} />
      <br /><br />
      <button onClick={handleUpload}>Upload to Dropbox</button>
      <br /><br />
      {message && <p>{message}</p>}
    </div>
  );
}

export default App;
