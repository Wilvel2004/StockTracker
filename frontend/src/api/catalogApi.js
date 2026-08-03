import axios from "axios";


const API_URL = "http://localhost:8081/catalog";


export const searchAssets = (query) => {

    return axios.get(`${API_URL}/search`, {
        params: {
            query: query
        }
    });

};

export const createAsset = (catalogId) => {

    return axios.post(
        "http://localhost:8081/assets",
        {
            catalogId: catalogId
        }
    );

};