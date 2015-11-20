FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD root
EXPOSE 3306
COPY springdb.sql /springdb.sql
COPY sql-entrypoint.sh /sql-entrypoint.sh

#docker run -it  -P --name abc13  manish1900/mysql
#docker exec -it abc13 sh /sql-entrypoint.sh
