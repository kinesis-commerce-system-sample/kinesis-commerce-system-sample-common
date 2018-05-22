
--
-- 在庫テーブル
--
-- 同一商品をひとつの在庫レコードとして扱う。（複数の在庫レコードは想定しない。）

create table item_inventory (
    id character varying(36) NOT NULL
    , item_id character varying(36) NOT NULL
    , quantity int NOT NULL
    , CONSTRAINT item_inventory_id PRIMARY KEY (id)
    , CONSTRAINT item_inventory_uq_01 UNIQUE (item_id)
);