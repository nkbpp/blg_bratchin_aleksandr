--вопросы на которые есть правельные ответы
select text_quest,
       comment_quest,
       level_quest,
       answer.answer,
       answer.correct_answer--, rt.theme
from quest q
         left join answer on q.quest_id = answer.quest_id
where exists (select answer  from answer an where an.quest_id = q.quest_id and an.correct_answer = true)
order by q.level_quest, q.quest_id;

--вопросы для которых нет правельных ответов
select text_quest,
       comment_quest,
       level_quest,
       answer.answer,
       answer.correct_answer--, rt.theme
from quest q
         left join answer on q.quest_id = answer.quest_id
where not exists (select answer  from answer an where an.quest_id = q.quest_id and an.correct_answer = true)
order by q.level_quest, q.quest_id;

--пакет с самым большим количеством вопросов
select p.name_package,
       count(rq.quest_id) as kol
from packages p
         left join rounds r on r.package_id = p.package_id
         left join  round_quest rq on rq.round_id = r.round_id
group by p.name_package
order by kol desc  limit 1

--вывести количество вопросов в каждом пакете
select p.name_package,
       count(rq.quest_id) as kol
from packages p
         left join rounds r on r.package_id = p.package_id
         left join round_quest rq on rq.round_id = r.round_id
group by p.name_package
order by p.name_package, kol