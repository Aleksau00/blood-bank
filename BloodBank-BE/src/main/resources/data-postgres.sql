insert into loyalty_card (name, points) values ('silver', 1000);
insert into loyalty_card (name, points) values ('gold', 2000);
insert into loyalty_card (name, points) values ('platinum', 3000);

insert into role(name) values ('USER');
insert into role(name) values ('STAFF');
insert into role(name) values ('ADMIN');

insert into address (country, city, street, number, postal_code) values ('Serbia', 'Belgrade', 'Puskinova', '77', '11000');
insert into address (country, city, street, number, postal_code) values ('Serbia', 'Belgrade', 'Safarikova', '40', '11000');
insert into address (country, city, street, number, postal_code) values ('Serbia', 'Novi Sad', 'Pavla Papa', '66', '21400');
insert into address (country, city, street, number, postal_code) values ('Serbia', 'Novi Sad', 'Lasla Gala', '55', '21400');

<<<<<<< HEAD
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role, address_id, points, is_locked, is_enabled, app_user_role, loyalty_card_id, penalties) values ('perica', 'perap', 'pera@pera.com', 'Petar', 'Petrovic', '086745384', '1305994779876', 0,'FTN', 'registeredUser', 1, 1020, false, true,  1, '1', 0);
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role, address_id, points, is_locked, is_enabled, app_user_role, loyalty_card_id, penalties) values ('jelica', 'jelenas', 'jelena@jelena.com', 'Jelena', 'Savic', '086745444', '1305994779876', 1,'FTN', 'registeredUser', 1, 1080, false, true, 0, '1', 1);
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role, address_id, points, is_locked, is_enabled, app_user_role, loyalty_card_id, penalties) values ('anica', 'anaj', 'ana@ana.com', 'Ana', 'Jovanovic', '086745777', '1805994773344', 1,'Economy faculty in Novi Sad', 'registeredUser', 1, 3090, false, true, 0, '2', 2);
insert into app_user (username, password, email, first_name, last_name, phone_number, umcn, gender, institution, role, address_id, is_locked, is_enabled, app_user_role, penalties) values ('vanja', 'vanjak', 'vanja@vanja.com', 'Vanja', 'Krstic', '066756573', '2312990779440', 2,'System administrator', 'admin', 4, false, true, 1, 0);
=======
insert into app_user (username, password,  first_name, last_name, phone_number, umcn, gender, institution, role, address_id, points, is_locked, is_enabled,  loyalty_card_id) values ('perica@perica.com', '$2a$10$dDjrNg.RVyF4ozF/cr4auO3q6.AgihxSWUz.dqLI/OzoPZjSnXoji',  'Petar', 'Petrovic', '086745384', '1305994779876', 0,'FTN', 'registeredUser', 1, 1020, false, true, '1');
insert into app_user (username, password,  first_name, last_name, phone_number, umcn, gender, institution, role, address_id, points, is_locked, is_enabled,  loyalty_card_id) values ('jelica@jelica.com', '$2a$10$dDjrNg.RVyF4ozF/cr4auO3q6.AgihxSWUz.dqLI/OzoPZjSnXoji', 'Jelena', 'Savic', '086745444', '1305994779876', 1,'FTN', 'registeredUser', 1, 1080, false, true,'1');
insert into app_user (username, password,  first_name, last_name, phone_number, umcn, gender, institution, role, address_id, points, is_locked, is_enabled,  loyalty_card_id) values ('anica@anica.com', '$2a$10$dDjrNg.RVyF4ozF/cr4auO3q6.AgihxSWUz.dqLI/OzoPZjSnXoji',  'Ana', 'Jovanovic', '086745777', '1805994773344', 1,'Economy faculty in Novi Sad', 'registeredUser', 1, 3090, false, true, '2');


insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 1);
insert into user_role (user_id, role_id) values (3, 1);

>>>>>>> 5c59b18642c0ccfbb8d34ba9d334fc2cefcd0a3f

insert into center (name, address_id, description, average_grade, start_time, end_time) values ('VMA', '1', 'Ambulance', '3.0', '07:00', '20:00');
insert into center (name, address_id, description, average_grade, start_time, end_time) values ('Health Clinic', '2', 'Health clinic for respiratory diseases', '5.0', '07:00', '18:00');
insert into center (name, address_id, description, average_grade, start_time, end_time) values ('HCNS', '3', 'Ambulance', '4.0', '07:00', '21:00');
insert into center (name, address_id, description, average_grade, start_time, end_time) values ('Dragise Misovica', '4', 'Ambulance', '2.0', '07:00', '21:00');
insert into app_user (username, password,  first_name, last_name, phone_number, umcn, gender, institution, role, address_id, is_locked, is_enabled, center_id, is_first_login) values ('vanja@vanja.com', '$2a$10$dDjrNg.RVyF4ozF/cr4auO3q6.AgihxSWUz.dqLI/OzoPZjSnXoji',  'Vanja', 'Krstic', '066756573', '2312990779440', 2,'System administrator', 'admin', 4, false, true, 2, false);
insert into user_role (user_id, role_id) values (4, 3);
insert into app_user (username, password,  first_name, last_name, phone_number, umcn, gender, institution, role, address_id, center_id, is_locked, is_enabled) values ('sanjica@sanjica.com', '$2a$10$dDjrNg.RVyF4ozF/cr4auO3q6.AgihxSWUz.dqLI/OzoPZjSnXoji',  'Sanja', 'Kondic', '086746534', '1505994779876', 1,'Medical technician', 'staff', 2, 1, false, true);
insert into app_user (username, password,  first_name, last_name, phone_number, umcn, gender, institution, role, address_id, center_id, is_locked, is_enabled) values ('danica@danica.com', '$2a$10$dDjrNg.RVyF4ozF/cr4auO3q6.AgihxSWUz.dqLI/OzoPZjSnXoji',  'Danica', 'Dakic', '086746573', '1807994779477', 1,'Medical technician', 'staff', 3, 2, false, true);
insert into app_user (username, password,  first_name, last_name, phone_number, umcn, gender, institution, role, address_id, center_id, is_locked, is_enabled) values ('saki@saki.com', '$2a$10$dDjrNg.RVyF4ozF/cr4auO3q6.AgihxSWUz.dqLI/OzoPZjSnXoji', 'Slavica', 'Misic', '068665573', '1411987790440', 1,'Center administrator', 'staff', 1, 1, false, true);

