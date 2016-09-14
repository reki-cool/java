use Library


insert into bookType values('编程')

select *
from bookType

insert into book values('123456','ios','清华大学','2015-01-01',86,10,'2016-08-08','中文','编程',0,'初学者必备')

select *
from book

select *
from auther

select *
from book,bookType,auther
where book.auId = auther.auId and book.typeId = bookType.typeId

select *
from reader

select *
from admin







