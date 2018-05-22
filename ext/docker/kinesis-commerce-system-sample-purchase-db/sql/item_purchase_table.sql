
--
-- 発注(仕入れ)テーブル
--
-- 色々端折ってる。

create table item_purchase (
    id character varying(36) NOT NULL
    , item_id character varying(36) NOT NULL
    , quantity int NOT NULL
    , status character varying(20) NOT NULL
    , CONSTRAINT item_purchase_id PRIMARY KEY (id)
);