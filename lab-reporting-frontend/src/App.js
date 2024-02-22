import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LaborantAddForm from './pages/LaborantAddForm';
import PatientAddForm from './pages/PatientAddForm';
import ReportAddForm from './pages/ReportAddForm';
import Register from './pages/Register';
import Login from './pages/Login';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function App() {
  return (
    <Router>
    <div className="App">
      <Routes>
      <Route path="/" element={<Navigate replace to="/register" />} />
      <Route path="/register" element={<Register/>} />
      <Route path="/login" element={<Login/>} />
        <Route path="/patient" element={<PatientAddForm />} />
        <Route path="/laborant" element={<LaborantAddForm />} />
        <Route path="/report" element={<ReportAddForm />} />
      </Routes>
      <ToastContainer />

    </div>
  </Router>
  );
}

export default App;
