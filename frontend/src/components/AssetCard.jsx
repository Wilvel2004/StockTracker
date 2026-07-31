import "./AssetCard.css";

function AssetCard({ asset, onDelete }) {

    const formattedPrice = Number(asset.currentPrice).toLocaleString("en-US", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
    });

    return (
        <article className="asset-card">

            <div className="asset-card-top">

                <div>
                    <p className="asset-symbol">{asset.symbol}</p>
                    <h3 className="asset-name">{asset.name}</h3>
                </div>

                <span
                    className={`asset-badge ${
                        asset.type === "CRYPTO" ? "crypto" : "stock"
                    }`}
                >
                    {asset.type}
                </span>

            </div>

            <div className="asset-card-bottom">

                <span className="asset-label">
                    Current price
                </span>

                <strong className="asset-price">
                    ${formattedPrice}
                </strong>

                <button
                    className="delete-btn"
                    onClick={() => onDelete(asset.id)}
                >
                    🗑 Eliminar
                </button>

            </div>

        </article>
    );
}

export default AssetCard;