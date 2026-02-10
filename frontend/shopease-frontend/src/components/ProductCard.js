function ProductCard({ product }) {
  return (
    <div className="border rounded-lg shadow hover:shadow-lg transition p-4 bg-white">
      
      <img
        src={product.imageUrl}
        alt={product.name}
        className="h-40 w-full object-contain mb-4"
      />

      <h2 className="text-lg font-semibold">{product.name}</h2>

      <p className="text-gray-600 text-sm mb-2">
        {product.description}
      </p>

      <p className="text-xl font-bold text-green-600 mb-2">
        ₹{product.price}
      </p>

      <button className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 w-full">
        Add to Cart
      </button>
    </div>
  );
}

export default ProductCard;
