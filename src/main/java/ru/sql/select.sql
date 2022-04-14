--вопросы на которые есть правельные ответы
select text_quest,
       comment_quest,
       level_quest,
       answer.answer,
       answer.correct_answer--, rt.theme
from quest q
         left join answer on q.quest_id = answer.quest_id
where q.quest_id in (select a.quest_id
                     from answer as a
                     where a.quest_id in (select quest_id
                                          from answer as a2
                                          where correct_answer = true
                                          group by quest_id)) --есть ответы
order by q.level_quest

--вопросы для которых нет правельных ответов
select text_quest,
       comment_quest,
       level_quest,
       answer.answer,
       answer.correct_answer
from quest q
         left join answer on q.quest_id = answer.quest_id
where q.quest_id not in (select a.quest_id
                         from answer as a
                         where a.quest_id in
                               (select quest_id from answer as a2 where correct_answer = true group by quest_id))
order by q.level_quest

--пакет с самым большим количеством вопросов
select text_quest,
       comment_quest,
       level_quest,
       answer.answer,
       answer.correct_answer
from quest q
         left join answer on q.quest_id = answer.quest_id
where q.quest_id not in (select a.quest_id
                         from answer as a
                         where a.quest_id in
                               (select quest_id from answer as a2 where correct_answer = true group by quest_id))
order by q.level_quest

--вывести количество вопросов в каждом пакете
select p.name_package,
       count(r.quest_id) as kol
from packages p
         left join (select rounds.round_id, rounds.package_id, rounds.name_round, rq.quest_id
                    from rounds
                             left join round_quest rq on rounds.round_id = rq.round_id
                    order by round_id) r
                   on r.package_id = p.package_id
group by p.name_package
order by p.name_package, kol