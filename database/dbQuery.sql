
CREATE TABLE userprofile (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    age INT CHECK (age >= 12)
);

CREATE TABLE visitrecord (
    id INT PRIMARY KEY AUTO_INCREMENT,
    visitor_id INT,
    visited_id INT,
    visit_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (visitor_id) REFERENCES users(id),
    FOREIGN KEY (visited_id) REFERENCES users(id)
);

CREATE TABLE likerecord (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    liked_user_id INT,
    like_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (liked_user_id) REFERENCES users(id)
);

SELECT u.id, u.name
	FROM userprofile u
	JOIN visitrecord v ON u.id = v.visitor_id 
	WHERE v.visited_id = <User ID>
	ORDER BY v.visit_time DESC;

