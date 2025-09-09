-- Synthetic test data for existing users (assumes user_id 1 = CUT, user_id 2 = BULK)
-- User 1 (CUT) - 2 weeks of workout logs (more cardio focused, maintaining strength)
INSERT INTO workout_logs (id, user_id, date, notes, created_at) VALUES
-- Week 1
(1, 1, '2025-08-26', 'Upper body strength - feeling energetic on new cut', '2025-08-26'),
(2, 1, '2025-08-27', 'Lower body + cardio - 20min HIIT after', '2025-08-27'),
(3, 1, '2025-08-29', 'Push day - maintaining weights while cutting', '2025-08-29'),
(4, 1, '2025-08-30', 'Pull day - focusing on form over weight', '2025-08-30'),
-- Week 2  
(5, 1, '2025-09-02', 'Upper body - energy lower but pushing through', '2025-09-02'),
(6, 1, '2025-09-03', 'Leg day - reduced volume due to fatigue', '2025-09-03'),
(7, 1, '2025-09-05', 'Push day - strength holding steady', '2025-09-05'),
(8, 1, '2025-09-06', 'Pull day - added extra cardio', '2025-09-06');

-- User 2 (BULK) - 2 weeks of workout logs (progressive overload focused)
INSERT INTO workout_logs (id, user_id, date, notes, created_at) VALUES
-- Week 1
(9, 2, '2025-08-26', 'Chest and triceps - feeling strong on bulk', '2025-08-26'),
(10, 2, '2025-08-27', 'Back and biceps - progressive overload', '2025-08-27'),
(11, 2, '2025-08-29', 'Legs - squats feeling heavy but good', '2025-08-29'),
(12, 2, '2025-08-30', 'Shoulders and arms - pump was incredible', '2025-08-30'),
-- Week 2
(13, 2, '2025-09-02', 'Chest and triceps - increased weight on bench', '2025-09-02'),
(14, 2, '2025-09-03', 'Back and biceps - new PR on rows', '2025-09-03'),
(15, 2, '2025-09-05', 'Legs - squats going up consistently', '2025-09-05'),
(16, 2, '2025-09-06', 'Shoulders and arms - volume increase', '2025-09-06');

-- Exercise entries for User 1 (CUT) - maintaining/slight decreases
INSERT INTO exercise_entries (workout_log_id, exercise_name, sets, reps, weight) VALUES
-- Workout 1 (Aug 26)
(1, 'Bench Press', 4, 8, 185.0),
(1, 'Incline Dumbbell Press', 3, 10, 70.0),
(1, 'Tricep Dips', 3, 12, 25.0),
(1, 'Pull-ups', 3, 8, 0.0),
-- Workout 2 (Aug 27)  
(2, 'Squats', 4, 8, 205.0),
(2, 'Romanian Deadlifts', 3, 10, 155.0),
(2, 'Leg Press', 3, 12, 270.0),
(2, 'Calf Raises', 4, 15, 45.0),
-- Workout 3 (Aug 29)
(3, 'Bench Press', 4, 8, 185.0),
(3, 'Overhead Press', 3, 8, 95.0),
(3, 'Dumbbell Flyes', 3, 12, 30.0),
(3, 'Tricep Extensions', 3, 12, 40.0),
-- Workout 4 (Aug 30)
(4, 'Deadlifts', 3, 6, 225.0),
(4, 'Barbell Rows', 4, 8, 135.0),
(4, 'Lat Pulldowns', 3, 10, 120.0),
(4, 'Bicep Curls', 3, 12, 35.0),
-- Workout 5 (Sep 2) - Week 2, slight fatigue
(5, 'Bench Press', 4, 7, 180.0),
(5, 'Incline Dumbbell Press', 3, 9, 65.0),
(5, 'Tricep Dips', 3, 10, 25.0),
(5, 'Pull-ups', 3, 6, 0.0),
-- Workout 6 (Sep 3)
(6, 'Squats', 3, 8, 200.0),
(6, 'Romanian Deadlifts', 3, 8, 150.0),
(6, 'Leg Press', 3, 10, 270.0),
(6, 'Calf Raises', 3, 15, 45.0),
-- Workout 7 (Sep 5)
(7, 'Bench Press', 4, 8, 185.0),
(7, 'Overhead Press', 3, 8, 95.0),
(7, 'Dumbbell Flyes', 3, 12, 30.0),
(7, 'Tricep Extensions', 3, 12, 40.0),
-- Workout 8 (Sep 6)
(8, 'Deadlifts', 3, 6, 225.0),
(8, 'Barbell Rows', 4, 8, 135.0),
(8, 'Lat Pulldowns', 3, 10, 120.0),
(8, 'Bicep Curls', 3, 12, 35.0);

