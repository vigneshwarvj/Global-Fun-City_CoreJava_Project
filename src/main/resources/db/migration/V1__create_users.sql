
USE java_project;
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
    from_date DATETIME NOT NULL,
    to_date DATETIME NOT NULL,
    no_of_adult INT NOT NULL,
    no_of_children INT NOT NULL,
    created_by INT NOT NULL,
    adult_price INT NOT NULL,
    children_price INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users (user_id),
    FOREIGN KEY (adult_price) REFERENCES ticketprices (ticket_price_id),
    FOREIGN KEY (children_price) REFERENCES ticketprices (ticket_price_id)
);

-- Rooms
CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_name VARCHAR(255) NOT NULL,
    room_name VARCHAR(255) NOT NULL,
    no_of_beds INT NOT NULL,
    price INT NOT NULL
);



CREATE TABLE rooms (
    ticket_id INT NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id),
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_name VARCHAR(255) NOT NULL,
    room_name VARCHAR(255) NOT NULL,
    no_of_beds INT NOT NULL,
    price INT NOT NULL
);


CREATE TABLE rooms (
    ticket_id INT NOT NULL,
    FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id),
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    hotel_name VARCHAR(255) NOT NULL,
    room_name VARCHAR(255) NOT NULL,
    no_of_beds INT NOT NULL,
    price INT NOT NULL
);