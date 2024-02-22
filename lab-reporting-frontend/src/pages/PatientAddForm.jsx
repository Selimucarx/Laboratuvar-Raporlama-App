import React, { useState } from "react";
import Add from "../components/Add";
import List from "../components/List";
import "../styles/Patient.css";

function PatientAddForm() {
  const [dataUpdated, setDataUpdated] = useState(false);

  const patientFields = [
    { name: "firstName", type: "text", placeholder: "Hasta Adi" },
    { name: "lastName", type: "text", placeholder: "Hasta Soyadi" },
    { name: "nationalNumber", type: "text", placeholder: "Hasta TC" },
  ];

  const patientColumns = [
    { header: "UUID", render: (item) => item.uuid },
    { header: "Hasta Adi", render: (item) => item.firstName },
    { header: "Hasta Soyadi", render: (item) => item.lastName },
    { header: "Hasta TC", render: (item) => item.nationalNumber },
  ];

  const handleAddSuccess = (data) => {
    console.log("Hasta eklendi!", data);
  };

  const handleAddError = (error) => {
    console.error("Hasta eklenirken hata!", error);
  };

  const handleAddUpdate = () => {
    setDataUpdated((prevState) => !prevState);
  };

  return (
    <div className="patient">
      <Add
        fields={patientFields}
        apiEndpoint="/patients"
        onAddSuccess={handleAddSuccess}
        onAddError={handleAddError}
        onAddUpdate={handleAddUpdate}
      />
      <List
        key={dataUpdated}
        endpoint="/patients"
        searchEndpoint="/patients/search"
        deleteEndpoint="/patients"
        columns={patientColumns}
        refreshKey={dataUpdated}
      />
    </div>
  );
}

export default PatientAddForm;
