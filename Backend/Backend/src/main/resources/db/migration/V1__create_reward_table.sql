CREATE TABLE reward (
    id TINYINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    points_required INT NOT NULL,
    image_url VARCHAR(255),
    category CHAR(20),
    is_active BOOLEAN,
    stock_count TINYINT
);