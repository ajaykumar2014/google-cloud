
# Connect to SQL instance from cloud terminal.
$ gcloud sql connect <INSTANCE_CONNECTION_NAME> --user=root 

$ gcloud sql connect my-demo-example --user=root 
# Database Setup in SQL

CREATE DATABASE sitepoint CHARACTER SET utf8 COLLATE utf8_general_ci;
USE sitepoint;

CREATE TABLE authors (
id int(11) NOT NULL AUTO_INCREMENT,
name varchar(50),
city varchar(50),
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

INSERT INTO authors (id, name, city) VALUES
(1, 'Michaela Lehr', 'Berlin'),
(2, 'Michael Wanyoike', 'Nairobi'),
(3, 'James Hibbard', 'Munich'),
(4, 'Karolina Gawron', 'Wrocław');
