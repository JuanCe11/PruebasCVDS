alter table RECURSO 
add constraint FK_RECURSO_TIPORECURSO 
foreign key(id_tipo) 
references TIPO_RECURSO(id);

alter table RESERVA 
add constraint FK_RESERVA_RECURSO 
foreign key(id_recurso) 
references RECURSO(id_interno);

alter table RESERVA 
add constraint FK_RESERVA_USUARIO 
foreign key(id_usuario) 
references USUARIO(id);

alter table HORARIO 
add constraint FK_HORARIO_RECURSO 
foreign key(id_recurso) 
references RECURSO(id_interno);

