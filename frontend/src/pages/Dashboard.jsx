import { useEffect, useState } from "react";

import { getMarketAssets } from "../api/assetApi";

import "./Dashboard.css";

function Dashboard() {

    const [assets, setAssets] = useState([]);

    useEffect(() => {

        loadAssets();

    }, []);

    async function loadAssets() {

    try {

        const response = await getMarketAssets();

        console.log("Respuesta completa:", response);

        console.log("Datos:", response.data);

        setAssets(response.data);

    } catch (error) {

        console.error(error);

    }

}

    return (
    <div>

        <h1>StockTracker</h1>

        {
            assets.map(asset => (
                <div key={asset.id}>
                    <h2>{asset.symbol}</h2>
                    <p>{asset.name}</p>
                    <p>{asset.type}</p>
                    <strong>${asset.currentPrice}</strong>
                </div>
            ))
        }

    </div>
);
}

export default Dashboard;