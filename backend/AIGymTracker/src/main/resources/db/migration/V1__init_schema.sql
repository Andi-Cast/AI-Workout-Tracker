CREATE TABLE users
(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   email VARCHAR(255) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   firstname VARCHAR(255) NOT NULL,
   lastname VARCHAR(255) NOT NULL,
   age INT NOT NULL,
   goal_type VARCHAR(20) DEFAULT 'maintaining' NOT NULL,
   created_at DATE DEFAULT (curdate()) NOT NULL
);

CREATE TABLE body_stats
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    date DATE DEFAULT (curdate()),
    weight DECIMAL(5, 2),
    body_fat_percentage DECIMAL(5, 2),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE sleep_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    date DATE DEFAULT (curdate()),
    hours_slept DECIMAL(3,1),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE workout_logs (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  date DATE DEFAULT (curdate()),
  notes TEXT,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE exercise_entries
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  workout_log_id BIGINT,
  exercise_name VARCHAR(100),
  sets INT,
  reps INT,
  weight DECIMAL(5,2),
  FOREIGN KEY (workout_log_id) REFERENCES workout_logs(id) ON DELETE CASCADE
);

CREATE TABLE workout_feedback
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  workout_log_id BIGINT,
  energy_level INT,
  mood INT,
  difficulty INT,
  notes TEXT,
  FOREIGN KEY (workout_log_id) REFERENCES workout_logs(id) ON DELETE CASCADE
);
