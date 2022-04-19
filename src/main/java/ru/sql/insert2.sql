--вопрос1
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Зимой и летом одним цветом',
    'русская народная загодка',
    'Простой'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Елка', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Радуга', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Лист', false::boolean, (select max(quest_id) from quest):: int),
       ('Конфети', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://zagadki1.ru/zagadka/zimoj-i-letom-odnim-cvetom.htm'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Природа');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос2
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Герой одной пьесы — злой герцог СкамвУлья — воспользовался набожностью доброго герцога ФАджио. Ответьте, что Скамвулья сделал с ногами стоявшего в часовне изваяния святого.',
    'Добрый герцог, приходя к мессе, целовал ступни изваяния — так и погиб через козни своего злого соседа.',
    'Средний'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id) --правельный ответ
select 'Отравил', true::boolean, quest1.quest_id
from quest1;
INSERT INTO answer (answer, correct_answer, quest_id) --не правельный ответ
VALUES ('Покрасил', false::boolean, (select max(quest_id) from quest):: int),
       ('Подстриг ногти', false::boolean, (select max(quest_id) from quest):: int),
       ('Побрил', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link) --cсылки
VALUES ((select max(quest_id) from quest):: int,
        'Томас Рагглз Пинчон. Выкрикивается лот 49. — СПб.: Симпозиум, 2000 (http://lib.aldebaran.ru/author/pinchon_tomas/pinchon_tomas_vykrikivaetsya_lot_49/).');
