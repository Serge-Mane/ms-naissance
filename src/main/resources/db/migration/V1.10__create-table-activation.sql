create table activations (
    id int primary key auto_increment,
    code varchar(200),
    active boolean,
    creation datetime default current_timestamp,
    desactivation datetime,
    profiles_id int,
    constraint fk_activations_profiles foreign key(profiles_id) references profiles(id)

)