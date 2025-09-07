-- ========================
-- Update Users Table
-- ========================
ALTER TABLE users
    MODIFY COLUMN created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- ========================
-- Add created_at to Body Stats
-- ========================
ALTER TABLE body_stats
    ADD COLUMN created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- ========================
-- Add created_at to Sleep Logs
-- ========================
ALTER TABLE sleep_logs
    ADD COLUMN created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

-- ========================
-- Add created_at to Workout Logs
-- ========================
ALTER TABLE workout_logs
    ADD COLUMN created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;
