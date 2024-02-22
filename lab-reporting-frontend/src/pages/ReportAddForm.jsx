import React, { useState } from "react";
import apiService from "../services/apiService";
import List from "../components/List";
import "../styles/Report.css";
import Update from "../components/Update";

function ReportAddForm() {
  const [formData, setFormData] = useState({});

  const reportFields = [
    { name: "fileNumber", type: "text", placeholder: "Dosya Numarası" },
    { name: "patientFirstName", type: "text", placeholder: "Hasta Adı" },
    { name: "patientLastName", type: "text", placeholder: "Hasta Soyadı" },
    { name: "nationalNumber", type: "text", placeholder: "TC Kimlik No" },
    { name: "diagnosisTitle", type: "text", placeholder: "Tanı Başlığı" },
    { name: "diagnosisDetails", type: "text", placeholder: "Tanı Detayları" },
    { name: "laborantUuid", type: "text", placeholder: "Laborant UUID" },
    { name: "laborantFirstName", type: "text", placeholder: "Laborant Adı" },
    { name: "laborantLastName", type: "text", placeholder: "Laborant Soyadı" },
    { name: "hospitalNumber", type: "text", placeholder: "Hastane Numarası" },
    { name: "reportImage", type: "file", placeholder: "Rapor Resmi" },
  ];

  const reportColumns = [
    { header: "UUID", render: (item) => item.uuid },
    { header: "Dosya Numarasi", render: (item) => item.fileNumber },
    { header: "Hasta Adi", render: (item) => item.patientFirstName },
    { header: "Hasta Soyadi", render: (item) => item.patientLastName },
    { header: "TC ", render: (item) => item.nationalNumber },
    { header: "Tani Basligi", render: (item) => item.diagnosisTitle },
    { header: "Tani Detaylari", render: (item) => item.diagnosisDetails },
    {
      header: "Laborant UUID",
      render: (item) => (item.laborantDTO ? item.laborantDTO.uuid : ""),
    },
    {
      header: "Laborant Adı",
      render: (item) => (item.laborantDTO ? item.laborantDTO.firstName : ""),
    },
    {
      header: "Laborant Soyadı",
      render: (item) => (item.laborantDTO ? item.laborantDTO.lastName : ""),
    },
    {
      header: "Hastane Numarası",
      render: (item) =>
        item.laborantDTO ? item.laborantDTO.hospitalNumber : "",
    },
    {
      header: "Rapor Resmi",
      render: (item) => (
        item.reportImage ? 
          <img src={`data:image/png;base64,${item.reportImage}`} alt="Rapor Resmi"         style={{ width: '30px', height: '30px' }} 
          /> 
          : null
      )
    }
      ];

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    setFormData((prev) => ({ ...prev, [name]: files ? files[0] : value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const dataToSend = new FormData();
    dataToSend.append(
      "report",
      JSON.stringify({
        fileNumber: formData.fileNumber,
        patientFirstName: formData.patientFirstName,
        patientLastName: formData.patientLastName,
        nationalNumber: formData.nationalNumber,
        diagnosisTitle: formData.diagnosisTitle,
        diagnosisDetails: formData.diagnosisDetails,
        laborantDTO: {
          uuid: formData.laborantUuid,
          firstName: formData.laborantFirstName,
          lastName: formData.laborantLastName,
          hospitalNumber: formData.hospitalNumber,
        },
      })
    );
    if (formData.reportImage) {
      dataToSend.append("file", formData.reportImage);
    }

    try {
      const response = await apiService.post("/reports", dataToSend, true);
      console.alert(
        "Report eklendi Doruk abi sayfayi yenilemen gerekiyor :(",
        response
      );
    } catch (error) {
      console.error("Report eklenirken hata!", error);
    }
  };

  return (
    <div className="report">
      <form onSubmit={handleSubmit}>
        {reportFields.map((field) => (
          <div key={field.name}>
            <label htmlFor={field.name}>{field.placeholder}</label>
            <input
              type={field.type}
              name={field.name}
              onChange={handleChange}
              placeholder={field.placeholder}
            />
          </div>
        ))}
        <button type="submit">Rapor Ekle</button>
      </form>
      <List
        endpoint="/reports"
        searchEndpoint="/reports/search"
        deleteEndpoint="/reports"
        columns={reportColumns}
        showSortButtons={true}
      />
      <Update />
    </div>
  );
}

export default ReportAddForm;
