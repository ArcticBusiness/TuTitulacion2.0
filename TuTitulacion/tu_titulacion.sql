--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 9.5.12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: codigo_verificacion_de; Type: TABLE; Schema: public; Owner: miguel
--

CREATE TABLE public.codigo_verificacion_de (
    id_codigo_verificacion integer NOT NULL,
    codigo_verificacion character varying(6),
    fk_usuario integer
);


ALTER TABLE public.codigo_verificacion_de OWNER TO miguel;

--
-- Name: codigo_verificacion_de_id_codigo_verificacion_seq; Type: SEQUENCE; Schema: public; Owner: miguel
--

CREATE SEQUENCE public.codigo_verificacion_de_id_codigo_verificacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.codigo_verificacion_de_id_codigo_verificacion_seq OWNER TO miguel;

--
-- Name: codigo_verificacion_de_id_codigo_verificacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: miguel
--

ALTER SEQUENCE public.codigo_verificacion_de_id_codigo_verificacion_seq OWNED BY public.codigo_verificacion_de.id_codigo_verificacion;


--
-- Name: comentario; Type: TABLE; Schema: public; Owner: miguel
--

CREATE TABLE public.comentario (
    id_comentario integer NOT NULL,
    contenido_comentario text,
    fecha timestamp without time zone
);


ALTER TABLE public.comentario OWNER TO miguel;

--
-- Name: comentario_en; Type: TABLE; Schema: public; Owner: miguel
--

CREATE TABLE public.comentario_en (
    id_comentario_en integer NOT NULL,
    fk_comentario integer,
    fk_usuario integer,
    fk_pregunta integer
);


ALTER TABLE public.comentario_en OWNER TO miguel;

--
-- Name: comentario_en_id_comentario_en_seq; Type: SEQUENCE; Schema: public; Owner: miguel
--

CREATE SEQUENCE public.comentario_en_id_comentario_en_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comentario_en_id_comentario_en_seq OWNER TO miguel;

--
-- Name: comentario_en_id_comentario_en_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: miguel
--

ALTER SEQUENCE public.comentario_en_id_comentario_en_seq OWNED BY public.comentario_en.id_comentario_en;


--
-- Name: comentario_id_comentario_seq; Type: SEQUENCE; Schema: public; Owner: miguel
--

CREATE SEQUENCE public.comentario_id_comentario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comentario_id_comentario_seq OWNER TO miguel;

--
-- Name: comentario_id_comentario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: miguel
--

ALTER SEQUENCE public.comentario_id_comentario_seq OWNED BY public.comentario.id_comentario;


--
-- Name: pregunta; Type: TABLE; Schema: public; Owner: miguel
--

CREATE TABLE public.pregunta (
    id_pregunta integer NOT NULL,
    contenido_pregunta text,
    fecha_pregunta timestamp without time zone
);


ALTER TABLE public.pregunta OWNER TO miguel;

--
-- Name: pregunta_de; Type: TABLE; Schema: public; Owner: miguel
--

CREATE TABLE public.pregunta_de (
    id_pregunta_de integer NOT NULL,
    fk_pregunta integer,
    fk_usuario integer
);


ALTER TABLE public.pregunta_de OWNER TO miguel;

--
-- Name: pregunta_de_id_pregunta_de_seq; Type: SEQUENCE; Schema: public; Owner: miguel
--

CREATE SEQUENCE public.pregunta_de_id_pregunta_de_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pregunta_de_id_pregunta_de_seq OWNER TO miguel;

--
-- Name: pregunta_de_id_pregunta_de_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: miguel
--

ALTER SEQUENCE public.pregunta_de_id_pregunta_de_seq OWNED BY public.pregunta_de.id_pregunta_de;


--
-- Name: pregunta_id_pregunta_seq; Type: SEQUENCE; Schema: public; Owner: miguel
--

CREATE SEQUENCE public.pregunta_id_pregunta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pregunta_id_pregunta_seq OWNER TO miguel;

--
-- Name: pregunta_id_pregunta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: miguel
--

ALTER SEQUENCE public.pregunta_id_pregunta_seq OWNED BY public.pregunta.id_pregunta;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: miguel
--

CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    nombre_usuario text,
    contrasenia text,
    correo_electronico text,
    url_imagen text,
    correo_verificado boolean
);


ALTER TABLE public.usuario OWNER TO miguel;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: miguel
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_usuario_seq OWNER TO miguel;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: miguel
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- Name: id_codigo_verificacion; Type: DEFAULT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.codigo_verificacion_de ALTER COLUMN id_codigo_verificacion SET DEFAULT nextval('public.codigo_verificacion_de_id_codigo_verificacion_seq'::regclass);


--
-- Name: id_comentario; Type: DEFAULT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.comentario ALTER COLUMN id_comentario SET DEFAULT nextval('public.comentario_id_comentario_seq'::regclass);


--
-- Name: id_comentario_en; Type: DEFAULT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.comentario_en ALTER COLUMN id_comentario_en SET DEFAULT nextval('public.comentario_en_id_comentario_en_seq'::regclass);


--
-- Name: id_pregunta; Type: DEFAULT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.pregunta ALTER COLUMN id_pregunta SET DEFAULT nextval('public.pregunta_id_pregunta_seq'::regclass);


