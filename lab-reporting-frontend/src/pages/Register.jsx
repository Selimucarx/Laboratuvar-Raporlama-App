import React, { useState } from "react";
import axios from "axios";
import "../styles/Register.css";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [authority, setAuthority] = useState("ROLE_USER");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/users/register",
        {
          username,
          password,
          authorities: [authority],
        }
      );
      console.log("User registered:", response.data);
      navigate("/login");
    } catch (error) {
      console.error("Registration error:", error);
    }
  };

  return (
    <div className="register">
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
        <div>
          <select
            value={authority}
            onChange={(e) => setAuthority(e.target.value)}
          >
            <option value="ROLE_USER">User</option>
            <option value="ROLE_ADMIN">Admin</option>
          </select>
        </div>
        <button type="submit">Kayit Ol</button>
      </form>
    </div>
  );
};

export default Register;
