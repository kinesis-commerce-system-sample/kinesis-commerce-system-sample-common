# https://hub.docker.com/_/postgres/
FROM postgres:9.4

RUN localedef -i ja_JP -c -f UTF-8 -A /usr/share/locale/locale.alias ja_JP.UTF-8
COPY ./sql/*.sql /docker-entrypoint-initdb.d/