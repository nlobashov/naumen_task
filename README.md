<h1>Тестовое задание на Groovy/Java разработчика</h1>

<h2>Методы API</h2>
<p><b>GET</b> /api/products/ - получить все позиции</p>
<p><b>GET</b> /api/products/{id} - получить позицию</p>
<p><b>GET</b> /api/products/filter?pattern={pattern} - найти позиции по шаблону</p>
<p><b>POST</b> /api/products/ - добавить позицию</p>
<p><b>PUT</b> /api/products/ - обновить позицию</p>
<p><b>DELETE</b> /api/products/{id} - удалить позицию</p>

<h2>SQL-запрос для создания БД в MySQL 8</h2>
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
<h2>Рабочее окружение:</h2>
<ul>
<li>IDE Intellij Idea Ultimate Edition</li>
<li>Spring Boot 2.7.10</li>
<li>MySQL 8 (Win-10)</li>
</ul>
<h2>В проекте использовал:</h2>
<ul>
<li>Spring 5</li>
<li>Hibernate ORM 5</li>
<li>Hibernate Validator</li>
<li>Thymeleaf</li>
<li>Tomcat</li>
</ul>
