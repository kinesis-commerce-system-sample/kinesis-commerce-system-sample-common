
--
-- メッセージテーブル
--
-- 二重処理防止の為、在庫Consumerで処理済みのメッセージIDを記録。
-- RDBよりも管理に適したデータベースを採用すべき。

create table "message" (
    "id" character varying(36) NOT NULL
    , CONSTRAINT "message_id" PRIMARY KEY ("id")
);