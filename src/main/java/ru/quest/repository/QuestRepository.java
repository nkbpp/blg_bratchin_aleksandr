package ru.quest.repository;

import ru.quest.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class QuestRepository implements Repository<Quest>{

    private List<Quest> quests;
    private long answersId = 0;
    private long linksId = 0;
    private long themesId = 0;

    {
        quests = new ArrayList<>();
        quests.add(new Quest((long) (quests.size()+1),"Зимой и летом одним цветом", "русская народная загадка", Level.EASY,
                        Arrays.asList(new Answer(++answersId,"Елка",true),
                        new Answer(++answersId,"Радуга",false),
                        new Answer(++answersId,"Лист",false),
                        new Answer(++answersId,"Конфети",false)
                ),
                Arrays.asList(new Link(++linksId,"http://zagadki1.ru/zagadka/zimoj-i-letom-odnim-cvetom.htm")
                ),
                Arrays.asList(new Theme(++themesId,"Природа"))
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Герой одной пьесы — злой герцог СкамвУлья — воспользовался набожностью доброго герцога ФАджио. Ответьте, что Скамвулья сделал с ногами стоявшего в часовне изваяния святого.",
                "Добрый герцог, приходя к мессе, целовал ступни изваяния — так и погиб через козни своего злого соседа.",
                Level.MEDIUM,
                Arrays.asList(new Answer(++answersId,"Отравил",true),
                        new Answer(++answersId,"Покрасил",false),
                        new Answer(++answersId,"Подстриг ногти",false),
                        new Answer(++answersId,"Побрил",false)
                ),
                Arrays.asList(new Link(++linksId,
                        "Томас Рагглз Пинчон. Выкрикивается лот 49. — СПб.: Симпозиум, 2000 (http://lib.aldebaran.ru/author/pinchon_tomas/pinchon_tomas_vykrikivaetsya_lot_49/).")
                ),
                Arrays.asList(new Theme(++themesId,"Религия"))
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "После того как этот человек в детстве погладил своего кота МАчака, его жизнь переменилась. Назовите его.",
                "Кстати, Мачак с сербского переводится как 'кот'. Погладив кота, Тесла познакомился со статическим электричеством. Слово 'переменилась' намекает на лоббирование Теслой сети переменного тока.",
                Level.EASY,
                Arrays.asList(new Answer(++answersId,"Никола Тесла",true),
                        new Answer(++answersId,"Эдиссон",false),
                        new Answer(++answersId,"Маск",false),
                        new Answer(++answersId,"Ньютон",false)
                ),
                Arrays.asList(new Link(++linksId,""),
                        new Link(++linksId,
                                "http://zagadki1.ru/zagadka/zimoj-i-letom-odnim-cvetom.htm")
                ),
                Arrays.asList(new Theme(++themesId,""),
                        new Theme(++themesId,"Физика")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Мы достоверно знаем вес самого большого колокола Свято-Вознесенского Кафедрального собора. Напишите этот вес совершенно точно.",
                "Гомером окрестили тигра, ослепшего от постоянных фотовспышек.",
                Level.EASY,
                Arrays.asList(new Answer(++answersId,"100пудов",true),
                        new Answer(++answersId,"5грамм",false),
                        new Answer(++answersId,"1,5кг",false),
                        new Answer(++answersId,"1024Мб",false)
                ),
                Arrays.asList(new Link(++linksId,""),
                        new Link(++linksId,
                                "http://blagovest.narod.ru/")
                ),
                Arrays.asList()
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Серые. ИгрЕневые. СоловЫе. Назовите всех их двумя словами — цитатой из произведения 1980 года.",
                "Упомянутые масти лошадей подразумевают наличие белой гривы. Произведение — песня из мультфильма 'Трям! Здравствуйте!'.",
                Level.HARD,
                Arrays.asList(new Answer(++answersId,"Белогривые лошадки",true),
                        new Answer(++answersId,"Игривые котики",false),
                        new Answer(++answersId,"Свистящие раки",false),
                        new Answer(++answersId,"Злые собаки",false)
                ),
                Arrays.asList(new Link(++linksId,"http://ru.wikipedia.org/wiki/Масть_лошади"),
                        new Link(++linksId,
                                "http://ru.wikipedia.org/wiki/Трям!_Здравствуйте!")
                ),
                Arrays.asList(new Theme(++themesId,"Животные")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Честертон пишет, что в странах, где царит олигархия, важны лица, а не законы, ОНА, а не избирательный бюллетень. На сайте milliony.ru [миллионы точка ру] бумажные деньги названы ЕЮ государства. Назовите ЕЁ.",
                "Большее значение имеет происхождение, титул и репутация, чем воля народа. Этим вопросом редакторская группа в составе Юрия Выменца и Александра Коробейникова, можно сказать, предъявляет свою визитную карточку.",
                Level.HARD,
                Arrays.asList(new Answer(++answersId,"Визитная карточка",true),
                        new Answer(++answersId,"Марка",false),
                        new Answer(++answersId,"Билет",false),
                        new Answer(++answersId,"Проездной",false)
                ),
                Arrays.asList(new Link(++linksId,"Г.К. Честертон. Перелетный кабак (http://www.flibusta.net/b/11323/read)")
                ),
                Arrays.asList(new Theme(++themesId,"Деньги")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "На картине Брейгеля 'Страна лентяев' около спящих персонажей разгуливает ТАКОЙ поросенок. В другом известном произведении, написанном, скорее всего, в 1918 году, ТАКОЙ персонаж разгуливал на свободе недолго. Назовите этого персонажа.",
                "На картине разгуливает жареный поросенок. 'Цыпленок жареный, // Цыпленок пареный // Пошел по Невскому гулять. // Его поймали, // Арестовали...'.",
                Level.HARD,
                Arrays.asList(new Answer(++answersId,"Цыпленок",true),
                        new Answer(++answersId,"Кот",false),
                        new Answer(++answersId,"Собака",false),
                        new Answer(++answersId,"Сом",false)
                ),
                Arrays.asList(new Link(++linksId,"К.А. Роке. Брейгель, или Мастерская сновидений. — М.: Молодая гвардия, 2008. — С. 237.")
                ),
                Arrays.asList(new Theme(++themesId,"Еда"),
                        new Theme(++themesId,"Животные")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Дмитрий Быков рассказывает о французском гвардейском офицере, который однажды в присутствии Наполеона совершил ошибку, начав движение не с той ноги. Наполеон тут же изгнал его из гвардии. Мы не спрашиваем, какими словами Наполеон прокомментировал свое решение. Ответьте, к какому роду войск принадлежал офицер.",
                "'Сапер ошибается только один раз', — якобы сказал Наполеон. Эта его фраза стала крылатой.",
                Level.MEDIUM,
                Arrays.asList(new Answer(++answersId,"К саперам",true),
                        new Answer(++answersId,"К пограничникам",false),
                        new Answer(++answersId,"К артилеристам",false),
                        new Answer(++answersId,"К десантникам",false)
                ),
                Arrays.asList(new Link(++linksId,"Д. Быков, И. Лукьянова. В мире животиков (http://lib.rus.ec/b/176188/read).")
                ),
                Arrays.asList(new Theme(++themesId,"Армия")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "В телепередаче, посвященной юбилею Щукинского театрального училища, бывшие студенты, ныне маститые актеры, вспоминали слова своих преподавателей [цитата]: 'Как нужно играть, можно прочесть уже при входе в училище'. Ответьте двумя словами, как же должен играть настоящий артист.",
                "На двери была надпись: 'От себя'. 'Играть от себя' на языке актеров значит 'проживать свою роль'",
                Level.EASY,
                Arrays.asList(new Answer(++answersId,"От себя",true),
                        new Answer(++answersId,"Открыто круглосуточно",false),
                        new Answer(++answersId,"Бесплатный завтрак",false),
                        new Answer(++answersId,"Наденьте маску",false)
                ),
                Arrays.asList(new Link(++linksId,"https://db.chgk.info/tour/eduka11.2/print")
                ),
                Arrays.asList(new Theme(++themesId,"Театр")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "В одном эксперименте двум группам показали эпизод, в котором девятимесячный ребенок закричал, когда перед ним из коробки выпрыгнула игрушка на пружинке. Представители одной группы решили, что младенец испугался, представители другой — что он рассердился. Различие между группами состояло в сообщенной им информации. Какую информацию сообщили испытуемым?",
                "Группа, которой сообщили, что ребенок — девочка, решила, что он испугался. Группа, которой сообщили, что ребенок — мальчик, решила, что он рассердился.",
                Level.EASY,
                Arrays.asList(new Answer(++answersId,"Пол ребенка",true),
                        new Answer(++answersId,"Возраст ребенка",false),
                        new Answer(++answersId,"Цвет ребенка",false),
                        new Answer(++answersId,"Настроение ребенка",false)
                ),
                Arrays.asList(new Link(++linksId,"Е. Ильин. Дифференциальная психофизиология мужчины и женщины (http://www.bookap.info/genpsy/ilyin/gl57.shtm).")
                ),
                Arrays.asList(new Theme(++themesId,"Эксперименты")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Считается, что от французского слова, обозначающего слугу, происходит название средства связи. Какого именно?",
                "От слова 'page' ('паж').",
                Level.MEDIUM,
                Arrays.asList(new Answer(++answersId,"Пейджер",true),
                        new Answer(++answersId,"Смартфон",false),
                        new Answer(++answersId,"Голубь",false),
                        new Answer(++answersId,"Почта",false)
                ),
                Arrays.asList(new Link(++linksId,"http://etimology.net.ua/p.php")
                ),
                Arrays.asList(new Theme(++themesId,"Связь")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Вопрос задает Даниил Бернулли. Перед тем как по городским улицам пройдут гонки скоростных болидов, на эти улицы выходят сварщики. Иначе может произойти авария, как, например, в 1990 году во время автогонок в Монреале. Что же делают сварщики?",
                "У гоночных болидов такая аэродинамика, что на высокой скорости за счет эффекта Бернулли под днищем создается настолько низкое давление, что крышку люка может подбросить в воздух. В Монреале так и случилось, и подброшенной крышкой разбило идущий сзади болид.",
                Level.EASY,
                Arrays.asList(new Answer(++answersId,"Приваривают крышки канализационных люков",true),
                        new Answer(++answersId,"Устанавливают знаки поворотов",false),
                        new Answer(++answersId,"Варят ограды",false),
                        new Answer(++answersId,"Устраивают световое шоу",false)
                ),
                Arrays.asList(new Link(++linksId,"http://ru.wikipedia.org/wiki/Бернулли,_Даниил")
                ),
                Arrays.asList(new Theme(++themesId,"Гонки")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "В одном фантастическом фильме действует темный аналог святого Грааля. Назовите абсолютно точно то, из чего сделана эта чаша.",
                "Эта серебряная чаша носит имя Иуды.",
                Level.EASY,
                Arrays.asList(new Answer(++answersId,"30 сребреников",true),
                        new Answer(++answersId,"Переплавленный колокол",false),
                        new Answer(++answersId,"Золотые таблички мармонов",false),
                        new Answer(++answersId,"Меч Вельзевула",false)
                ),
                Arrays.asList(new Link(++linksId,"К/ф 'Библиотекарь: Проклятие чаши Иуды'")
                ),
                Arrays.asList(new Theme(++themesId,"Артефакты")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Два года назад вдовствующая королева Бельгии Фабиола получила послание с угрозой покушения. Несмотря на это, в день национального праздника Бельгии она рискнула появиться на публике, держа в руках округлый предмет. Догадавшись, с чем появилась королева, назовите предполагаемое оружие покушения.",
                "В 2009 году вдовствующую королеву Фабиолу пригрозили застрелить из арбалета во время национального праздника. В ответ Фабиола появилась на публике с яблоком в качестве мишени, однако покушения так и не произошло. Тем не менее, служба безопасности попросила королеву не повторять эту шутку в дальнейшем.",
                Level.EASY,
                Arrays.asList(new Answer(++answersId,"Арбалет",true),
                        new Answer(++answersId,"Лук",true),
                        new Answer(++answersId,"Пистолет",true),
                        new Answer(++answersId,"Танк",false),
                        new Answer(++answersId,"Автомат",false)
                ),
                Arrays.asList(new Link(++linksId,"http://lenta.ru/news/2010/07/20/fabiola/")
                ),
                Arrays.asList(new Theme(++themesId,"Оружие")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Рассказывают, что в 70-е годы, когда Большой театр начал выезжать на гастроли за границу и оставлять там некоторое количество актеров, не пожелавших возвращаться, появилась шутка: уезжает Большой театр — возвращается Малый, уезжает Малый — возвращается Камерный, уезжает Камерный — возвращается Театр одного актера. А что возвращается, если уезжает Театр одного актера?",
                "Анекдот",
                Level.HARD,
                Arrays.asList(new Answer(++answersId,"Театр теней",true),
                        new Answer(++answersId,"Балет",false),
                        new Answer(++answersId,"Кукольный театр",false),
                        new Answer(++answersId,"Дельфинарий",false)
                ),
                Arrays.asList(new Link(++linksId,"http://ru.wikipedia.org/wiki/Анекдот")
                ),
                Arrays.asList(new Theme(++themesId,"Культура")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "В одном приюте для животных трудной судьбы живет тигр ГомЕр. Ответьте, от представителей какой профессии он пострадал.",
                "Сленговое выражение 'сто пудов' означает 'достоверно', 'гарантированно', 'совершенно точно'.",
                Level.HARD,
                Arrays.asList(new Answer(++answersId,"Фотографы",true),
                        new Answer(++answersId,"Блогеры",false),
                        new Answer(++answersId,"Музыканты",false),
                        new Answer(++answersId,"Репортеры",false)
                ),
                Arrays.asList(new Link(++linksId,"'Моя планета', серия 'Краснодарский край. Курортный роман' (http://rutracker.org/forum/viewtopic.php?t=2941015)")
                ),
                Arrays.asList(new Theme(++themesId,"Звери"),
                        new Theme(++themesId,"Животные")//
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "На купюрах несуществующей Лунной Республики есть портреты Птолемея, Николая Коперника, Иоганна Кеплера, Жюля Верна, Юрия Гагарина, Нейла Армстронга. На одной из купюр на одной стороне изображены много восьмых, а на другой — человек, родившийся в 1770 году. Назовите его.",
                "Написал 'Лунную сонату', нотная запись которой приведена на обороте купюры. 'Восьмая' — обозначение длительности ноты, 'Лунная соната' в основном восьмыми и написана.",
                Level.HARD,
                Arrays.asList(new Answer(++answersId,"Людвиг ван Бетховен",true),
                        new Answer(++answersId,"Моцарт",false),
                        new Answer(++answersId,"Бах",false),
                        new Answer(++answersId,"Лев Толстой",false)
                ),
                Arrays.asList(new Link(++linksId,"http://pictures.live4fun.ru/last/joke/380416"),
                        new Link(++linksId,
                                "http://en.wikipedia.org/wiki/Ludwig_van_Beethoven")
                ),
                Arrays.asList(new Theme(++themesId,"История")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "ИМИ были родители Леонида Филатова: Алексей Еремеевич Филатов и Клавдия Николаевна Филатова. Корею часто называют страной ИХ. Назовите ИХ.",
                "В Корее используется всего около 250 фамилий, при этом на три самые распространенные фамилии приходится почти половина всего населения.",
                Level.EASY,
                Arrays.asList(new Answer(++answersId,"Однофамильцы",true),
                        new Answer(++answersId,"Близнецы",false),
                        new Answer(++answersId,"Азиаты",false),
                        new Answer(++answersId,"Родственники",false)
                ),
                Arrays.asList(new Link(++linksId,"http://www.peoples.ru/art/theatre/actor/filatov/index1.html"),
                        new Link(++linksId,
                                "http://www.ruskorinfo.ru/wiki/odnofamiltsy/")
                ),
                Arrays.asList(new Theme(++themesId,"Корея")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Персонаж рассказа Джона Кольера, неисправимый романтик, называвший себя новым Джеком Лондоном, рассказывал, что потерял несколько пальцев по разным причинам: один отстрелила из ревности циркачка во время аттракциона, второй откусил кайман, третий оттяпали свайкой во время бунта на корабле. А большой палец он потерял, пересекая Лабрадор... Как именно пересекая?",
                "Отморозил, путешествуя зимой автостопом. 'Доголосовался' в буран.",
                Level.MEDIUM,
                Arrays.asList(new Answer(++answersId,"Автостопом",true),
                        new Answer(++answersId,"Вплавь",false),
                        new Answer(++answersId,"На поезде",false),
                        new Answer(++answersId,"На пальцах",false)
                ),
                Arrays.asList(new Link(++linksId,"Д. Кольер. Творческое содружество. // Сборник 'На полпути в ад'. http://lib.ru/INPROZ/KOLER/halfway.txt")
                ),
                Arrays.asList(new Theme(++themesId,"Путешествия")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Дрессировщик Карл Гагенбек использовал методы поощрения животных. Поясняя свои действия, он менял в известном утверждении некое слово на слово 'животного'. Какое именно слово?",
                "'Путь к сердцу животного также лежит через его желудок', — говорил Гагенбек, обосновывая метод поощрения.",
                Level.MEDIUM,
                Arrays.asList(new Answer(++answersId,"Мужчины",true),
                        new Answer(++answersId,"Волк",false),
                        new Answer(++answersId,"Демон",false),
                        new Answer(++answersId,"Крокодил",false)
                ),
                Arrays.asList(new Link(++linksId,"http://www.animalkingdom.su/books/item/f00/s00/z0000052/st001.shtml")
                ),
                Arrays.asList(new Theme(++themesId,"Животные")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Согласно одной шотландской шутке, в бедной семье даже ОНА черно-белая. Михаил Задорнов в своих этимологических предположениях производит ЕЕ название от имени египетского бога и ЕЕ геометрической формы. Назовите ЕЕ.",
                "Не только телевизор, но и даже радуга в бедной семье черно-белая. Михаил Задорнов считает, что слово 'радуга' происходит от имени бога солнца Ра (так как радуга бывает только при Солнце) и слова 'дуга'.",
                Level.MEDIUM,
                Arrays.asList(new Answer(++answersId,"Радуга",true),
                        new Answer(++answersId,"Елка",false),
                        new Answer(++answersId,"Зубы",false),
                        new Answer(++answersId,"Ковры",false)
                ),
                Arrays.asList(new Link(++linksId,"http://www.kabanik.ru/page/scottish-superstitions-and-humor")
                ),
                Arrays.asList(new Theme(++themesId,"Природа")
                )
        ));
        quests.add(new Quest((long) (quests.size()+1),
                "Чего хотят женщины?",
                "Вопрос без ответа",
                Level.HARD,
                Arrays.asList(new Answer(++answersId,"Шубу",true),
                        new Answer(++answersId,"Свадьбу",false),
                        new Answer(++answersId,"Посудомойку",false),
                        new Answer(++answersId,"Бряцки-цацки",false)
                ),
                Arrays.asList(new Link(++linksId,"жизнь")
                ),
                Arrays.asList(new Theme(++themesId,"Загадки тысячелетия")
                )
        ));

    }

    @Override
    public List<Quest> query(Specification<Quest> specification) {
        return quests.stream().filter(specification::specified).collect(Collectors.toList());
    }

    @Override
    public void add(Quest s) {
        s.setQuest_id((long) (quests.size()+1));
        quests.add(s);
    }

    @Override
    public void remove(Quest s) {
        quests.remove(s);
    }

    @Override
    public void update(Quest quest) {
        int index = quests.indexOf(quest);
        quests.get(index).setQuest(quest);
    }

}
