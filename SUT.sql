DROP SCHEMA IF EXISTS "public" CASCADE;
Create schema "public";

CREATE TABLE IF NOT EXISTS tsystem --Система
( 
	id		serial, --id информации о системе
	description	character varying(250), --описание системы
	code		character varying(50) not null unique, --код системы
	name	character varying(50) not null, --краткое название
	PRIMARY KEY (id) 
);

CREATE TABLE IF NOT EXISTS trelease --Релиз
( 
	id			serial, --id релиза
	system_info_id		integer not null, --id информации о системе
	release_version		character varying(50) not null, --версия
	release_date		date not null, --дата ввода в действие
	release_estimated_date	date not null, --плановая дата
	release_description	character varying(250), --описание релиза
	PRIMARY KEY (id),
	FOREIGN KEY (system_info_id) REFERENCES tsystem (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ttestplan --Тест-план
( 
	id		serial, --id тест-плана
	author		character varying(50) not null, --автор
	update_date	date not null, --дата последнего изменения
	creation_date	date not null, --дата создания
	release_id		integer not null, --id релиза
	PRIMARY KEY (id),
	FOREIGN KEY (release_id) REFERENCES trelease (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tcl_element --Элемент тестирования
( 
	id			serial, --id элемента тестирования
	element_name		character varying(20) not null, --название элемента тестирования
	description		character varying(250), --описание элемента тестирования
	PRIMARY KEY (id) 
);

CREATE TABLE IF NOT EXISTS trequirement --Требования к элементу тестирования
( 
	id			serial, --id требования к элементу тестирования
	description		character varying(250), --описание требования
	element_id		integer not null, --id элемента тестирования
	PRIMARY KEY (id),
	FOREIGN KEY (element_id) REFERENCES tcl_element (id) 
	ON DELETE CASCADE ON UPDATE CASCADE
);
--drop table ttestcase;
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
	element_id		integer,
	PRIMARY KEY (id),
	FOREIGN KEY (chain_id) REFERENCES ttestcase (id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (testplan_id) REFERENCES ttestplan (id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (element_id) REFERENCES tcl_element (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ttestreport --Отчет о тестировании
( 
	id			serial, --id отчета о тестировании
	author			character varying(50) not null, --Автор
	update_date		date not null, --Дата последнего изменения
	creation_date		date not null, --Дата создания
	PRIMARY KEY (id) 
);

CREATE TABLE IF NOT EXISTS ttestcasereport --Отчет о прохождении тестового сценария
( 
	id				serial, --id отчета о прохождении тестового сценрия
	author				character varying(50) not null, --Автор
	creation_date			date not null, --Дата создания
	success				boolean, --Успешность прохождения тестового сценария
	testcase_id			integer, --id тестового сценария
	testreport_id			integer, --id отчета о тестировании
	PRIMARY KEY (id),
	FOREIGN KEY (testreport_id) REFERENCES ttestreport (id)
	ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (testcase_id) REFERENCES ttestcase (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tresult --Результат тестирования
( 
	id		serial, --id результата тестирования
	author		character varying(50) not null, --Автор
	creation_date	date not null, --Дата добавления результата
	description	character varying(250) not null, --Результат тестирования
	testcase_id		integer,
	PRIMARY KEY (id),
	FOREIGN KEY (testcase_id) REFERENCES ttestcase (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tcomment --Комментарий об ошибке
( 
	id			serial, --id комментария об ошибке
	author			character varying(50) not null, --Автор
	creation_date		date not null, --Дата добавления комментария
	description		character varying(50) not null, --Описание ошибки
	result_id		integer,
	PRIMARY KEY (id),
	FOREIGN KEY (result_id) REFERENCES tresult (id)
	ON DELETE CASCADE ON UPDATE CASCADE
);