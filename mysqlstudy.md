# MySQL


## MySQL数据库
### 数据模型
MySQL数据库是关系型数据库管理系统（RDBMS）的一种，它使用SQL语言作为查询语言。MySQL数据库的结构化查询语言（Structured Query Language，SQL）是一种声明性语言，它使用关系代数来处理数据。
数据库和表
MySQL数据库由数据库和表组成。数据库是一组相关表的集合，数据库中的表共享相同的结构。数据库可以包含多个表，每个表可以包含多个行和列。

## SQL语言
SQL的通用语法
可以单行或者多行执行SQL语句。SQL语句以分号（;）结尾。
不区分大小写
注释使用--或#。

### 常用SQL命令
包括SELECT、INSERT、UPDATE、DELETE、CREATE、ALTER、DROP、TRUNCATE、GRANT、REVOKE、COMMIT、ROLLBACK等命令。
### SQL语句分类
DDL（Data Definition Language，数据定义语言）：用于定义数据库对象，如数据库、表、视图、索引等。
DML（Data Manipulation Language，数据操纵语言）：用于操作数据库对象，如插入、删除、更新数据。
DCL（Data Control Language，数据控制语言）：用于控制数据库的访问权限，如事务处理、连接管理等。
DQL（Data Query Language，数据查询语言）：用于查询数据库对象，如检索、搜索数据。

### DDL
###### DDL表操作命令
```sql
CREATE DATABASE：创建数据库。
CREATE TABLE：创建表。
create table table_name (
     column_name data_type constraint,
     id int comment '主键',
     name varchar(50) not null comment '姓名',
     age int comment '年龄'
    ...)：创建表。
```
```sql
show tables：//显示当前数据库中的所有表。
DESC table_name：//显示表的结构。
ALTER TABLE：//修改表。
DROP TABLE：//删除表。
```
###### DDL表操作数据类型
```sql
int：整型
varchar(n)：字符串，最大长度为n
text：长文本
date;datetime：日期时间
```
###### DDL表操作修改
```sql
//添加字段
alter table table_name add column_name data_type (comment '注释');
//删除字段
alter table table_name drop column_name;
//修改字段数据类型
alter table table_name modify column_name data_type ；
//修改字段名称
alter table table_name change column_name new_column_name data_type;
//修改表名
alter table table_name rename to new_table_name;
```
###### DDL表操作删除

```sql
drop table table_name：//删除表。
truncate table table_name：//清空表，删除所有数据。
```
###### DDL小结
DDL命令用于定义数据库对象，包括创建数据库、创建表、修改表、删除表等。
### DML
完成数据的增删改查操作。

###### DML插入数据
```sql
insert into table_name (column1, column2,...) values (value1, value2,...);//给指定字段赋值
insert into table_name values (value1, value2,...);//给全部字段赋值，需要保证字段顺序一致。
insert into table_name (column1, column2,...) values (value1, value2,...),(value1, value2,...);//插入多条记录。
//注意插入数据时，字段类型要与表结构一致。
//字符串与日期型数据需要加单引号。
//插入数据的大小应该小于表的最大值。
```

###### DML修改数据
```sql
update table_name set column1=value1, column2=value2,... where condition;//条件可以没有
```
###### DML删除数据
```sql
DELETE FROM table_name WHERE condition;//条件也可以没有
//不能删除表，只能删除数据。
//不能删除某一个字段，只能整表删除。
```
###### DML小结
DML命令用于操作数据库对象，包括插入数据、更新数据、删除数据等。

