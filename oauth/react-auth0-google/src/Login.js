import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

export default function Login() {
  const { loginWithRedirect } = useAuth0();

  const handleGoogle = async () => {
    // connection: 'google-oauth2' asks Auth0 to use Google directly
    await loginWithRedirect({ connection: "google-oauth2" });
  };

  return (
    <div style={{ maxWidth: 420, margin: "60px auto", textAlign: "center" }}>
      <h1>React + Auth0 (Exam demo)</h1>
      <p>Sign in to continue</p>
      <button
        onClick={handleGoogle}
        style={{
          padding: "10px 18px",
          fontSize: 16,
          borderRadius: 6,
          cursor: "pointer"
        }}
      >
        Sign in with Google
      </button>
      <p style={{ marginTop: 18, color: "#666" }}>
        (This opens Auth0's Universal Login which will redirect to Google.)
      </p>
    </div>
  );
}
