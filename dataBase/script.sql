-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 14-12-2024 a las 15:29:38
-- Versión del servidor: 9.1.0
-- Versión de PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `deskify`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `assigments`
--

DROP TABLE IF EXISTS `assigments`;
CREATE TABLE IF NOT EXISTS `assigments` (
  `assigments_id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint NOT NULL,
  `agent_id` bigint NOT NULL,
  `assigned_at` timestamp NOT NULL,
  PRIMARY KEY (`assigments_id`),
  KEY `fk_assigments_ticket_id` (`ticket_id`),
  KEY `fk_assigments_agent_id` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `assigments`
--

INSERT INTO `assigments` (`assigments_id`, `ticket_id`, `agent_id`, `assigned_at`) VALUES
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `category_id` tinyint NOT NULL AUTO_INCREMENT,
  `name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categories`
--

INSERT INTO `categories` (`category_id`, `name`, `description`) VALUES
(1, 'Technical Support', 'Issues related to hardware, software, and system troubleshooting.'),
(2, 'Billing', 'Issues regarding invoices, payments, and subscription plans.'),
(3, 'Account Management', 'Questions or problems related to user accounts and profiles.'),
(4, 'General Inquiry', 'General questions or requests not related to specific issues.'),
(5, 'Network Issues', 'Problems related to internet connectivity, network access, and infrastructure.'),
(6, 'Security', 'Concerns regarding system security, data breaches, or suspicious activity.'),
(7, 'Software Installation', 'Assistance with installing or configuring software applications.'),
(8, 'Feature Request', 'Requests for new features or improvements to existing functionality.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `comment` text COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` timestamp NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comments_ticket_id` (`ticket_id`),
  KEY `fk_comments_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comments`
--

INSERT INTO `comments` (`comment_id`, `ticket_id`, `user_id`, `comment`, `created_at`) VALUES
(36, 1, 1, 'The issue is being investigated, please hold on.', '2023-01-06 08:30:00'),
(37, 2, 2, 'The request has been acknowledged, will update soon.', '2023-02-15 09:00:00'),
(38, 3, 3, 'We are currently working on a fix for this issue.', '2023-03-22 10:15:00'),
(39, 4, 4, 'The problem has been identified, resolution is in progress.', '2023-04-04 10:00:00'),
(40, 5, 5, 'We need further details to process your request.', '2023-05-06 12:20:00'),
(41, 6, 6, 'The issue has been resolved, please confirm if everything is working.', '2023-06-17 07:45:00'),
(42, 7, 7, 'We are aware of the issue and working on a solution.', '2023-07-03 08:30:00'),
(43, 8, 8, 'Please provide the error message for further investigation.', '2023-08-03 09:30:00'),
(44, 9, 9, 'We are unable to reproduce the issue, please provide more details.', '2023-09-16 10:00:00'),
(45, 10, 10, 'The issue has been escalated to the development team.', '2023-10-12 12:45:00'),
(46, 11, 11, 'We have applied a temporary fix, testing in progress.', '2023-11-15 14:30:00'),
(47, 12, 12, 'The problem is resolved, please check and confirm.', '2023-12-03 15:10:00'),
(48, 13, 13, 'Investigating the cause, updates will follow soon.', '2023-01-22 09:30:00'),
(49, 14, 14, 'The issue is being worked on, expect an update shortly.', '2023-02-08 10:00:00'),
(50, 15, 15, 'The cause has been identified, we will provide a fix soon.', '2023-03-29 11:30:00'),
(51, 16, 16, 'Please verify if the issue persists after the recent update.', '2023-04-10 12:00:00'),
(52, 17, 17, 'The issue is resolved, no further action needed.', '2023-05-22 13:10:00'),
(53, 18, 18, 'Fix applied successfully, awaiting confirmation.', '2023-06-19 08:50:00'),
(54, 19, 19, 'The error message is being reviewed for a solution.', '2023-07-24 14:40:00'),
(55, 20, 20, 'We are testing the solution, will provide an update soon.', '2023-08-06 11:00:00'),
(56, 21, 1, 'Ticket updated, awaiting final review.', '2023-09-17 09:40:00'),
(57, 22, 2, 'The issue has been fixed, confirming with the user.', '2023-10-13 10:30:00'),
(58, 23, 3, 'We have released a new update, please check again.', '2023-11-16 08:15:00'),
(59, 24, 4, 'Still working on resolving the issue, please be patient.', '2023-12-04 09:00:00'),
(60, 25, 5, 'Request is under review, a fix will be applied soon.', '2023-01-25 13:40:00'),
(61, 26, 6, 'The issue has been fixed, please verify if resolved.', '2023-02-10 12:20:00'),
(62, 27, 7, 'We are preparing the resolution, it should be available soon.', '2023-03-24 14:50:00'),
(63, 28, 8, 'Please allow us some time to work on a solution.', '2023-04-05 14:30:00'),
(64, 29, 9, 'The issue has been acknowledged, we are working on a fix.', '2023-05-07 07:10:00'),
(65, 30, 10, 'The solution is being tested, expect an update soon.', '2023-06-18 10:20:00'),
(66, 31, 11, 'The problem should be resolved after the recent changes.', '2023-07-04 09:50:00'),
(67, 32, 12, 'We are investigating the cause, a solution is in progress.', '2023-08-07 08:15:00'),
(68, 33, 13, 'The issue has been reported to the senior team, working on it.', '2023-09-18 12:00:00'),
(69, 34, 14, 'Please try again and confirm if the problem still exists.', '2023-10-14 09:30:00'),
(70, 35, 15, 'Fix applied, please confirm if it resolved the issue.', '2023-11-17 14:40:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `priorities`
--

DROP TABLE IF EXISTS `priorities`;
CREATE TABLE IF NOT EXISTS `priorities` (
  `priority_id` tinyint NOT NULL AUTO_INCREMENT,
  `name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`priority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `priorities`
--

INSERT INTO `priorities` (`priority_id`, `name`, `description`) VALUES
(1, 'Low', 'The issue is not urgent and can be resolved at a later time.'),
(2, 'Medium', 'The issue is important but does not require immediate attention.'),
(3, 'High', 'The issue needs to be addressed promptly and is of significant importance.'),
(4, 'Urgent', 'The issue requires immediate attention and resolution.'),


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` tinyint NOT NULL AUTO_INCREMENT,
  `name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `name`, `description`) VALUES
(1, 'Admin', 'Full access to all system functionalities including user management and configuration.'),
(2, 'Supervisor', 'Monitors ticket quality and agent performance.'),
(3, 'Manager', 'Supervises agents and manages ticket assignments and performance.'),
(4, 'Agent', 'Handles tickets and provides technical support to users.'),
(5, 'User', 'Can create tickets and track their status.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE IF NOT EXISTS `status` (
  `status_id` tinyint NOT NULL AUTO_INCREMENT,
  `name` bigint NOT NULL,
  `status_description` tinyint NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `status`
--

INSERT INTO `status` (`status_id`, `name`, `status_description`, `is_active`) VALUES
(1, 0, 0, 1),
(2, 0, 0, 1),
(3, 0, 0, 1),
(4, 0, 0, 0),
(5, 0, 0, 1),
(6, 0, 0, 1),
(7, 0, 0, 1),
(8, 0, 0, 1),
(9, 0, 0, 0),
(10, 0, 0, 1),
(11, 0, 0, 1),
(12, 0, 0, 1),
(13, 0, 0, 1),
(14, 0, 0, 0),
(15, 0, 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `ticket_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `category_id` tinyint DEFAULT NULL,
  `priority_id` tinyint DEFAULT NULL,
  `title` tinytext COLLATE utf8mb4_general_ci,
  `description` text COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `fk_tickets_user_id` (`user_id`),
  KEY `fk_tickets_category_id` (`category_id`),
  KEY `fk_tickets_priority_id` (`priority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`ticket_id`, `user_id`, `category_id`, `priority_id`, `title`, `description`, `created_at`, `updated_at`) VALUES
(1, 1, 2, 3, 'Login issue', 'User unable to log in to the system with correct credentials', '2023-01-05 09:15:32', '2023-01-05 09:15:32'),
(2, 2, 3, 4, 'Password Reset', 'Request for password reset due to forgotten password', '2023-02-12 10:30:25', '2023-02-12 10:30:25'),
(3, 3, 1, 2, 'Bug in checkout process', 'Bug where the checkout button does not work on the payment page', '2023-03-20 12:14:09', '2023-03-20 12:14:09'),
(4, 4, 2, 5, 'Email not sending', 'Issue with email not sending after submission of a form', '2023-04-03 07:42:18', '2023-04-03 07:42:18'),
(5, 5, 3, 3, 'Page loading error', 'Page loads with a 404 error when clicking the homepage link', '2023-05-05 08:51:11', '2023-05-05 08:51:11'),
(6, 6, 1, 4, 'Payment gateway failure', 'Payment gateway fails to process payments', '2023-06-15 12:22:30', '2023-06-15 12:22:30'),
(7, 7, 2, 3, 'UI bug in dashboard', 'Bug with the display of user statistics on the dashboard', '2023-07-01 10:07:56', '2023-07-01 10:07:56'),
(8, 8, 3, 5, 'Server overload', 'High traffic leading to server overload and crashing', '2023-08-02 06:19:18', '2023-08-02 06:19:18'),
(9, 9, 1, 4, 'Profile picture upload issue', 'Error while uploading a profile picture on user account settings', '2023-09-15 08:50:42', '2023-09-15 08:50:42'),
(10, 10, 2, 2, 'Broken links', 'Broken links on the FAQ page', '2023-10-11 12:35:55', '2023-10-11 12:35:55'),
(11, 11, 3, 3, 'Feature request for custom themes', 'Request for new feature to add custom themes to the website', '2023-11-13 14:21:04', '2023-11-13 14:21:04'),
(12, 12, 1, 2, 'Account locked after multiple login attempts', 'Account locked after multiple failed login attempts', '2023-12-02 09:11:29', '2023-12-02 09:11:29'),
(13, 13, 2, 4, '2FA issue', 'User cannot receive 2FA code for login', '2023-01-20 10:00:34', '2023-01-20 10:00:34'),
(14, 14, 3, 5, 'Slow website performance', 'Website takes too long to load, affecting user experience', '2023-02-07 12:55:48', '2023-02-07 12:55:48'),
(15, 15, 1, 3, 'API authentication error', 'Error when trying to authenticate via the API', '2023-03-28 07:42:11', '2023-03-28 07:42:11'),
(16, 16, 2, 4, 'Content not updating', 'Changes made to the content do not reflect on the site after saving', '2023-04-09 08:21:55', '2023-04-09 08:21:55'),
(17, 17, 3, 2, 'App crash on startup', 'The mobile app crashes immediately upon launch', '2023-05-21 13:09:32', '2023-05-21 13:09:32'),
(18, 18, 1, 5, 'Database connection error', 'Unable to connect to the database server', '2023-06-18 11:13:03', '2023-06-18 11:13:03'),
(19, 19, 2, 4, 'Unable to upload files', 'User is unable to upload files to the server', '2023-07-23 12:52:17', '2023-07-23 12:52:17'),
(20, 20, 3, 5, 'Broken search feature', 'Search functionality returns incorrect results or no results at all', '2023-08-05 14:40:11', '2023-08-05 14:40:11'),
(21, 1, 2, 3, 'Login issue', 'User unable to log in to the system with correct credentials', '2023-01-05 09:15:32', '2023-01-05 09:15:32'),
(22, 2, 3, 4, 'Password Reset', 'Request for password reset due to forgotten password', '2023-02-12 10:30:25', '2023-02-12 10:30:25'),
(23, 3, 1, 2, 'Bug in checkout process', 'Bug where the checkout button does not work on the payment page', '2023-03-20 12:14:09', '2023-03-20 12:14:09'),
(24, 4, 2, 5, 'Email not sending', 'Issue with email not sending after submission of a form', '2023-04-03 07:42:18', '2023-04-03 07:42:18'),
(25, 5, 3, 3, 'Page loading error', 'Page loads with a 404 error when clicking the homepage link', '2023-05-05 08:51:11', '2023-05-05 08:51:11'),
(26, 6, 1, 4, 'Payment gateway failure', 'Payment gateway fails to process payments', '2023-06-15 12:22:30', '2023-06-15 12:22:30'),
(27, 7, 2, 3, 'UI bug in dashboard', 'Bug with the display of user statistics on the dashboard', '2023-07-01 10:07:56', '2023-07-01 10:07:56'),
(28, 8, 3, 5, 'Server overload', 'High traffic leading to server overload and crashing', '2023-08-02 06:19:18', '2023-08-02 06:19:18'),
(29, 9, 1, 4, 'Profile picture upload issue', 'Error while uploading a profile picture on user account settings', '2023-09-15 08:50:42', '2023-09-15 08:50:42'),
(30, 10, 2, 2, 'Broken links', 'Broken links on the FAQ page', '2023-10-11 12:35:55', '2023-10-11 12:35:55'),
(31, 11, 3, 3, 'Feature request for custom themes', 'Request for new feature to add custom themes to the website', '2023-11-13 14:21:04', '2023-11-13 14:21:04'),
(32, 12, 1, 2, 'Account locked after multiple login attempts', 'Account locked after multiple failed login attempts', '2023-12-02 09:11:29', '2023-12-02 09:11:29'),
(33, 13, 2, 4, '2FA issue', 'User cannot receive 2FA code for login', '2023-01-20 10:00:34', '2023-01-20 10:00:34'),
(34, 14, 3, 5, 'Slow website performance', 'Website takes too long to load, affecting user experience', '2023-02-07 12:55:48', '2023-02-07 12:55:48'),
(35, 15, 1, 3, 'API authentication error', 'Error when trying to authenticate via the API', '2023-03-28 07:42:11', '2023-03-28 07:42:11'),
(36, 16, 2, 4, 'Content not updating', 'Changes made to the content do not reflect on the site after saving', '2023-04-09 08:21:55', '2023-04-09 08:21:55'),
(37, 17, 3, 2, 'App crash on startup', 'The mobile app crashes immediately upon launch', '2023-05-21 13:09:32', '2023-05-21 13:09:32'),
(38, 18, 1, 5, 'Database connection error', 'Unable to connect to the database server', '2023-06-18 11:13:03', '2023-06-18 11:13:03'),
(39, 19, 2, 4, 'Unable to upload files', 'User is unable to upload files to the server', '2023-07-23 12:52:17', '2023-07-23 12:52:17'),
(40, 20, 3, 5, 'Broken search feature', 'Search functionality returns incorrect results or no results at all', '2023-08-05 14:40:11', '2023-08-05 14:40:11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket_status_history`
--

DROP TABLE IF EXISTS `ticket_status_history`;
CREATE TABLE IF NOT EXISTS `ticket_status_history` (
  `history_id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint NOT NULL,
  `status_id` tinyint NOT NULL,
  `changed_at` timestamp NOT NULL,
  PRIMARY KEY (`history_id`),
  KEY `fk_ticket_status_history_ticket_id` (`ticket_id`),
  KEY `fk_ticket_status_history_status_id` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ticket_status_history`
--

INSERT INTO `ticket_status_history` (`history_id`, `ticket_id`, `status_id`, `changed_at`) VALUES
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `middle_name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_name` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(55) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` tinyint NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_users_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `middle_name`, `last_name`, `email`, `password`, `role`, `created_at`, `updated_at`) VALUES
(1, 'John', 'Doe', 'Smith', 'john.smith@example.com', '**************', 1, '2023-01-15 09:45:32', '2023-01-15 09:45:32'),
(2, 'Jane', 'A.', 'Doe', 'jane.doe@example.com', '**************', 4, '2023-02-03 08:30:12', '2023-02-03 08:30:12'),
(3, 'Alice', '', 'Johnson', 'alice.johnson@example.com', '**************', 5, '2023-05-22 14:21:47', '2023-05-22 14:21:47'),
(4, 'Bob', 'M.', 'Williams', 'bob.williams@example.com', '**************', 3, '2023-06-11 12:55:23', '2023-06-11 12:55:23'),
(5, 'Charlie', '', 'Brown', 'charlie.brown@example.com', '**************', 3, '2023-07-01 09:12:09', '2023-07-01 09:12:09'),
(6, 'Emily', 'R.', 'Clark', 'emily.clark@example.com', '**************', 5, '2023-08-15 11:45:51', '2023-08-15 11:45:51'),
(7, 'David', 'J.', 'Martinez', 'david.martinez@example.com', '**************', 5, '2023-09-09 15:09:28', '2023-09-09 15:09:28'),
(8, 'Sophia', 'L.', 'Garcia', 'sophia.garcia@example.com', '**************', 5, '2023-10-04 06:29:54', '2023-10-04 06:29:54'),
(9, 'Michael', '', 'Lopez', 'michael.lopez@example.com', '**************', 3, '2023-11-19 11:13:05', '2023-11-19 11:13:05'),
(10, 'Olivia', 'C.', 'Hernandez', 'olivia.hernandez@example.com', '**************', 5, '2023-12-02 13:24:36', '2023-12-02 13:24:36'),
(11, 'Liam', '', 'Taylor', 'liam.taylor@example.com', '**************', 4, '2023-01-25 08:41:56', '2023-01-25 08:41:56'),
(12, 'Mason', 'T.', 'Wilson', 'mason.wilson@example.com', '**************', 5, '2023-02-14 10:34:17', '2023-02-14 10:34:17'),
(13, 'Isabella', '', 'Moore', 'isabella.moore@example.com', '**************', 5, '2023-03-09 14:56:11', '2023-03-09 14:56:11'),
(14, 'Lucas', 'P.', 'Anderson', 'lucas.anderson@example.com', '**************', 4, '2023-04-10 08:29:38', '2023-04-10 08:29:38'),
(15, 'Zoe', 'B.', 'Thomas', 'zoe.thomas@example.com', '**************', 3, '2023-05-15 10:16:53', '2023-05-15 10:16:53'),
(16, 'Amelia', 'K.', 'Jackson', 'amelia.jackson@example.com', '**************', 5, '2023-06-20 07:02:17', '2023-06-20 07:02:17'),
(17, 'Aiden', 'D.', 'White', 'aiden.white@example.com', '**************', 3, '2023-07-30 12:47:42', '2023-07-30 12:47:42'),
(18, 'Ethan', '', 'Lee', 'ethan.lee@example.com', '**************', 4, '2023-08-13 14:36:24', '2023-08-13 14:36:24'),
(19, 'Abigail', 'V.', 'Young', 'abigail.young@example.com', '**************', 5, '2023-09-25 08:05:17', '2023-09-25 08:05:17'),
(20, 'Mia', 'H.', 'Walker', 'mia.walker@example.com', '**************', 4, '2023-10-30 07:19:22', '2023-10-30 07:19:22'),
(21, 'William', 'N.', 'Allen', 'william.allen@example.com', '**************', 5, '2023-11-03 12:03:58', '2023-11-03 12:03:58'),
(22, 'James', 'L.', 'King', 'james.king@example.com', '**************', 1, '2023-12-01 11:18:11', '2023-12-01 11:18:11'),
(23, 'Benjamin', 'Q.', 'Scott', 'benjamin.scott@example.com', '**************', 5, '2023-01-18 08:27:32', '2023-01-18 08:27:32'),
(24, 'Chloe', 'R.', 'Adams', 'chloe.adams@example.com', '**************', 4, '2023-02-23 10:14:04', '2023-02-23 10:14:04'),
(25, 'Avery', '', 'Baker', 'avery.baker@example.com', '**************', 5, '2023-03-29 12:02:43', '2023-03-29 12:02:43'),
(26, 'Daniel', 'J.', 'Gonzalez', 'daniel.gonzalez@example.com', '**************', 5, '2023-04-26 14:11:29', '2023-04-26 14:11:29'),
(27, 'Grace', 'S.', 'Nelson', 'grace.nelson@example.com', '**************', 5, '2023-05-16 11:37:10', '2023-05-16 11:37:10'),
(28, 'Jack', '', 'Carter', 'jack.carter@example.com', '**************', 5, '2023-06-01 08:25:44', '2023-06-01 08:25:44'),
(29, 'Lily', 'P.', 'Mitchell', 'lily.mitchell@example.com', '**************', 5, '2023-07-04 10:18:59', '2023-07-04 10:18:59');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `assigments`
--
ALTER TABLE `assigments`
  ADD CONSTRAINT `assigments_agent_id_fk` FOREIGN KEY (`agent_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `assigments_ticket_id_fk` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`),
  ADD CONSTRAINT `fk_assigments_agent_id` FOREIGN KEY (`agent_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `fk_assigments_ticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`);

--
-- Filtros para la tabla `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ticket_id_fk` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`),
  ADD CONSTRAINT `comments_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `fk_comments_ticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`),
  ADD CONSTRAINT `fk_comments_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `fk_tickets_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  ADD CONSTRAINT `fk_tickets_priority_id` FOREIGN KEY (`priority_id`) REFERENCES `priorities` (`priority_id`),
  ADD CONSTRAINT `fk_tickets_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `tickets_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  ADD CONSTRAINT `tickets_priority_id_fk` FOREIGN KEY (`priority_id`) REFERENCES `priorities` (`priority_id`),
  ADD CONSTRAINT `tickets_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Filtros para la tabla `ticket_status_history`
--
ALTER TABLE `ticket_status_history`
  ADD CONSTRAINT `fk_ticket_status_history_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  ADD CONSTRAINT `fk_ticket_status_history_ticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`),
  ADD CONSTRAINT `ticket_status_history_status_id_fk` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  ADD CONSTRAINT `ticket_status_history_ticket_id_fk` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_role` FOREIGN KEY (`role`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