### DQL
完成数据的查询操作。
##### DQL基础语法
```sql
SELECT
    //字段列表
FROM 
    //表名列表
WHERE 
    //条件列表
GROUP BY 
    //分组字段列表
HAVING 
    //分组后过滤条件
ORDER BY 
    //排序字段列表
LIMIT 
    //分页参数
```
###### DQL基础查询
```sql
SELECT column1, column2,... FROM table_name WHERE condition;//查询指定字段
SELECT * FROM table_name WHERE condition;//查询全部字段
SELECT DISTINCT column1, column2,... FROM table_name WHERE condition;//查询指定字段的不同值
```
设置别名
```sql
SELECT column1 AS c1, column2 AS c2,... FROM table_name WHERE condition;//给字段设置别名
```
去除重复记录
```sql
SELECT DISTINCT column1, column2,... FROM table_name WHERE condition;
```
###### DQL条件查询
```sql
select * from table_name where condition1 and condition2 and condition3;
/*
比较运算符:
=   等于
>   大于
<   小于
>=  大于等于
<=  小于等于
<>  不等于
!=  不等于
BETWEEN and介于
LIKE  使用_和%作为通配符的模糊查询
IN  范围查询
IS NULL 为空
逻辑运算符:
AND && 与
OR  || 或
NOT !  非
*/
```
###### 聚合函数
将一列数据作为整体进行纵向的统计，如求和、平均值、最大值、最小值等。
```sql
SELECT COUNT(*) FROM table_name;//计算行数
SELECT SUM(column1) FROM table_name;//求和
SELECT AVG(column1) FROM table_name;//平均值
SELECT MAX(column1) FROM table_name;//最大值
SELECT MIN(column1) FROM table_name;//最小值
```
###### DQL分组查询
将数据按照指定字段进行分组，然后对分组后的结果进行聚合操作。
```sql
SELECT column1, column2,... FROM table_name where condition
GROUP BY column1, column2,... 
Having condition;
//where_condition为可选条件，用于过滤分组。
//Having条件为分组后过滤条件，与where条件类似。
```
###### DQL排序查询
```sql
SELECT column1, column2,... FROM table_name WHERE condition
ORDER BY column1 [ASC|DESC], column2 [ASC|DESC], ...;
//ASC为升序，DESC为降序。
```
###### DQL分页查询
```sql
SELECT column1, column2,... FROM table_name WHERE condition
LIMIT start, count;
//start为起始位置，count为每页显示的记录数。
//start从0开始，起始索引=（查询页码-1）*每页显示的记录数。
//不用完全遍历整个表，减少查询时间。
```
### DCL
用来管理数据库的访问权限，包括事务处理、连接管理等。还有数据库用户
###### DCL管理用户
```sql
USE mysql;
SELECT user, host FROM user;//查询用户
CREATE USER '用户名'@'主机名' IDENTIFIED BY '密码';//创建用户
ALTER USER '用户名'@'主机名' IDENTIFIED BY '新密码';//修改用户密码
DROP USER '用户名'@'主机名';//删除用户

```
###### DCL权限管理
```sql
SHOW GRANTS FOR '用户名'@'主机名';//查看用户权限
GRANT 权限 ON 数据库.表 TO '用户名'@'主机名';//授予用户权限
REVOKE 权限 ON 数据库.表 FROM '用户名'@'主机名';//收回用户权限
```

## 函数
使用函数
```sql
SELECT 函数名(参数) FROM table_name;//from和后面的内容可以省略
UPDATE table_name SET column1=函数名(参数), column2=函数名(参数) WHERE condition;
```
### 字符串函数
```sql
LENGTH(str)：返回字符串的长度。
CONCAT(str1, str2,...)：连接字符串。
SUBSTRING(str, pos, len)：返回子字符串。
TRIM(str)：去除字符串两端的空格。
REPLACE(str, old_str, new_str)：替换字符串。
LOWER(str)：转换为小写。
UPPER(str)：转换为大写。
LPAD(str, len, pad)：在左侧填充字符串。
RPAD(str, len, pad)：在右侧填充字符串。
```
### 日期函数
```sql
NOW()//返回当前日期和时间。
CURDATE()//返回当前日期。
CURTIME()//返回当前时间。
DATE_ADD(date, INTERVAL expr type)//日期加减。
DATE_FORMAT(date, format)//格式化日期。
datediff(datepart, startdate, enddate)//计算两个日期之间的差值。
```
### 数学函数
```sql
ceil(num)：向上取整。
floor(num)：向下取整。
mod(num1, num2)：求余。
rand()：返回一个随机数。
pi()：返回圆周率。
exp(num)：返回e的num次方。
pow(num1, num2)：返回num1的num2次方。
ABS(num)：返回绝对值。
ROUND(num, len)：返回四舍五入的数字。
...
//生成验证码
select lpad(round(rand()*1000000,0), 6, '0');
```
### 流程控制函数
```sql
IF(expr, true_result, false_result)//if expr is true,return true_result, else return false_result 
IFNULL (expr, null_result)//if expr is null, return null_result, else return expr 
CASE WHEN expr1 THEN result1 [WHEN expr2 THEN result2]... [ELSE else_result] END
//case when expr1 is true, return result1, when expr2 is true, return result2, else return else_result 选择语句

CASE expr WHEN value1 THEN result1 [WHEN value2 THEN result2]... [ELSE else_result] END
//如果expr等于value1，返回result1，如果等于value2，返回result2，否则返回else_result 
```
```sql
select name,(case score when 90 then 'A' when 80 then 'B' when 70 then 'C' else 'D' end) as grade from student;
//根据分数显示等级
```
###### 小结
函数是SQL语言的重要组成部分，可以完成各种数据处理操作。
 

