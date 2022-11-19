CREATE DATABASE BLOGS_DB
GO

USE BLOGS_DB
GO



CREATE TABLE users
(
    username VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT username PRIMARY KEY (username)
)
GO

CREATE TABLE blogs 
(
    id_blog INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    published INT DEFAULT 0,

    CONSTRAINT id_blog PRIMARY KEY (id_blog),
    CONSTRAINT fk_user_blogs FOREIGN KEY (username) REFERENCES users(username),

)
GO

CREATE TABLE blogs_favourites
(
        id_favourite INT NOT NULL,
        username VARCHAR(50) NOT NULL,
        id_blog INT NOT NULL ,
        fecha DATETIME DEFAULT GETDATE(),

        CONSTRAINT fk_blog FOREIGN KEY (id_blog) REFERENCES blogs(id_blog),
        CONSTRAINT fk_user_favourites FOREIGN KEY (username) REFERENCES users(username),
        CONSTRAINT id_favourite PRIMARY KEY (id_favourite),

)
GO

SELECT * FROM blogs
GO

SELECT * FROM users
GO

SELECT * FROM blogs_favourites
GO

-- DELETE FROM users WHERE username = 'jarrisoncano'

DROP TABLE blogs_favourites
GO


DROP TABLE blogs
GO

DROP TABLE users
GO

