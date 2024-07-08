-- Insertion étudiants

insert into student (
       student_id,
       firstname,
       lastname,
       address,
       email,
       phone_number
)
values (
       1,
       'Antsa',
       'Rafanomezantsoa',
       'Ampitatafika',
       'antsa@mail.com',
       '034 71 720 97'
);

insert into student (
       student_id,
       firstname,
       lastname,
       address,
       email,
       phone_number
)
values (
       2,
       'Jason',
       'Rahanetra',
       'Ambatoroka',
       'jason@mail.com',
       '038 77 667 97'
);

insert into student (
       student_id,
       firstname,
       lastname,
       address,
       email,
       phone_number
)
values (
       3,
       'Dihary',
       'Rabearimanana',
       'Andranomena',
       'dihary@mail.com',
       '034 09 241 65'
);

-- Insertion parcours

insert into major (
       major_id,
       major_name
)
values (
       1,
       'Infrastructures cloud et devops'
);

insert into major (
       major_id,
       major_name
)
values (
       2,
       'Développement web et application mobile'
);

insert into major (
       major_id,
       major_name
)
values (
       3,
       'Intelligence artificielle et big data'
);

-- Insertion niveaux

insert into level (
       level_id,
       level_name
)
values (
       1,
       "L1"
);

insert into level (
       level_id,
       level_name
)
values (
       2,
       "L2"
);

insert into level (
       level_id,
       level_name
)
values (
       3,
       "L3"
);

insert into level (
       level_id,
       level_name
)
values (
       4,
       "M1"
);

insert into level (
       level_id,
       level_name
)
values (
       5,
       "M2"
);

insert into registration (
       student_id,
       major_id,
       level_id,
       year
)
values (
       1,
       3,
       2,
       2024
);

insert into registration (
       student_id,
       major_id,
       level_id,
       year
)
values (
       2,
       2,
       2,
       2024
);

insert into registration (
       student_id,
       major_id,
       level_id,
       year
)
values (
       3,
       3,
       2,
       2024
);
