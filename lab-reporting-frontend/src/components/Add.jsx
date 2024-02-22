import React, { useState } from "react";
import apiService from "../services/apiService";

function Add({ fields, apiEndpoint, onAddSuccess, onAddError, onAddUpdate }) {
  const [formData, setFormData] = useState(
    fields.reduce((acc, field) => ({ ...acc, [field.name]: "" }), {})
  );

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await apiService.post(apiEndpoint, formData);
      onAddSuccess(response);
      onAddUpdate();
    } catch (error) {
      onAddError(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      {fields.map((field) => (
        <div key={field.name}>
          <label htmlFor={field.name}>{field.label}</label>
          <input
            type={field.type}
            name={field.name}
            value={formData[field.name]}
            onChange={handleChange}
            placeholder={field.placeholder}
          />
        </div>
      ))}
      <button type="submit">Ekle</button>
    </form>
  );
}

export default Add;
