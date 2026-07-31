import { useState } from "react";
import { createAsset } from "../api/assetApi";
import "./AddAssetModal.css";

function AddAssetModal({ onClose, onAssetCreated }) {

    const [form, setForm] = useState({
        symbol: "",
        name: "",
        type: "STOCK",
        marketId: ""
    });

    function handleChange(event) {

        const { name, value } = event.target;

        setForm(previous => ({
            ...previous,
            [name]: value
        }));
    }

    async function handleSubmit(event) {

        event.preventDefault();

        try {

            await createAsset(form);

            onAssetCreated();

            onClose();

        } catch (error) {

            console.error(error);

            alert("No se pudo crear el activo");

        }

    }

    return (

        <div className="modal-overlay">

            <div className="modal">

                <h2>Nuevo activo</h2>

                <form onSubmit={handleSubmit}>

                    <label>Símbolo</label>

                    <input
                        name="symbol"
                        value={form.symbol}
                        onChange={handleChange}
                    />

                    <label>Nombre</label>

                    <input
                        name="name"
                        value={form.name}
                        onChange={handleChange}
                    />

                    <label>Tipo</label>

                    <select
                        name="type"
                        value={form.type}
                        onChange={handleChange}
                    >

                        <option value="STOCK">
                            STOCK
                        </option>

                        <option value="CRYPTO">
                            CRYPTO
                        </option>

                    </select>

                    <label>Market ID</label>

                    <input
                        name="marketId"
                        value={form.marketId}
                        onChange={handleChange}
                    />

                    <div className="modal-buttons">

                        <button
                            type="button"
                            onClick={onClose}
                        >
                            Cancelar
                        </button>

                        <button type="submit">

                            Guardar

                        </button>

                    </div>

                </form>

            </div>

        </div>

    );

}

export default AddAssetModal;