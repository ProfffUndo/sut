DROP SCHEMA IF EXISTS "public" CASCADE;
Create schema "public";

CREATE TABLE IF NOT EXISTS tsystem --Система
( 
	system_info_id		serial, --id информации о системе
	system_description	character varying(250), --описание системы
	system_code		character varying(50) not null unique, --код системы
	system_short_name	character varying(50) not null, --краткое название
	PRIMARY KEY (system_info_id) 
);

CREATE TABLE IF NOT EXISTS trelease --Релиз
( 
	release_id		serial, --id релиза
	system_info_id		integer not null, --id информации о системе
	release_version		character varying(50) not null, --версия
	release_date		date not null, --дата ввода в действие
	release_estimated_date	date not null, --плановая дата
	release_description	character varying(250), --описание релиза
	PRIMARY KEY (release_id),
	FOREIGN KEY (system_info_id) REFERENCES tsystem (system_info_id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ttestplan --Тест-план
( 
	testplan_id		serial, --id тест-плана
	testplan_author		character varying(50) not null, --автор
	testplan_update_date	date not null, --дата последнего изменения
	testplan_creation_date	date not null, --дата создания
	release_id		integer not null, --id релиза
	PRIMARY KEY (testplan_id),
	FOREIGN KEY (release_id) REFERENCES trelease (release_id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tcl_element --Элемент тестирования
( 
	element_id		serial, --id элемента тестирования
	element_name		character varying(20) not null, --название элемента тестирования
	element_description	character varying(250), --описание элемента тестирования
	PRIMARY KEY (element_id) 
);

CREATE TABLE IF NOT EXISTS trequirement --Требования к элементу тестирования
( 
	requirement_id		serial, --id требования к элементу тестирования
	requirement_description	character varying(250), --описание требования
	element_id		integer not null, --id элемента тестирования
	PRIMARY KEY (requirement_id),
	FOREIGN KEY (element_id) REFERENCES tcl_element (element_id) 
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ttestcase --Тестовый сценарий
( 
	id		serial, --id тестового сценария
	author		character varying(50) not null, --автор
	update_date	date not null, --Дата последнего изменения
	creation_date	date not null, --Дата создания
	case_name		character varying(50), --Название
	step		character varying(50), --Шаг тестирования
	input_data	character varying(50), --Входные данные
	result		character varying(50), --Ожидаемый результат
	chain_id	integer,
	testplan_id		integer,
	element_id		integer/*,
	PRIMARY KEY (testcase_id),
	FOREIGN KEY (testcase_chain_id) REFERENCES ttestcase (testcase_id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (testplan_id) REFERENCES ttestplan (testplan_id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (element_id) REFERENCES tcl_element (element_id)
	ON DELETE CASCADE ON UPDATE CASCADE*/
);

CREATE TABLE IF NOT EXISTS ttestreport --Отчет о тестировании
( 
	testreport_id			serial, --id отчета о тестировании
	testreport_author		character varying(50) not null, --Автор
	testreport_update_date		date not null, --Дата последнего изменения
	testreport_creation_date	date not null, --Дата создания
	PRIMARY KEY (testreport_id) 
);

CREATE TABLE IF NOT EXISTS ttestcasereport --Отчет о прохождении тестового сценария
( 
	testcasereport_id		serial, --id отчета о прохождении тестового сценрия
	testcasereport_author		character varying(50) not null, --Автор
	testcasereport_creation_date	date not null, --Дата создания
	testcasereport_success		boolean, --Успешность прохождения тестового сценария
	testcase_id			integer, --id тестового сценария
	testreport_id			integer, --id отчета о тестировании
	PRIMARY KEY (testcasereport_id),
	FOREIGN KEY (testreport_id) REFERENCES ttestreport (testreport_id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (testcase_id) REFERENCES ttestcase (testcase_id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tresult --Результат тестирования
( 
	result_id		serial, --id результата тестирования
	result_author		character varying(50) not null, --Автор
	result_creation_date	date not null, --Дата добавления результата
	result_description	character varying(250) not null, --Результат тестирования
	testcase_id		integer,
	PRIMARY KEY (result_id),
	FOREIGN KEY (testcase_id) REFERENCES ttestcase (testcase_id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tcomment --Комментарий об ошибке
( 
	comment_id		serial, --id комментария об ошибке
	comment_author		character varying(50) not null, --Автор
	comment_creation_date	date not null, --Дата добавления комментария
	error_description	character varying(50) not null, --Описание ошибки
	result_id		integer,
	PRIMARY KEY (comment_id),
	FOREIGN KEY (result_id) REFERENCES tresult (result_id)
	ON DELETE CASCADE ON UPDATE CASCADE
);