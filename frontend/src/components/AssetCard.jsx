import "./AssetCard.css";

function AssetCard({ asset }) {

    return (
        <div className="asset-card">

            <div className="asset-header">

                <h2>{asset.symbol}</h2>

                <span>
                    {asset.type}
                </span>

            </div>


            <p>
                {asset.name}
            </p>


            <h3>
                ${Number(asset.currentPrice).toFixed(2)}
            </h3>

        </div>
    );
}

export default AssetCard;