function login() {
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  if (username === "admin" && password === "admin") {
    window.location.href = "dashboard.html";
  } else {
    alert("Invalid Credentials");
  }
}
function addProduct() {
  const product = {
    name: document.getElementById("name").value,
    price: document.getElementById("price").value,
    category: document.getElementById("category").value
  };

  fetch("http://localhost:8080/api/products", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(product)
  })
  .then(res => res.json())
  .then(data => {
    alert("Product Added Successfully!");
  })
  .catch(err => alert("Error"));
}
