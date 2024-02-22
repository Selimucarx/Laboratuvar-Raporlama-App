import React, { useEffect, useState, useCallback } from "react";
import apiService from "../services/apiService";
import Delete from "./Delete";
import "react-toastify/dist/ReactToastify.css";

function List({
  endpoint,
  searchEndpoint,
  deleteEndpoint,
  columns,
  showSortButtons = false,
}) {
  const [data, setData] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");

  const fetchData = useCallback(async () => {
    try {
      const fullEndpoint = searchQuery
        ? `${searchEndpoint}?searchQuery=${encodeURIComponent(searchQuery)}`
        : endpoint;
      const result = await apiService.get(fullEndpoint);
      setData(result);
    } catch (error) {
      console.error("Data loading error", error);
      setData([]);
    }
  }, [endpoint, searchEndpoint, searchQuery]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleDeleteSuccess = (response) => {
    console.log("Delete success:", response);
    fetchData();
  };

  const handleDeleteError = (error) => {
    console.error("Delete error:", error);
  };

  const handleSortAscending = async () => {
    try {
      const result = await apiService.get(`${endpoint}/sorted/asc`);
      setData(result);
    } catch (error) {
      console.error("Error fetching sorted data", error);
    }
  };

  const handleSortDescending = async () => {
    try {
      const result = await apiService.get(`${endpoint}/sorted/desc`);
      setData(result);
    } catch (error) {
      console.error("Error fetching sorted data", error);
    }
  };

  return (
    <div>
      <input
        className="search-input"
        type="text"
        placeholder="Arama cubugu"
        value={searchQuery}
        onChange={handleSearchChange}
      />

      {showSortButtons && (
        <div>
          <button onClick={handleSortAscending}>ASC</button>
          <button onClick={handleSortDescending}>DESC</button>
        </div>
      )}

      <table>
        <tbody>
          {data.length > 0 ? (
            data.map((item, rowIndex) => (
              <tr key={rowIndex}>
                {columns.map((col, colIndex) => (
                  <td key={colIndex}>{col.render(item)}</td>
                ))}
                <td>
                  <Delete
                    endpoint={deleteEndpoint}
                    itemId={item.uuid}
                    onDeleteSuccess={handleDeleteSuccess}
                    onDeleteError={handleDeleteError}
                  />
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={columns.length + 1}>Veri yok</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}
export default List;