-- Exercise entries for User 2 (BULK) - progressive increases
INSERT INTO exercise_entries (workout_log_id, exercise_name, sets, reps, weight) VALUES
-- Workout 9 (Aug 26)
(9, 'Bench Press', 4, 8, 175.0),
(9, 'Incline Dumbbell Press', 3, 10, 65.0),
(9, 'Tricep Dips', 3, 12, 35.0),
(9, 'Close Grip Bench', 3, 10, 135.0),
-- Workout 10 (Aug 27)
(10, 'Deadlifts', 3, 6, 205.0),
(10, 'Barbell Rows', 4, 8, 125.0),
(10, 'Lat Pulldowns', 3, 10, 110.0),
(10, 'Bicep Curls', 4, 12, 32.5),
-- Workout 11 (Aug 29)
(11, 'Squats', 4, 8, 185.0),
(11, 'Romanian Deadlifts', 3, 10, 145.0),
(11, 'Leg Press', 3, 12, 250.0),
(11, 'Calf Raises', 4, 15, 50.0),
-- Workout 12 (Aug 30)
(12, 'Overhead Press', 4, 8, 85.0),
(12, 'Lateral Raises', 3, 12, 20.0),
(12, 'Rear Delt Flyes', 3, 15, 15.0),
(12, 'Tricep Extensions', 3, 12, 45.0),
-- Workout 13 (Sep 2) - Week 2, progressive increases
(13, 'Bench Press', 4, 8, 180.0),
(13, 'Incline Dumbbell Press', 3, 10, 70.0),
(13, 'Tricep Dips', 3, 12, 40.0),
(13, 'Close Grip Bench', 3, 10, 140.0),
-- Workout 14 (Sep 3)
(14, 'Deadlifts', 3, 6, 215.0),
(14, 'Barbell Rows', 4, 8, 130.0),
(14, 'Lat Pulldowns', 3, 10, 115.0),
(14, 'Bicep Curls', 4, 12, 35.0),
-- Workout 15 (Sep 5)
(15, 'Squats', 4, 8, 195.0),
(15, 'Romanian Deadlifts', 3, 10, 155.0),
(15, 'Leg Press', 3, 12, 270.0),
(15, 'Calf Raises', 4, 15, 50.0),
-- Workout 16 (Sep 6)
(16, 'Overhead Press', 4, 8, 90.0),
(16, 'Lateral Raises', 3, 12, 22.5),
(16, 'Rear Delt Flyes', 3, 15, 17.5),
(16, 'Tricep Extensions', 3, 12, 50.0);

-- Workout feedback for User 1 (CUT) - energy declining over time due to deficit
INSERT INTO workout_feedback (workout_log_id, energy_level, mood, difficulty, notes) VALUES
(1, 8, 8, 5, 'Great energy to start the cut, weights felt light'),
(2, 7, 7, 6, 'Cardio was tough but manageable'),
(3, 7, 6, 6, 'Starting to feel the caloric deficit'),
(4, 6, 6, 7, 'Deadlifts felt heavier than usual'),
(5, 5, 5, 8, 'Low energy, had to dig deep to finish'),
(6, 5, 5, 7, 'Legs felt weak, reduced volume'),
(7, 6, 6, 7, 'Energy slightly better, caffeine helped'),
(8, 5, 6, 8, 'Tough session, strength maintaining though');

