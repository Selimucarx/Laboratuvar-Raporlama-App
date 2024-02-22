import React, { useState } from "react";
import Add from "../components/Add";
import List from "../components/List";
import "../styles/Laborant.css";

function LaborantAddForm() {
  const [dataUpdated, setDataUpdated] = useState(false);

  const laborantFields = [
    { name: "firstName", type: "text", placeholder: "Laborant Adi" },
    { name: "lastName", type: "text", placeholder: "Laborant Soyadi" },
    { name: "hospitalNumber", type: "text", placeholder: "Hastane Numarasi" },
  ];

  const laborantsColumns = [
    { header: "UUID", render: (item) => item.uuid },
    { header: "Laborant Adi", render: (item) => item.firstName },
    { header: "Laborant Soyadi", render: (item) => item.lastName },
    { header: "Hastane Numarasi", render: (item) => item.hospitalNumber },
  ];

  const handleAddSuccess = (data) => {
    console.log("Laborant eklendi!", data);
  };

  const handleAddError = (error) => {
    console.error("Laborant eklenirken hata!", error);
  };

  const handleAddUpdate = () => {
    setDataUpdated((prevState) => !prevState);
  };

  return (
    <div className="laborant">
      <Add
        fields={laborantFields}
        apiEndpoint="/laborants"
        onAddSuccess={handleAddSuccess}
        onAddError={handleAddError}
        onAddUpdate={handleAddUpdate}
      />

      <List
        key={dataUpdated}
        endpoint="/laborants"
        searchEndpoint="/laborants/search"
        deleteEndpoint="/laborants"
        columns={laborantsColumns}
      />
    </div>
  );
}
export default LaborantAddForm;
