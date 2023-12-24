--liquibase formatted sql
--changeset thang.vuduc:1.3.1_add_liquibase_config
--validCheckSum ANY


INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value") VALUES ('sb-users-services', 'default', 'default', 'spring.liquibase.enabled', 'true');
INSERT INTO "sbmaster"."configuration"("application", "profile", "label", "key", "value", "is_public") VALUES ('sb-users-services', 'default', 'default', 'spring.liquibase.change-log', 'classpath:/db/changelog/changelog-master.xml', 'false');

--rollback DELETE FROM "sbmaster"."configuration" WHERE "key" = 'spring.liquibase.enabled';
--rollback DELETE FROM "sbmaster"."configuration" WHERE "key" = 'spring.liquibase.change-log';