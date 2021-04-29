SELECT
s.applicant,
s.reviewer,
s.subscribe_status,
s.room_num,
s.application_start_time,
s.use_interval,
s.apply_use_date,
s.room_num,

u.user_num,
u.user_name,
u.mailbox

FROM t_subscribe s
LEFT JOIN t_user AS u

ON s.applicant=u.user_num
WHERE s.room_num=1 

AND
s.application_start_time>='2021-01-01 00:00:00' AND s.application_start_time<='2021-05-01 00:00:00'

ORDER BY s.application_start_time ASC limit 0,10;