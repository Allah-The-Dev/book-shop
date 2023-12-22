CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS ORDER_ITEM;

CREATE TABLE ORDER_ITEM(
    ORDER_ITEM_ID UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    ORDER_ID UUID REFERENCES ORDERS(ORDER_ID),
    BOOK_ID INTEGER REFERENCES BOOK(BOOK_ID),
    QUANTITY INTEGER,
    PRICE NUMERIC(10,2),
    CREATED_AT TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMPTZ,
    DELETED_AT TIMESTAMPTZ
)
