INSERT INTO professores (id, nome, especialidade)
VALUES ('11111111-1111-1111-1111-111111111111', 'Carlos Souza', 'Nado crawl'),
       ('22222222-2222-2222-2222-222222222222', 'Fernanda Lima', 'Nado infantil');

INSERT INTO alunos (id, nome, email, status)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Ana Silva', 'ana@aquaflow.com', 'ATIVO'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Bruno Costa', 'bruno@aquaflow.com', 'ATIVO'),
       ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Clara Rocha', 'clara@aquaflow.com', 'INATIVO');

INSERT INTO turmas (id, capacidade_maxima, nivel, nome, professor_id)
VALUES ('33333333-3333-3333-3333-333333333333', 20, 'BASICO', 'Turma Infantil A',
        '22222222-2222-2222-2222-222222222222'),
       ('44444444-4444-4444-4444-444444444444', 15, 'INTERMEDIARIO', 'Turma Adulto B',
        '11111111-1111-1111-1111-111111111111');

INSERT INTO matriculas (id, aluno_id, turma_id, status, valor_mensalidade)
VALUES ('55555555-5555-5555-5555-555555555555', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
        '33333333-3333-3333-3333-333333333333', 'ATIVA', 199.90),
       ('66666666-6666-6666-6666-666666666666', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
        '44444444-4444-4444-4444-444444444444', 'ATIVA', 249.90);

INSERT INTO frequencias (id, data_aula, matricula_id, presente)
VALUES ('77777777-7777-7777-7777-777777777777', '2026-06-10', '55555555-5555-5555-5555-555555555555', true),
       ('88888888-8888-8888-8888-888888888888', '2026-06-11', '55555555-5555-5555-5555-555555555555', false),
       ('99999999-9999-9999-9999-999999999999', '2026-06-12', '66666666-6666-6666-6666-666666666666', true);

