import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

export default function Home() {
  const { user, logout } = useAuth0();

  return (
    <div style={{ maxWidth: 700, margin: "40px auto", textAlign: "center" }}>
      <h2>Welcome, {user?.name || "User"}!</h2>

      {user?.picture && (
        <img
          src={user.picture}
          alt="avatar"
          style={{ width: 96, height: 96, borderRadius: "50%", marginTop: 8 }}
        />
      )}

      <div style={{ marginTop: 16, textAlign: "left", display: "inline-block" }}>
        <div><strong>Email:</strong> {user?.email}</div>
        <div><strong>Nickname:</strong> {user?.nickname}</div>
      </div>

      <div style={{ marginTop: 24 }}>
        <button
          onClick={() => logout({ logoutParams: { returnTo: window.location.origin } })}
          style={{ padding: "8px 14px", borderRadius: 6, cursor: "pointer" }}
        >
          Log out
        </button>
      </div>
    </div>
  );
}
