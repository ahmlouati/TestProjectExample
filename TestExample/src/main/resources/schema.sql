create sequence employe_seq NO MAXVALUE start 1 increment 1;

CREATE TABLE employe (
    id integer IDENTITY,
    full_name varchar(64) NOT NULL,  
    date_of_birth date not null ,    
    CONSTRAINT pk_employe_id PRIMARY KEY (id)
);