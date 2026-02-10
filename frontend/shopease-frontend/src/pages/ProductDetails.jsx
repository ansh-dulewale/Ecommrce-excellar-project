import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../services/api";

const ProductDetails = () => {
  const { id } = useParams();

  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchProduct();
  }, []);

  const fetchProduct = async () => {
    try {
      const response = await api.get(`/products/${id}`);
      setProduct(response.data);
    } catch (err) {
      setError("Product not found");
    } finally {
      setLoading(false);
    }
  };

  const handleAddToCart = async () => {
    try {
      await api.post("/cart/add", {
        productId: product.id,
        quantity: 1,
      });
      alert("Added to cart");
    } catch (err) {
      alert("Please login first");
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen flex justify-center items-center">
        <p className="text-xl font-semibold">Loading...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen flex justify-center items-center">
        <p className="text-red-500 text-xl">{error}</p>
      </div>
    );
  }

  return (
    <div className="bg-gray-100 min-h-screen py-10">
      <div className="max-w-6xl mx-auto bg-white p-8 rounded-xl shadow-md flex flex-col md:flex-row gap-10">

        {/* Image Section */}
        <div className="flex-1 flex justify-center">
          <img
            src={product.imageUrl}
            alt={product.name}
            className="h-80 object-contain"
          />
        </div>

        {/* Details Section */}
        <div className="flex-1">
          <h2 className="text-3xl font-bold mb-4">
            {product.name}
          </h2>

          <p className="text-green-600 text-2xl font-semibold mb-4">
            ₹{product.price}
          </p>

          <p className="text-gray-600 mb-6">
            {product.description}
          </p>

          <button
            onClick={handleAddToCart}
            className="bg-yellow-500 px-6 py-3 rounded-lg font-semibold mr-4 hover:bg-yellow-600 transition"
          >
            Add to Cart
          </button>

          <button className="bg-orange-500 text-white px-6 py-3 rounded-lg font-semibold hover:bg-orange-600 transition">
            Buy Now
          </button>
        </div>

      </div>
    </div>
  );
};

export default ProductDetails;
