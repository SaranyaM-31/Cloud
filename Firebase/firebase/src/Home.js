// src/Home.js
import React from "react";
import { signOut } from "firebase/auth";
import { auth } from "./firebase";

export default function Home({ user }) {
  const handleSignOut = async () => {
    try {
      await signOut(auth);
    } catch (err) {
      console.error("Sign out error", err);
    }
  };

  return (
    <div style={{padding:20}}>
      <h1>Welcome, {user.displayName || user.email}!</h1>
      <p>Email: {user.email}</p>
      <button onClick={handleSignOut} style={{padding:"8px 16px"}}>Sign out</button>
    </div>
  );
}
