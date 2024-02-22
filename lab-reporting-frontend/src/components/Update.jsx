import React, { useState } from "react";
import apiService from "../services/apiService";
import "../styles/Update.css";
import "react-toastify/dist/ReactToastify.css";

function Update() {
  const [reportDetails, setReportDetails] = useState({
    uuid: "",
    fileNumber: "",
    patientFirstName: "",
    patientLastName: "",
    nationalNumber: "",
    diagnosisTitle: "",
    diagnosisDetails: "",
    laborantDTO: {
      uuid: "",
      firstName: "",
      lastName: "",
      hospitalNumber: "",
    },
  });

  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append(
      "report",
      JSON.stringify({
        fileNumber: reportDetails.fileNumber,
        patientFirstName: reportDetails.patientFirstName,
        patientLastName: reportDetails.patientLastName,
        nationalNumber: reportDetails.nationalNumber,
        diagnosisTitle: reportDetails.diagnosisTitle,
        diagnosisDetails: reportDetails.diagnosisDetails,
        laborantDTO: reportDetails.laborantDTO,
      })
    );
    if (selectedFile) {
      formData.append("file", selectedFile);
    }

    try {
      const endpoint = `/reports/${reportDetails.uuid}`;
      const response = await apiService.put(endpoint, formData, true);
      alert("Report güncellendi. Sayfayı yenilemeniz gerekiyor.");
      console.log(response);
    } catch (error) {
      console.error("Report güncellenirken hata oluştu!", error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    if (
      [
        "laborantUUID",
        "laborantFirstName",
        "laborantLastName",
        "hospitalNumber",
      ].includes(name)
    ) {
      let fieldName = name.replace("laborant", "");
      fieldName = fieldName.charAt(0).toLowerCase() + fieldName.slice(1);
      if (fieldName === "uUID") fieldName = "uuid";

      setReportDetails((prevState) => ({
        ...prevState,
        laborantDTO: {
          ...prevState.laborantDTO,
          [fieldName]: value,
        },
      }));
    } else {
      setReportDetails((prevState) => ({
        ...prevState,
        [name]: value,
      }));
    }
  };

  return (
     <div className="update">
    <form onSubmit={handleSubmit}>
      <input
        name="uuid"
        value={reportDetails.uuid}
        onChange={handleInputChange}
        placeholder="UUID"
      />
      <input
        name="fileNumber"
        value={reportDetails.fileNumber}
        onChange={handleInputChange}
        placeholder="Dosya Numarası"
      />
      <input
        name="patientFirstName"
        value={reportDetails.patientFirstName}
        onChange={handleInputChange}
        placeholder="Hasta Adı"
      />
      <input
        name="patientLastName"
        value={reportDetails.patientLastName}
        onChange={handleInputChange}
        placeholder="Hasta Soyadı"
      />
      <input
        name="nationalNumber"
        value={reportDetails.nationalNumber}
        onChange={handleInputChange}
        placeholder="Ulusal Numara"
      />
      <input
        name="diagnosisTitle"
        value={reportDetails.diagnosisTitle}
        onChange={handleInputChange}
        placeholder="Tanı Başlığı"
      />
      <input
        name="diagnosisDetails"
        value={reportDetails.diagnosisDetails}
        onChange={handleInputChange}
        placeholder="Tanı Detayları"
      />
      <input
        name="laborantUUID"
        value={reportDetails.laborantDTO.uuid}
        onChange={handleInputChange}
        placeholder="Laborant UUID"
      />
      <input
        name="laborantFirstName"
        value={reportDetails.laborantDTO.firstName}
        onChange={handleInputChange}
        placeholder="Laborant Adı"
      />
      <input
        name="laborantLastName"
        value={reportDetails.laborantDTO.lastName}
        onChange={handleInputChange}
        placeholder="Laborant Soyadı"
      />
      <input
        name="hospitalNumber"
        value={reportDetails.laborantDTO.hospitalNumber}
        onChange={handleInputChange}
        placeholder="Hastane Numarası"
      />
      <input type="file" onChange={handleFileChange} />

      <button type="submit">Güncelle</button>
    </form>
    </div>
  );
}

export default Update;
