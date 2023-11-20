create schema if not exists ecomms;
-- Other script tags
create TABLE IF NOT EXISTS ecomms.cart (
    id uuid NOT NULL,
    user_id uuid NOT NULL,
    FOREIGN KEY (user_id) REFERENCES ecomms.user(id),
    PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomms.cart_item (
 cart_id uuid NOT NULL,
 item_id uuid NOT NULL,
 FOREIGN KEY (cart_id) REFERENCES ecomms.cart(id),
 FOREIGN KEY(item_id) REFERENCES ecomms.item(id)
);
-- other SQL scripts
