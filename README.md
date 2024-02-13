# **Ecommerce Website**

## Overview:
  This project implements the backend architecture for an Ecommerce Website, providing functionalities such as user management, product catalog, cart and checkout processes, order management, payment processing, and authentication.

## Table of Contents:
```
1.  Functional Requirements
2.  High-Level Design (HLD)
3.  Architecture Components
4.  Typical Flow with Kafka & Elasticsearch Integration
5.  Getting Started
6.  Prerequisites
7.  Installation
8.  Usage
9.  Contributing
10. License
11. Functional Requirements
```

## User Management:
 ```
  Registration: Allow new users to create an account using their email or social media profiles.
  Login: Users should be able to securely log in using their credentials.
  Profile Management: Users should have the ability to view and modify their profile details.
  Password Reset: Users must have the option to reset their password through a secure link.
```

## Product Catalog:
```
   Browsing: Users should be able to browse products by different categories.
   Product Details: Detailed product pages with product images, descriptions,
   specifications, and other relevant information.
   Search: Users must be able to search for products using keywords.
```

## Cart & Checkout:
```
 Add to Cart: Users should be able to add products to their cart.
 Cart Review: View selected items in the cart with price, quantity, and total details.
 Checkout: Seamless process to finalize the purchase, including specifying delivery address and payment method.
```
      
## Order Management:
```
  Order Confirmation: After making a purchase, users should receive a confirmation with order details.
  Order History: Users should be able to view their past orders.
  Order Tracking: Provide users with a way to track their order's delivery status.
```
      
## Payment:
```
  Multiple Payment Options: Support for credit/debit cards, online banking, and other popular payment methods.
  Secure Transactions: Ensure user trust by facilitating secure payment transactions.
  Payment Receipt: Provide users with a receipt after a successful payment.
```
      
## Authentication:
```
  Secure Authentication: Ensure that user data remains private and secure during login and throughout their session.
  Session Management: Users should remain logged in for a specified duration or until they decide to log out.
```
      
## High-Level Design (HLD):
### Architecture Components:

* Load Balancers (LB):
```
   Function: Distribute incoming user requests across multiple server instances to balance load and ensure high availability.
   Tool: Amazon Elastic Load Balancing (ELB).
```
      
* API Gateway:
```
  Function: Entry point for clients. Routes requests to the right microservices,
  handles rate limiting, and manages authentication.
  Tool: Kong.
```

      
## Microservices Architecture:
* User Management Service
```
  Function: Handles user registration, login, profile management, and password reset.
  Database: MySQL for structured user data.
  Communication: Uses Kafka to communicate relevant user activities to other services.
 ```     
* Product Catalog Service
```
  Function: Manages product listings, details, categorization.
  Databases: MySQL for product data, Elasticsearch for fast searches.
```  
* Cart Service
```
  Function: Manages user's shopping cart.
  Databases: MongoDB for flexibility in cart structures, Redis for fast, in-memory data access.
```      
* Order Management Service
```
  Function: Handles order processing, history, and tracking.
  Database: MySQL.
  Communication: Communicates with Payment Service and User Management Service through Kafka.
```      
* Payment Service
```
  Function: Manages payment gateways and transaction logs.
  Database: MySQL.
  Communication: Produces a message on Kafka to notify the Order Management Service after payment confirmation.
```      
* Notification Service
```
  Function: Manages email and other notifications.
  Communication: Consumes Kafka messages for events requiring user notifications,
  integrates with third-party platforms like Amazon SES.
```      
## Databases:
```
   MySQL: For structured data.
   MongoDB: For flexible, unstructured data.
```
## Kafka:
```
   Function: Central message broker allowing asynchronous communication between microservices,
             ensuring data consistency, and acting as an event store for critical actions.
```
## Caching with Redis:
```
   Use: Primarily by Cart Service for faster response times.
```      
## Elasticsearch:
```
   Use: Used by Product Catalog for fast and relevant product searches.
```

## Typical Flow with Kafka & Elasticsearch Integration:
```
   Part 1:
      User logs in and searches for a product.
      Request reaches LB, then passed to API Gateway.
      API Gateway routes the search request to Product Catalog Service.
      Product Catalog Service queries Elasticsearch for a fast product search.

   Part 2:
      User adds a product to the cart.
      Cart Service produces a message to Kafka about this action.

   Part 3:
      User checks out, triggering the Order Management Service.
      After placing the order, a message is sent to Kafka.
      Payment Service consumes the Kafka message to process payment.
```

## Getting Started:
      Prerequisites
      List any prerequisites or system requirements here.
      Installation
      Clone the repository.
      Follow installation instructions for each microservice in their respective directories.
      
## Usage
      Provide information on how to use the project, including configuration settings and any necessary steps.

## Contributing
      If you'd like to contribute, please fork the repository and create a pull request.
      For major changes, please open an issue first to discuss the proposed changes.
