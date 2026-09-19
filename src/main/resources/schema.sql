-- ===================================================================
-- SMART AGRICULTURE MANAGEMENT SYSTEM - DDL SCHEMA DEFINITION
-- ===================================================================

CREATE TABLE IF NOT EXISTS crops (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    soil_type VARCHAR(100) NOT NULL,
    base_n DOUBLE NOT NULL,
    base_p DOUBLE NOT NULL,
    base_k DOUBLE NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS mandi_listings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_name VARCHAR(100) NOT NULL,
    crop_name VARCHAR(100) NOT NULL,
    quantity_quintals DOUBLE NOT NULL,
    price DOUBLE NOT NULL,
    location VARCHAR(150) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    listed_date DATE
);

CREATE TABLE IF NOT EXISTS pest_advices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crop_name VARCHAR(100) NOT NULL,
    symptom VARCHAR(255) NOT NULL,
    bio_remedy TEXT NOT NULL,
    chemical_dosage TEXT NOT NULL
);
