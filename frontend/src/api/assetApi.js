import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8081"
});

export const getMarketAssets = () => api.get("/assets/market");

export const getAssets = () => api.get("/assets");

export const createAsset = (asset) => api.post("/assets", asset);

export const deleteAsset = (id) => api.delete(`/assets/${id}`);