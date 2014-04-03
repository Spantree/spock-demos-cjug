import groovy.sql.Sql

def sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver")
sql.execute("""
create table calcdata (
    id int primary key,
    year integer,
    interest decimal,
    amt integer
)
""")

def values = [
    [id: 1, year: 2, interest: 1000.0, amount: 10000],
    [id: 2, year: 5, interest: 25.0, amount: 100]
]

values.each { v ->
    sql.executeInsert("insert into calcdata values (${v.id}, ${v.year}, ${v.interest}, ${v.amount});")
}

sql.rows("SELECT * FROM calcdata").each { row ->
    println row
}