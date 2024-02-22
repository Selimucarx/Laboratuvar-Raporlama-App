import React from "react";
import apiService from "../services/apiService";

function Delete({ endpoint, itemId, onDeleteSuccess, onDeleteError }) {
  const handleDelete = async () => {
    try {
      const response = await apiService.delete(`${endpoint}/${itemId}`);
      onDeleteSuccess(response);
    } catch (error) {
      onDeleteError(error);
    }
  };

  return <button onClick={handleDelete}>Sil</button>;
}

export default Delete;
