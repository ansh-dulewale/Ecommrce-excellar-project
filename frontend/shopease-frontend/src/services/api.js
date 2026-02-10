import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/api",
});

// PRODUCTS
export const getProducts = () => API.get("/products");
export const getProductById = (id) => API.get(`/products/${id}`);
export const addProduct = (product) => API.post("/products", product);
export const updateProduct = (id, product) =>
  API.put(`/products/${id}`, product);
export const deleteProduct = (id) => API.delete(`/products/${id}`);

// ORDERS
export const getOrders = () => API.get("/orders");
