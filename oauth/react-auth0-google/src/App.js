import React from "react";
import Login from "./Login";
import Home from "./Home";
import { useAuth0 } from "@auth0/auth0-react";

function App() {
  const { isAuthenticated, isLoading } = useAuth0();

  if (isLoading) return <div style={{padding:20}}>Loading...</div>;

  return (
    <div style={{ fontFamily: "sans-serif", textAlign: "center", padding: 20 }}>
      {!isAuthenticated ? <Login /> : <Home />}
    </div>
  );
}

export default App;
