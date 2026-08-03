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
        const [toastMessage, setToastMessage] = useState("");
        const [toastTimer, setToastTimer] = useState(null);

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

function showToastMessage(message) {

    setToastMessage(message);

    if (toastTimer) {
        clearTimeout(toastTimer);
    }

    const timer = setTimeout(() => {
        setToastMessage("");
    }, 2500);

    setToastTimer(timer);
}

async function copyAssetsToClipboard(type) {

    let filteredAssets = assets;


    if (type === "STOCK") {

        filteredAssets = assets.filter(
            asset => asset.type === "STOCK"
        );

    }


    if (type === "CRYPTO") {

        filteredAssets = assets.filter(
            asset => asset.type === "CRYPTO"
        );

    }


    const cryptos = filteredAssets.filter(
        asset => asset.type === "CRYPTO"
    );


    const stocks = filteredAssets.filter(
        asset => asset.type === "STOCK"
    );


    let text = "📊 StockTracker\n\n";


    function formatAsset(asset) {

        const change = Number(asset.changePercent ?? 0);

        const arrow =
            change > 0
                ? "▲"
                : change < 0
                    ? "▼"
                    : "➖";


        const sign =
            change > 0
                ? "+"
                : "";


        return `${asset.symbol}: $${Number(asset.currentPrice).toLocaleString(
            "en-US",
            {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2
            }
        )} ${arrow} ${sign}${change.toFixed(2)}%\n\n`;
    }


    if (stocks.length > 0) {

        text += "📈 Stocks\n\n";

        stocks.forEach(asset => {
            text += formatAsset(asset);
        });

    }


    if (cryptos.length > 0) {

        if (stocks.length > 0) {
            text += "\n";
        }

        text += "🪙 Cryptos\n\n";

        cryptos.forEach(asset => {
            text += formatAsset(asset);
        });

    }


    try {

        await navigator.clipboard.writeText(text);

        showToastMessage("✅ Lista copiada al portapapeles");


    } catch (error) {

        console.error(error);

        showToastMessage("❌ No se pudo copiar la lista.");

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
                        <select
                            className="copy-select"
                            onChange={(e) => copyAssetsToClipboard(e.target.value)}
                            defaultValue=""
                        >
                            <option value="" disabled>
                                Copiar lista
                            </option>

                            <option value="ALL">
                                📊 Todo
                            </option>

                            <option value="STOCK">
                                📈 Stocks
                            </option>

                            <option value="CRYPTO">
                                🪙 Cryptos
                            </option>

                        </select>
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
    {
    toastMessage && (
        <div className="toast">
            {toastMessage}
        </div>
    )
    }
            </div>
        );
    }

    export default Dashboard;