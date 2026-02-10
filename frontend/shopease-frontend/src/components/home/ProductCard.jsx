import { useNavigate } from "react-router-dom";

const ProductCard = ({ product }) => {
  const navigate = useNavigate();

  return (
    <div
      onClick={() => navigate(`/product/${product.id}`)}
      className="bg-white rounded-xl shadow-md hover:shadow-xl transition duration-300 p-4 cursor-pointer"
    >
      <div className="flex justify-center">
        <img
          src={product.imageUrl}
          alt={product.name}
          className="h-48 object-contain"
        />
      </div>

      <div className="mt-4">
        <h3 className="font-semibold text-lg truncate">
          {product.name}
        </h3>

        <p className="text-green-600 font-bold mt-1">
          ₹{product.price}
        </p>
      </div>
    </div>
  );
};

export default ProductCard;
