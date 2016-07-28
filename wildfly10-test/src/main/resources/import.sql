--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
insert into "public"."member" (id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212');
INSERT INTO "public"."rol" (id,descripcion,estado,fecha_modificacion,fecha_registro,nombre,usuario_registro,id_rol_parent) VALUES ('1', 'GRUPO SUPER ADMINISTRADOR', 'AC', null, '2016-01-01 00:00:01', 'SUPER ADMINISTRADOR', '0', null);
ALTER SEQUENCE "public"."rol_id_seq" RESTART WITH 2;
INSERT INTO "public"."usuario" (id,email,estado,fecha_modificacion,fecha_registro,foto_perfil,login,nombre,pagina_inicio,password,peso_foto,tipo,usuario_registro,id_rol) VALUES ('1', 'mbr.bejarano@gmail.com', 'AC', null, '2016-01-01 00:00:01', null, 'admin', 'SUPER ADMINISTRADOR', '/pages/config/compania/index.xhtml', 'admin', '0', 'SUPER', '1', '1');
