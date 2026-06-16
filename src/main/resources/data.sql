INSERT INTO professores (id, nome, especialidade)
VALUES ('11111111-1111-1111-1111-111111111111', 'Carlos Souza', 'Nado crawl'),
       ('22222222-2222-2222-2222-222222222222', 'Fernanda Lima', 'Nado infantil'),
       ('12341234-1234-1234-1234-123412341234', 'Mariana Torres', 'Treino avancado');

INSERT INTO alunos (id, nome, email, status)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Ana Silva', 'ana@aquaflow.com', 'ATIVO'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Bruno Costa', 'bruno@aquaflow.com', 'ATIVO'),
       ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Clara Rocha', 'clara@aquaflow.com', 'INATIVO'),
       ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Diego Mendes', 'diego@aquaflow.com', 'ATIVO'),
       ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Elisa Prado', 'elisa@aquaflow.com', 'ATIVO'),
       ('ffffffff-ffff-ffff-ffff-ffffffffffff', 'Felipe Nunes', 'felipe@aquaflow.com', 'ATIVO');

INSERT INTO turmas (id, capacidade_maxima, nivel, nome, professor_id)
VALUES ('33333333-3333-3333-3333-333333333333', 20, 'BASICO', 'Turma Infantil A',
        '22222222-2222-2222-2222-222222222222'),
       ('44444444-4444-4444-4444-444444444444', 15, 'INTERMEDIARIO', 'Turma Adulto B',
        '11111111-1111-1111-1111-111111111111'),
       ('12121212-1212-1212-1212-121212121212', 10, 'AVANCADO', 'Turma Performance C',
        '12341234-1234-1234-1234-123412341234');

INSERT INTO matriculas (id, aluno_id, turma_id, status, valor_mensalidade)
VALUES ('55555555-5555-5555-5555-555555555555', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        '33333333-3333-3333-3333-333333333333', 'ATIVA', 199.90),
       ('66666666-6666-6666-6666-666666666666', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
        '44444444-4444-4444-4444-444444444444', 'ATIVA', 249.90),
       ('77770000-0000-0000-0000-000000000000', 'cccccccc-cccc-cccc-cccc-cccccccccccc',
        '44444444-4444-4444-4444-444444444444', 'CANCELADA', 229.90),
       ('88880000-0000-0000-0000-000000000000', 'dddddddd-dddd-dddd-dddd-dddddddddddd',
        '33333333-3333-3333-3333-333333333333', 'ATIVA', 189.90),
       ('99990000-0000-0000-0000-000000000000', 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee',
        '12121212-1212-1212-1212-121212121212', 'ATIVA', 299.90),
       ('aaaa0000-0000-0000-0000-000000000000', 'ffffffff-ffff-ffff-ffff-ffffffffffff',
        '12121212-1212-1212-1212-121212121212', 'ATIVA', 279.90);

INSERT INTO frequencias (id, data_aula, matricula_id, presente)
VALUES ('77777777-7777-7777-7777-777777777777', '2026-06-10', '55555555-5555-5555-5555-555555555555', true),
       ('88888888-8888-8888-8888-888888888888', '2026-06-11', '55555555-5555-5555-5555-555555555555', false),
       ('99999999-9999-9999-9999-999999999999', '2026-06-12', '66666666-6666-6666-6666-666666666666', true),
       ('11112222-3333-4444-5555-666677778888', '2026-06-13', '66666666-6666-6666-6666-666666666666', true),
       ('22223333-4444-5555-6666-777788889999', '2026-06-10', '88880000-0000-0000-0000-000000000000', true),
       ('33334444-5555-6666-7777-888899990000', '2026-06-11', '88880000-0000-0000-0000-000000000000', true),
       ('44445555-6666-7777-8888-999900001111', '2026-06-12', '99990000-0000-0000-0000-000000000000', false),
       ('55556666-7777-8888-9999-000011112222', '2026-06-13', '99990000-0000-0000-0000-000000000000', true),
       ('66667777-8888-9999-0000-111122223333', '2026-06-14', 'aaaa0000-0000-0000-0000-000000000000', true);

