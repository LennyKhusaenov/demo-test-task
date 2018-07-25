PGDMP     	                    v            users    9.6.3    9.6.3     j           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            k           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            l           1262    35495    users    DATABASE     �   CREATE DATABASE users WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE users;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            m           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            n           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    43723    hibernate_sequence    SEQUENCE     t   CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    3            �            1259    43710    sectors    TABLE     k   CREATE TABLE sectors (
    sector_id bigint NOT NULL,
    label character varying,
    parent_id bigint
);
    DROP TABLE public.sectors;
       public         postgres    false    3            �            1259    43744    spring_session    TABLE     /  CREATE TABLE spring_session (
    primary_id character(36) NOT NULL,
    session_id character(36) NOT NULL,
    creation_time bigint NOT NULL,
    last_access_time bigint NOT NULL,
    max_inactive_interval integer NOT NULL,
    expiry_time bigint NOT NULL,
    principal_name character varying(100)
);
 "   DROP TABLE public.spring_session;
       public         postgres    false    3            �            1259    43752    spring_session_attributes    TABLE     �   CREATE TABLE spring_session_attributes (
    session_primary_id character(36) NOT NULL,
    attribute_name character varying(200) NOT NULL,
    attribute_bytes bytea NOT NULL
);
 -   DROP TABLE public.spring_session_attributes;
       public         postgres    false    3            �            1259    43715    users    TABLE     �   CREATE TABLE users (
    session_id character varying(255) NOT NULL,
    agreement boolean,
    name character varying(255),
    sector_id bigint
);
    DROP TABLE public.users;
       public         postgres    false    3            o           0    0    hibernate_sequence    SEQUENCE SET     :   SELECT pg_catalog.setval('hibernate_sequence', 1, false);
            public       postgres    false    187            c          0    43710    sectors 
   TABLE DATA               7   COPY sectors (sector_id, label, parent_id) FROM stdin;
    public       postgres    false    185   �       f          0    43744    spring_session 
   TABLE DATA               �   COPY spring_session (primary_id, session_id, creation_time, last_access_time, max_inactive_interval, expiry_time, principal_name) FROM stdin;
    public       postgres    false    188   g!       g          0    43752    spring_session_attributes 
   TABLE DATA               a   COPY spring_session_attributes (session_primary_id, attribute_name, attribute_bytes) FROM stdin;
    public       postgres    false    189   �!       d          0    43715    users 
   TABLE DATA               @   COPY users (session_id, agreement, name, sector_id) FROM stdin;
    public       postgres    false    186   �!       �           2606    43714    sectors sectors_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY sectors
    ADD CONSTRAINT sectors_pkey PRIMARY KEY (sector_id);
 >   ALTER TABLE ONLY public.sectors DROP CONSTRAINT sectors_pkey;
       public         postgres    false    185    185            �           2606    43759 6   spring_session_attributes spring_session_attributes_pk 
   CONSTRAINT     �   ALTER TABLE ONLY spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_primary_id, attribute_name);
 `   ALTER TABLE ONLY public.spring_session_attributes DROP CONSTRAINT spring_session_attributes_pk;
       public         postgres    false    189    189    189            �           2606    43748     spring_session spring_session_pk 
   CONSTRAINT     _   ALTER TABLE ONLY spring_session
    ADD CONSTRAINT spring_session_pk PRIMARY KEY (primary_id);
 J   ALTER TABLE ONLY public.spring_session DROP CONSTRAINT spring_session_pk;
       public         postgres    false    188    188            �           2606    43722    users users_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (session_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public         postgres    false    186    186            �           1259    43749    spring_session_ix1    INDEX     S   CREATE UNIQUE INDEX spring_session_ix1 ON spring_session USING btree (session_id);
 &   DROP INDEX public.spring_session_ix1;
       public         postgres    false    188            �           1259    43750    spring_session_ix2    INDEX     M   CREATE INDEX spring_session_ix2 ON spring_session USING btree (expiry_time);
 &   DROP INDEX public.spring_session_ix2;
       public         postgres    false    188            �           1259    43751    spring_session_ix3    INDEX     P   CREATE INDEX spring_session_ix3 ON spring_session USING btree (principal_name);
 &   DROP INDEX public.spring_session_ix3;
       public         postgres    false    188            �           2606    43725 #   sectors fkq1348ewyn6hb4vr2yypo2ofcy    FK CONSTRAINT        ALTER TABLE ONLY sectors
    ADD CONSTRAINT fkq1348ewyn6hb4vr2yypo2ofcy FOREIGN KEY (parent_id) REFERENCES sectors(sector_id);
 M   ALTER TABLE ONLY public.sectors DROP CONSTRAINT fkq1348ewyn6hb4vr2yypo2ofcy;
       public       postgres    false    185    185    2017            �           2606    43730 !   users fksm6sv7j8yj27ns2josjloy4pl    FK CONSTRAINT     }   ALTER TABLE ONLY users
    ADD CONSTRAINT fksm6sv7j8yj27ns2josjloy4pl FOREIGN KEY (sector_id) REFERENCES sectors(sector_id);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT fksm6sv7j8yj27ns2josjloy4pl;
       public       postgres    false    186    185    2017            �           2606    43760 6   spring_session_attributes spring_session_attributes_fk    FK CONSTRAINT     �   ALTER TABLE ONLY spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_primary_id) REFERENCES spring_session(primary_id) ON DELETE CASCADE;
 `   ALTER TABLE ONLY public.spring_session_attributes DROP CONSTRAINT spring_session_attributes_fk;
       public       postgres    false    189    2024    188            c   �  x�mU�r�6>CO�S�;�m�����v��&�xf�3{�%�f-�.Iś[_���'�ʲ�N/6�� �Ѓ�C+j?X�7��K���h��P{et�/���$��N���j�ď{PNw�4�v+_�	kA�b'�[�C\�����58ʣ�����A
���.�����F���=���,&�r��;�9-�]ܢ*R��V�&וd(�o�1�����Q����mA+h�U]c��篿]|������V�Ut�^����c۪Ze1��x����)J|c�ZBO��v��\$�)�So3���쬂�~*5H�_@��A�{@�ޘnċ�>�شh����U^�2��tC����Q��⃱������9�B��q|���#z٪}l��
��{tء����c/�x2j̒cYN��|�J{���e�}er�)�p]��e�����7U�EYA����q���G�<w06VG#�-sZ~Y~Y��Ye���HW� i��1��蓀��W��5�z��x4O���x�?#��� GR�!%>��J~�kf'��	s.8-�����8�p�Z:��e�զ3����gؙC�RD9.l�1;��)���
e����e�Vu2d/f�n4�آ"����U8W̢"�(�����iT�]� ��gt/ֲ�Ni��w�i%�{�N����oyR�<*��3h}�%�~�鹲�UT,*�iL.6"�r6��0Q��j��ԫ��n�R&Q���ؼ]����W����6XЏ��i����
����a_�eN�uk,��_�V������(�Yxq��rU�)����'��i�A`!����������z�^�Vh׍]�_#�7և���F��
hI7�R��g�� ��و&�h��rY��q�UN�rEw�m�h���C���(����'�      f   `   x�U���0�j{�<��� M���G�C�+ ]�8!�O�fA�s�o^���ٸ+�� �he����.E�����U(�?��\g��Z�Z�.      g      x������ � �      d   Z  x����U1D��_��p��!�@4�4N숣�J��wE��dfL��Zs�p���
H��a�CO���?�g9�z�
���"��:U�������Gk�R�@�� �
�>+pe�~hn��?�O�R�5qp�8�g,����(�O3/����3�x�1������Z���+�9u��aW莹V-�]"�����eO����.MJ�n%��oЅ
k��Fe��1���J,�\�HU��;h�幀/cY��,���J�����l�^��R�c+|�n�H�����%�岈����&�fع�!�~��߇%���Nm�c�;�-�7��O.m�-��uŎ���9����     