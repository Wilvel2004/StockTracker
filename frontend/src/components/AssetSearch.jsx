import { useState } from "react";
import { Search, Plus } from "lucide-react";
import { searchAssets } from "../api/catalogApi";
import { createAsset } from "../api/assetApi";
import "./AssetSearch.css";

function AssetSearch({ onAssetAdded }) {

    const [query, setQuery] = useState("");
    const [results, setResults] = useState([]);

    const handleSearch = async (value) => {

        setQuery(value);

        if (value.length < 2) {
            setResults([]);
            return;
        }

        try {

            const response = await searchAssets(value);

            setResults(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    const handleAdd = async (catalogId) => {

        try {

            await createAsset(catalogId);

            setQuery("");
            setResults([]);

            onAssetAdded();

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="asset-search">

            <div className="search-box">

                <Search size={20} className="search-icon" />

                <input
                    type="text"
                    placeholder="Buscar por nombre o símbolo..."
                    value={query}
                    onChange={(e) => handleSearch(e.target.value)}
                />

            </div>

{query.length >= 2 && (

    <div className="search-results">

        {results.length === 0 ? (

            <div className="empty-results">
                🔍 No se encontraron activos.
            </div>

        ) : (

            results.map(asset => (

                <div
                    key={asset.id}
                    className="search-result"
                >

                    <div className="asset-info">

                        <div className="asset-main">

                            <strong>{asset.symbol}</strong>

                            <span>{asset.name}</span>

                        </div>

                        <span
                            className={`asset-type ${asset.type.toLowerCase()}`}
                        >
                            {asset.type}
                        </span>

                    </div>

                    <button
                        className="add-button"
                        onClick={() => handleAdd(asset.id)}
                    >

                        <Plus size={16} />
                        Añadir

                    </button>

                </div>

            ))

        )}

    </div>

)}

        </div>

    );

}

export default AssetSearch;