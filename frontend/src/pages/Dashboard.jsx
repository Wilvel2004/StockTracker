    import { useCallback, useEffect, useMemo, useState } from "react";
    import { getMarketAssets } from "../api/assetApi";
    import AssetCard from "../components/AssetCard";
    import "./Dashboard.css";
    import { deleteAsset } from "../api/assetApi";
    import AddAssetModal from "../components/AddAssetModal";
    import AssetSearch from "../components/AssetSearch";

    function Dashboard() {
        const [assets, setAssets] = useState([]);
        const [loading, setLoading] = useState(true);
        const [error, setError] = useState("");
        const [showModal, setShowModal] = useState(false);

        const loadAssets = useCallback(async () => {
            try {
                setLoading(true);
                setError("");

                const response = await getMarketAssets();
                setAssets(response.data);
            } catch (err) {
                console.error(err);
                setError("No se pudieron cargar los activos.");
            } finally {
                setLoading(false);
            }
        }, []);

        useEffect(() => {
            loadAssets();
        }, [loadAssets]);

        const totalAssets = useMemo(() => assets.length, [assets]);

        const cryptoCount = useMemo(
            () => assets.filter((asset) => asset.type === "CRYPTO").length,
            [assets]
        );

        const stockCount = useMemo(
            () => assets.filter((asset) => asset.type === "STOCK").length,
            [assets]
        );

        async function handleDelete(id) {

        const confirmed = window.confirm(
            "¿Seguro que quieres eliminar este activo?"
        );

        if (!confirmed) {
            return;
        }

        try {

            await deleteAsset(id);

            loadAssets();

        } catch (error) {

            console.error(error);

        }

    }

    async function copyAssetsToClipboard() {

        const cryptos = assets.filter(asset => asset.type === "CRYPTO");

        const stocks = assets.filter(asset => asset.type === "STOCK");

        let text = "";

        if (cryptos.length > 0) {

            text += "Cryptos\n\n";

            cryptos.forEach(asset => {

                text += `${asset.symbol}: $${asset.currentPrice.toFixed(2)} ${asset.changePercent >= 0 ? "▲" : "▼"} ${asset.changePercent >= 0 ? "+" : ""}${asset.changePercent.toFixed(2)}%\n`;

            });

        }

        if (stocks.length > 0) {

            if (cryptos.length > 0) {

                text += "\n";

            }

            text += "Stocks\n\n";

            stocks.forEach(asset => {

                text += `${asset.symbol}: $${asset.currentPrice.toFixed(2)} ${asset.changePercent >= 0 ? "▲" : "▼"} ${asset.changePercent >= 0 ? "+" : ""}${asset.changePercent.toFixed(2)}%\n`;

            });

        }

        try {

            await navigator.clipboard.writeText(text);

            alert("✅ Lista copiada al portapapeles");

        } catch (error) {

            console.error(error);

            alert("No se pudo copiar la lista.");

        }

    }

        return (
            <div className="dashboard-page">
                <header className="dashboard-hero">
                    <div>
                        <p className="dashboard-kicker">Personal market tracker</p>
                        <h1 className="dashboard-title">StockTracker</h1>
                        <p className="dashboard-subtitle">
                            Sigue tus acciones y criptomonedas favoritas en una sola pantalla.
                        </p>
                    </div>

                    <div className="dashboard-actions">
                        <button className="btn btn-secondary" onClick={loadAssets}>
                            Actualizar precios
                        </button>
                        <button
                            className="btn btn-secondary"
                            onClick={copyAssetsToClipboard}
                        >
                            Copiar lista
                        </button>
                        <button
                            className="btn btn-primary"
                            onClick={() => setShowModal(true)}
                        >
                            Añadir activo
                        </button>
                    </div>
                </header>

                <section className="dashboard-toolbar">
                    <AssetSearch
                        onAssetAdded={loadAssets}
                    />
                </section>

                <section className="dashboard-stats">
                    <div className="stat-card">
                        <span className="stat-label">Activos</span>
                        <strong className="stat-value">{totalAssets}</strong>
                    </div>
                    <div className="stat-card">
                        <span className="stat-label">Stocks</span>
                        <strong className="stat-value">{stockCount}</strong>
                    </div>
                    <div className="stat-card">
                        <span className="stat-label">Cryptos</span>
                        <strong className="stat-value">{cryptoCount}</strong>
                    </div>
                </section>

                <main className="dashboard-content">
                    {loading && (
                        <div className="state-box">
                            <p>Cargando precios...</p>
                        </div>
                    )}

                    {!loading && error && (
                        <div className="state-box state-error">
                            <p>{error}</p>
                        </div>
                    )}

                    {!loading && !error && assets.length === 0 && (
                        <div className="state-box">
                            <p>No hay activos todavía. Añade el primero para empezar.</p>
                        </div>
                    )}

                    {!loading && !error && assets.length > 0 && (
                        <div className="asset-grid">
                            {assets.map((asset) => (
                            <AssetCard
                                key={asset.id}
                                asset={asset}
                                onDelete={handleDelete}
                            />
                        ))}
                        </div>
                    )}
                </main>
                {
        showModal && (

            <AddAssetModal

                onClose={() => setShowModal(false)}

                onAssetCreated={loadAssets}

            />

        )
    }
            </div>
        );
    }

    export default Dashboard;