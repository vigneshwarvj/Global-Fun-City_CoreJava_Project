CREATE DATABASE IF NOT EXISTS java_project_2;

USE java_project_2;

-- User
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone_no VARCHAR(15) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- TicketPrice
CREATE TABLE ticketprices (
    ticket_price_id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Ticket
CREATE TABLE tickets (
	ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    no_of_adult INT NOT NULL,
    no_of_children INT,
    no_of_days INT NOT NULL,
    no_of_nights INT NOT NULL,
    created_by INT NOT NULL,
    adult_price INT NOT NULL,
    children_price INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users (user_id),
    total_price INT NOT NULL
);

-- rooms
CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_name VARCHAR(255) NOT NULL,
    room_name VARCHAR(255) NOT NULL,
    no_of_beds INT NOT NULL,
    price INT NOT NULL,
    room_image VARCHAR(255) NOT NULL,
    room_amenities VARCHAR(255), 
    is_active BOOLEAN DEFAULT TRUE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
 
 -- users_rooms 
CREATE TABLE users_rooms (
    user_room_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    ticket_id INT NOT NULL,
    room_booked_id INT not null,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    room_name VARCHAR(50) NOT NULL,
    no_of_nights INT NOT NULL,
    total_price INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id),
    FOREIGN KEY (room_booked_id) REFERENCES rooms (room_id)
);