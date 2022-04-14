--Добавить пакет
INSERT INTO packages (package_id, name_package, info, author)
select p.package_id,
       concat('name_package_', p.package_id),
       concat('info_', p.package_id),
       concat('author_', p.package_id)
from generate_series(
             (select CASE WHEN max(package_id) IS NULL THEN 0 ELSE max(package_id) end from packages):: int + 1,
             (select CASE WHEN max(package_id) IS NULL THEN 0 ELSE max(package_id) end
              from packages):: int + 10) as p (package_id); --сколько добавить 10

--Добавить раунд к пакету у которого наименьшее количество раундов или их нет
INSERT INTO rounds (round_id, name_round, index_round, package_id)
select (select CASE WHEN max(round_id) IS NULL THEN 1 ELSE max(round_id) + 1 end from rounds),
       concat(concat('package_', p1.package_id),
              concat('_round_', (select CASE WHEN max(index_round) IS NULL THEN 1 ELSE max(index_round) + 1 end
                                 from rounds
                                 where package_id = p1.package_id))),
       (select CASE WHEN max(index_round) IS NULL THEN 1 ELSE max(index_round) + 1 end
        from rounds
        where package_id = p1.package_id),
       p1.package_id
from (
         select package_id
         from packages
         where package_id in (select package_id
                              from packages
                              where package_id not in (select package_id from rounds))
            or package_id in (
             select package_id
             from rounds
             group by package_id
             having count(*) in (select count(*) m
                                 from rounds
                                 group by package_id
                                 order by m
             limit 1)
    )
ORDER BY RANDOM()
    LIMIT 1
    ) as p1 (package_id);

--Добавить вопросы
INSERT INTO quest (quest_id, text_quest, comment_quest, level_quest)
select p.quest_id,
       concat('text_quest_', p.quest_id),
       concat('comment_quest_', p.quest_id),
       CASE floor(random() * 3 + 1)::int
         WHEN 1 THEN 'Простой'::levels_type
         WHEN 2 THEN 'Средний'::levels_type
         ELSE 'Сложный'::levels_type
END
from 
	generate_series(
    		(select CASE WHEN max(quest_id) IS NULL THEN 0 ELSE max(quest_id) end from quest)::int + 1, 
    		(select CASE WHEN max(quest_id) IS NULL THEN 0 ELSE max(quest_id) end from quest)::int + 50) as p (quest_id); --сколько добавить 50
	
--Добавить ответы к случайному вопросу
INSERT INTO answer (answer_id, answer, correct_answer, quest_id)
SELECT p.answer_id,
       concat('answer_', p.answer_id),
       CASE floor(random() * 3 + 1)::int
         	WHEN 1 THEN true::boolean
         	ELSE false::boolean
end
,
		p1.answer_id
	from 
    	generate_series(
    		(select CASE WHEN max(answer_id) IS NULL THEN 0 ELSE max(answer_id) end from answer)::int + 1, 
    		(select CASE WHEN max(answer_id) IS NULL THEN 0 ELSE max(answer_id) end from answer)::int + 5) as p (answer_id), --сколько добавить 5
    	(SELECT quest_id 
		FROM quest 
		where quest_id NOT IN 
			(select quest_id 
			from answer
			group by quest_id
			having count(quest_id) > 4) 
		or 
			(select quest_id 
			from answer
			group by quest_id
			having count(quest_id) > 4 limit 1) is null
		ORDER BY RANDOM()
		LIMIT 1) as p1 (answer_id);
		
--Добавить ссылки к случайному вопросу у которого нет ссылки
INSERT INTO link (link_id, quest_id, link)
SELECT p.link_id,
       p1.quest_id,
       concat('link_', p.link_id)
from generate_series(
             (select CASE WHEN max(link_id) IS NULL THEN 0 ELSE max(link_id) end from link):: int + 1,
             (select CASE WHEN max(link_id) IS NULL THEN 0 ELSE max(link_id) end
              from link):: int + floor(random() * 3 + 1):: int) as p (link_id), --случайное количество ссылок от 1 до 3
     (SELECT quest_id
      FROM quest
      where quest_id not in
            (select quest_id
             from link
             group by quest_id)
      ORDER BY RANDOM() LIMIT 1) as p1 (quest_id);


-- связывает вопрос и раунд
INSERT INTO round_quest (round_id, quest_id)
select r.id,
       q.id
from (
         select round_id
         from rounds
         where round_id in (select round_id
                            from rounds
                            where round_id not in (select round_id from round_quest))
            or round_id in (
             select round_id
             from round_quest
             group by round_id
             having count(*) in (select count(*) m
                                 from round_quest
                                 group by round_id
                                 order by m
             limit 1)
    )
ORDER BY RANDOM()
    LIMIT 1
    ) as r (id),
    (
select quest_id
from quest
where
    quest_id in (select quest_id from quest
    where quest_id not in (select quest_id from round_quest))
   or
    quest_id in (
    select quest_id from round_quest
    group by quest_id having
    count (*) in (select count (*) m from round_quest
    group by quest_id order by m limit 1)
    )
ORDER BY RANDOM()
    LIMIT 1
    ) as q (id);

--Добавить темы
INSERT INTO theme (theme_id, theme)
select t.id,
       concat('theme_', t.id)
from generate_series(
             (select CASE WHEN max(theme_id) IS NULL THEN 0 ELSE max(theme_id) end from theme):: int + 1,
             (select CASE WHEN max(theme_id) IS NULL THEN 0 ELSE max(theme_id) end from theme):: int + 100) as t (id);
--сколько добавить 100

-- связывает вопрос и тему
INSERT INTO quest_theme (quest_id, theme_id)
select q.id,
       t.id
from (
         select theme_id
         from theme
         where theme_id in (select theme_id
                            from theme
                            where theme_id not in (select theme_id from quest_theme))
            or theme_id in (
             select theme_id
             from quest_theme
             group by theme_id
             having count(*) in (select count(*) m
                                 from quest_theme
                                 group by theme_id
                                 order by m
             limit 1)
    )
ORDER BY RANDOM()
    LIMIT 1
    ) as t (id),
    (
select quest_id
from quest
where
    quest_id in (select quest_id from quest
    where quest_id not in (select quest_id from quest_theme))
   or
    quest_id in (
    select quest_id from quest_theme
    group by quest_id having
    count (*) in (select count (*) m from quest_theme
    group by quest_id order by m limit 1)
    )
ORDER BY RANDOM()
    LIMIT 1
    ) as q (id);

-- связывает раунд и тему
INSERT INTO round_theme (round_id, theme_id)
select r.id,
       t.id
from (
         select theme_id
         from theme
         where theme_id in (select theme_id
                            from theme
                            where theme_id not in (select theme_id from round_theme))
            or theme_id in (
             select theme_id
             from round_theme
             group by theme_id
             having count(*) in (select count(*) m
                                 from round_theme
                                 group by theme_id
                                 order by m
             limit 1)
    )
ORDER BY RANDOM()
    LIMIT 1
    ) as t (id),
    (
select round_id
from rounds
where
    round_id in (select round_id from rounds
    where round_id not in (select round_id from round_theme))
   or
    round_id in (
    select round_id from round_theme
    group by round_id having
    count (*) in (select count (*) m from round_theme
    group by round_id order by m limit 1)
    )
ORDER BY RANDOM()
    LIMIT 1
    ) as r (id);