--
-- Name: id_pregunta_de; Type: DEFAULT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.pregunta_de ALTER COLUMN id_pregunta_de SET DEFAULT nextval('public.pregunta_de_id_pregunta_de_seq'::regclass);


--
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- Data for Name: codigo_verificacion_de; Type: TABLE DATA; Schema: public; Owner: miguel
--

COPY public.codigo_verificacion_de (id_codigo_verificacion, codigo_verificacion, fk_usuario) FROM stdin;
\.


--
-- Name: codigo_verificacion_de_id_codigo_verificacion_seq; Type: SEQUENCE SET; Schema: public; Owner: miguel
--

SELECT pg_catalog.setval('public.codigo_verificacion_de_id_codigo_verificacion_seq', 1, true);


--
-- Data for Name: comentario; Type: TABLE DATA; Schema: public; Owner: miguel
--

COPY public.comentario (id_comentario, contenido_comentario, fecha) FROM stdin;
1	hola	\N
\.


--
-- Data for Name: comentario_en; Type: TABLE DATA; Schema: public; Owner: miguel
--

COPY public.comentario_en (id_comentario_en, fk_comentario, fk_usuario, fk_pregunta) FROM stdin;
\.


--
-- Name: comentario_en_id_comentario_en_seq; Type: SEQUENCE SET; Schema: public; Owner: miguel
--

SELECT pg_catalog.setval('public.comentario_en_id_comentario_en_seq', 1, false);


--
-- Name: comentario_id_comentario_seq; Type: SEQUENCE SET; Schema: public; Owner: miguel
--

SELECT pg_catalog.setval('public.comentario_id_comentario_seq', 1, true);


--
-- Data for Name: pregunta; Type: TABLE DATA; Schema: public; Owner: miguel
--

COPY public.pregunta (id_pregunta, contenido_pregunta, fecha_pregunta) FROM stdin;
1	ola	2018-04-17 00:00:00
\.


--
-- Data for Name: pregunta_de; Type: TABLE DATA; Schema: public; Owner: miguel
--

COPY public.pregunta_de (id_pregunta_de, fk_pregunta, fk_usuario) FROM stdin;
\.


--
-- Name: pregunta_de_id_pregunta_de_seq; Type: SEQUENCE SET; Schema: public; Owner: miguel
--

SELECT pg_catalog.setval('public.pregunta_de_id_pregunta_de_seq', 1, false);


--
-- Name: pregunta_id_pregunta_seq; Type: SEQUENCE SET; Schema: public; Owner: miguel
--

SELECT pg_catalog.setval('public.pregunta_id_pregunta_seq', 1, true);


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: miguel
--

COPY public.usuario (id_usuario, nombre_usuario, contrasenia, correo_electronico, url_imagen, correo_verificado) FROM stdin;
1	MIguel	1995mamp	migue95ange@gmail.com	jseadfhagkuyfbaehr	t
\.


--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: miguel
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 1, true);


--
-- Name: codigo_verificacion_de_pkey; Type: CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.codigo_verificacion_de
    ADD CONSTRAINT codigo_verificacion_de_pkey PRIMARY KEY (id_codigo_verificacion);


--
-- Name: comentario_en_pkey; Type: CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.comentario_en
    ADD CONSTRAINT comentario_en_pkey PRIMARY KEY (id_comentario_en);


--
-- Name: comentario_pkey; Type: CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_pkey PRIMARY KEY (id_comentario);


--
-- Name: pregunta_de_pkey; Type: CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.pregunta_de
    ADD CONSTRAINT pregunta_de_pkey PRIMARY KEY (id_pregunta_de);


--
-- Name: pregunta_pkey; Type: CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.pregunta
    ADD CONSTRAINT pregunta_pkey PRIMARY KEY (id_pregunta);


--
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- Name: codigo_verificacion_de_fk_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.codigo_verificacion_de
    ADD CONSTRAINT codigo_verificacion_de_fk_usuario_fkey FOREIGN KEY (fk_usuario) REFERENCES public.usuario(id_usuario);


--
-- Name: comentario_en_fk_comentario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.comentario_en
    ADD CONSTRAINT comentario_en_fk_comentario_fkey FOREIGN KEY (fk_comentario) REFERENCES public.comentario(id_comentario);


--
-- Name: comentario_en_fk_pregunta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.comentario_en
    ADD CONSTRAINT comentario_en_fk_pregunta_fkey FOREIGN KEY (fk_pregunta) REFERENCES public.pregunta(id_pregunta);


--
-- Name: comentario_en_fk_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.comentario_en
    ADD CONSTRAINT comentario_en_fk_usuario_fkey FOREIGN KEY (fk_usuario) REFERENCES public.usuario(id_usuario);


--
-- Name: pregunta_de_fk_pregunta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.pregunta_de
    ADD CONSTRAINT pregunta_de_fk_pregunta_fkey FOREIGN KEY (fk_pregunta) REFERENCES public.pregunta(id_pregunta);


--
-- Name: pregunta_de_fk_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: miguel
--

ALTER TABLE ONLY public.pregunta_de
    ADD CONSTRAINT pregunta_de_fk_usuario_fkey FOREIGN KEY (fk_usuario) REFERENCES public.usuario(id_usuario);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

