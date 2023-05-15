use start_for_interview;

-- выводим полное расписание с актуальной ценой на билет
select title, day_name, start_time ,duration, time(start_time+duration ) as end_time ,  price as base_price, ((price*day_rate/100)+ (price*time_rate/100)+ price) as actual_price from sessions
	join films on sessions.film_id=films.id 
    join days on sessions.day_id=days.id
    join prices on films.base_price_id=prices.id
    join durations on films.duration_id=durations.id
    join  time_intervals on sessions.start_time where (sessions.start_time>= time_intervals.time_begin  and sessions.start_time<time_intervals.time_end)
order by start_time;

-- список пересекающихся фильмов
with q1 as(
	select film_id, start_time, duration, title ,lead(start_time) over(order by start_time) as next_start_time, lead(film_id) over (order by start_time) as next_id from sessions as s
	join films as f on s.film_id=f.id 
    join durations as d on f.duration_id=d.id
)
select q1.title as `Фильм 1`, q1.start_time as `Время начала`, q1.duration as `Длительность`, addtime(q1.start_time, q1.duration) as `Окончание`,
		f.title as `Фильм 2`, q1.next_start_time as `Время начала`,  d.duration as `Длительность` from q1
	join films as f on q1.next_id=f.id 
    join durations as d on f.duration_id=d.id
where start_time+q1.duration>=next_start_time;

-- список фильмов с перерывом 30 мин и более
with q2 as(
	select film_id, start_time, duration, title ,lead(start_time) over(order by start_time) as next_start_time, lead(film_id) over (order by start_time) as next_id from sessions as s
	join films as f on s.film_id=f.id 
    join durations as d on f.duration_id=d.id
)
select q2.title as `Фильм 1`, q2.start_time as `Время начала`, q2.duration as `Длительность`, f2.title `Фильм 2`, next_start_time as `Время начала второго фильма`, timediff(next_start_time, addtime(q2.start_time, q2.duration)) as `Длительность перерыва` from q2
	join films as f2 on q2.next_id=f2.id 
    join durations as d2 on f2.duration_id=d2.id
where timediff(next_start_time, addtime(q2.start_time, q2.duration))>=maketime(0, 30, 0)
order by `Длительность перерыва`;


-- список фильмов, для каждого — с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
 
 with q3 as (
 select distinct 
	title, 
	count(film_id) over(partition by  film_id) as `Общее число посетителей`, 
	count(s.id) over(partition by session_id) as `Число зрителей за сеанс`,
	price as `Базовая цена`,
	((price*day_rate/100)+ (price*time_rate/100)+ price) as actual_price,
	sum(((price*day_rate/100)+ (price*time_rate/100)+ price)) over (partition by title) as `Выручка`
from sessions s
	join films f on s.film_id=f.id
    join tickets t on s.id=t.session_id
    join prices on f.base_price_id=prices.id
	join days on s.day_id=days.id
	join  time_intervals on s.start_time 
    where (s.start_time>= time_intervals.time_begin  and s.start_time<time_intervals.time_end)
order by title
),
q4 as(
select  distinct
	title, 
    count(film_id) over(partition by  film_id) as `Общее число посетителей`,
	sum(((price*day_rate/100)+ (price*time_rate/100)+ price)) over (partition by title) as `Выручка`
from sessions s
	join films f on s.film_id=f.id
    join tickets t on s.id=t.session_id
    join prices on f.base_price_id=prices.id
	join days on s.day_id=days.id
	join  time_intervals on s.start_time 
    where (s.start_time>= time_intervals.time_begin  and s.start_time<time_intervals.time_end)
order by title
)

select distinct title, `Общее число посетителей`, format( `Общее число посетителей`/sum( `Число зрителей за сеанс`) over(), 2) as `Среднее число зрителей за сеанс`, `Выручка` from q3 
	union 
select  'Итого', sum(`Общее число посетителей`),  null,sum(`Выручка`)  from q4

	;	 

-- число посетителей и кассовые сборы, 
-- сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).

with q6 as(
	select distinct  count(f.id) over(partition by time_intervals.id) as `Число посетителей`, sum(((price*day_rate/100)+ (price*time_rate/100)+ price)) over (partition by time_intervals.id) as `Выручка`, time_begin, time_end 
from sessions s
	join films f on s.film_id=f.id
    join tickets t on s.id=t.session_id
    join prices on f.base_price_id=prices.id
	join days on s.day_id=days.id
	join  time_intervals on s.start_time 
where (s.start_time>= time_intervals.time_begin  and s.start_time<time_intervals.time_end)
)
select  `Число посетителей`, `Выручка`, time_begin, time_end from q6
union 
select 'Итого', '', '', ''
	union
select sum(`Число посетителей`), sum( `Выручка`) , '', '' from q6
