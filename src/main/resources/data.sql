-- Insertar datos iniciales para pruebas
INSERT INTO users (id, name, email, password, created, modified, last_login, token, is_active)
VALUES
    ('1a2b3c4d-5678-9101-1121-314151617181', 'Juan Pérez', 'juan.perez@colombia.com', 'Password123!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'mocked-token-1', true),
    ('2b3c4d5e-6789-1011-1213-415161718192', 'María González', 'maria.gonzalez@chile.cl', 'Password123!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'mocked-token-2', true);
