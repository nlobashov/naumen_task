# Тестовое задание на Groovy/Java разработчика

<p>SQL-запрос для создания БД в MySQL 8</p>
<pre>
CREATE DATABASE naumen DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE naumen;
CREATE TABLE products (
	id INT PRIMARY KEY AUTO_INCREMENT ,
	name VARCHAR(100) NOT NULL,
	description TEXT NOT NULL,
	quantity DOUBLE NOT NULL
) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;
</pre>
<p>Рабочее окружение:<p>
<ul>
<li>IDE Intellij Idea Ultimate Edition</li>
<li>Spring Boot 2.7.10</li>
<li>MySQL 8 (Win-10)</li>
</ul>
<p>В проекте использовал:<p>
<ul>
<li>Spring 5</li>
<li>Hibernate ORM 5</li>
<li>Hibernate Validator</li>
<li>Thymeleaf</li>
<li>Tomcat</li>
</ul>
