import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function AddProduct() {
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    name: "",
    description: "",
    price: "",
    quantity: "",
    imageUrl: "",
  });

  const [error, setError] = useState("");

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // ✅ Validation
    if (
      !product.name ||
      !product.description ||
      !product.price ||
      !product.quantity ||
      !product.imageUrl
    ) {
      setError("All fields are required");
      return;
    }

    try {
      await axios.post("http://localhost:8080/api/products", product);
      alert("Product Added Successfully");
      navigate("/admin/products");
    } catch (err) {
      setError("Failed to add product. Server error.");
    }
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h2 className="text-2xl font-bold mb-4">Add Product</h2>

      {error && <p className="text-red-600 mb-3">{error}</p>}

      <form onSubmit={handleSubmit} className="space-y-4">
        <input name="name" placeholder="Name" onChange={handleChange} className="w-full border p-2" />
        <input name="description" placeholder="Description" onChange={handleChange} className="w-full border p-2" />
        <input name="price" type="number" placeholder="Price" onChange={handleChange} className="w-full border p-2" />
        <input name="quantity" type="number" placeholder="Quantity" onChange={handleChange} className="w-full border p-2" />
        <input name="imageUrl" placeholder="Image URL" onChange={handleChange} className="w-full border p-2" />

        <button className="bg-blue-600 text-white px-4 py-2 rounded">
          Add Product
        </button>
      </form>
    </div>
  );
}

export default AddProduct;
