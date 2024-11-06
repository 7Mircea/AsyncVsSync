CREATE TABLE CUSTOMER_EMPLOYEE_SUPPLIER
(
    id_ces   INTEGER                NOT NULL,
    name     CHARACTER VARYING(40)  NOT NULL,
    EIN      CHARACTER VARYING(10)  NULL,
    type_ces CHAR(1)                NOT NULL,
    address  CHARACTER VARYING(100) NULL,
    IBAN     CHARACTER VARYING(34)  NOT NULL,
    SSN      CHARACTER VARYING(13)  NULL,
    CONSTRAINT PK_CUSTOMER_EMPLOYEE_SUPPLIER PRIMARY KEY (id_ces)
)
;

CREATE TABLE INVOICE
(
    nr           INTEGER       NOT NULL,
    invoice_date DATE          NOT NULL,
    id_vendor    INTEGER       NOT NULL,
    type         CHAR(1)       NOT NULL,
    value        decimal(9, 2) NOT NULL,
    VAT          decimal(9, 2) NOT NULL,
    id_employee  INTEGER       NOT NULL,
    id_buyer     INTEGER       NOT NULL,
    CONSTRAINT PK_INVOICE PRIMARY KEY (invoice_date, nr)
)
;

CREATE TABLE PRODUCTS
(
    id_prod      INTEGER                NOT NULL,
    prod_name    CHARACTER VARYING(50)  NOT NULL,
    id_supplier  INTEGER                NOT NULL,
    availability CHARACTER VARYING(20)  NOT NULL,
    category     CHARACTER VARYING(20)  NOT NULL,
    add_info     CHARACTER VARYING(100) NULL,
    CONSTRAINT PK_PRODUCTS PRIMARY KEY (id_prod)
)
;

CREATE TABLE ITEM
(
    invoice_nr   INTEGER              NOT NULL,
    invoice_date DATE                 NOT NULL,
    id_item      INTEGER              NOT NULL,
    id_prod      INTEGER              NOT NULL,
    unit         CHARACTER VARYING(5) NOT NULL,
    quantity     INTEGER              NOT NULL,
    unit_price   decimal(7, 2)        NOT NULL,
    CONSTRAINT PK_ITEM PRIMARY KEY (invoice_nr, invoice_date, id_item)
)
;

CREATE TABLE CHARACTERISTIC
(
    id_prod           INTEGER                NOT NULL,
    id_characteristic INTEGER                NOT NULL,
    name              CHARACTER VARYING(50)  NOT NULL,
    value             CHARACTER VARYING(150) NOT NULL,
    CONSTRAINT PK_CHARACTERISTIC PRIMARY KEY (id_prod, id_characteristic)
)
;

ALTER TABLE INVOICE
    ADD
        CONSTRAINT FK_CES_IN_VENDOR FOREIGN KEY
            (id_vendor)
            REFERENCES CUSTOMER_EMPLOYEE_SUPPLIER
                (id_ces)
;

ALTER TABLE INVOICE
    ADD
        CONSTRAINT FK_CES_IN_EMPLOYEE FOREIGN KEY
            (id_employee)
            REFERENCES CUSTOMER_EMPLOYEE_SUPPLIER
                (id_ces)
;

ALTER TABLE INVOICE
    ADD
        CONSTRAINT FK_CES_IN_BUYER FOREIGN KEY
            (id_buyer)
            REFERENCES CUSTOMER_EMPLOYEE_SUPPLIER
                (id_ces)
;

ALTER TABLE PRODUCTS
    ADD
        CONSTRAINT FK_CES_P FOREIGN KEY
            (id_supplier)
            REFERENCES CUSTOMER_EMPLOYEE_SUPPLIER
                (id_ces)
;

ALTER TABLE ITEM
    ADD
        CONSTRAINT FK_P_IT FOREIGN KEY
            (id_prod)
            REFERENCES PRODUCTS
                (id_prod)
;

ALTER TABLE ITEM
    ADD
        CONSTRAINT FK_IN_IT FOREIGN KEY
            (invoice_date, invoice_nr)
            REFERENCES INVOICE
                (invoice_date, nr)
;

ALTER TABLE CHARACTERISTIC
    ADD
        CONSTRAINT FK_P_C FOREIGN KEY
            (id_prod)
            REFERENCES PRODUCTS
                (id_prod)
;
