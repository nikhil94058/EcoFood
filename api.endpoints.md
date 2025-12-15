Sure! Since your backend is a **Spring Boot “Food Donation + Pickup + User” system**, I’ll summarize **all the API endpoints** based on the models and services you described (`User`, `Food`, `Pickup`) including authentication, verification, CRUD, and file upload.

This will help your Angular frontend connect properly.

---

# 1️⃣ **User APIs**

### Base URL: `/api/users`

| Method | Endpoint       | Description                   | Request Body                                 | Response                                      |
| ------ | -------------- | ----------------------------- | -------------------------------------------- | --------------------------------------------- |
| POST   | `/signup`      | Register new user             | `{ "name", "email", "password", "address" }` | Created `User` object (with `verified=false`) |
| POST   | `/login`       | Login user                    | `{ "email", "password" }`                    | JWT or session info, or error if not verified |
| GET    | `/`            | Get all users                 | —                                            | List of users                                 |
| GET    | `/{id}`        | Get user by ID                | —                                            | User object                                   |
| PUT    | `/verify/{id}` | Verify user (after OTP/email) | —                                            | Updated User (`verified=true`)                |
| DELETE | `/{id}`        | Delete user                   | —                                            | Success message                               |

> Optional: You can also have `/otp/send` and `/otp/verify` endpoints if you implement OTP verification.

---

# 2️⃣ **Food APIs**

### Base URL: `/api/food`

| Method | Endpoint  | Description              | Request Body                                   | Response             |
| ------ | --------- | ------------------------ | ---------------------------------------------- | -------------------- |
| POST   | `/upload` | Upload food info + image | `FormData: { name, quantity, address, photo }` | Created Food object  |
| GET    | `/`       | List all food donations  | —                                              | List of Food objects |
| GET    | `/{id}`   | Get food by ID           | —                                              | Food object          |
| PUT    | `/{id}`   | Update food info         | Food JSON                                      | Updated Food object  |
| DELETE | `/{id}`   | Delete food donation     | —                                              | Success message      |

> Image is uploaded via `MultipartFile` in Spring Boot.

---

# 3️⃣ **Pickup APIs**

### Base URL: `/api/pickup`

| Method | Endpoint       | Description           | Request Body                     | Response               |
| ------ | -------------- | --------------------- | -------------------------------- | ---------------------- |
| POST   | `/request`     | Create pickup request | `{ foodId, userId, pickupDate }` | Created Pickup object  |
| GET    | `/`            | List all pickups      | —                                | List of Pickup objects |
| GET    | `/{id}`        | Get pickup by ID      | —                                | Pickup object          |
| PUT    | `/{id}/status` | Update pickup status  | `{ status }`                     | Updated Pickup object  |
| DELETE | `/{id}`        | Cancel pickup         | —                                | Success message        |

---

# 4️⃣ **Authentication & Security (Optional)**

If using JWT/Spring Security:

* POST `/api/auth/login` → Returns JWT token
* POST `/api/auth/signup` → Register user
* All `/api/food` and `/api/pickup` endpoints → Secured with `Authorization: Bearer <token>`

---

# 5️⃣ **Example Usage**

**Sign up user:**

```http
POST /api/users/signup
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "pass123",
  "address": "123 Main St"
}
```

**Upload food donation:**

```http
POST /api/food/upload
Content-Type: multipart/form-data

FormData: {
  "name": "Pizza",
  "quantity": 3,
  "address": "123 Main St",
  "photo": <file>
}
```

**Request pickup:**

```http
POST /api/pickup/request
Content-Type: application/json

{
  "foodId": "64eabc12345",
  "userId": "64eabc67890",
  "pickupDate": "2025-12-20T10:00:00"
}
```

---

If you want, I can **also generate the Angular services (`UserService`, `FoodService`, `PickupService`) with all HTTP calls** for these endpoints so your frontend is **ready to connect**.

Do you want me to do that next?
