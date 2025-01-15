
-- Création de la table cityhalls
create table declarations
(
    id int auto_increment primary key,
    creation datetime default current_timestamp,
    child_id int,
    first_parent_id int,
    second_parent_id int,
    constraint fk_declarations_children foreign key(child_id) references profiles (id),
    constraint fk_declarations_second_parent foreign key(second_parent_id) references profiles (id),
    constraint fk_declarations_first_parent foreign key(first_parent_id) references profiles (id)
);

