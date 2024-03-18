--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: change_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.change_list (
    id bigint NOT NULL,
    user_id bigint,
    product_id bigint,
    created_at date NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.change_list OWNER TO postgres;

--
-- Name: change_list_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.change_list_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.change_list_id_seq OWNER TO postgres;

--
-- Name: change_list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.change_list_id_seq OWNED BY public.change_list.id;


--
-- Name: production_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.production_list (
    id bigint NOT NULL,
    created_date timestamp without time zone NOT NULL,
    user_id bigint,
    product_id bigint,
    quantity_produced integer NOT NULL
);


ALTER TABLE public.production_list OWNER TO postgres;

--
-- Name: production_list_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.production_list_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.production_list_id_seq OWNER TO postgres;

--
-- Name: production_list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.production_list_id_seq OWNED BY public.production_list.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id bigint NOT NULL,
    product_name character varying(100) NOT NULL,
    product_quantity integer NOT NULL,
    produced_quantity integer NOT NULL,
    measurement_system character varying(50) NOT NULL,
    created_date date NOT NULL,
    last_change_date date NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: rating; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rating (
    id bigint NOT NULL,
    user_id bigint,
    rating_value integer NOT NULL,
    rating_date date NOT NULL
);


ALTER TABLE public.rating OWNER TO postgres;

--
-- Name: rating_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rating_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rating_id_seq OWNER TO postgres;

--
-- Name: rating_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rating_id_seq OWNED BY public.rating.id;


--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roles_id_seq OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    role_id bigint,
    avr_rating integer
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: change_list id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.change_list ALTER COLUMN id SET DEFAULT nextval('public.change_list_id_seq'::regclass);


--
-- Name: production_list id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.production_list ALTER COLUMN id SET DEFAULT nextval('public.production_list_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: rating id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rating ALTER COLUMN id SET DEFAULT nextval('public.rating_id_seq'::regclass);


--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: change_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.change_list (id, user_id, product_id, created_at, quantity) FROM stdin;
2	4	3	2024-03-11	20
3	4	2	2024-03-12	40
4	4	3	2024-03-12	30
5	6	3	2024-03-12	30
6	6	3	2024-03-13	30
\.


--
-- Data for Name: production_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.production_list (id, created_date, user_id, product_id, quantity_produced) FROM stdin;
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, product_name, product_quantity, produced_quantity, measurement_system, created_date, last_change_date) FROM stdin;
2	Молоко	50	40	л	2024-03-09	2024-03-12
3	Яйца	110	110	ш	2024-03-11	2024-03-13
6	Кукуруза	15	0	кг	2024-03-14	2024-03-14
7	Яблоки	30	0	кг	2024-03-14	2024-03-14
8	Вишня	10	15	кг	2024-03-14	2024-03-14
\.


--
-- Data for Name: rating; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rating (id, user_id, rating_value, rating_date) FROM stdin;
1	6	5	2024-03-13
2	6	2	2024-03-13
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name) FROM stdin;
1	ROLE_USER
2	ROLE_OWNER
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, username, email, password, role_id, avr_rating) FROM stdin;
3	Petr	petr@gmail.com	$2a$10$BCISGiu14KjHiD/eV2qOpeKD/Y/lMP5kpS3DKpbfzJLpQRSmWbnW2	2	\N
4	Шевцов Владимир Сергеевич	vladimir@gmail.com	$2a$10$56vHuY0.pautZ6HCWqbDuusk3zVVTbWdqWgYrtEkcik/ult2.NqMO	1	\N
6	Иванов Иван Иванович	ivan@gmail.com	$2a$10$sipf5Z5tcc/eb7QIFABJdOgxR9D.RWOJfo164pabbxaZXg7IDYhz.	1	3
\.


--
-- Name: change_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.change_list_id_seq', 9, true);


--
-- Name: production_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.production_list_id_seq', 1, false);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 8, true);


--
-- Name: rating_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rating_id_seq', 2, true);


--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 2, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 8, true);


--
-- Name: change_list change_list_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.change_list
    ADD CONSTRAINT change_list_pkey PRIMARY KEY (id);


--
-- Name: production_list production_list_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.production_list
    ADD CONSTRAINT production_list_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: rating rating_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rating
    ADD CONSTRAINT rating_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: change_list change_list_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.change_list
    ADD CONSTRAINT change_list_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: change_list change_list_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.change_list
    ADD CONSTRAINT change_list_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: production_list production_list_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.production_list
    ADD CONSTRAINT production_list_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- Name: production_list production_list_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.production_list
    ADD CONSTRAINT production_list_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: rating rating_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rating
    ADD CONSTRAINT rating_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: users users_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- PostgreSQL database dump complete
--

