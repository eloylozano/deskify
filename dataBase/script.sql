-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 15-12-2024 a las 11:32:57
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
-- Base de datos: `deskifydb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `assigments`
--

DROP TABLE IF EXISTS `assigments`;
CREATE TABLE IF NOT EXISTS `assigments` (
  `agent_id` bigint NOT NULL,
  `assigned_at` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKieenbvdduj75j8xt3iau803hi` (`agent_id`),
  KEY `FKqspxs4odn5b4xj33dgbgvxev1` (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `assigments`
--

INSERT INTO `assigments` (`agent_id`, `assigned_at`, `id`, `ticket_id`) VALUES
(1, '2023-01-05 07:30:00.000000', 1, 1),
(2, '2023-02-14 08:00:00.000000', 2, 2),
(3, '2023-03-21 09:00:00.000000', 3, 3),
(4, '2023-04-03 09:30:00.000000', 4, 4),
(5, '2023-05-05 10:45:00.000000', 5, 5),
(6, '2023-06-16 12:00:00.000000', 6, 6),
(7, '2023-07-02 13:00:00.000000', 7, 7),
(8, '2023-08-02 14:00:00.000000', 8, 8),
(9, '2023-09-15 15:00:00.000000', 9, 9),
(10, '2023-10-11 16:00:00.000000', 10, 10),
(11, '2023-11-14 07:15:00.000000', 11, 11),
(12, '2023-12-02 08:30:00.000000', 12, 12),
(13, '2023-01-20 09:00:00.000000', 13, 13),
(14, '2023-02-07 10:30:00.000000', 14, 14),
(15, '2023-03-28 10:00:00.000000', 15, 15),
(16, '2023-04-09 11:30:00.000000', 16, 16),
(17, '2023-05-21 12:00:00.000000', 17, 17),
(18, '2023-06-18 13:15:00.000000', 18, 18),
(19, '2023-07-23 14:30:00.000000', 19, 19),
(20, '2023-08-05 15:45:00.000000', 20, 20),
(1, '2023-09-16 06:00:00.000000', 21, 21),
(2, '2023-10-12 07:15:00.000000', 22, 22),
(3, '2023-11-15 09:30:00.000000', 23, 23),
(4, '2023-12-03 10:45:00.000000', 24, 24),
(5, '2023-01-24 12:00:00.000000', 25, 25),
(6, '2023-02-09 13:15:00.000000', 26, 26),
(7, '2023-03-23 14:30:00.000000', 27, 27),
(8, '2023-04-04 14:00:00.000000', 28, 28),
(9, '2023-05-06 15:00:00.000000', 29, 29),
(10, '2023-06-17 16:00:00.000000', 30, 30),
(11, '2023-07-05 06:45:00.000000', 31, 31),
(12, '2023-08-06 07:30:00.000000', 32, 32),
(13, '2023-09-19 08:45:00.000000', 33, 33),
(14, '2023-10-15 10:00:00.000000', 34, 34),
(15, '2023-11-18 12:15:00.000000', 35, 35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(55) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `categories`
--

INSERT INTO `categories` (`id`, `name`, `description`) VALUES
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
  `created_at` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`id`),
  KEY `FKj7vc0aigr2m5mew52v7ddt4fo` (`ticket_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `comments`
--

INSERT INTO `comments` (`created_at`, `id`, `ticket_id`, `user_id`, `comment`) VALUES
('2023-01-06 08:30:00.000000', 1, 1, 1, 'The issue is being investigated, please hold on.'),
('2023-02-15 09:00:00.000000', 2, 2, 2, 'The request has been acknowledged, will update soon.'),
('2023-03-22 10:15:00.000000', 3, 3, 3, 'We are currently working on a fix for this issue.'),
('2023-04-04 10:00:00.000000', 4, 4, 4, 'The problem has been identified, resolution is in progress.'),
('2023-05-06 12:20:00.000000', 5, 5, 5, 'We need further details to process your request.'),
('2023-06-17 07:45:00.000000', 6, 6, 6, 'The issue has been resolved, please confirm if everything is working.'),
('2023-07-03 08:30:00.000000', 7, 7, 7, 'We are aware of the issue and working on a solution.'),
('2023-08-03 09:30:00.000000', 8, 8, 8, 'Please provide the error message for further investigation.'),
('2023-09-16 10:00:00.000000', 9, 9, 9, 'We are unable to reproduce the issue, please provide more details.'),
('2023-10-12 12:45:00.000000', 10, 10, 10, 'The issue has been escalated to the development team.'),
('2023-11-15 14:30:00.000000', 11, 11, 11, 'We have applied a temporary fix, testing in progress.'),
('2023-12-03 15:10:00.000000', 12, 12, 12, 'The problem is resolved, please check and confirm.'),
('2023-01-22 09:30:00.000000', 13, 13, 13, 'Investigating the cause, updates will follow soon.'),
('2023-02-08 10:00:00.000000', 14, 14, 14, 'The issue is being worked on, expect an update shortly.'),
('2023-03-29 11:30:00.000000', 15, 15, 15, 'The cause has been identified, we will provide a fix soon.'),
('2023-04-10 12:00:00.000000', 16, 16, 16, 'Please verify if the issue persists after the recent update.'),
('2023-05-22 13:10:00.000000', 17, 17, 17, 'The issue is resolved, no further action needed.'),
('2023-06-19 08:50:00.000000', 18, 18, 18, 'Fix applied successfully, awaiting confirmation.'),
('2023-07-24 14:40:00.000000', 19, 19, 19, 'The error message is being reviewed for a solution.'),
('2023-08-06 11:00:00.000000', 20, 20, 20, 'We are testing the solution, will provide an update soon.'),
('2023-09-17 09:40:00.000000', 21, 21, 1, 'Ticket updated, awaiting final review.'),
('2023-10-13 10:30:00.000000', 22, 22, 2, 'The issue has been fixed, confirming with the user.'),
('2023-11-16 08:15:00.000000', 23, 23, 3, 'We have released a new update, please check again.'),
('2023-12-04 09:00:00.000000', 24, 24, 4, 'Still working on resolving the issue, please be patient.'),
('2023-01-25 13:40:00.000000', 25, 25, 5, 'Request is under review, a fix will be applied soon.'),
('2023-02-10 12:20:00.000000', 26, 26, 6, 'The issue has been fixed, please verify if resolved.'),
('2023-03-24 14:50:00.000000', 27, 27, 7, 'We are preparing the resolution, it should be available soon.'),
('2023-04-05 14:30:00.000000', 28, 28, 8, 'Please allow us some time to work on a solution.'),
('2023-05-07 07:10:00.000000', 29, 29, 9, 'The issue has been acknowledged, we are working on a fix.'),
('2023-06-18 10:20:00.000000', 30, 30, 10, 'The solution is being tested, expect an update soon.'),
('2023-07-04 09:50:00.000000', 31, 31, 11, 'The problem should be resolved after the recent changes.'),
('2023-08-07 08:15:00.000000', 32, 32, 12, 'We are investigating the cause, a solution is in progress.'),
('2023-09-18 12:00:00.000000', 33, 33, 13, 'The issue has been reported to the senior team, working on it.'),
('2023-10-14 09:30:00.000000', 34, 34, 14, 'Please try again and confirm if the problem still exists.'),
('2023-11-17 14:40:00.000000', 35, 35, 15, 'Fix applied, please confirm if it resolved the issue.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `priorities`
--

DROP TABLE IF EXISTS `priorities`;
CREATE TABLE IF NOT EXISTS `priorities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `priorities`
--

INSERT INTO `priorities` (`id`, `name`, `description`) VALUES
(1, 'Low', 'The issue is not urgent and can be resolved at a later time.'),
(2, 'Medium', 'The issue is important but does not require immediate attention.'),
(3, 'High', 'The issue needs to be addressed promptly and is of significant importance.'),
(4, 'Urgent', 'The issue requires immediate attention and resolution.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `is_active` bit(1) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `status`
--

INSERT INTO `status` (`is_active`, `id`, `description`, `name`) VALUES
(b'1', 1, 'The ticket has been created and is awaiting attention.', 'Open'),
(b'1', 2, 'The ticket is being handled by an agent.', 'In Progress'),
(b'1', 3, 'The ticket is awaiting further review or action.', 'Pending'),
(b'0', 4, 'The ticket has been resolved and closed.', 'Closed'),
(b'1', 5, 'The ticket has been resolved but no closed.', 'Resolved');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `category_id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `priority_id` bigint NOT NULL,
  `status_id` bigint NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `description` text NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK40lfvi239woyqe915ww8b9996` (`category_id`),
  KEY `FKano4h3jfjbejh4aqwp8phfm69` (`priority_id`),
  KEY `FKq830rms3y6k7td8gjklhvsbfs` (`status_id`),
  KEY `FK4eqsebpimnjen0q46ja6fl2hl` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`category_id`, `created_at`, `id`, `priority_id`, `status_id`, `updated_at`, `user_id`, `description`, `title`) VALUES
(2, '2023-01-05 10:15:32.000000', 1, 3, 1, '2023-01-05 10:15:32.000000', 1, 'User unable to log in to the system with correct credentials', 'Login issue'),
(3, '2023-02-12 11:30:25.000000', 2, 4, 1, '2023-02-12 11:30:25.000000', 2, 'Request for password reset due to forgotten password', 'Password Reset'),
(1, '2023-03-20 13:14:09.000000', 3, 2, 1, '2023-03-20 13:14:09.000000', 3, 'Bug where the checkout button does not work on the payment page', 'Bug in checkout process'),
(2, '2023-04-03 09:42:18.000000', 4, 1, 1, '2023-04-03 09:42:18.000000', 4, 'Issue with email not sending after submission of a form', 'Email not sending'),
(3, '2023-05-05 10:51:11.000000', 5, 3, 1, '2023-05-05 10:51:11.000000', 5, 'Page loads with a 404 error when clicking the homepage link', 'Page loading error'),
(1, '2023-06-15 14:22:30.000000', 6, 4, 1, '2023-06-15 14:22:30.000000', 6, 'Payment gateway fails to process payments', 'Payment gateway failure'),
(2, '2023-07-01 12:07:56.000000', 7, 3, 1, '2023-07-01 12:07:56.000000', 7, 'Bug with the display of user statistics on the dashboard', 'UI bug in dashboard'),
(3, '2023-08-02 08:19:18.000000', 8, 1, 1, '2023-08-02 08:19:18.000000', 8, 'High traffic leading to server overload and crashing', 'Server overload'),
(1, '2023-09-15 10:50:42.000000', 9, 4, 1, '2023-09-15 10:50:42.000000', 9, 'Error while uploading a profile picture on user account settings', 'Profile picture upload issue'),
(2, '2023-10-11 14:35:55.000000', 10, 2, 1, '2023-10-11 14:35:55.000000', 10, 'Broken links on the FAQ page', 'Broken links'),
(3, '2023-11-13 15:21:04.000000', 11, 3, 1, '2023-11-13 15:21:04.000000', 11, 'Request for new feature to add custom themes to the website', 'Feature request for custom themes'),
(1, '2023-12-02 10:11:29.000000', 12, 2, 1, '2023-12-02 10:11:29.000000', 12, 'Account locked after multiple failed login attempts', 'Account locked after multiple login attempts'),
(2, '2023-01-20 11:00:34.000000', 13, 4, 1, '2023-01-20 11:00:34.000000', 13, 'User cannot receive 2FA code for login', '2FA issue'),
(3, '2023-02-07 13:55:48.000000', 14, 1, 1, '2023-02-07 13:55:48.000000', 14, 'Website takes too long to load, affecting user experience', 'Slow website performance'),
(1, '2023-03-28 09:42:11.000000', 15, 3, 1, '2023-03-28 09:42:11.000000', 15, 'Error when trying to authenticate via the API', 'API authentication error'),
(2, '2023-04-09 10:21:55.000000', 16, 4, 1, '2023-04-09 10:21:55.000000', 16, 'Changes made to the content do not reflect on the site after saving', 'Content not updating'),
(3, '2023-05-21 15:09:32.000000', 17, 2, 1, '2023-05-21 15:09:32.000000', 17, 'The mobile app crashes immediately upon launch', 'App crash on startup'),
(1, '2023-06-18 13:13:03.000000', 18, 1, 1, '2023-06-18 13:13:03.000000', 18, 'Unable to connect to the database server', 'Database connection error'),
(2, '2023-07-23 14:52:17.000000', 19, 4, 1, '2023-07-23 14:52:17.000000', 19, 'User is unable to upload files to the server', 'Unable to upload files'),
(3, '2023-08-05 16:40:11.000000', 20, 1, 1, '2023-08-05 16:40:11.000000', 20, 'Search functionality returns incorrect results or no results at all', 'Broken search feature'),
(1, '2023-09-18 12:05:13.000000', 21, 2, 1, '2023-09-18 12:05:13.000000', 21, 'Login takes too long to complete', 'Slow login time'),
(2, '2023-10-04 13:30:45.000000', 22, 3, 1, '2023-10-04 13:30:45.000000', 22, 'Data displayed incorrectly on the user dashboard', 'Incorrect data display'),
(3, '2023-10-12 09:45:22.000000', 23, 1, 1, '2023-10-12 09:45:22.000000', 23, 'UI of the mobile app freezes when navigating to settings', 'Mobile app UI freeze'),
(1, '2023-10-25 16:22:01.000000', 24, 4, 1, '2023-10-25 16:22:01.000000', 24, 'Users are not receiving email notifications after an action is performed', 'Error in email notifications'),
(2, '2023-11-03 08:10:58.000000', 25, 2, 1, '2023-11-03 08:10:58.000000', 25, 'Certain feature does not work correctly on mobile devices', 'Feature not working on mobile'),
(3, '2023-11-12 14:40:15.000000', 26, 3, 1, '2023-11-12 14:40:15.000000', 26, 'Login form is not properly responsive on mobile devices', 'Login form not responsive'),
(1, '2023-11-20 10:00:32.000000', 27, 4, 1, '2023-11-20 10:00:32.000000', 27, 'Uploaded file is not in the correct format, causing an error', 'File format issue'),
(2, '2023-12-01 11:55:28.000000', 28, 1, 1, '2023-12-01 11:55:28.000000', 28, 'Buttons on the homepage do not respond when clicked', 'Unresponsive buttons on homepage'),
(3, '2023-12-05 09:20:47.000000', 29, 2, 1, '2023-12-05 09:20:47.000000', 29, 'App performance lags when streaming videos', 'Performance lag during video streaming'),
(1, '2023-12-10 17:35:13.000000', 30, 4, 1, '2023-12-10 17:35:13.000000', 10, 'User cannot access settings menu due to a bug', 'Cannot access settings'),
(2, '2023-12-15 10:25:04.000000', 31, 3, 1, '2023-12-15 10:25:04.000000', 21, 'Error messages shown to users are not clear or informative', 'Unclear error messages'),
(3, '2023-12-18 12:47:18.000000', 32, 2, 1, '2023-12-18 12:47:18.000000', 22, 'App fails to update to the latest version', 'App update failure'),
(1, '2023-12-22 14:11:40.000000', 33, 1, 1, '2023-12-22 14:11:40.000000', 23, 'User unable to reset their password using the recovery form', 'Cannot reset password'),
(2, '2023-12-24 16:55:33.000000', 34, 4, 1, '2023-12-24 16:55:33.000000', 24, 'Images on product pages are not displaying correctly due to broken links', 'Broken image links'),
(3, '2023-12-28 15:39:29.000000', 35, 3, 1, '2023-12-28 15:39:29.000000', 25, 'User is logged out automatically after a short period of inactivity', 'Mobile app login timeout'),
(1, '2023-12-30 13:22:01.000000', 36, 2, 1, '2023-12-30 13:22:01.000000', 26, 'User did not receive an order confirmation email after purchase', 'Order confirmation not received'),
(2, '2024-01-02 11:05:45.000000', 37, 1, 1, '2024-01-02 11:05:45.000000', 27, 'Cart page does not load correctly when adding products', 'Issues with cart page'),
(3, '2024-01-06 09:50:12.000000', 38, 4, 1, '2024-01-06 09:50:12.000000', 28, 'Navigation links on the website lead to 404 pages', 'Broken navigation links'),
(1, '2024-01-09 12:33:01.000000', 39, 3, 1, '2024-01-09 12:33:01.000000', 29, 'Discount code does not apply at checkout', 'Error when applying discount code'),
(2, '2024-01-11 15:10:23.000000', 40, 2, 1, '2024-01-11 15:10:23.000000', 20, 'Pages are taking too long to render, slowing down user interaction', 'Slow page rendering'),
(3, '2024-01-14 08:15:19.000000', 41, 1, 1, '2024-01-14 08:15:19.000000', 11, 'User does not receive push notifications after enabling them', 'Push notifications not received'),
(1, '2024-01-17 11:25:32.000000', 42, 4, 1, '2024-01-17 11:25:32.000000', 12, 'Payment fails to process even with valid card details', 'Payment not processed'),
(2, '2024-01-20 14:50:01.000000', 43, 3, 1, '2024-01-20 14:50:01.000000', 13, 'User gets error messages even when entering valid information on the signup form', 'Signup form errors'),
(3, '2024-01-22 17:30:55.000000', 44, 2, 1, '2024-01-22 17:30:55.000000', 14, 'Mobile app crashes immediately after updating to the latest version', 'App crashes after update'),
(1, '2024-01-25 09:10:14.000000', 45, 1, 1, '2024-01-25 09:10:14.000000', 15, 'Documentation contains broken links leading to missing pages', 'Broken links in documentation'),
(2, '2024-01-28 12:35:11.000000', 46, 4, 1, '2024-01-28 12:35:11.000000', 16, 'Changes made to homepage content are not displaying to users', 'Content not updating on homepage'),
(3, '2024-01-31 13:25:42.000000', 47, 3, 1, '2024-01-31 13:25:42.000000', 17, 'Videos fail to load or play correctly on the website', 'Video playback issues'),
(1, '2024-02-02 16:14:56.000000', 48, 2, 1, '2024-02-02 16:14:56.000000', 18, 'User cannot update profile information due to a system error', 'Profile update error'),
(2, '2024-02-05 11:03:23.000000', 49, 1, 1, '2024-02-05 11:03:23.000000', 19, 'Users cannot send messages via the contact form due to an error', 'Error sending contact form'),
(3, '2024-02-08 13:25:39.000000', 50, 4, 1, '2024-02-08 13:25:39.000000', 3, 'User is unable to verify their email address', 'Email verification failed');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket_status_history`
--

DROP TABLE IF EXISTS `ticket_status_history`;
CREATE TABLE IF NOT EXISTS `ticket_status_history` (
  `changed_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status_id` bigint NOT NULL,
  `ticket_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr8d0b2r98r2e1wq1ah47snbdl` (`status_id`),
  KEY `FKq6so8n7mkovvevqv3t1ihuete` (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ticket_status_history`
--

INSERT INTO `ticket_status_history` (`changed_at`, `id`, `status_id`, `ticket_id`) VALUES
('2023-01-05 09:30:00.000000', 1, 1, 1),
('2023-02-12 10:45:00.000000', 2, 4, 2),
('2023-03-20 12:30:00.000000', 3, 2, 3),
('2023-04-03 07:50:00.000000', 4, 5, 4),
('2023-05-05 09:00:00.000000', 5, 3, 5),
('2023-06-15 12:30:00.000000', 6, 4, 6),
('2023-07-01 10:15:00.000000', 7, 3, 7),
('2023-08-02 06:25:00.000000', 8, 5, 8),
('2023-09-15 08:55:00.000000', 9, 4, 9),
('2023-10-11 12:40:00.000000', 10, 2, 10),
('2023-11-13 14:30:00.000000', 11, 3, 11),
('2023-12-02 09:20:00.000000', 12, 1, 12),
('2023-01-20 10:10:00.000000', 13, 4, 13),
('2023-02-07 13:00:00.000000', 14, 5, 14),
('2023-03-28 07:50:00.000000', 15, 2, 15),
('2023-04-09 08:30:00.000000', 16, 3, 16),
('2023-05-21 13:10:00.000000', 17, 4, 17),
('2023-06-18 11:20:00.000000', 18, 5, 18),
('2023-07-23 12:55:00.000000', 19, 4, 19),
('2023-08-05 14:45:00.000000', 20, 1, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `created_at` datetime(6) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `first_name` varchar(55) DEFAULT NULL,
  `last_name` varchar(55) DEFAULT NULL,
  `middle_name` varchar(55) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`created_at`, `id`, `role_id`, `updated_at`, `first_name`, `last_name`, `middle_name`, `password`, `email`) VALUES
('2023-01-15 09:45:32.000000', 1, 1, '2023-01-15 09:45:32.000000', 'John', 'Smith', 'Doe', '**************', 'john.smith@example.com'),
('2023-02-03 08:30:12.000000', 2, 4, '2023-02-03 08:30:12.000000', 'Jane', 'Doe', 'A.', '**************', 'jane.doe@example.com'),
('2023-05-22 14:21:47.000000', 3, 5, '2023-05-22 14:21:47.000000', 'Alice', 'Johnson', '', '**************', 'alice.johnson@example.com'),
('2023-06-11 12:55:23.000000', 4, 3, '2023-06-11 12:55:23.000000', 'Bob', 'Williams', 'M.', '**************', 'bob.williams@example.com'),
('2023-07-01 09:12:09.000000', 5, 3, '2023-07-01 09:12:09.000000', 'Charlie', 'Brown', '', '**************', 'charlie.brown@example.com'),
('2023-08-15 11:45:51.000000', 6, 5, '2023-08-15 11:45:51.000000', 'Emily', 'Clark', 'R.', '**************', 'emily.clark@example.com'),
('2023-09-09 15:09:28.000000', 7, 5, '2023-09-09 15:09:28.000000', 'David', 'Martinez', 'J.', '**************', 'david.martinez@example.com'),
('2023-10-04 06:29:54.000000', 8, 5, '2023-10-04 06:29:54.000000', 'Sophia', 'Garcia', 'L.', '**************', 'sophia.garcia@example.com'),
('2023-11-19 11:13:05.000000', 9, 3, '2023-11-19 11:13:05.000000', 'Michael', 'Lopez', '', '**************', 'michael.lopez@example.com'),
('2023-12-02 13:24:36.000000', 10, 5, '2023-12-02 13:24:36.000000', 'Olivia', 'Hernandez', 'C.', '**************', 'olivia.hernandez@example.com'),
('2023-01-25 08:41:56.000000', 11, 4, '2023-01-25 08:41:56.000000', 'Liam', 'Taylor', '', '**************', 'liam.taylor@example.com'),
('2023-02-14 10:34:17.000000', 12, 5, '2023-02-14 10:34:17.000000', 'Mason', 'Wilson', 'T.', '**************', 'mason.wilson@example.com'),
('2023-03-09 14:56:11.000000', 13, 5, '2023-03-09 14:56:11.000000', 'Isabella', 'Moore', '', '**************', 'isabella.moore@example.com'),
('2023-04-10 08:29:38.000000', 14, 4, '2023-04-10 08:29:38.000000', 'Lucas', 'Anderson', 'P.', '**************', 'lucas.anderson@example.com'),
('2023-05-15 10:16:53.000000', 15, 3, '2023-05-15 10:16:53.000000', 'Zoe', 'Thomas', 'B.', '**************', 'zoe.thomas@example.com'),
('2023-06-20 07:02:17.000000', 16, 5, '2023-06-20 07:02:17.000000', 'Amelia', 'Jackson', 'K.', '**************', 'amelia.jackson@example.com'),
('2023-07-30 12:47:42.000000', 17, 3, '2023-07-30 12:47:42.000000', 'Aiden', 'White', 'D.', '**************', 'aiden.white@example.com'),
('2023-08-13 14:36:24.000000', 18, 4, '2023-08-13 14:36:24.000000', 'Ethan', 'Lee', '', '**************', 'ethan.lee@example.com'),
('2023-09-25 08:05:17.000000', 19, 5, '2023-09-25 08:05:17.000000', 'Abigail', 'Young', 'V.', '**************', 'abigail.young@example.com'),
('2023-10-30 07:19:22.000000', 20, 4, '2023-10-30 07:19:22.000000', 'Mia', 'Walker', 'H.', '**************', 'mia.walker@example.com'),
('2023-11-03 12:03:58.000000', 21, 5, '2023-11-03 12:03:58.000000', 'William', 'Allen', 'N.', '**************', 'william.allen@example.com'),
('2023-12-01 11:18:11.000000', 22, 1, '2023-12-01 11:18:11.000000', 'James', 'King', 'L.', '**************', 'james.king@example.com'),
('2023-01-18 08:27:32.000000', 23, 5, '2023-01-18 08:27:32.000000', 'Benjamin', 'Scott', 'Q.', '**************', 'benjamin.scott@example.com'),
('2023-02-23 10:14:04.000000', 24, 4, '2023-02-23 10:14:04.000000', 'Chloe', 'Adams', 'R.', '**************', 'chloe.adams@example.com'),
('2023-03-29 12:02:43.000000', 25, 5, '2023-03-29 12:02:43.000000', 'Avery', 'Baker', '', '**************', 'avery.baker@example.com'),
('2023-04-26 14:11:29.000000', 26, 5, '2023-04-26 14:11:29.000000', 'Daniel', 'Gonzalez', 'J.', '**************', 'daniel.gonzalez@example.com'),
('2023-05-16 11:37:10.000000', 27, 5, '2023-05-16 11:37:10.000000', 'Grace', 'Nelson', 'S.', '**************', 'grace.nelson@example.com'),
('2023-06-01 08:25:44.000000', 28, 5, '2023-06-01 08:25:44.000000', 'Jack', 'Carter', '', '**************', 'jack.carter@example.com'),
('2023-07-04 10:18:59.000000', 29, 5, '2023-07-04 10:18:59.000000', 'Lily', 'Mitchell', 'P.', '**************', 'lily.mitchell@example.com');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `assigments`
--
ALTER TABLE `assigments`
  ADD CONSTRAINT `FKieenbvdduj75j8xt3iau803hi` FOREIGN KEY (`agent_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKqspxs4odn5b4xj33dgbgvxev1` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`);

--
-- Filtros para la tabla `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKj7vc0aigr2m5mew52v7ddt4fo` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`);

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `FK40lfvi239woyqe915ww8b9996` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `FK4eqsebpimnjen0q46ja6fl2hl` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKano4h3jfjbejh4aqwp8phfm69` FOREIGN KEY (`priority_id`) REFERENCES `priorities` (`id`),
  ADD CONSTRAINT `FKq830rms3y6k7td8gjklhvsbfs` FOREIGN KEY (`status_id`) REFERENCES `priorities` (`id`);

--
-- Filtros para la tabla `ticket_status_history`
--
ALTER TABLE `ticket_status_history`
  ADD CONSTRAINT `FKq6so8n7mkovvevqv3t1ihuete` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`),
  ADD CONSTRAINT `FKr8d0b2r98r2e1wq1ah47snbdl` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
