// src/Login.js
import React from "react";
import { auth, provider } from "./firebase";
import { signInWithPopup } from "firebase/auth";

export default function Login() {
  const signInWithGoogle = async () => {
    try {
      await signInWithPopup(auth, provider);
      // onAuthStateChanged in App will handle redirecting to Home
    } catch (err) {
      console.error("Login error", err);
      alert("Login failed: " + err.message);
    }
  };

  return (
    <div style={{display:"flex",height:"100vh",alignItems:"center",justifyContent:"center",flexDirection:"column"}}>
      <h2>Sign in to view Home</h2>
      <button onClick={signInWithGoogle} style={{padding:"10px 20px",fontSize:16}}>
        Sign in with Google
      </button>
    </div>
  );
}