## 约束
约束是用来限制表中数据的规则，包括唯一性约束、非空约束、外键约束等。

### 唯一性约束
唯一性约束用于保证表中字段值的唯一性。
```sql
CREATE TABLE table_name (
    column_name data_type constraint,
    ...
    UNIQUE (column_name)
);
```
### 非空约束
非空约束用于保证表中字段值不能为空。
```sql
CREATE TABLE table_name (
    column_name data_type constraint,
    ...
    NOT NULL
);
```
### 主键约束
主键约束是一行数据的唯一标识，要求非空且唯一
```sql
CREATE TABLE table_name (
    column_name data_type constraint,
    ...
    PRIMARY KEY (column_name)
);
```
### 检查约束
检查约束用于检查表中字段值的有效性。
```sql
CREATE TABLE table_name (
    column_name data_type constraint,
    ...
    CHECK (column_name > 0)
);
```
### 外键约束
外键约束用于保证两个表的数据一致性。
```sql
CREATE TABLE table_name (
    column_name data_type constraint,
    ...
    FOREIGN KEY (column_name) REFERENCES table_name(column_name)
);
//删除外键
alter table table_name drop foreign key 外键名;
```
### 默认值约束
默认值约束用于设置字段的默认值。
```sql
CREATE TABLE table_name (
    column_name data_type constraint DEFAULT default_value,
    ...
);
```
### 自动递增约束
自动递增约束用于设置字段的自增值。
```sql
CREATE TABLE table_name (
    column_name data_type constraint AUTO_INCREMENT,
    ...
);
```
## 多表查询
多表查询是指查询多个表的数据。

### 内连接
内连接（INNER JOIN）是指两个表中都存在的行才会被返回。
```sql
SELECT table1.column1, table1.column2, table2.column1, table2.column2
FROM table1
INNER JOIN table2
ON table1.column1 = table2.column1;
```
### 外连接
外连接（OUTER JOIN）是指左表（table1）和右表（table2）中都存在的行都将被返回，左表中不存在的行将显示为NULL，右表中不存在的行将显示为NULL。
```sql
SELECT table1.column1, table1.column2, table2.column1, table2.column2
FROM table1
LEFT outer JOIN table2
ON table1.column1 = table2.column1;
``` 
### 自连接
自连接（SELF JOIN）是指一个表与自身进行连接
一定要起别名。
```sql
SELECT table1.column1, table1.column2, table1.column3, table2.column1, table2.column2
FROM table1
INNER JOIN table1 AS table2
ON table1.column1 = table2.column1;
```
### 联合查询
联合查询（UNION）是指将两个或多个SELECT语句的结果组合在一起。
多张表的列数必须保持一致，字段类型也需要保持一致
union all 保留所有结果，union 去重
```sql
SELECT column1, column2
FROM table1
UNION all
SELECT column1, column2
FROM table2;
``` 
### 子查询
子查询（Subquery）是指在一个查询中嵌入另一个查询。
```sql
SELECT column1, column2
FROM table1
where column1 = (SELECT column1 FROM table2 WHERE condition);
```
##### 标量子查询
子查询的结果是单个值（数字，字符串，日期等），称为标量子查询。
```sql
SELECT column1, column2
FROM table1
where column1 = (SELECT MAX(column1) FROM table2);
```
##### 列子查询
子查询的结果是一列值，称为列子查询。
```sql
SELECT column1, column2
FROM table1
where column1 IN (SELECT column1 FROM table2 WHERE condition);
```
##### 行子查询
子查询的结果是一行值，称为行子查询。
```sql
SELECT column1, column2
FROM table1
where column1 in (SELECT column1 FROM table2 WHERE condition LIMIT 1);
```
##### 表子查询
子查询的结果是一张表，称为表子查询。
```sql
SELECT column1, column2
FROM table1
where column1 in (SELECT column1 FROM table2 WHERE condition);
```