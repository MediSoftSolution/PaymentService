# PaymentService - Hospital System Microservices

**PaymentService** is a microservice responsible for handling payments and transactions in the **Hospital System Microservices** platform. It processes payment requests using **PayPal**, records transaction data in a database, and ensures secure and reliable financial operations.

## 📌 Overview

The **PaymentService** manages:

- **Payment Processing**: Handles payment requests from users (patients) for various hospital services using **PayPal**.
- **Transaction Management**: Stores transaction details (e.g., payment method, amount, status) in a database.
- **Payment Verification**: Verifies payments and updates the transaction status accordingly.
- **Refund Management**: Manages refunds for unsuccessful or canceled transactions.
- **Database Integration**: Uses a relational database to persist transaction data securely.

---

## 🔧 Technologies Used

- **Backend**: Java - Spring Boot
- **Database**: PostgreSQL / MySQL (Choose the database as per your preference)
- **Payment Gateway**: **PayPal** (for processing payments)
- **Service Discovery**: Consul
- **API Gateway**: Ocelot (for routing requests to services)
- **Security**: JWT Token for authentication and authorization

---

## 🚀 Features

- **Payment Initiation**: Users (patients) can initiate payments for services like consultations, surgeries, or other hospital bills using **PayPal**.
- **Transaction Recording**: Each payment is recorded with transaction details, including the amount, method, status, and user information.
- **Payment Status Updates**: The service keeps track of payment status, including success, failure, or pending status.
- **Refund Processing**: Refund requests can be processed if the payment is canceled or fails.
- **Admin Dashboard**: Admins can view transaction details and manage payment statuses.
- **Database Support**: Uses PostgreSQL to store transaction data persistently.

---

## 🔧 PayPal Integration

### 1. **Setting Up PayPal**

In order to process payments via **PayPal**, you'll need to configure **PayPal's REST API**. This includes setting up your **Client ID** and **Secret Key** for **sandbox** or **production** environments.

**PayPal Configuration** in `application.properties`:

```properties
paypal.client-id=your-client-id
paypal.secret=your-secret-key
paypal.mode=sandbox
