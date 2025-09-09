CREATE TABLE workout_insights (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    workout_log_id BIGINT NOT NULL,
    generated_feedback TEXT,
    context_data TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_workout_insights_workout_log 
        FOREIGN KEY (workout_log_id) 
        REFERENCES workout_logs(id) 
        ON DELETE CASCADE,
    
    CONSTRAINT uk_workout_insights_workout_log 
        UNIQUE (workout_log_id)
);