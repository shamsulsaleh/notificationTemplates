-- test.notification_template definition

CREATE TABLE test.notification_template (
	record_id INT(11) auto_increment,
	name varchar(255) NOT NULL,
	notification_type varchar(255) NOT NULL,
	category varchar(100) NOT NULL,
	content LONGTEXT NULL,
	subject LONGTEXT null,
	channel varchar(100) NOT NULL,
	PRIMARY KEY (`record_id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=latin1
COLLATE=latin1_swedish_ci;
CREATE INDEX notification_template_record_id_IDX USING BTREE ON test.notification_template (record_id);
CREATE INDEX notification_template_notification_type_IDX USING BTREE ON test.notification_template (notification_type);