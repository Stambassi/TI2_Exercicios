--
-- PostgreSQL database dump
--

-- Dumped from database version 13.6 (Ubuntu 13.6-0ubuntu0.21.10.1)
-- Dumped by pg_dump version 13.6 (Ubuntu 13.6-0ubuntu0.21.10.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: id_form; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

CREATE SEQUENCE public."id_form"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000
    CACHE 1;


ALTER TABLE public."id_form" OWNER TO ti2cc;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: form; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.form (
    id_form integer DEFAULT nextval('public."id_form"'::regclass) NOT NULL,
    id_animal integer,
    id_usuario integer,
    status integer, 
    experiencia boolean,
    visitas_ong boolean,
    moradia text,
    disponibilidade text,
    viagem text,
    comentarios text
);


ALTER TABLE public.form OWNER TO ti2cc;

--
-- Name: form produto_pkey; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.form
    ADD CONSTRAINT form_pkey PRIMARY KEY (id_form);


--
-- PostgreSQL database dump complete
--    