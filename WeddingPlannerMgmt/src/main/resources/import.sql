INSERT INTO CLIENT (id, name, email, phone_number, address, budget, registration_date) VALUES (1, 'Alice Smith', 'alice@example.com', '1234567890', '123 Main St', 10000, CURRENT_DATE);
INSERT INTO CLIENT (id, name, email, phone_number, address, budget, registration_date) VALUES (2, 'Bob Brown', 'bob@example.com', '0987654321', '456 Elm St', 15000, CURRENT_DATE);

INSERT INTO VENDOR (id, name, service_type, available, service_price, contact_number) VALUES (1, 'Catering Service A', 'Catering', true, 2000, '1231231234');
INSERT INTO VENDOR (id, name, service_type, available, service_price, contact_number) VALUES (2, 'Photography Service B', 'Photography', true, 3000, '3213214321');

INSERT INTO EVENT (id, event_name, event_date, client_id, status) VALUES (1, 'Alice Wedding', CURRENT_DATE + 30, 1, 'UPCOMING');
INSERT INTO EVENT (id, event_name, event_date, client_id, status) VALUES (2, 'Bob Anniversary', CURRENT_DATE + 45, 2, 'UPCOMING');

INSERT INTO PAYMENT (id, amount, payment_date, status, client_id) VALUES (1, 1000, CURRENT_DATE, 'COMPLETED', 1);
INSERT INTO PAYMENT (id, amount, payment_date, status, client_id) VALUES (2, 1500, CURRENT_DATE, 'COMPLETED', 2);
