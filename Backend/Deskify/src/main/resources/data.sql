INSERT INTO `permissions` (`id`, `name`, `description`) VALUES
(1, 'CREATE_TICKET', 'Permission to create new tickets.'),
(2, 'VIEW_TICKET', 'Permission to view ticket details.'),
(3, 'UPDATE_TICKET', 'Permission to update tickets.'),
(4, 'DELETE_TICKET', 'Permission to delete tickets.'),
(5, 'MANAGE_USERS', 'Permission to manage users (create, update, delete).'),
(6, 'MANAGE_ROLES', 'Permission to manage roles and their permissions.'),
(7, 'VIEW_REPORTS', 'Permission to view system-generated reports.'),
(8, 'ASSIGN_TICKET', 'Permission to assign tickets to agents.'),
(9, 'CHANGE_TICKET_PRIORITY', 'Permission to change ticket priority levels.'),
(10, 'ACCESS_ADMIN_PANEL', 'Permission to access the admin panel features.');

INSERT INTO `status` (`id`,`name`, `description`, `is_active`) 
VALUES 
(1, 'Open', 'El ticket ha sido creado y está a la espera de atención.', TRUE),
(2, 'In Progress', 'El ticket está siendo gestionado por un agente.', TRUE),
(3, 'Pending', 'El ticket está a la espera de una respuesta por parte del cliente.', TRUE),
(4, 'Closed', 'El ticket ha sido resuelto y cerrado.', FALSE),
(5, 'Solved', 'El ticket ha sido resuelto pero no cerrado.', TRUE);

INSERT INTO `plans` (`id`, `name`, `description`, `price`, `duration`) 
VALUES 
(1, 'Gratis', 'Plan gratuito con acceso limitado a las características básicas durante 14 días.', NULL, 14),
(2, 'Empleado', 'Plan de propiedad de los empleados para trabajar como agentes.', NULL, NULL),
(3, 'Básico', 'Plan básico con funciones limitadas, adecuado para usuarios ocasionales.', 9.95, 30),
(4, 'Estándar', 'Plan intermedio con más funciones y acceso a soporte prioritario.', 14.99, 30),
(5, 'Premium', 'Plan premium con acceso completo a todas las funciones y soporte prioritario.', 19.95, 30),
(6, 'Básico Tres Meses', 'Plan básico por tres meses, ideal para usuarios que necesitan más tiempo.', 25.95, 90),
(7, 'Estándar Tres Meses', 'Plan intermedio por tres meses con funciones y beneficios adicionales.', 39.95, 90),
(8, 'Premium Tres Meses', 'Plan premium por tres meses con acceso completo a todas las funciones.', 49.95, 90),
(9, 'Básico Año', 'Plan anual básico, ideal para quienes prefieren suscripciones a largo plazo con descuento.', 19.45, 365),
(10, 'Estándar Año', 'Plan anual intermedio con más funciones y precios competitivos.', 164.89, 365),
(11, 'Premium Año', 'Plan anual premium con acceso completo a todas las funciones durante un año.', 219.45, 365);

INSERT INTO `priorities` (`id`, `name`, `description`) VALUES
(1, 'Low', 'El problema no es urgente y puede resolverse en un momento posterior.'),
(2, 'Medium', 'El problema es importante, pero no requiere atención inmediata.'),
(3, 'High', 'El problema debe ser atendido de inmediato y tiene una importancia significativa.'),
(4, 'Urgent', 'El problema requiere atención inmediata y resolución.');

INSERT INTO `categories` (`id`, `name`, `description`) VALUES
(1, 'Technical Support', 'Issues related to hardware, software, and system troubleshooting.'),
(2, 'Billing', 'Issues related to invoices, payments, and subscription plans.'),
(3, 'Account Management', 'Questions or issues related to user accounts and profiles.'),
(4, 'General Inquiry', 'General questions or requests not related to specific issues.'),
(5, 'Network Issues', 'Problems related to internet connectivity, network access, and infrastructure.'),
(6, 'Security', 'Concerns related to system security, data breaches, or suspicious activities.'),
(7, 'Software Installation', 'Assistance with installing or configuring software applications.'),
(8, 'Feature Request', 'Requests for new features or enhancements to existing functionality.');

