-- Создание таблицы reason
CREATE TABLE reason (
                        id SERIAL PRIMARY KEY,
                        reason_text VARCHAR(255) NOT NULL
);

-- Заполнение таблицы reason
INSERT INTO reason (id, reason_text) VALUES
                                         (1, 'Отпуск'),
                                         (2, 'Больничный'),
                                         (3, 'Прогул');

-- Создание таблицы accounting
CREATE TABLE accounting (
                            id SERIAL PRIMARY KEY,
                            reason_id INT NOT NULL REFERENCES reason(id),
                            start_date DATE NOT NULL,
                            duration INT NOT NULL,
                            discounted BOOLEAN NOT NULL,
                            description TEXT NOT NULL
);
