import "./AssetCard.css";

function AssetCard({ asset, onDelete }) {

    const formattedPrice = Number(asset.currentPrice).toLocaleString("en-US", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2,
    });


    const change = Number(asset.changePercent);

    const formattedChange = Math.abs(change).toFixed(2);


    const changeClass =
        change > 0
            ? "positive"
            : change < 0
                ? "negative"
                : "neutral";


    const changeIcon =
        change > 0
            ? "▲"
            : change < 0
                ? "▼"
                : "➖";


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

                <div className="price-row">

                    <strong className="asset-price">
                        ${formattedPrice}
                    </strong>

                    <div className={`asset-change ${changeClass}`}>
                        <span>
                            {changeIcon}
                        </span>

                        <span>
                            {change > 0 && "+"}
                            {change < 0 && "-"}
                            {formattedChange}%
                        </span>
                    </div>

                </div>

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