--liquibase formatted sql
--changeset thangducvu:create_product
--validCheckSum ANY

CREATE TABLE IF NOT EXISTS "sbmaster"."branch_bussiness"(
    "id" SERIAL NOT NULL,
    "company_name" VARCHAR(50) NOT NULL,
    "image" VARCHAR(255) NULL,
    "create_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "update_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."branchs"(
    "id" SERIAL NOT NULL,
    "branch_name" VARCHAR(50) NOT NULL,
    "address" VARCHAR(255) NOT NULL,
    "create_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "branch_id" integer NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(branch_name)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."room"(
    "id" SERIAL NOT NULL,
    "room_name" VARCHAR(50) NOT NULL,
    "room_type" VARCHAR(15) NOT NULL,
    "branch_id" integer NOT NULL,
    "status" bool DEFAULT true,
    PRIMARY KEY (id),
    UNIQUE(room_name, room_type)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."table_billiard"(
    "id" SERIAL NOT NULL,
    "table_name" VARCHAR(50) NOT NULL,
    "table_branch" VARCHAR(50) NOT NULL,
    "status" VARCHAR(20) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."customers"(
    "id" SERIAL NOT NULL,
    "customer_name" VARCHAR(50) NULL,
    "phone_number" VARCHAR(50) NULL,
    "email" VARCHAR(50) NULL,
    "note" VARCHAR(100) NULL,
    "status" VARCHAR(20) NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."bill"(
     "id" SERIAL NOT NULL,
     "bill_name" VARCHAR(50) NULL,
     "amount" numeric(10,2) null,
     "price" numeric(10, 2) null,
     "status" VARCHAR(20) NULL,
     "create_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
     "end_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."food"(
    "id" SERIAL NOT NULL,
    "food_name" VARCHAR(50) NULL,
    "image" VARCHAR(255) NULL,
    "quantity" INTEGER null,
    "price" numeric(10, 2) NULL,
    "create_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."beverage"(
    "id" SERIAL NOT NULL,
    "drink_name" VARCHAR(50) NULL,
    "image" VARCHAR(255) NULL,
    "quantity" INTEGER NOT NULL,
    "price" numeric(10, 2) NULL,
    "create_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."cue"(
    "id" SERIAL NOT NULL,
    "cue_name" VARCHAR(50) NULL,
    "cue_type" VARCHAR(50) NULL,
    "image" VARCHAR(255) NULL,
    "quantity" INTEGER NOT NULL,
    "price" numeric(10, 2) NULL,
    "create_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."combo"(
   "id" SERIAL NOT NULL,
   "combo_name" VARCHAR(50) NULL,
   "food_id" INTEGER NULL,
   "drink_id" INTEGER NULL,
   "cue_id" INTEGER NULL,
   "table_id" INTEGER NULL,
   "quantity" INTEGER NOT NULL,
   "price" numeric(10, 2) NULL,
   "create_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "sbmaster"."bill_details"(
    "id" SERIAL NOT NULL,
    "bill_id" VARCHAR(50) NULL,
    "amount" numeric(10,2) NULL,
    "price" numeric(10, 2) NULL,
    "price_sale" numeric(10, 2) NULL,
    "food_id" INTEGER NULL,
    "drink_id" INTEGER NULL,
    "cue_id" INTEGER NULL,
    "table_id" INTEGER NULL,
    "combo_id" INTEGER NULL,
    "status" VARCHAR(20) NULL,
    "create_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "end_timestamp" TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);