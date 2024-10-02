Схема базы данных для сервиса записи на обслуживание
Таблицы:

Appointment (Запись на обслуживание)

id (PK)
appointment_date (Дата записи)
status (Статус: подтверждено, отменено, выполнено)
customer_id (FK на таблицу Customer)
service_center_id (FK на таблицу ServiceCenter)
service_id (FK на таблицу Service)
master_id (FK на таблицу Master)
Customer (Клиент)

id (PK)
first_name
last_name
phone
email
appointments (Relation to Appointment)
ServiceCenter (Автосервис)

id (PK)
name
address
phone
email
appointments (Relation to Appointment)
Service (Услуга)

id (PK)
name
description
price
appointments (Relation to Appointment)
Master (Мастер)

id (PK)
first_name
last_name
specialization
available (boolean)
appointments (Relation to Appointment)