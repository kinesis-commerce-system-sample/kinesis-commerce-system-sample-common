
--
-- 受注テーブル
--
-- 注文した人とか納品先とかの情報は色々端折ってる。

create table item_order (
    id character varying(36) NOT NULL
    , item_id character varying(36) NOT NULL
    , quantity int NOT NULL
    , status character varying(20) NOT NULL
    , CONSTRAINT item_order_id PRIMARY KEY (id)
);