INSERT INTO `roles` (`id`, `name`, `description`) VALUES
(1, 'Admin', 'Acceso completo a todas las funcionalidades del sistema, incluyendo la gestión de usuarios y configuración.'),
(2, 'Supervisor', 'Monitorea la calidad de los tickets y el rendimiento de los agentes.'),
(3, 'Manager', 'Supervisa a los agentes y gestiona las asignaciones de tickets y el rendimiento.'),
(4, 'Agent', 'Gestiona los tickets y proporciona soporte técnico a los usuarios.'),
(5, 'User', 'Puede crear tickets y realizar un seguimiento de su estado.');

INSERT INTO `role_permissions` (`role_id`, `permission_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(2, 7),
(3, 5),
(3, 6),
(3, 8),
(4, 1),
(4, 2),
(4, 3),
(4, 4);


INSERT INTO `users` (`id`, `first_name`, `middle_name`, `last_name`, `phone_number`, `email`, `password`,`company`, `profile_picture_url`, `role_id`, `created_at`, `updated_at`) VALUES
(1, 'Eloy', 'Lozano', 'Barrios', '+34633640634', 'eloy.lozano@gmail.com', 'Abcd123', NULL, "/upload/default-profile.jpg", 4, '2023-01-15 09:45:32', '2023-01-15 09:45:32'),
(2, 'Jane', 'A.', 'Doe', '+34623234567', 'jane.doe@example.com', 'Abcd123', NULL, NULL, 4, '2023-02-03 08:30:12', '2023-02-03 08:30:12'),
(3, 'Alice', '', 'Johnson', '+34634345678', 'alice.johnson@example.com', 'Abcd123', NULL, NULL, 5, '2023-05-22 14:21:47', '2023-05-22 14:21:47'),
(4, 'Bob', 'M.', 'Williams', '+34645456789', 'bob.williams@example.com', 'Abcd123', NULL, NULL, 3, '2023-06-11 12:55:23', '2023-06-11 12:55:23'),
(5, 'Charlie', '', 'Brown', '+34656567890', 'charlie.brown@example.com', 'Abcd123', NULL, NULL, 3, '2023-07-01 09:12:09', '2023-07-01 09:12:09'),
(6, 'Emily', 'R.', 'Clark', '+34667678901', 'emily.clark@example.com', 'Abcd123', NULL, NULL, 5, '2023-08-15 11:45:51', '2023-08-15 11:45:51'),
(7, 'David', 'J.', 'Martinez', '+34678789012', 'david.martinez@example.com', 'Abcd123', NULL, NULL, 5, '2023-09-09 15:09:28', '2023-09-09 15:09:28'),
(8, 'Sophia', 'L.', 'Garcia', '+34689890123', 'sophia.garcia@example.com', 'Abcd123', NULL, NULL, 5, '2023-10-04 06:29:54', '2023-10-04 06:29:54'),
(9, 'Michael', '', 'Lopez', '+34690901234', 'michael.lopez@example.com', 'Abcd123', NULL, NULL, 3, '2023-11-19 11:13:05', '2023-11-19 11:13:05'),
(10, 'Olivia', 'C.', 'Hernandez', '+34691012345', 'olivia.hernandez@example.com', 'Abcd123', NULL, NULL, 5, '2023-12-02 13:24:36', '2023-12-02 13:24:36'),
(11, 'Liam', '', 'Taylor', '+34692123456', 'liam.taylor@example.com', 'Abcd123', NULL, NULL, 4, '2023-01-25 08:41:56', '2023-01-25 08:41:56'),
(12, 'Mason', 'T.', 'Wilson', '+34693234567', 'mason.wilson@example.com', 'Abcd123', NULL, NULL, 5, '2023-02-14 10:34:17', '2023-02-14 10:34:17'),
(13, 'Isabella', '', 'Moore', '+34694345678', 'isabella.moore@example.com', 'Abcd123', NULL, NULL, 5, '2023-03-09 14:56:11', '2023-03-09 14:56:11'),
(14, 'Lucas', 'P.', 'Anderson', '+34695456789', 'lucas.anderson@example.com', 'Abcd123', NULL, NULL, 4, '2023-04-10 08:29:38', '2023-04-10 08:29:38'),
(15, 'Zoe', 'B.', 'Thomas', '+34696567890', 'zoe.thomas@example.com', 'Abcd123', NULL, NULL, 3, '2023-05-15 10:16:53', '2023-05-15 10:16:53'),
(16, 'Amelia', 'K.', 'Jackson', '+34697678901', 'amelia.jackson@example.com', 'Abcd123', NULL, NULL, 5, '2023-06-20 07:02:17', '2023-06-20 07:02:17'),
(17, 'Aiden', 'D.', 'White', '+34698789012', 'aiden.white@example.com', 'Abcd123', NULL, NULL, 3, '2023-07-30 12:47:42', '2023-07-30 12:47:42'),
(18, 'Ethan', '', 'Lee', '+34699890123', 'ethan.lee@example.com', 'Abcd123', NULL, NULL, 4, '2023-08-13 14:36:24', '2023-08-13 14:36:24'),
(19, 'Abigail', 'V.', 'Young', '+34600901234', 'abigail.young@example.com', 'Abcd123', NULL, NULL, 5, '2023-09-25 08:05:17', '2023-09-25 08:05:17'),
(20, 'Mia', 'H.', 'Walker', '+34601012345', 'mia.walker@example.com', 'Abcd123', NULL, NULL, 4, '2023-10-30 07:19:22', '2023-10-30 07:19:22'),
(21, 'William', 'N.', 'Allen', '+34602123456', 'william.allen@example.com', 'Abcd123', NULL, NULL, 5, '2023-11-03 12:03:58', '2023-11-03 12:03:58'),
(22, 'James', 'L.', 'King', '+34603234567', 'james.king@example.com', 'Abcd123', NULL, NULL, 1, '2023-12-01 11:18:11', '2023-12-01 11:18:11'),
(23, 'Benjamin', 'Q.', 'Scott', '+34604345678', 'benjamin.scott@example.com', 'Abcd123', NULL, NULL, 5, '2023-01-18 08:27:32', '2023-01-18 08:27:32'),
(24, 'Chloe', 'R.', 'Adams', '+34605456789', 'chloe.adams@example.com', 'Abcd123', NULL, NULL, 4, '2023-02-23 10:14:04', '2023-02-23 10:14:04'),
(25, 'Avery', '', 'Baker', '+34606567890', 'avery.baker@example.com', 'Abcd123', NULL, NULL, 5, '2023-03-29 12:02:43', '2023-03-29 12:02:43'),
(26, 'Daniel', 'J.', 'Gonzalez', '+34607678901', 'daniel.gonzalez@example.com', 'Abcd123', NULL, NULL, 5, '2023-04-26 14:11:29', '2023-04-26 14:11:29'),
(27, 'Grace', 'S.', 'Nelson', '+34608789012', 'grace.nelson@example.com', 'Abcd123', NULL, NULL, 5, '2023-05-16 11:37:10', '2023-05-16 11:37:10'),
(28, 'Jack', '', 'Carter', '+34609890123', 'jack.carter@example.com', 'Abcd123', NULL, NULL, 5, '2023-06-01 08:25:44', '2023-06-01 08:25:44'),
(29, 'Lily', 'P.', 'Mitchell', '+34610901234', 'lily.mitchell@example.com', 'Abcd123', NULL, NULL, 5, '2023-07-04 10:18:59', '2023-07-04 10:18:59');


INSERT INTO `subscriptions` (`user_id`, `plan_id`, `start_date`, `end_date`, `is_active`) 
VALUES
(1, 2, '2024-01-01 00:00:00', '2025-01-31 23:59:59', TRUE),
(3, 2, '2024-01-01 00:00:00', '2024-01-31 23:59:59', TRUE),
(5, 3, '2024-02-01 00:00:00', '2024-02-29 23:59:59', TRUE),
(6, 4, '2024-03-01 00:00:00', '2024-03-31 23:59:59', TRUE),
(7, 5, '2024-04-01 00:00:00', '2024-06-30 23:59:59', TRUE),
(8, 6, '2024-05-01 00:00:00', '2024-07-31 23:59:59', TRUE),
(10, 7, '2024-06-01 00:00:00', '2024-08-31 23:59:59', FALSE),
(12, 8, '2024-07-01 00:00:00', '2025-06-30 23:59:59', TRUE),
(13, 9, '2024-08-01 00:00:00', '2025-07-31 23:59:59', TRUE),
(16, 10, '2024-09-01 00:00:00', '2025-08-31 23:59:59', TRUE);

INSERT INTO `tickets` (`id`, `category_id`, `priority_id`, `title`, `description`, `created_by`, `created_at`, `updated_at`)  
VALUES
(1, 2, 3, 'Problema de inicio de sesión', 'El usuario no puede iniciar sesión en el sistema con las credenciales correctas', 3, '2023-01-05 10:15:32', '2023-01-05 10:15:32'),
(2, 3, 4, 'Restablecimiento de contraseña', 'Solicitud de restablecimiento de contraseña debido a una contraseña olvidada', 6, '2023-02-12 11:30:25', '2023-02-12 11:30:25'),
(3, 1, 2, 'Error en el proceso de pago', 'Error donde el botón de pago no funciona en la página de pago', 7, '2023-03-20 13:14:09', '2023-03-20 13:14:09'),
(4, 2, 1, 'Correo electrónico no enviado', 'Problema con el envío de correos electrónicos después de enviar un formulario', 8, '2023-04-03 09:42:18', '2023-04-03 09:42:18'),
(5, 3, 3, 'Error de carga de página', 'La página muestra un error 404 al hacer clic en el enlace de la página de inicio', 26, '2023-05-05 10:51:11', '2023-05-05 10:51:11'),
(6, 1, 4, 'Fallo en la pasarela de pago', 'La pasarela de pago no puede procesar los pagos', 27, '2023-06-15 14:22:30', '2023-06-15 14:22:30'),
(7, 2, 3, 'Error de interfaz en el panel de control', 'Error con la visualización de las estadísticas de usuario en el panel de control', 28, '2023-07-01 12:07:56', '2023-07-01 12:07:56'),
(8, 3, 1, 'Sobrecarga del servidor', 'Tráfico elevado que provoca sobrecarga en el servidor y caídas', 29, '2023-08-02 08:19:18', '2023-08-02 08:19:18'),
(9, 1, 4, 'Problema al cargar la foto de perfil', 'Error al intentar cargar una foto de perfil en la configuración de la cuenta del usuario', 3, '2023-09-15 10:50:42', '2023-09-15 10:50:42'),
(10, 2, 2, 'Enlaces rotos', 'Enlaces rotos en la página de preguntas frecuentes', 6, '2023-10-11 14:35:55', '2023-10-11 14:35:55'),
(11, 3, 3, 'Solicitud de función para temas personalizados', 'Solicitud de nueva función para agregar temas personalizados al sitio web', 7, '2023-11-13 15:21:04', '2023-11-13 15:21:04'),
(12, 1, 2, 'Cuenta bloqueada después de varios intentos de inicio de sesión', 'Cuenta bloqueada después de múltiples intentos fallidos de inicio de sesión', 8, '2023-12-02 10:11:29', '2023-12-02 10:11:29'),
(13, 2, 4, 'Problema con 2FA', 'El usuario no puede recibir el código de 2FA para iniciar sesión', 26, '2023-01-20 11:00:34', '2023-01-20 11:00:34'),
(14, 3, 1, 'Rendimiento lento del sitio web', 'El sitio web tarda demasiado en cargar, afectando la experiencia del usuario', 27, '2023-02-07 13:55:48', '2023-02-07 13:55:48'),
(15, 1, 3, 'Error de autenticación de la API', 'Error al intentar autenticarse a través de la API', 28, '2023-03-28 09:42:11', '2023-03-28 09:42:11'),
(16, 2, 4, 'Contenido no se actualiza', 'Los cambios realizados en el contenido no se reflejan en el sitio después de guardarlos', 29, '2023-04-09 10:21:55', '2023-04-09 10:21:55'),
(17, 3, 2, 'La app se cierra al iniciarse', 'La app se cierra inmediatamente al iniciarse', 3, '2023-05-21 15:09:32', '2023-05-21 15:09:32'),
(18, 1, 1, 'Error de conexión con la base de datos', 'No se puede conectar al servidor de la base de datos', 6, '2023-06-18 13:13:03', '2023-06-18 13:13:03'),
(19, 2, 4, 'No se pueden cargar archivos', 'El usuario no puede cargar archivos al servidor', 7, '2023-07-23 14:52:17', '2023-07-23 14:52:17'),
(20, 3, 1, 'Función de búsqueda rota', 'La funcionalidad de búsqueda devuelve resultados incorrectos o ninguno', 8, '2023-08-05 16:40:11', '2023-08-05 16:40:11'),
(21, 1, 2, 'Tiempo de inicio de sesión lento', 'El inicio de sesión tarda demasiado en completarse', 26, '2023-09-18 12:05:13', '2023-09-18 12:05:13'),
(22, 2, 3, 'Datos mostrados incorrectamente', 'Los datos se muestran incorrectamente en el panel de control del usuario', 27, '2023-10-04 13:30:45', '2023-10-04 13:30:45'),
(23, 3, 1, 'Congelamiento de la interfaz de la app móvil', 'La interfaz de la app móvil se congela al navegar a configuración', 28, '2023-10-12 09:45:22', '2023-10-12 09:45:22'),
(24, 1, 4, 'Error en las notificaciones por correo electrónico', 'Los usuarios no reciben notificaciones por correo electrónico después de realizar una acción', 29, '2023-10-25 16:22:01', '2023-10-25 16:22:01'),
(25, 2, 2, 'Función no funciona en móvil', 'Cierta función no funciona correctamente en dispositivos móviles', 3, '2023-11-03 08:10:58', '2023-11-03 08:10:58'),
(26, 3, 3, 'Formulario de inicio de sesión no es responsivo', 'El formulario de inicio de sesión no es completamente responsivo en dispositivos móviles', 6, '2023-11-12 14:40:15', '2023-11-12 14:40:15'),
(27, 1, 4, 'Problema con el formato del archivo', 'El archivo cargado no está en el formato correcto, causando un error', 7, '2023-11-20 10:00:32', '2023-11-20 10:00:32'),
(28, 2, 1, 'Botones no responden en la página de inicio', 'Los botones en la página de inicio no responden cuando se hacen clic', 8, '2023-12-01 11:55:28', '2023-12-01 11:55:28'),
(29, 3, 2, 'Retraso en el rendimiento durante la transmisión de video', 'El rendimiento de la app se retrasa al transmitir videos', 26, '2023-12-05 09:20:47', '2023-12-05 09:20:47'),
(30, 1, 4, 'No se puede acceder a la configuración', 'El usuario no puede acceder al menú de configuración debido a un error', 27, '2023-12-10 17:35:13', '2023-12-10 17:35:13'),
(31, 2, 3, 'Mensajes de error poco claros', 'Los mensajes de error mostrados a los usuarios no son claros ni informativos', 28, '2023-12-15 10:25:04', '2023-12-15 10:25:04'),
(32, 3, 2, 'Error en la actualización de la app', 'La app no se actualiza a la última versión', 29, '2023-12-18 12:47:18', '2023-12-18 12:47:18'),
(33, 1, 1, 'No se puede restablecer la contraseña', 'El usuario no puede restablecer su contraseña usando el formulario de recuperación', 3, '2023-12-22 14:11:40', '2023-12-22 14:11:40'),
(34, 2, 4, 'Enlaces de imagen rotos', 'Las imágenes en las páginas de productos no se muestran correctamente debido a enlaces rotos', 6, '2023-12-24 16:55:33', '2023-12-24 16:55:33'),
(35, 3, 3, 'Tiempo de espera para el inicio de sesión en la app móvil', 'El usuario se desconecta automáticamente después de un corto periodo de inactividad', 7, '2023-12-28 15:39:29', '2023-12-28 15:39:29'),
(36, 1, 2, 'No se recibe confirmación de pedido', 'El usuario no recibe un correo de confirmación de pedido después de la compra', 8, '2023-12-30 13:22:01', '2023-12-30 13:22:01'),
(37, 2, 1, 'Problemas con la carga de videos', 'Los videos no se cargan correctamente o están corruptos', 26, '2024-01-03 14:51:13', '2024-01-03 14:51:13'),
(38, 3, 4, 'Error en la interfaz de pago', 'La interfaz de pago no responde después de que el usuario hace clic en enviar', 27, '2024-01-08 11:25:35', '2024-01-08 11:25:35');

INSERT INTO `ticket_status_history` (`id`, `ticket_id`, `status_id`, `changed_at`) VALUES
(1, 1, 1, '2023-01-05 09:30:00'),
(2, 2, 4, '2023-02-12 10:45:00'),
(3, 3, 2, '2023-03-20 12:30:00'),
(4, 4, 5, '2023-04-03 07:50:00'),
(5, 5, 3, '2023-05-05 09:00:00'),
(6, 6, 4, '2023-06-15 12:30:00'),
(7, 7, 3, '2023-07-01 10:15:00'),
(8, 8, 5, '2023-08-02 06:25:00'),
(9, 9, 4, '2023-09-15 08:55:00'),
(10, 10, 2, '2023-10-11 12:40:00'),
(11, 11, 3, '2023-11-13 14:30:00'),
(12, 12, 1, '2023-12-02 09:20:00'),
(13, 13, 4, '2023-01-20 10:10:00'),
(14, 14, 5, '2023-02-07 13:00:00'),
(15, 15, 2, '2023-03-28 07:50:00'),
(16, 16, 3, '2023-04-09 08:30:00'),
(17, 17, 4, '2023-05-21 13:10:00'),
(18, 18, 5, '2023-06-18 11:20:00'),
(19, 19, 4, '2023-07-23 12:55:00'),
(20, 20, 1, '2023-08-05 14:45:00');

INSERT INTO `comments` (`id`, `ticket_id`, `user_id`, `comment`, `created_at`) VALUES
(1, 1, 10, 'My printer does not work properly', '2023-01-06 08:30:00'),
(2, 1, 5, 'The request has been acknowledged, will update soon.', '2023-02-15 09:00:00'),
(3, 3, 3, 'We are currently working on a fix for this issue.', '2023-03-22 10:15:00'),
(4, 4, 4, 'The problem has been identified, resolution is in progress.', '2023-04-04 10:00:00'),
(5, 5, 5, 'We need further details to process your request.', '2023-05-06 12:20:00'),
(6, 6, 6, 'The issue has been resolved, please confirm if everything is working.', '2023-06-17 07:45:00'),
(7, 7, 7, 'We are aware of the issue and working on a solution.', '2023-07-03 08:30:00'),
(8, 8, 8, 'Please provide the error message for further investigation.', '2023-08-03 09:30:00'),
(9, 9, 9, 'We are unable to reproduce the issue, please provide more details.', '2023-09-16 10:00:00'),
(10, 10, 10, 'The issue has been escalated to the development team.', '2023-10-12 12:45:00'),
(11, 11, 11, 'We have applied a temporary fix, testing in progress.', '2023-11-15 14:30:00'),
(12, 12, 12, 'The problem is resolved, please check and confirm.', '2023-12-03 15:10:00'),
(13, 13, 13, 'Investigating the cause, updates will follow soon.', '2023-01-22 09:30:00'),
(14, 14, 14, 'The issue is being worked on, expect an update shortly.', '2023-02-08 10:00:00'),
(15, 15, 15, 'The cause has been identified, we will provide a fix soon.', '2023-03-29 11:30:00'),
(16, 16, 16, 'Please verify if the issue persists after the recent update.', '2023-04-10 12:00:00'),
(17, 17, 17, 'The issue is resolved, no further action needed.', '2023-05-22 13:10:00'),
(18, 18, 18, 'Fix applied successfully, awaiting confirmation.', '2023-06-19 08:50:00'),
(19, 19, 19, 'The error message is being reviewed for a solution.', '2023-07-24 14:40:00'),
(20, 20, 20, 'We are testing the solution, will provide an update soon.', '2023-08-06 11:00:00'),
(21, 21, 1, 'Ticket updated, awaiting final review.', '2023-09-17 09:40:00'),
(22, 22, 2, 'The issue has been fixed, confirming with the user.', '2023-10-13 10:30:00'),
(23, 23, 3, 'We have released a new update, please check again.', '2023-11-16 08:15:00'),
(24, 24, 4, 'Still working on resolving the issue, please be patient.', '2023-12-04 09:00:00'),
(25, 25, 5, 'Request is under review, a fix will be applied soon.', '2023-01-25 13:40:00'),
(26, 26, 6, 'The issue has been fixed, please verify if resolved.', '2023-02-10 12:20:00'),
(27, 27, 7, 'We are preparing the resolution, it should be available soon.', '2023-03-24 14:50:00'),
(28, 28, 8, 'Please allow us some time to work on a solution.', '2023-04-05 14:30:00'),
(29, 29, 9, 'The issue has been acknowledged, we are working on a fix.', '2023-05-07 07:10:00'),
(30, 30, 10, 'The solution is being tested, expect an update soon.', '2023-06-18 10:20:00'),
(31, 31, 11, 'The problem should be resolved after the recent changes.', '2023-07-04 09:50:00'),
(32, 32, 12, 'We are investigating the cause, a solution is in progress.', '2023-08-07 08:15:00'),
(33, 33, 13, 'The issue has been reported to the senior team, working on it.', '2023-09-18 12:00:00'),
(34, 34, 14, 'Please try again and confirm if the problem still exists.', '2023-10-14 09:30:00'),
(35, 35, 15, 'Fix applied, please confirm if it resolved the issue.', '2023-11-17 14:40:00');


INSERT INTO `assigments` (`id`, `ticket_id`, `agent_id`, `assigned_at`) VALUES
(1, 1, 1, '2023-01-05 07:30:00'),
(2, 2, 2, '2023-02-14 08:00:00'),
(3, 3, 3, '2023-03-21 09:00:00'),
(4, 4, 4, '2023-04-03 09:30:00'),
(5, 5, 5, '2023-05-05 10:45:00'),
(6, 6, 6, '2023-06-16 12:00:00'),
(7, 7, 7, '2023-07-02 13:00:00'),
(8, 8, 8, '2023-08-02 14:00:00'),
(9, 9, 9, '2023-09-15 15:00:00'),
(10, 10, 10, '2023-10-11 16:00:00'),
(11, 11, 11, '2023-11-14 07:15:00'),
(12, 12, 12, '2023-12-02 08:30:00'),
(13, 13, 13, '2023-01-20 09:00:00'),
(14, 14, 14, '2023-02-07 10:30:00'),
(15, 15, 15, '2023-03-28 10:00:00'),
(16, 16, 16, '2023-04-09 11:30:00'),
(17, 17, 17, '2023-05-21 12:00:00'),
(18, 18, 18, '2023-06-18 13:15:00'),
(19, 19, 19, '2023-07-23 14:30:00'),
(20, 20, 20, '2023-08-05 15:45:00'),
(21, 21, 1, '2023-09-16 06:00:00'),
(22, 22, 2, '2023-10-12 07:15:00'),
(23, 23, 3, '2023-11-15 09:30:00'),
(24, 24, 4, '2023-12-03 10:45:00'),
(25, 25, 5, '2023-01-24 12:00:00'),
(26, 26, 6, '2023-02-09 13:15:00'),
(27, 27, 7, '2023-03-23 14:30:00'),
(28, 28, 8, '2023-04-04 14:00:00'),
(29, 29, 9, '2023-05-06 15:00:00'),
(30, 30, 10, '2023-06-17 16:00:00'),
(31, 31, 11, '2023-07-05 06:45:00'),
(32, 32, 12, '2023-08-06 07:30:00'),
(33, 33, 13, '2023-09-19 08:45:00'),
(34, 34, 14, '2023-10-15 10:00:00'),
(35, 35, 15, '2023-11-18 12:15:00');

