import { useNavigate } from "react-router-dom";
import { ShoppingCart } from "lucide-react";

const Navbar = () => {
  const navigate = useNavigate();

  return (
    <div className="bg-blue-600 shadow-md">
      <div className="max-w-7xl mx-auto px-6 py-3 flex items-center justify-between text-white">
        
        {/* Logo */}
        <h1
          className="text-2xl font-bold cursor-pointer"
          onClick={() => navigate("/")}
        >
          ShopEase
        </h1>

        {/* Search */}
        <input
          type="text"
          placeholder="Search products..."
          className="w-1/3 px-4 py-2 rounded-md text-black focus:outline-none"
        />

        {/* Cart */}
        <ShoppingCart
          className="cursor-pointer"
          onClick={() => navigate("/cart")}
        />
      </div>
    </div>
  );
};

export default Navbar;