-- Workout feedback for User 2 (BULK) - energy high, getting stronger
INSERT INTO workout_feedback (workout_log_id, energy_level, mood, difficulty, notes) VALUES
(9, 8, 9, 5, 'Feeling strong and pumped for this bulk'),
(10, 8, 8, 6, 'Back pumps were insane, loving the extra food'),
(11, 7, 8, 6, 'Legs felt solid, weight is moving well'),
(12, 9, 9, 4, 'Amazing pump, shoulders felt great'),
(13, 8, 8, 5, 'Bench press PR! The bulk is working'),
(14, 8, 9, 5, 'New row PR, back is getting thick'),
(15, 9, 9, 6, 'Squats going up consistently, feeling powerful'),
(16, 8, 8, 5, 'Shoulders growing, loving the progress');

-- Body stats for User 1 (CUT) - losing weight and body fat
INSERT INTO body_stats (user_id, date, weight, body_fat_percentage, created_at) VALUES
(1, '2025-08-26', 180.5, 15.2, '2025-08-26'),
(1, '2025-09-02', 178.8, 14.8, '2025-09-02'),
(1, '2025-09-09', 177.2, 14.3, '2025-09-09');

-- Body stats for User 2 (BULK) - gaining weight
INSERT INTO body_stats (user_id, date, weight, body_fat_percentage, created_at) VALUES
(2, '2025-08-26', 165.2, 12.8, '2025-08-26'),
(2, '2025-09-02', 167.1, 13.1, '2025-09-02'),
(2, '2025-09-09', 168.8, 13.4, '2025-09-09');

-- Sleep logs for both users
INSERT INTO sleep_logs (user_id, date, hours_slept, created_at) VALUES
-- User 1 (CUT) - sleep affected by deficit
(1, '2025-08-25', 7.5, '2025-08-26'),
(1, '2025-08-26', 6.8, '2025-08-27'),
(1, '2025-08-27', 7.2, '2025-08-28'),
(1, '2025-08-28', 6.5, '2025-08-29'),
(1, '2025-08-29', 7.0, '2025-08-30'),
(1, '2025-08-30', 6.2, '2025-08-31'),
(1, '2025-08-31', 7.8, '2025-09-01'),
(1, '2025-09-01', 6.9, '2025-09-02'),
(1, '2025-09-02', 6.4, '2025-09-03'),
(1, '2025-09-03', 7.1, '2025-09-04'),
(1, '2025-09-04', 6.8, '2025-09-05'),
(1, '2025-09-05', 6.3, '2025-09-06'),
(1, '2025-09-06', 7.2, '2025-09-07'),
(1, '2025-09-07', 6.9, '2025-09-08'),
-- User 2 (BULK) - good sleep on surplus
(2, '2025-08-25', 8.1, '2025-08-26'),
(2, '2025-08-26', 8.3, '2025-08-27'),
(2, '2025-08-27', 7.8, '2025-08-28'),
(2, '2025-08-28', 8.2, '2025-08-29'),
(2, '2025-08-29', 8.0, '2025-08-30'),
(2, '2025-08-30', 8.5, '2025-08-31'),
(2, '2025-08-31', 8.1, '2025-09-01'),
(2, '2025-09-01', 8.4, '2025-09-02'),
(2, '2025-09-02', 7.9, '2025-09-03'),
(2, '2025-09-03', 8.3, '2025-09-04'),
(2, '2025-09-04', 8.1, '2025-09-05'),
(2, '2025-09-05', 8.6, '2025-09-06'),
(2, '2025-09-06', 8.2, '2025-09-07'),
(2, '2025-09-07', 8.4, '2025-09-08');