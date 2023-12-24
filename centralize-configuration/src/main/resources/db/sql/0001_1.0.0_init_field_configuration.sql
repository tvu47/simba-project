-- liquibase formated sql
-- changeset thang.vuduc: init_field_configuration
-- validCheckSum ANY

create table "sbmaster"."configuration" (
    "id" SERIAL not null ,
    "application" varchar(255) not null default 'default',
    "profile" varchar(255) not null default 'default',
    "label" varchar(255) not null default 'default',
    "key" varchar(255) not null ,
    "value" varchar not null ,
    "is_public" boolean default true,
    "last_modified_at" timestamp(0) default current_timestamp
);

ALTER TABLE "sbmaster"."configuration" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE "sbmaster"."configuration" ADD CONSTRAINT "configuration_uniq1" UNIQUE ("application", "profile","label","key") NOT DEFERRABLE INITIALLY IMMEDIATE;

--rollback DROP TABLE IF EXISTS sbmaster.configuration;