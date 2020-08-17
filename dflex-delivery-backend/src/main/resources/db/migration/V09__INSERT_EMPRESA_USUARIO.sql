INSERT INTO public.tb001_endereco(
	co_endereco, co_cep, no_logradouro, nu_numero, no_complemento, no_bairro, no_cidade, no_uf, co_ibge)
	VALUES (1, '72544406', 'quadra 214 conjunto f', 25, '', 'Santa Maria', 'Brasilia', 'DF', '5300108');

INSERT INTO public.tb002_contato(
	co_contato, nu_telefone_movel, nu_telefone_fixo, no_email)
	VALUES (1, '61986460330', '6133946018', 'vendas@convenienciabrasilia.com');

INSERT INTO public.tb004_empresa(
	co_empresa, co_cnpj, no_razao_social, no_nome_fantasia, de_situacao, no_natureza_juridica, dt_abertura, no_tipo_empresa, de_porte, de_status, contato_co, endereco_co)
	VALUES (1, '72031754000167', 'u2d tecnologia', 'dflex delivery', 'ATIVA', '230-5 - Empresa Individual de Responsabilidade Limitada (de Natureza Empresária)', '13/07/2018', 'MATRIZ', 'MICRO EMPRESA', 'OK', 1, 1);

INSERT INTO public.tb005_usuario(
	no_nome, nu_cpf, co_senha, de_email, empresa_co)
	VALUES ('David Jeremias', '01696587166', '$2a$10$/6hcNUcKSQIv112gkJTUcOq2B/BVWsg9Vg9rMrIMOHhef6dUvVQJy', 'u2dtecnologia@gmail.com', 1);

INSERT INTO public.tb006_permissao(
	co_permissao, de_permissao)
	VALUES (1, 'ROLE_MASTER');

INSERT INTO public.tb006_permissao(
	co_permissao, de_permissao)
	VALUES (2, 'ROLE_ADMINISTRADOR');

INSERT INTO public.tb006_permissao(
	co_permissao, de_permissao)
	VALUES (3, 'ROLE_LOGISTA');

INSERT INTO public.tb006_permissao(
	co_permissao, de_permissao)
	VALUES (4, 'ROLE_OPERADOR');


INSERT INTO public.tb007_usuario_permissao(
	usuario_co, permissao_co)
	VALUES (1, 1);

INSERT INTO public.tb007_usuario_permissao(
	usuario_co, permissao_co)
	VALUES (1, 2);

INSERT INTO public.tb007_usuario_permissao(
	usuario_co, permissao_co)
	VALUES (1, 3);

INSERT INTO public.tb007_usuario_permissao(
	usuario_co, permissao_co)
	VALUES (1, 4);


