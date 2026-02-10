import { useEffect, useState } from "react";
import axios from "axios";

function AdminOrders() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/orders");
      setOrders(res.data);
    } catch (error) {
      console.error("Error fetching orders", error);
    }
  };

  const getStatusColor = (status) => {
    if (status === "PLACED") return "bg-yellow-400";
    if (status === "DELIVERED") return "bg-green-500";
    if (status === "CANCELLED") return "bg-red-500";
    return "bg-gray-400";
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Admin Orders</h1>

      <table className="w-full border">
        <thead>
          <tr className="bg-gray-200">
            <th className="p-2">Order ID</th>
            <th className="p-2">Customer</th>
            <th className="p-2">Amount</th>
            <th className="p-2">Status</th>
          </tr>
        </thead>

        <tbody>
          {orders.map((order) => (
            <tr key={order.id} className="text-center border">
              <td className="p-2">{order.id}</td>
              <td className="p-2">{order.customerName}</td>
              <td className="p-2">₹{order.totalAmount}</td>
              <td className="p-2">
                <span
                  className={`text-white px-3 py-1 rounded ${getStatusColor(
                    order.status
                  )}`}
                >
                  {order.status}
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminOrders;
