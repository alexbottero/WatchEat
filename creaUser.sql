DROP TABLE public."user";

CREATE TABLE public.User(
	idUser SERIAL NOT NULL ,
	mail   VARCHAR (25)  ,
	pwd    VARCHAR (25)  ,
	CONSTRAINT prk_constraint_User PRIMARY KEY (idUser)
)WITHOUT OIDS;