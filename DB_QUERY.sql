CREATE DATABASE BLOGS_DB
GO

USE BLOGS_DB
GO

CREATE TABLE blogs 
(
    id_blog INT NOT NULL ,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    published INT DEFAULT 0,

    CONSTRAINT id_blog PRIMARY KEY (id_blog)
)
GO


CREATE TABLE users
(
    id_user INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,

    CONSTRAINT id_user PRIMARY KEY (id_user)
)
GO

CREATE TABLE blogs_favourites
(
        id_user INT NOT NULL,
        id_blog INT NOT NULL ,
        fecha DATETIME DEFAULT GETDATE(),

        CONSTRAINT fk_blog FOREIGN KEY (id_blog) REFERENCES blogs(id_blog),
        CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users(id_user),
        CONSTRAINT pk_favourite PRIMARY KEY (id_user, id_blog),
)
GO

SELECT * FROM blogs
GO
