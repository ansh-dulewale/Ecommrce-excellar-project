import { useEffect, useState } from "react";
import api from "../services/api";

const Cart = () => {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchCart();
  }, []);

  const fetchCart = async () => {
    try {
      const response = await api.get("/cart");
      setCartItems(response.data);
    } catch (err) {
      console.log("Error fetching cart");
    } finally {
      setLoading(false);
    }
  };

  const handleRemove = async (id) => {
    try {
      await api.delete(`/cart/remove/${id}`);
      fetchCart(); // refresh cart
    } catch (err) {
      console.log("Error removing item");
    }
  };

  const handleCheckout = async () => {
    try {
      await api.post("/orders/checkout");
      alert("Order placed successfully!");
      fetchCart();
    } catch (err) {
      alert("Checkout failed");
    }
  };

  const totalAmount = cartItems.reduce(
    (total, item) => total + item.product.price * item.quantity,
    0
  );

  if (loading) {
    return (
      <div className="min-h-screen flex justify-center items-center">
        <p className="text-xl font-semibold">Loading cart...</p>
      </div>
    );
  }

  return (
    <div className="bg-gray-100 min-h-screen py-10">
      <div className="max-w-6xl mx-auto bg-white p-8 rounded-xl shadow-md">

        <h2 className="text-2xl font-bold mb-6">
          My Cart
        </h2>

        {cartItems.length === 0 ? (
          <p>Your cart is empty.</p>
        ) : (
          <>
            {cartItems.map((item) => (
              <div
                key={item.id}
                className="flex flex-col md:flex-row items-center justify-between border-b py-4"
              >
                <div className="flex items-center gap-4">
                  <img
                    src={item.product.imageUrl}
                    alt={item.product.name}
                    className="h-20 object-contain"
                  />
                  <div>
                    <h3 className="font-semibold">
                      {item.product.name}
                    </h3>
                    <p>₹{item.product.price}</p>
                    <p>Quantity: {item.quantity}</p>
                  </div>
                </div>

                <button
                  onClick={() => handleRemove(item.id)}
                  className="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition"
                >
                  Remove
                </button>
              </div>
            ))}

            <div className="flex justify-between items-center mt-6">
              <h3 className="text-xl font-bold">
                Total: ₹{totalAmount}
              </h3>

              <button
                onClick={handleCheckout}
                className="bg-green-600 text-white px-6 py-3 rounded-lg hover:bg-green-700 transition"
              >
                Checkout
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default Cart;
