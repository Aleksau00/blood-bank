insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role) values ('perica', 'perap', 'pera@pera.com', 'Petar', 'Petrovic', '086745384', '1305994779876', 0,'FTN', 'registeredUser');
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role) values ('jelica', 'jelenas', 'jelena@jelena.com', 'Jelena', 'Savic', '086745444', '1305994779876', 1,'FTN', 'registeredUser');
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role) values ('anica', 'anaj', 'ana@ana.com', 'Ana', 'Jovanovic', '086745777', '1805994773344', 1,'Economy faculty in Novi Sad', 'registeredUser');
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role) values ('sanjica', 'sanjak', 'sanja@sanja.com', 'Sanja', 'Kondic', '086746534', '1505994779876', 1,'Medical technician', 'staff');
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role) values ('danica', 'danicak', 'dana@dana.com', 'Danica', 'Dakic', '086746573', '1807994779477', 1,'Medical technician', 'staff');
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role) values ('vanja', 'vanjak', 'vanja@vanja.com', 'Vanja', 'Krstic', '066756573', '2312990779440', 2,'System administrator', 'admin');
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role) values ('saki', 'slavicam', 'saki@saki.com', 'Slavica', 'Misic', '068665573', '1411987790440', 1,'Center administrator', 'staff');

insert into address (country, city, street, number, postal_code) values ('Serbia', 'Belgrade', 'Puskinova', '77', '11000');
insert into address (country, city, street, number, postal_code) values ('Serbia', 'Belgrade', 'Safarikova', '40', '11000');
insert into address (country, city, street, number, postal_code) values ('Serbia', 'Novi Sad', 'Pavla Papa', '66', '21400');
insert into address (country, city, street, number, postal_code) values ('Serbia', 'Novi Sad', 'Lasla Gala', '55', '21400');


insert into center (name, address_id, description, average_grade, start_time, end_time) values ('VMA', '1', 'Ambulance', '4.0', '07:00:00.751278', '09:52:20.751278');
insert into center (name, address_id, description, average_grade, start_time, end_time) values ('Health Clinic', '2', 'Health clinic for respiratory diseases', '4.5', '07:00:00.751278', '09:00:00.751278');
insert into center (name, address_id, description, average_grade, start_time, end_time) values ('HCNS', '3', 'Ambulance', '4.0', '07:00:00.751278', '09:00:00.751278');
insert into center (name, address_id, description, average_grade, start_time, end_time) values ('Dragise Misovica', '4', 'Ambulance', '2.5', '07:00:00.751278', '09:00:00.751278');

insert into blood (amount, type) values (500, 0);
insert into blood (amount, type) values (60, 1);
insert into blood (amount, type) values (100.55, 2);
insert into blood (amount, type) values (80, 3);

insert into center_blood (center_id, blood_id) values (1,1);
insert into center_blood (center_id, blood_id) values (2,2);
insert into center_blood (center_id, blood_id) values (3,3);
insert into center_blood (center_id, blood_id) values (4,4);

insert into appointment (registered_user_id, center_id, date, duration) values (1, 1, '2022-11-11 14:00:00', 30);
insert into appointment (registered_user_id, center_id, date, duration) values (2, 1, '2022-12-10 14:00:00', 45);
insert into appointment (registered_user_id, center_id, date, duration) values (3, 1, '2022-12-11 14:20:00', 50);
insert into appointment (registered_user_id, center_id, date, duration) values (1, 1, '2022-08-08 14:30:00', 30);
insert into appointment (registered_user_id, center_id, date, duration) values (2, 1, '2022-10-10 14:45:00', 45);

insert into appointment_staff (staff_id, appointment_id) values (4, 1);
insert into appointment_staff (staff_id, appointment_id) values (5, 2);

insert into complaint (content, registered_user_id) values ('I hate it', 1);
insert into complaint (content, registered_user_id) values ('It is smelly', 2);

insert into equipment (amount, name, center_id) values (10, 'Scissors', 1);
insert into equipment (amount, name, center_id) values (15, 'Needles', 2);
insert into equipment (amount, name, center_id) values (5, 'Chairs', 3);
insert into equipment (amount, name, center_id) values (20, 'Knives', 3);

insert into feedback (grade, registered_user_id) values (5, 3);
insert into feedback (grade, registered_user_id) values (1, 1);
insert into feedback (grade, registered_user_id) values (2, 2);

insert into loyalty_card (name, points) values ('silver', 1000);
insert into loyalty_card (name, points) values ('gold', 2000);
insert into loyalty_card (name, points) values ('platinum', 3000);

insert into poll (date, donation_count, occupation, registered_user_id) values ('2022-07-07', 5, 'Student', 1);
insert into poll (date, donation_count, occupation, registered_user_id) values ('2022-06-06', 3, 'Student', 2);
insert into poll (date, donation_count, occupation, registered_user_id) values ('2022-05-05', 9, 'Student', 3);