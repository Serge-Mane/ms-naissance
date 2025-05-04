insert into profiles (first_name, last_name, password, email, active, roles_id)
values
('Agent', 'sam.tech', '$2a$10$gQrdj6PMYabPXSMp.lZ/XuHreV8ceal5ADPvIBXUNSCy0s1S6umu6', 'agent@sam.tech', true, (select  id from roles where  name = 'AGENT')),
('Admin', 'sam.tech', '$2a$10$gQrdj6PMYabPXSMp.lZ/XuHreV8ceal5ADPvIBXUNSCy0s1S6umu6', 'admin@sam.tech', true, (select  id from roles where  name = 'ADMINISTRATOR'));