INSERT INTO theme (theme) --тема
VALUES ('Религия');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос3
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'После того как этот человек в детстве погладил своего кота МАчака, его жизнь переменилась. Назовите его.',
    'Кстати, Мачак с сербского переводится как "кот". Погладив кота, Тесла познакомился со статическим электричеством. Слово "переменилась" намекает на лоббирование Теслой сети переменного тока.',
    'Простой'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Никола Тесла', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Эдиссон', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Маск', false::boolean, (select max(quest_id) from quest):: int),
       ('Ньютон', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://zagadki1.ru/zagadka/zimoj-i-letom-odnim-cvetom.htm'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Физика');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос4
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Мы достоверно знаем вес самого большого колокола Свято-Вознесенского Кафедрального собора. Напишите этот вес совершенно точно.',
    'Гомером окрестили тигра, ослепшего от постоянных фотовспышек.',
    'Простой'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Фотографы.', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('5грамм', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('1,5кг', false::boolean, (select max(quest_id) from quest):: int),
       ('1024Мб', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://blagovest.narod.ru/');
--cсылки

--вопрос5
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Серые. ИгрЕневые. СоловЫе. Назовите всех их двумя словами — цитатой из произведения 1980 года. ',
    'Упомянутые масти лошадей подразумевают наличие белой гривы. Произведение — песня из мультфильма "Трям! Здравствуйте!".',
    'Сложный'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Белогривые лошадки', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Игривые котики', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Свистящие раки', false::boolean, (select max(quest_id) from quest):: int),
       ('Злые собаки', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://ru.wikipedia.org/wiki/Масть_лошади '),
       ((select max(quest_id) from quest):: int, 'http://ru.wikipedia.org/wiki/Трям!_Здравствуйте!'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Животные');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос6
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Честертон пишет, что в странах, где царит олигархия, важны лица, а не законы, ОНА, а не избирательный бюллетень. На сайте milliony.ru [миллионы точка ру] бумажные деньги названы ЕЮ государства. Назовите ЕЁ.',
    'Большее значение имеет происхождение, титул и репутация, чем воля народа. Этим вопросом редакторская группа в составе Юрия Выменца и Александра Коробейникова, можно сказать, предъявляет свою визитную карточку.',
    'Сложный'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Визитная карточка', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Марка', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Билет', false::boolean, (select max(quest_id) from quest):: int),
       ('Проездной', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        'Г.К. Честертон. Перелетный кабак (http://www.flibusta.net/b/11323/read)'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Деньги');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос7
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'На картине Брейгеля "Страна лентяев" около спящих персонажей разгуливает ТАКОЙ поросенок. В другом известном произведении, написанном, скорее всего, в 1918 году, ТАКОЙ персонаж разгуливал на свободе недолго. Назовите этого персонажа.',
    'На картине разгуливает жареный поросенок. "Цыпленок жареный, // Цыпленок пареный // Пошел по Невскому гулять. // Его поймали, // Арестовали...".',
    'Сложный'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Цыпленок', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Кот', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Собака', false::boolean, (select max(quest_id) from quest):: int),
       ('Сом', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        'К.А. Роке. Брейгель, или Мастерская сновидений. — М.: Молодая гвардия, 2008. — С. 237.'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Еда');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select theme_id from theme where theme = 'Животные'):: int);


--вопрос8
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Дмитрий Быков рассказывает о французском гвардейском офицере, который однажды в присутствии Наполеона совершил ошибку, начав движение не с той ноги. Наполеон тут же изгнал его из гвардии. Мы не спрашиваем, какими словами Наполеон прокомментировал свое решение. Ответьте, к какому роду войск принадлежал офицер.',
    '"Сапер ошибается только один раз", — якобы сказал Наполеон. Эта его фраза стала крылатой.',
    'Средний'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'К саперам', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('К пограничникам', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('К артилеристам', false::boolean, (select max(quest_id) from quest):: int),
       ('К десантникам', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        'Д. Быков, И. Лукьянова. В мире животиков (http://lib.rus.ec/b/176188/read).'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Армия');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос9
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'В телепередаче, посвященной юбилею Щукинского театрального училища, бывшие студенты, ныне маститые актеры, вспоминали слова своих преподавателей [цитата]: "Как нужно играть, можно прочесть уже при входе в училище". Ответьте двумя словами, как же должен играть настоящий артист. ',
    'На двери была надпись: "От себя". "Играть от себя" на языке актеров значит "проживать свою роль"',
    'Простой'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'От себя', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Открыто круглосуточно', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Бесплатный завтрак', false::boolean, (select max(quest_id) from quest):: int),
       ('Наденьте маску', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'https://db.chgk.info/tour/eduka11.2/print'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Театр');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос10
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'В одном эксперименте двум группам показали эпизод, в котором девятимесячный ребенок закричал, когда перед ним из коробки выпрыгнула игрушка на пружинке. Представители одной группы решили, что младенец испугался, представители другой — что он рассердился. Различие между группами состояло в сообщенной им информации. Какую информацию сообщили испытуемым?',
    'Группа, которой сообщили, что ребенок — девочка, решила, что он испугался. Группа, которой сообщили, что ребенок — мальчик, решила, что он рассердился.',
    'Простой'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Пол ребенка', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Возраст ребенка', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Цвет ребенка', false::boolean, (select max(quest_id) from quest):: int),
       ('Настроение ребенка', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        'Е. Ильин. Дифференциальная психофизиология мужчины и женщины (http://www.bookap.info/genpsy/ilyin/gl57.shtm).'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Эксперименты');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос11
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Считается, что от французского слова, обозначающего слугу, происходит название средства связи. Какого именно? ',
    'От слова "page" ("паж"). ',
    'Средний'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Пейджер', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Смартфон', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Голубь', false::boolean, (select max(quest_id) from quest):: int),
       ('Почта', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://etimology.net.ua/p.php '); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Связь');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос12
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Вопрос задает Даниил Бернулли. Перед тем как по городским улицам пройдут гонки скоростных болидов, на эти улицы выходят сварщики. Иначе может произойти авария, как, например, в 1990 году во время автогонок в Монреале. Что же делают сварщики?',
    'У гоночных болидов такая аэродинамика, что на высокой скорости за счет эффекта Бернулли под днищем создается настолько низкое давление, что крышку люка может подбросить в воздух. В Монреале так и случилось, и подброшенной крышкой разбило идущий сзади болид.',
    'Простой'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Приваривают крышки канализационных люков', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Устанавливают знаки поворотов', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Варят ограды', false::boolean, (select max(quest_id) from quest):: int),
       ('Устраивают световое шоу', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://ru.wikipedia.org/wiki/Бернулли,_Даниил'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Гонки');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос13
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'В одном фантастическом фильме действует темный аналог святого Грааля. Назовите абсолютно точно то, из чего сделана эта чаша.',
    'Эта серебряная чаша носит имя Иуды. ',
    'Простой'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select '30 сребреников. ', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Переплавленный колокол', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Золотые таблички мармонов', false::boolean, (select max(quest_id) from quest):: int),
       ('Меч Вельзевула', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'К/ф "Библиотекарь: Проклятие чаши Иуды". '); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Артефакты');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос14
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Два года назад вдовствующая королева Бельгии Фабиола получила послание с угрозой покушения. Несмотря на это, в день национального праздника Бельгии она рискнула появиться на публике, держа в руках округлый предмет. Догадавшись, с чем появилась королева, назовите предполагаемое оружие покушения. ',
    'В 2009 году вдовствующую королеву Фабиолу пригрозили застрелить из арбалета во время национального праздника. В ответ Фабиола появилась на публике с яблоком в качестве мишени, однако покушения так и не произошло. Тем не менее, служба безопасности попросила королеву не повторять эту шутку в дальнейшем. ',
    'Простой'::levels_type); --вопрос

INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Арбалет', true::boolean, (select max (quest_id) from quest)::int),
       ('Лук', true ::boolean, (select max (quest_id) from quest)::int),
       ('Пистолет', false::boolean, (select max(quest_id) from quest)::int),--не правельный ответ
       ('Танк', false::boolean, (select max(quest_id) from quest)::int),
       ('Автомат', false::boolean, (select max(quest_id) from quest)::int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://lenta.ru/news/2010/07/20/fabiola/ '); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Оружие');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос15
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Рассказывают, что в 70-е годы, когда Большой театр начал выезжать на гастроли за границу и оставлять там некоторое количество актеров, не пожелавших возвращаться, появилась шутка: уезжает Большой театр — возвращается Малый, уезжает Малый — возвращается Камерный, уезжает Камерный — возвращается Театр одного актера. А что возвращается, если уезжает Театр одного актера? ',
    'Анекдот',
    'Сложный'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Театр теней', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Балет', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Кукольный театр', false::boolean, (select max(quest_id) from quest):: int),
       ('Дельфинарий', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://ru.wikipedia.org/wiki/Анекдот '); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Культура');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос16
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'В одном приюте для животных трудной судьбы живет тигр ГомЕр. Ответьте, от представителей какой профессии он пострадал.',
    'Сленговое выражение "сто пудов" означает "достоверно", "гарантированно", "совершенно точно".',
    'Сложный'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Фотографы.', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Блогеры', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Музыканты', false::boolean, (select max(quest_id) from quest):: int),
       ('Репортеры', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        '"Моя планета", серия "Краснодарский край. Курортный роман" (http://rutracker.org/forum/viewtopic.php?t=2941015).'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Звери');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select theme_id from theme where theme = 'Животные'):: int);


--вопрос17
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'На купюрах несуществующей Лунной Республики есть портреты Птолемея, Николая Коперника, Иоганна Кеплера, Жюля Верна, Юрия Гагарина, Нейла Армстронга. На одной из купюр на одной стороне изображены много восьмых, а на другой — человек, родившийся в 1770 году. Назовите его. ',
    'Написал "Лунную сонату", нотная запись которой приведена на обороте купюры. "Восьмая" — обозначение длительности ноты, "Лунная соната" в основном восьмыми и написана. ',
    'Сложный'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Людвиг ван Бетховен', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Моцарт', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Бах', false::boolean, (select max(quest_id) from quest):: int),
       ('Лев Толстой', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://pictures.live4fun.ru/last/joke/380416 '),
       ((select max(quest_id) from quest):: int, 'http://en.wikipedia.org/wiki/Ludwig_van_Beethoven '); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('История');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос18
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'ИМИ были родители Леонида Филатова: Алексей Еремеевич Филатов и Клавдия Николаевна Филатова. Корею часто называют страной ИХ. Назовите ИХ. ',
    'В Корее используется всего около 250 фамилий, при этом на три самые распространенные фамилии приходится почти половина всего населения. ',
    'Простой'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Однофамильцы', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Близнецы', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Азиаты', false::boolean, (select max(quest_id) from quest):: int),
       ('Родственники', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int, 'http://www.peoples.ru/art/theatre/actor/filatov/index1.html'),
       ((select max(quest_id) from quest):: int, 'http://www.ruskorinfo.ru/wiki/odnofamiltsy/ '); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Корея');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос19
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Персонаж рассказа Джона Кольера, неисправимый романтик, называвший себя новым Джеком Лондоном, рассказывал, что потерял несколько пальцев по разным причинам: один отстрелила из ревности циркачка во время аттракциона, второй откусил кайман, третий оттяпали свайкой во время бунта на корабле. А большой палец он потерял, пересекая Лабрадор... Как именно пересекая? ',
    'Отморозил, путешествуя зимой автостопом. "Доголосовался" в буран. ',
    'Средний'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Автостопом', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Вплавь', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('На поезде', false::boolean, (select max(quest_id) from quest):: int),
       ('На пальцах', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        'Д. Кольер. Творческое содружество. // Сборник "На полпути в ад". http://lib.ru/INPROZ/KOLER/halfway.txt '); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Путешествия');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);

--вопрос20
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Дрессировщик Карл Гагенбек использовал методы поощрения животных. Поясняя свои действия, он менял в известном утверждении некое слово на слово "животного". Какое именно слово? ',
    '"Путь к сердцу животного также лежит через его желудок", — говорил Гагенбек, обосновывая метод поощрения. ',
    'Средний'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Мужчины', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Волк', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Демон', false::boolean, (select max(quest_id) from quest):: int),
       ('Крокодил', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        ' http://www.animalkingdom.su/books/item/f00/s00/z0000052/st001.shtml '); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Цирк');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select theme_id from theme where theme = 'Животные'):: int);

--вопрос21
with quest1 as (
INSERT
INTO quest (text_quest, comment_quest, level_quest)
VALUES (
    'Согласно одной шотландской шутке, в бедной семье даже ОНА черно-белая. Михаил Задорнов в своих этимологических предположениях производит ЕЕ название от имени египетского бога и ЕЕ геометрической формы. Назовите ЕЕ.',
    'Не только телевизор, но и даже радуга в бедной семье черно-белая. Михаил Задорнов считает, что слово "радуга" происходит от имени бога солнца Ра (так как радуга бывает только при Солнце) и слова "дуга".',
    'Средний'::levels_type) --вопрос
    RETURNING quest_id
    )
INSERT
INTO answer (answer, correct_answer, quest_id)
select 'Радуга', true::boolean, quest1.quest_id
from quest1; --правельный ответ
INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Елка', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Зубы', false::boolean, (select max(quest_id) from quest):: int),
       ('Ковры', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        'http://www.kabanik.ru/page/scottish-superstitions-and-humor '); --cсылки
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select theme_id from theme where theme = 'Природа'):: int);

--вопрос22
insert INTO quest (text_quest, comment_quest, level_quest)
VALUES (
           'Чего хотят женщины?',
           'Вопрос без ответа',
           'Сложный'::levels_type);

INSERT INTO answer (answer, correct_answer, quest_id)
VALUES ('Шубу', false::boolean, (select max(quest_id) from quest):: int),--не правельный ответ
       ('Свадьбу', false::boolean, (select max(quest_id) from quest):: int),
       ('Посудомойку', false::boolean, (select max(quest_id) from quest):: int),
       ('Бряцки-цацки', false::boolean, (select max(quest_id) from quest):: int);
INSERT INTO link (quest_id, link)
VALUES ((select max(quest_id) from quest):: int,
        'жизнь'); --cсылки
INSERT INTO theme (theme) --тема
VALUES ('Загадки тысячелетия');
INSERT INTO quest_theme (quest_id, theme_id)
VALUES ((select max(quest_id) from quest):: int, (select max(theme_id) from theme):: int);


--пакет1
INSERT INTO packages (name_package, info, author)
VALUES ('От простого к сложному', 'Туры по уровню сложности', 'макнак');

INSERT INTO rounds (name_round, index_round, package_id)
select 'Простые вопросы',
       CASE
           when t.m is null
               THEN 1
           ELSE t.m + 1
           end,
       (select max(package_id) from packages) ::int
from (select max(index_round) as m from rounds where package_id = (select max(package_id) from packages)::int) as t;

INSERT INTO round_quest (round_id, quest_id)
select (select max(round_id) from rounds)::int round_id , quest_id
from quest
where level_quest = 'Простой'::levels_type;

INSERT INTO rounds (name_round, index_round, package_id)
select 'Средние вопросы',
       CASE
           when t.m is null
               THEN 1
           ELSE t.m + 1
           end,
       (select max(package_id) from packages) ::int
from (select max(index_round) as m from rounds where package_id = (select max(package_id) from packages)::int) as t;

INSERT INTO round_quest (round_id, quest_id)
select (select max(round_id) from rounds)::int round_id , quest_id
from quest
where level_quest = 'Средний'::levels_type;

INSERT INTO rounds (name_round, index_round, package_id)
select 'Сложные вопросы',
       CASE
           when t.m is null
               THEN 1
           ELSE t.m + 1
           end,
       (select max(package_id) from packages) ::int
from (select max(index_round) as m from rounds where package_id = (select max(package_id) from packages)::int) as t;

INSERT INTO round_quest (round_id, quest_id)
select (select max(round_id) from rounds)::int round_id , quest_id
from quest
where level_quest = 'Сложный'::levels_type;

--пакет2
INSERT INTO packages (name_package, info, author)
VALUES ('Природа', 'Вопросы о природе и животных', 'Дроздов');

INSERT INTO rounds (name_round, index_round, package_id)
select 'Звери',
       CASE
           when t.m is null
               THEN 1
           ELSE t.m + 1
           end,
       (select max(package_id) from packages) ::int
from (select max(index_round) as m from rounds where package_id = (select max(package_id) from packages)::int) as t;

INSERT INTO round_quest (round_id, quest_id) --добавить вопросы с темой Животные
select (select max(round_id) from rounds)::int round_id , quest_id
from quest
where quest_id in
      (select distinct quest_id
       from quest_theme
       where theme_id in (select theme_id from theme where theme in ('Животные')));

INSERT INTO round_theme (round_id, theme_id) --привязать тему Животные к раунду
VALUES ((select max(round_id) from rounds):: int, (select theme_id from theme where theme = 'Животные'):: int);


INSERT INTO rounds (name_round, index_round, package_id)
select 'Природоведение',
       CASE
           when t.m is null
               THEN 1
           ELSE t.m + 1
           end,
       (select max(package_id) from packages) ::int
from (select max(index_round) as m from rounds where package_id = (select max(package_id) from packages)::int) as t;

INSERT INTO round_quest (round_id, quest_id) --добавить вопросы с темой Животные
select (select max(round_id) from rounds)::int round_id , quest_id
from quest
where quest_id in
      (select distinct quest_id
       from quest_theme
       where theme_id in (select theme_id from theme where theme in ('Природа')));

INSERT INTO round_theme (round_id, theme_id) --привязать тему Животные к раунду
VALUES ((select max(round_id) from rounds):: int, (select theme_id from theme where theme = 'Природа'):: int);
