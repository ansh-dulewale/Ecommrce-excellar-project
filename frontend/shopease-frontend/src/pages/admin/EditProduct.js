import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function EditProduct() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    name: "",
    price: "",
    imageUrl: "",
    description: "",
  });

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/products/${id}`)
      .then((res) => setProduct(res.data));
  }, [id]);

  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    await axios.put(
      `http://localhost:8080/api/products/${id}`,
      product
    );

    navigate("/admin/products");
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Edit Product</h1>

      <form onSubmit={handleSubmit} className="space-y-3">
        <input
          name="name"
          value={product.name}
          onChange={handleChange}
          placeholder="Name"
          className="border p-2 w-full"
        />

        <input
          name="price"
          value={product.price}
          onChange={handleChange}
          placeholder="Price"
          className="border p-2 w-full"
        />

        <input
          name="imageUrl"
          value={product.imageUrl}
          onChange={handleChange}
          placeholder="Image URL"
          className="border p-2 w-full"
        />

        <textarea
          name="description"
          value={product.description}
          onChange={handleChange}
          placeholder="Description"
          className="border p-2 w-full"
        />

        <button className="bg-green-600 text-white px-4 py-2 rounded">
          Update Product
        </button>
      </form>
    </div>
  );
}

export default EditProduct;