insert into app_user (username, password,  first_name, last_name, phone_number, umcn, gender, institution, role, address_id, is_locked, is_enabled, center_id, is_first_login) values ('nemke@nemke.com', '$2a$10$dDjrNg.RVyF4ozF/cr4auO3q6.AgihxSWUz.dqLI/OzoPZjSnXoji',  'Vanja', 'Krstic', '066756573', '2312990779440', 2,'System administrator', 'admin', 4, false, true, 2, true);
insert into user_role (user_id, role_id) values (5, 2);
insert into user_role (user_id, role_id) values (6, 2);
insert into user_role (user_id, role_id) values (7, 2);
insert into user_role (user_id, role_id) values (8, 3);

insert into blood (amount, type) values (500, 0);
insert into blood (amount, type) values (60, 1);
insert into blood (amount, type) values (100.55, 2);
insert into blood (amount, type) values (80, 3);

insert into center_blood (center_id, blood_id) values (1,1);
insert into center_blood (center_id, blood_id) values (2,2);
insert into center_blood (center_id, blood_id) values (3,3);
insert into center_blood (center_id, blood_id) values (4,4);

<<<<<<< HEAD
insert into appointment (registered_user_id, center_id, date, duration, status) values (1, 1, '2022-11-11 14:00:00', 30, 0);
insert into appointment (registered_user_id, center_id, date, duration, status) values (2, 1, '2022-12-10 14:00:00', 45, 1);
insert into appointment (registered_user_id, center_id, date, duration, status) values (3, 1, '2022-12-11 14:20:00', 50, 2);
insert into appointment (registered_user_id, center_id, date, duration, status) values (1, 1, '2022-08-08 14:30:00', 30, null);
insert into appointment (registered_user_id, center_id, date, duration, status) values (2, 1, '2022-10-10 14:45:00', 45, null);
insert into appointment (registered_user_id, center_id, date, duration, status) values (2, 2, '2022-12-03 13:00:00', 25, null);
insert into appointment (registered_user_id, center_id, date, duration, status) values (2, 1, '2022-12-04 10:00:00', 15, null);
insert into appointment (registered_user_id, center_id, date, duration, status) values (2, 1, '2022-12-01 12:45:00', 30, null);

=======
insert into appointment (registered_user_id, center_id, date, time, duration) values (1, 1, '2022-12-22', '09:00:00', 30);
insert into appointment (registered_user_id, center_id, date, time, duration) values (2, 2, '2022-12-25', '09:35:00', 45);
insert into appointment (registered_user_id, center_id, date, time, duration) values (3, 1, '2022-12-27', '10:00:00', 50);
insert into appointment (registered_user_id, center_id, date, time, duration) values (1, 1, '2022-12-29', '11:00:00', 30);
insert into appointment (registered_user_id, center_id, date, time, duration) values (2, 1, '2023-01-01', '11:45:00', 45);
insert into appointment (registered_user_id, center_id, date, time, duration) values (1, 2, '2022-01-01', '11:45:00', 30);
insert into appointment (registered_user_id, center_id, date, time, duration) values (2, 2, '2022-12-27', '09:35:00', 45);
insert into appointment (registered_user_id, center_id, date, time, duration) values (3, 1, '2022-12-28', '10:00:00', 50);
insert into appointment (registered_user_id, center_id, date, time, duration) values (1, 3, '2022-12-28', '10:00:00', 30);
insert into appointment (registered_user_id, center_id, date, time, duration) values (2, 1, '2023-01-03', '11:45:00', 45);

insert into appointment ( center_id, date, time, duration) values (1, '2022-12-27', '14:45:00', 45);
insert into appointment ( center_id, date, time, duration) values (1, '2022-12-26', '15:30:00', 45);
insert into appointment ( center_id, date, time, duration) values (1, '2022-12-25', '16:15:00', 45);
insert into appointment ( center_id, date, time, duration) values (1, '2022-12-24', '17:00:00', 45);
>>>>>>> 5c59b18642c0ccfbb8d34ba9d334fc2cefcd0a3f

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

insert into poll (date, donation_count, occupation, registered_user_id, question1, question2, question3, question4, question5, question6, question7) values ('2022-07-07', 5, 'Student', 1, false, false, false, false, false, false, false);
insert into poll (date, donation_count, occupation, registered_user_id, question1, question2, question3, question4, question5, question6, question7) values ('2022-06-06', 3, 'Student', 2, false, false, false, false, false, false, false);
insert into poll (date, donation_count, occupation, registered_user_id, question1, question2, question3, question4, question5, question6, question7) values ('2022-05-05', 9, 'Student', 3, false, false, false, false, false, true, false);
