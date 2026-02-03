import { useEffect, useState } from "react";
import axios from "axios";

const Products = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/products")
            .then(res => setProducts(res.data))
            .catch(err => console.log(err));
    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h2>Products</h2>

            <div style={{ display: "flex", gap: "20px", flexWrap: "wrap" }}>
                {products.map(p => (
                    <div key={p.id} style={{ border: "1px solid #ccc", padding: "10px" }}>
                        <img src={p.imageUrl} alt={p.name} width="150" />
                        <h4>{p.name}</h4>
                        <p>₹{p.price}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Products;
