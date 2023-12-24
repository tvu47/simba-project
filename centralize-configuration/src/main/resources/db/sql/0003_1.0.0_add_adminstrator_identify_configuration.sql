--liquibase formatted sql
--changeset thang.vuduc:add adminstrator identify configuration
--validCheckSum ANY


INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value", "is_public") VALUES ('sb-adminstrator-identify', 'default', 'default', 'server.port', '8082', 'false');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value", "is_public") VALUES ('sb-adminstrator-identify', 'default', 'default', 'server.servlet.context-path', '/sb-adminstrator-identify', 'false');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.datasource.url', 'jdbc:postgresql://${DATABASE_URL}/${DB_ADMIN}?currentSchema=sbmaster');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.datasource.username', 'postgres');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.datasource.password', 'postgres');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value", "is_public") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.datasource.driver-class-name', 'org.postgresql.Driver', 'false');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.mapping.datasource.jdbcUrl', 'jdbc:postgresql://${DATABASE_URL}/${DB_ADMIN}?currentSchema=sbmaster');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value", "is_public") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.mapping.datasource.driver-class-name', 'org.postgresql.Driver', 'false');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.jpa.properties.hibernate.generate_statistics', 'false');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value", "is_public") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.jpa.properties.hibernate.dialect', 'org.hibernate.dialect.PostgreSQLDialect', 'false');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.jpa.properties.hibernate.jdbc.batch_size', '100');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.jpa.properties.hibernate.order_inserts', 'true');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.jpa.open-in-view', 'false');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-adminstrator-identify', 'default', 'default', 'spring.jpa.show-sql', 'false');
--rollback DELETE FROM "sbmaster"."configuration" WHERE "application" = 'sb-adminstrator-identify';