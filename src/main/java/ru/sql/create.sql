CREATE TABLE theme
(
    theme_id serial NOT NULL PRIMARY KEY,
    theme    varchar(255) NULL
);

CREATE TYPE levels_type AS ENUM ('Простой', 'Средний', 'Сложный');

CREATE TABLE quest
(
    quest_id      serial      NOT NULL PRIMARY KEY,
    text_quest    text        NOT NULL,
    comment_quest text NULL,
    level_quest   levels_type NOT NULL DEFAULT 'Простой'::levels_type
);

CREATE TABLE answer
(
    answer_id      serial       NOT null PRIMARY KEY,
    answer         varchar(255) NOT NULL,
    correct_answer boolean      NOT NULL DEFAULT false,
    quest_id       int4         NOT NULL REFERENCES quest (quest_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE link
(
    link_id  serial       NOT null PRIMARY KEY,
    quest_id int4         NOT NULL REFERENCES quest (quest_id) ON DELETE CASCADE ON UPDATE CASCADE,
    link     varchar(511) NOT NULL
);

CREATE TABLE packages
(
    package_id   serial NOT NULL PRIMARY KEY,
    name_package text   NOT NULL,
    info         text NULL,
    author       varchar(255) NULL
);

CREATE TABLE rounds
(
    round_id    serial       NOT NULL PRIMARY KEY,
    name_round  VARCHAR(255) NOT NULL,
    index_round INTEGER      not NULL,
    package_id  int4         NOT NULL REFERENCES packages (package_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE round_quest
(
    round_id int4 NOT NULL REFERENCES rounds (round_id) ON DELETE CASCADE ON UPDATE CASCADE,
    quest_id int4 NOT NULL REFERENCES quest (quest_id) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key (round_id, quest_id)
);

CREATE TABLE round_theme
(
    round_id int4 NOT NULL REFERENCES rounds (round_id) ON DELETE CASCADE ON UPDATE CASCADE,
    theme_id int4 NOT NULL REFERENCES theme (theme_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    primary key (round_id, theme_id)
);

CREATE TABLE quest_theme
(
    quest_id int4 NOT NULL REFERENCES quest (quest_id) ON DELETE CASCADE ON UPDATE CASCADE,
    theme_id int4 NOT NULL REFERENCES theme (theme_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    primary key (quest_id, theme_id)
);