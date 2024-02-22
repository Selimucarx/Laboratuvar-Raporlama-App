import React, { useState } from "react";
import axios from "axios";
import "../styles/Login.css";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/login",
        {
          username,
          password,
        }
      );
      console.log("Login successful:", response.data);
      localStorage.setItem("jwtToken", response.data);
      navigate("/patient");
    } catch (error) {
      console.error("Login error:", error);
    }
  };

  return (
    <div className="login">
      <form onSubmit={handleSubmit}>
        <div>
          <input
            type="text"
            value={username}
            placeholder="Kullanici Adi"
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div>
          <input
            type="password"
            value={password}
            placeholder="Sifre"
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Giris Yap</button>
      </form>
    </div>
  );
};

export default Login;
