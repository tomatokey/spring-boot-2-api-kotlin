CREATE
USER 'user_upd'@'%' IDENTIFIED BY 'user_upd_password';
GRANT ALL
ON local_db.* TO 'user_upd'@'%';
CREATE
USER 'user_ref'@'%' IDENTIFIED BY 'user_ref_password';
GRANT
SELECT
ON local_db.* TO 'user_ref'@'%';
