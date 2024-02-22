import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/v1';

const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
});

axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem('jwtToken');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

const apiService = {
  get: async (endpoint) => {
    try {
      const response = await axiosInstance.get(endpoint);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
  post: async (endpoint, data, isFormData = false) => {
    try {
      const config = isFormData ? { headers: { 'Content-Type': 'multipart/form-data' } } : {};
      const response = await axiosInstance.post(endpoint, data, config);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
  put: async (endpoint, data, isFormData = false) => {
    try {
      const config = {};
      if (isFormData) {
        config.headers = {
          'Content-Type': 'multipart/form-data'
        };
      }

      const response = await axiosInstance.put(endpoint, data, config);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
  patch: async (endpoint, data) => {
    try {
      const response = await axiosInstance.patch(endpoint, data);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
  delete: async (endpoint) => {
    try {
      const response = await axiosInstance.delete(endpoint);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
  
};

export default apiService;
