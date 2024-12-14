CREATE TABLE IF NOT EXISTS users (
  user_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(500) NOT NULL,
  middle_name varchar(500),
  last_name varchar(500),
  email varchar(500) NOT NULL UNIQUE,
  password varchar(500) NOT NULL,
  role tinyint NOT NULL,  -- Cambiado, ya no es UNIQUE
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp
);

CREATE TABLE IF NOT EXISTS tickets (
  ticket_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id bigint NOT NULL,
  category_id tinyint,
  priority_id tinyint,
  title tinytext,
  description text NOT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp
);

CREATE TABLE IF NOT EXISTS categories (
  category_id tinyint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(500) NOT NULL UNIQUE,
  description text
);

CREATE TABLE IF NOT EXISTS comments (
  comment_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ticket_id bigint NOT NULL,
  user_id bigint NOT NULL,
  comment text NOT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ticket_status_history (
  history_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ticket_id bigint NOT NULL,
  status_id tinyint NOT NULL,
  changed_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS priorities (
  priority_id tinyint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(500) NOT NULL,
  description text NOT NULL
);

CREATE TABLE IF NOT EXISTS assigments (
  assignment_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ticket_id bigint NOT NULL,
  agent_id bigint NOT NULL,
  assigned_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS `status` (
  status_id tinyint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(500) NOT NULL,
  status_description text NOT NULL,
  is_active TINYINT(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
  id tinyint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(500) NOT NULL UNIQUE,
  description text
);

-- Clave foránea en tickets para user_id
ALTER TABLE tickets ADD CONSTRAINT fk_tickets_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);

-- Clave foránea en tickets para category_id
ALTER TABLE tickets ADD CONSTRAINT fk_tickets_category_id FOREIGN KEY (category_id) REFERENCES categories (category_id);

-- Clave foránea en tickets para priority_id
ALTER TABLE tickets ADD CONSTRAINT fk_tickets_priority_id FOREIGN KEY (priority_id) REFERENCES priorities (priority_id);

-- Clave foránea en comments para ticket_id
ALTER TABLE comments ADD CONSTRAINT fk_comments_ticket_id FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id);

-- Clave foránea en comments para user_id
ALTER TABLE comments ADD CONSTRAINT fk_comments_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);

-- Clave foránea en ticket_status_history para ticket_id
ALTER TABLE ticket_status_history ADD CONSTRAINT fk_ticket_status_history_ticket_id FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id);

-- Clave foránea en ticket_status_history para status_id
ALTER TABLE ticket_status_history ADD CONSTRAINT fk_ticket_status_history_status_id FOREIGN KEY (status_id) REFERENCES status (status_id);

-- Clave foránea en assigments para ticket_id
ALTER TABLE assigments ADD CONSTRAINT fk_assigments_ticket_id FOREIGN KEY (ticket_id) REFERENCES tickets (ticket_id);

-- Clave foránea en assigments para agent_id
ALTER TABLE assigments ADD CONSTRAINT fk_assigments_agent_id FOREIGN KEY (agent_id) REFERENCES users (user_id);

-- Clave foránea en roles para role_id en users
ALTER TABLE users ADD CONSTRAINT fk_users_role FOREIGN KEY (role) REFERENCES roles (id);

